package com.jubiman.scourgemod.item.magic;

import com.jubiman.scourgemod.item.projectile.DebugManaProjectile;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameBlackboard;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.projectile.Projectile;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.level.maps.Level;

public class ManaDebugItem extends PercentMagicProjectileToolItem {
	public ManaDebugItem() {
		super(400);
		//super(Rarity.UNIQUE, 300, 20, 120, 100, 400);
		this.rarity = Rarity.UNIQUE;
//		this.animSpeed = 300;
//		this.attackDmg = new GameDamage(DamageTypeRegistry.MAGIC, 20);
//		this.velocity = 100;
//		this.knockback = 50;
//		this.attackRange = 1500;

		this.attackXOffset = 12;
		this.attackYOffset = 22;

		attackAnimTime.setBaseValue(300);
		attackDamage.setBaseValue(20)
				.setUpgradedValue(1, 40)
				.setUpgradedValue(2, 60);
		velocity.setBaseValue(100)
				.setUpgradedValue(1, 160)
				.setUpgradedValue(2, 220);
		attackRange.setBaseValue(1500)
				.setUpgradedValue(1, 2000)
				.setUpgradedValue(2, 2500);

		manaCost.setBaseValue(10);

//		this.manaCost = 10;
	}

	@Override
	public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
		Projectile projectile = new DebugManaProjectile(
				level, attackerMob, // Level and owner
				attackerMob.x, attackerMob.y, // Start position of projectile
				x, y, // Target position of projectile
				getProjectileVelocity(item, attackerMob), // Will add player buffs, enchantments etc
				getAttackRange(item), // Will add player buffs, enchantments etc
				getAttackDamage(item), // Will add player buffs, enchantments etc
				getKnockback(item, attackerMob) // Will add player buffs, enchantments etc
		);

		GameRandom random = new GameRandom(seed);
		projectile.resetUniqueID(random);

		projectile.moveDist(40);

		attackerMob.addAndSendAttackerProjectile(projectile);

		consumeMana(attackerMob, item);

		return item;
	}


	@Override
	public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
		ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
		tooltips.add(Localization.translate("itemtooltip", "debugmanaitem"));
		return tooltips;
	}
}
