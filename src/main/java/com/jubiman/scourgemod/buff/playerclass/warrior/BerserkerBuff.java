package com.jubiman.scourgemod.buff.playerclass.warrior;

import com.jubiman.scourgemod.modifier.ScourgeBuffModifiers;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.warrior.BerserkerClass;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.MobHitEvent;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;

public class BerserkerBuff extends WarriorBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		BerserkerClass berserkerClass = (BerserkerClass) player.getPlayerClass();
		buff.setModifier(ScourgeBuffModifiers.LIFE_STEAL, berserkerClass.getLifeStealBuff());
	}

	@Override
	public void onAttacked(ActiveBuff buff, MobHitEvent hitEvent) {
		Mob owner = hitEvent.attacker.getAttackOwner();
		int healing = Math.round(owner.getHealth() + hitEvent.modifiedDamage * buff.getModifier(ScourgeBuffModifiers.LIFE_STEAL));
		owner.setHealth(healing);
		if (owner instanceof PlayerMob) {
			PlayerMob playerMob = (PlayerMob) owner;
			if (playerMob.isServerClient())
				playerMob.getServerClient().sendChatMessage("Healed you for " + healing);
		}
	}
}
