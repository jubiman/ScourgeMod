package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.GameLog;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.entity.mobs.PlayerMob;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventory;
import necesse.inventory.container.slots.ArmorContainerSlot;
import necesse.inventory.container.slots.ContainerSlot;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = ContainerSlot.class, name = "setItem", arguments = {InventoryItem.class})
public class ArmorContainerSlotSetItemPatch {
	@Advice.OnMethodEnter
	static void onEnter(@Advice.This ContainerSlot self, @Advice.Argument(0) InventoryItem item) {
		if (!(self instanceof ArmorContainerSlot)
				|| !(self.getInventory() instanceof PlayerInventory)
				|| ((PlayerInventory) self.getInventory()).getInventoryID() != 2) // non-cosmetic armor inventory
			return;

		ScourgePlayer player = ScourgePlayersHandler.getPlayer(((PlayerInventory) self.getInventory()).player);
		// Remove current gemstone boosts
		GNDItemMap scourgeData = (GNDItemMap) self.getItem().getGndData().getItem("scourge");
		if (scourgeData != null) {
			GNDItemMap gemstoneData = (GNDItemMap) scourgeData.getItem("gemstones");
			if (gemstoneData != null) {
				for (int i = 0; i < gemstoneData.getMapSize(); i++) {
					GNDItemMap gemstone = (GNDItemMap) gemstoneData.getItem(i);
					if (gemstone != null) {
						String gemstoneType = gemstone.getString("gemstoneType");
						if (!gemstoneType.equals("")) {
							player.removeGemstoneBoost(gemstoneType);
						}
					}
				}
			}
		}

		// Add gemstone boosts
		scourgeData = (GNDItemMap) item.getGndData().getItem("scourge");
		if (scourgeData != null) {
			GNDItemMap gemstoneData = (GNDItemMap) scourgeData.getItem("gemstones");
			if (gemstoneData != null) {
				for (int i = 0; i < gemstoneData.getMapSize(); i++) {
					GNDItemMap gemstone = (GNDItemMap) gemstoneData.getItem(i);
					if (gemstone != null) {
						String gemstoneType = gemstone.getString("gemstoneType");
						if (!gemstoneType.equals("")) {
							player.addGemstoneBoost(gemstoneType);
						}
					}
				}
			}
		}
	}
}
