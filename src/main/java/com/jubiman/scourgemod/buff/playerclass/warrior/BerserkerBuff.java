package com.jubiman.scourgemod.buff.playerclass.warrior;

import com.jubiman.scourgemod.modifier.ScourgeBuffModifiers;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.warrior.BerserkerClass;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.MobBeforeHitEvent;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.gameDamageType.MeleeDamageType;

public class BerserkerBuff extends WarriorBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		BerserkerClass berserkerClass = (BerserkerClass) player.getPlayerClass();
		buff.setModifier(ScourgeBuffModifiers.LIFE_STEAL, berserkerClass.getLifeStealBuff());
	}

	@Override
	public void onBeforeHit(ActiveBuff buff, MobBeforeHitEvent hitEvent) {
		// Only life steal off melee damage
		if (!(hitEvent.damage.type instanceof MeleeDamageType)) return;

		Mob owner = hitEvent.attacker.getAttackOwner();
		int healing = Math.round(owner.getHealth() + hitEvent.damage.damage * buff.getModifier(ScourgeBuffModifiers.LIFE_STEAL));
		owner.setHealth(healing);
		if (owner instanceof PlayerMob) {
			PlayerMob playerMob = (PlayerMob) owner;
			if (playerMob.isServerClient()) {
				// TODO: temporary, maybe completely remove or add some green floating numbers (like dmg numbers)
				playerMob.getServerClient().sendChatMessage("Healed you for " + healing);
			}
		}
	}
}
