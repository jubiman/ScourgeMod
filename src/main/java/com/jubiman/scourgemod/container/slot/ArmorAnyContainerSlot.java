package com.jubiman.scourgemod.container.slot;

import com.jubiman.scourgemod.container.GrindstoneContainer;
import com.jubiman.scourgemod.registry.GemstoneSlotRegistry;
import necesse.engine.GameLog;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.inventory.Inventory;
import necesse.inventory.InventoryItem;
import necesse.inventory.container.slots.ContainerSlot;

public class ArmorAnyContainerSlot extends ContainerSlot {
	public GNDItemMap gndData;
	private final GrindstoneContainer container;

	public ArmorAnyContainerSlot(Inventory inventory, int inventorySlot, GrindstoneContainer container) {
		super(inventory, inventorySlot);
		this.container = container;
	}

	@Override
	public boolean isItemValid(InventoryItem item) {
		// TODO: maybe remove the isArmorItem check, as weapons can also be used
		return item == null || (item.item.isArmorItem() && GemstoneSlotRegistry.get(item.item.getStringID()) != null);
	}

	@Override
	public int getItemStackLimit(InventoryItem item) {
		return 1;
	}

	@Override
	public void setItem(InventoryItem item) {
		container.itemChanged = true;
		if (item != null) {
			gndData = (GNDItemMap) item.getGndData().getItem("scourge");
			GemstoneSlotRegistry.GemstoneSlotList slotList;
			if (gndData == null) {
				gndData = new GNDItemMap();

				slotList = GemstoneSlotRegistry.get(item.item.getStringID());

				// Can't be null because of the isItemValid check
				gndData.setItem("gemstones", slotList.toGNDItemArray());
				item.getGndData().setItem("scourge", gndData);
			} else {
				slotList = GemstoneSlotRegistry.getListFromGNDData(gndData);
			}
			container.updateSlots(slotList);
		} else {
			container.gemstoneSlots.clear();
			container.ingredientInv.changeSize(1);
			gndData = null;
		}
		super.setItem(item);
	}

	public void updateItemGND() {
		container.getSlot(getInventorySlot()).getItem().setGndData(gndData);;
	}
}
