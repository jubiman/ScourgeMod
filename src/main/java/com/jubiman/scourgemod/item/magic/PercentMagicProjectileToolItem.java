package com.jubiman.scourgemod.item.magic;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameMath;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.projectileToolItem.magicProjectileToolItem.MagicProjectileToolItem;

public abstract class PercentMagicProjectileToolItem extends MagicProjectileToolItem {
	public PercentMagicProjectileToolItem(int enchantCost) {
		super(enchantCost);
	}

	@Override
	public String getManaCostTip(InventoryItem item, Mob mob) {
		float modifier = mob == null ? BuffModifiers.MANA_USAGE.defaultBuffManagerValue : mob.buffManager.getModifier(BuffModifiers.MANA_USAGE);
		double manaCost = this.getManaCost(item) * modifier;
		double manaCostOneDecimal = GameMath.toDecimals(manaCost, 1);
		String manaCostString;
		if ((double)((int)manaCostOneDecimal) == manaCostOneDecimal) {
			manaCostString = String.valueOf((int) manaCostOneDecimal);
		} else {
			manaCostString = String.valueOf(manaCostOneDecimal);
		}

		return Localization.translate("itemtooltip", "manapercentcosttip", "value", manaCostString);
	}
}
