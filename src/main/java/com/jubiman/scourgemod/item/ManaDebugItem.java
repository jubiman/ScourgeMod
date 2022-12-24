package com.jubiman.scourgemod.item;

import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.projectile.DebugManaProjectile;
import necesse.engine.localization.Localization;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.registries.DamageTypeRegistry;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.projectile.Projectile;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.inventory.item.toolItem.projectileToolItem.ProjectileToolItem;
import necesse.level.maps.Level;

public class ManaDebugItem extends ProjectileToolItem {
	public ManaDebugItem() {
		super(400);
		//super(Rarity.UNIQUE, 300, 20, 120, 100, 400);
		this.rarity = Rarity.UNIQUE;
		this.animSpeed = 300;
		this.attackDmg = new GameDamage(DamageTypeRegistry.MAGIC, 20);
		this.velocity = 100;
		this.knockback = 50;
		this.attackRange = 1500;

		this.attackXOffset = 12;
		this.attackYOffset = 22;
	}

	@Override
	public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
		boolean used = false;
		if (player.isServerClient())
			used = ScourgePlayersHandler.getPlayer(player.getServerClient().authentication).useMana(10);

		else if (player.isClientClient())
			used = ScourgePlayersHandler.getPlayer(player.getClientClient().authentication).useMana(10);

		if (used) {
			Projectile projectile = new DebugManaProjectile(
					level, player, // Level and owner
					player.x, player.y, // Start position of projectile
					x, y, // Target position of projectile
					getVelocity(item, player), // Will add player buffs, enchantments etc
					getAttackRange(item), // Will add player buffs, enchantments etc
					getDamage(item), // Will add player buffs, enchantments etc
					getKnockback(item, player) // Will add player buffs, enchantments etc
			);

			GameRandom random = new GameRandom(seed);
			projectile.resetUniqueID(random);

			projectile.moveDist(40);

			level.entityManager.projectiles.addHidden(projectile);

			if (level.isServerLevel()) {
				level.getServer().network.sendToClientsAtExcept(new PacketSpawnProjectile(projectile), player.getServerClient(), player.getServerClient());
			}
		}
		return item;
	}

	@Override
	public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
		ListGameTooltips tooltips = super.getTooltips(item, perspective);
		tooltips.add(Localization.translate("itemtooltip", "debugmanaitem"));
		tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
		tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
		addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
		return tooltips;
	}
}
