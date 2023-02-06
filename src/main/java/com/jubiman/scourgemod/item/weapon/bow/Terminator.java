package com.jubiman.scourgemod.item.weapon.bow;

import com.jubiman.scourgemod.item.projectile.arrow.TerminatorArrowProjectile;
import necesse.engine.Screen;
import necesse.engine.localization.Localization;
import necesse.engine.localization.message.GameMessage;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.registries.DamageTypeRegistry;
import necesse.engine.sound.SoundEffect;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.AttackAnimMob;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.friendly.human.HumanMob;
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
		this.animSpeed = 450;
		this.rarity = Rarity.UNIQUE;
		this.attackDmg = new GameDamage(DamageTypeRegistry.RANGED, 666.0F);
		this.velocity = 220;
		this.attackRange = 800;
		this.attackXOffset = 12;
		this.attackYOffset = 28;
	}

	public void showAttack(Level level, int x, int y, AttackAnimMob mob, int attackHeight, InventoryItem item, int seed, PacketReader contentReader) {
		if (level.isClientLevel()) {
			Screen.playSound(GameResources.magicbolt1, SoundEffect.effect(mob).pitch(1.1F));
		}
	}

	protected void addTooltips(ListGameTooltips tooltips, InventoryItem item, boolean isSettlerWeapon) {
		tooltips.add(Localization.translate("itemtooltip", "terminator1"));
		tooltips.add(Localization.translate("itemtooltip", "terminator2"));
	}

	public GameMessage getSettlerCanUseError(HumanMob mob, InventoryItem item) {
		return new LocalMessage("ui", "settlercantuseitem");
	}

	protected void fireProjectiles(Level level, int x, int y, PlayerMob player, InventoryItem item, int seed, ArrowItem arrow, boolean consumeAmmo, PacketReader contentReader) {
		GameRandom random = new GameRandom(seed);
		//boolean left = x < player.x; // TODO: is needed?

		Projectile[] projectiles = new TerminatorArrowProjectile[4];
		for (int i = 0; i < 4; ++ i) {
			projectiles[i] = new TerminatorArrowProjectile(player, player.x, player.y, x, y, this.getVelocity(item, player) * arrow.speedMod, this.getAttackRange(item), this.getDamage(item).add(arrow.dmg, arrow.armorPen, arrow.critChance), this.getKnockback(item, player));
			//projectiles[i].setAngle(projectiles[i].getAngle() + (left ? 5 * i : -5 * i)); // TODO: fine tune
			projectiles[i].setAngle(projectiles[i].getAngle() + 10 * i); // TODO: fine tune
			projectiles[i].dropItem = false;
			projectiles[i].getUniqueID(random);
			level.entityManager.projectiles.addHidden(projectiles[i]);
		}

		if (level.isServerLevel())
			for (Projectile projectile : projectiles)
				level.getServer().network.sendToClientsAtExcept(new PacketSpawnProjectile(projectile), player.getServerClient(), player.getServerClient());
	}
}
