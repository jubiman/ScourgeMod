package com.jubiman.scourgemod.buff.playerclass.warrior;

import com.jubiman.scourgemod.modifier.ScourgeBuffModifiers;
import com.jubiman.scourgemod.network.packet.PacketShowHealingTip;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.warrior.BerserkerClass;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.MobBeforeHitEvent;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.gameDamageType.MeleeDamageType;
import necesse.gfx.gameFont.FontOptions;
import necesse.level.maps.hudManager.floatText.DmgText;

import java.awt.*;

public class BerserkerBuff extends WarriorBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		BerserkerClass berserkerClass = (BerserkerClass) player.getPlayerClass();
		buff.setModifier(ScourgeBuffModifiers.LIFE_STEAL, berserkerClass.getLifeStealBuff());
	}

	@Override
	public void onBeforeAttacked(ActiveBuff buff, MobBeforeHitEvent hitEvent) {
		// Only life steal off melee damage
		if (!(hitEvent.damage.type instanceof MeleeDamageType)) return;

		Mob owner = hitEvent.attacker.getAttackOwner();
		int healing = Math.round(hitEvent.damage.damage * buff.getModifier(ScourgeBuffModifiers.LIFE_STEAL));
		int healingTxt = Math.min(owner.getMaxHealth() - owner.getHealth(), healing);
		// If we don't actually heal anything, don't bother doing anything
		if (healingTxt == 0) return;
		owner.setHealth(owner.getHealth() + healing);
		if (owner instanceof PlayerMob) {
			PlayerMob playerMob = (PlayerMob) owner;
			if (playerMob.isServerClient()) {
				// Send a packet to the client to show the healing
				playerMob.getServerClient().sendPacket(
						new PacketShowHealingTip(playerMob.getX(), playerMob.getY() - 32, healingTxt, 16));
			}
		}
	}
}
