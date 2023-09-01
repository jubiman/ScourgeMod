package com.jubiman.scourgemod.form;

import com.jubiman.scourgemod.container.GrindstoneContainer;
import necesse.engine.GameLog;
import necesse.engine.network.client.Client;
import necesse.engine.registries.ItemRegistry;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.components.FormFlow;
import necesse.gfx.forms.components.containerSlot.FormContainerPlayerArmorSlot;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.forms.presets.containerComponent.ContainerForm;
import necesse.gfx.gameFont.FontOptions;
import necesse.inventory.item.armorItem.ArmorItem;

import java.awt.*;
import java.util.ArrayList;

public class GrindstoneContainerForm extends ContainerForm<GrindstoneContainer> {
	public GrindstoneContainerForm(Client client, GrindstoneContainer container) {
		super(client, 250, 200, container);
		FormFlow flow = new FormFlow(10);
		addComponent(flow.next(new FormLocalLabel(ItemRegistry.getLocalization(ItemRegistry.getItemID("scourge_grindstone")),
				new FontOptions(20), 0, getWidth() / 2, 10, getWidth() - 20), 10));

		addComponent(flow.next(new FormContainerPlayerArmorSlot(client, container.ARMOR_SLOT, getWidth() / 2 - 20, 10, ArmorItem.ArmorType.CHEST, false), 10));
	}

	public void draw(TickManager tickManager, PlayerMob perspective, Rectangle renderBox) {
//		if (container.gemstoneSlots.size() > 0 && getComponentList().size() == 2) {
//			addGemstoneSlots(container.gemstoneSlots.size());
		if (container.itemChanged) {
			container.itemChanged = false;
			// remove all components
			removeComponentsIf(c -> c instanceof FormContainerGemstoneSlot);

			addGemstoneSlots(container.gemstoneSlots.size());
		}
		super.draw(tickManager, perspective, renderBox);
	}

	private void addGemstoneSlots(int size) {
		int width = getWidth();
		switch (size) {
			case 0: {
				break;
			}
			case 2: {
				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(0).getKey(), width / 2 - 20 - 40, 100, container.gemstoneSlots.get(0).getValue()));
				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(1).getKey(), width / 2 - 20 + 40, 100, container.gemstoneSlots.get(1).getValue()));
				break;
			}
			case 4: {
				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(0).getKey(), width / 2 - 20 - 40, 100, container.gemstoneSlots.get(0).getValue()));
				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(1).getKey(), width / 2 - 20 - 40 * 2, 100, container.gemstoneSlots.get(1).getValue()));

				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(2).getKey(), width / 2 - 20 + 40, 100, container.gemstoneSlots.get(2).getValue()));
				addComponent(new FormContainerGemstoneSlot(client, container.gemstoneSlots.get(3).getKey(), width / 2 - 20 + 40 * 2, 100, container.gemstoneSlots.get(3).getValue()));
				break;
			}
			default: {
				GameLog.err.println("Invalid gemstone slot size: " + size);
				break;
			}
		}
	}
}
