package com.jubiman.scourgemod.item.weapon.bow;

import com.jubiman.scourgemod.item.projectile.arrow.TerminatorArrowProjectile;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.util.GameBlackboard;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.entity.projectile.Projectile;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.arrowItem.ArrowItem;
import necesse.inventory.item.toolItem.projectileToolItem.bowProjectileToolItem.BowProjectileToolItem;
import necesse.level.maps.Level;

public class Terminator extends BowProjectileToolItem {
	public Terminator() {
		super(12521);
		this.rarity = Rarity.UNIQUE;

		attackAnimTime.setBaseValue(450);
		attackDamage.setBaseValue(333)
				.setUpgradedValue(1, 666)
				.setUpgradedValue(2, 999);
		velocity.setBaseValue(100)
				.setUpgradedValue(1, 160)
				.setUpgradedValue(2, 220);
		attackRange.setBaseValue(800)
				.setUpgradedValue(1, 1400)
				.setUpgradedValue(2, 2000);

		this.attackXOffset = 12;
		this.attackYOffset = 28;
	}

	@Override
	public void showAttack(Level level, int x, int y, ItemAttackerMob mob, int attackHeight, InventoryItem item, int animAttack, int seed, GNDItemMap mapContent) {
		if (level.isClient()) {
			SoundManager.playSound(GameResources.magicbolt1, SoundEffect.effect(mob).pitch(1.1F));
		}
	}

	@Override
	protected void fireProjectiles(Level level, int x, int y, ItemAttackerMob attackerMob, InventoryItem item, int seed, ArrowItem arrow, boolean dropItem, GNDItemMap mapContent) {
		GameRandom random = new GameRandom(seed);
		for (int i = 0; i < 4; ++ i) {
			Projectile projectile = this.getProjectile(level, x, y, attackerMob, item, seed, arrow, dropItem, mapContent);
			projectile.setAngle(projectile.getAngle() + (-10 + 10 * i));
			projectile.dropItem = false;
			projectile.resetUniqueID(random);
			level.entityManager.projectiles.addHidden(projectile);
			attackerMob.addAndSendAttackerProjectile(projectile);
		}
	}

	//	@Override
//	public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
//		GameRandom random = new GameRandom(seed);
//		//boolean left = x < player.x; // TODO: is needed?
//
//		for (int i = 0; i < 4; ++ i) {
//			TerminatorArrowProjectile projectile = new TerminatorArrowProjectile(
//					level, attackerMob,
//					attackerMob.x, attackerMob.y,
//					x, y,
//					getProjectileVelocity(item, attackerMob),
//					getAttackRange(item),
//					getAttackDamage(item),
//					getKnockback(item, attackerMob)
//			);
//			//projectiles[i].setAngle(projectiles[i].getAngle() + (left ? 5 * i : -5 * i)); // TODO: fine tune
//			projectile.setAngle(projectile.getAngle() + 10 * i); // TODO: fine tune
//			projectile.dropItem = false;
//			projectile.resetUniqueID(random);
//			level.entityManager.projectiles.addHidden(projectile);
//			attackerMob.addAndSendAttackerProjectile(projectile);
//		}
//
//
//		return item;
//	}

	@Override
	public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
		ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
		tooltips.add(Localization.translate("itemtooltip", "terminator1"));
		tooltips.add(Localization.translate("itemtooltip", "terminator2"));
		return tooltips;
	}
}
