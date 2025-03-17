package com.jubiman.scourgemod.item.consumable;

import com.jubiman.scourgemod.network.packet.PacketSyncStats;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.util.Logger;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.HungerMob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.placeableItem.consumableItem.ConsumableItem;
import necesse.inventory.item.placeableItem.consumableItem.food.FoodConsumableItem;
import necesse.level.maps.Level;

public class EssenceOfRebirth extends ConsumableItem {
	public EssenceOfRebirth() {
		super(16, true);
		rarity = Rarity.UNIQUE;
		allowRightClickToConsume = true;
	}

	@Override
	public InventoryItem onPlace(Level level, int x, int y, PlayerMob player, int seed, InventoryItem item, GNDItemMap mapContent) {
		if (player.isServerClient()) {
			Logger.debug("EssenceOfRebirth: Player is server client");
			ScourgePlayer scourgePlayer = ScourgePlayersHandler.getPlayer(player);
			scourgePlayer.resetStats();
			player.getServerClient().sendPacket(new PacketSyncStats(scourgePlayer));
		}
		item.setAmount(item.getAmount() - 1);
		return item;
	}

	@Override
	protected ListGameTooltips getBaseTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
		ListGameTooltips tooltips = super.getBaseTooltips(item, perspective, blackboard);
		tooltips.add(Localization.translate("itemtooltip", "essence_of_rebirth"));
		return tooltips;
	}
}
