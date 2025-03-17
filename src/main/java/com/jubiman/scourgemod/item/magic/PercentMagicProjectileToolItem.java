package com.jubiman.scourgemod.item.magic;

import com.jubiman.scourgemod.util.Logger;
import necesse.engine.GameLog;
import necesse.engine.localization.Localization;
import necesse.engine.util.GameMath;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.DoubleItemStatTip;
import necesse.inventory.item.ItemStatTipList;
import necesse.inventory.item.LocalMessageDoubleItemStatTip;
import necesse.inventory.item.toolItem.projectileToolItem.magicProjectileToolItem.MagicProjectileToolItem;

public abstract class PercentMagicProjectileToolItem extends MagicProjectileToolItem {
	public PercentMagicProjectileToolItem(int enchantCost) {
		super(enchantCost);
	}


	@Override
	public void consumeMana(float usedMana, ItemAttackerMob attackerMob) {
		Logger.debug("Mana consumed: " + usedMana);
		float percentCost = usedMana / 100;
		float manaCost = attackerMob.getMaxMana() * percentCost;
		if (manaCost > 0.0F) {
			attackerMob.useMana(usedMana, attackerMob.isPlayer && ((PlayerMob)attackerMob).isServerClient() ? ((PlayerMob)attackerMob).getServerClient() : null);
		}
	}

	@Override
	public void addManaCostTip(ItemStatTipList list, InventoryItem currentItem, InventoryItem lastItem, Mob mob) {
		float modifier = mob == null ? BuffModifiers.MANA_USAGE.defaultBuffManagerValue : mob.buffManager.getModifier(BuffModifiers.MANA_USAGE);
		double manaCost = this.getManaCost(currentItem) * modifier;
		double manaCostOneDecimal = GameMath.toDecimals(manaCost, 1);
		DoubleItemStatTip tip = new LocalMessageDoubleItemStatTip("itemtooltip", "manapercentcosttip", "value", manaCostOneDecimal, 1);
		if (lastItem != null) {
			tip.setCompareValue((this.getManaCost(lastItem) * modifier), false);
		}
		list.add(1000, tip);
	}
}
