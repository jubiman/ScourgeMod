package com.jubiman.scourgemod.container;

import com.jubiman.scourgemod.container.slot.ArmorAnyContainerSlot;
import com.jubiman.scourgemod.container.slot.GemstoneContainerSlot;
import com.jubiman.scourgemod.item.gemstone.GemstoneSlot;
import com.jubiman.scourgemod.registry.GemstoneSlotRegistry;
import necesse.engine.network.NetworkClient;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.server.ServerClient;
import necesse.inventory.PlayerTempInventory;
import necesse.inventory.container.Container;

import java.util.AbstractMap;
import java.util.ArrayList;

public class GrindstoneContainer extends Container {
	public final int ARMOR_SLOT;
	public final PlayerTempInventory ingredientInv;
	public final ArrayList<AbstractMap.SimpleEntry<Integer, GemstoneSlot.GemstoneSlotType>> gemstoneSlots = new ArrayList<>();
	public boolean itemChanged = false;

	public GrindstoneContainer(final NetworkClient client, int uniqueSeed, Packet content) {
		super(client, uniqueSeed);
		PacketReader reader = new PacketReader(content);
		Packet tempInvContent = reader.getNextContentPacket();
		this.ingredientInv = client.playerMob.getInv().applyTempInventoryPacket(tempInvContent, m -> isClosed());

		this.ARMOR_SLOT = addSlot(new ArmorAnyContainerSlot(this.ingredientInv, 0, this));
		addInventoryQuickTransfer(this.ARMOR_SLOT, this.ARMOR_SLOT);
	}


	public static Packet getContainerContent(ServerClient client) {
		Packet p = new Packet();
		PacketWriter writer = new PacketWriter(p);
		writer.putNextContentPacket(client.playerMob.getInv().getTempInventoryPacket(1));
		return p;
	}

	public void updateSlots(GemstoneSlotRegistry.GemstoneSlotList slotList) {
		this.gemstoneSlots.clear();
		this.ingredientInv.changeSize(1); // reset the inventory to prevent accidental duping of gemstones
		this.ingredientInv.changeSize(slotList.size() + 1);
		for (int i = 0; i < slotList.size(); i++) {
			gemstoneSlots.add(new AbstractMap.SimpleEntry<>(
					addSlot(new GemstoneContainerSlot(this.ingredientInv, i + 1, slotList.get(i), (ArmorAnyContainerSlot) getSlot(ARMOR_SLOT))),
					slotList.get(i).type)
			);
		}
	}
}
