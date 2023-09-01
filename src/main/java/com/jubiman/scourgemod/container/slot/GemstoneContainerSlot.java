package com.jubiman.scourgemod.container.slot;

import com.jubiman.scourgemod.item.gemstone.GemstoneItem;
import com.jubiman.scourgemod.item.gemstone.GemstoneSlot;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.inventory.Inventory;
import necesse.inventory.InventoryItem;
import necesse.inventory.container.slots.ContainerSlot;

public class GemstoneContainerSlot extends ContainerSlot {
	private final ArmorAnyContainerSlot armorSlot;
	private final GemstoneSlot gemstoneSlot;

	public GemstoneContainerSlot(Inventory inventory, int inventorySlot, GemstoneSlot gemstoneSlot, ArmorAnyContainerSlot armorSlot) {
		super(inventory, inventorySlot);
		this.armorSlot = armorSlot;
		this.gemstoneSlot = gemstoneSlot;


		if (this.gemstoneSlot.getGemstone() != null)
			super.setItem(new InventoryItem(this.gemstoneSlot.getGemstone(), 1)); // we do not want to update the item gnd, as we're reading from it
	}

	@Override
	public boolean isItemValid(InventoryItem inventoryItem) {
		return inventoryItem == null
				|| inventoryItem.item instanceof GemstoneItem
				&& gemstoneSlot.isGemstoneTypeValid(((GemstoneItem) inventoryItem.item).type);
	}

	@Override
	public void setItem(InventoryItem item) {
		GNDItemMap gemstone = (GNDItemMap) ((GNDItemMap) armorSlot.gndData.getItem("gemstones")).getItem(getInventorySlot() - 1);
		if (item != null) {
			gemstone.setString("gemstoneType", item.item.getStringID());
		} else {
			gemstone.setString("gemstoneType", "");
		}
		armorSlot.updateItemGND();
		super.setItem(item);
	}

	public int getItemStackLimit(InventoryItem inventory) {
		return 1;
	}
}
