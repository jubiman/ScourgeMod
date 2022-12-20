package com.jubiman.scourgemod.buff.playerclass.hunter;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.hunter.RangerClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class RangerBuff extends HunterBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		RangerClass rangerClass = ((RangerClass) player.getPlayerClass());

		buff.setModifier(BuffModifiers.RANGED_ATTACK_SPEED, rangerClass.getAttackSpeedBuff());
		buff.setModifier(BuffModifiers.RANGED_DAMAGE, rangerClass.getRangedDamageBuff());
		buff.setModifier(BuffModifiers.PROJECTILE_VELOCITY, rangerClass.getProjectileSpeedBuff());

		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, rangerClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, rangerClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.MELEE_DAMAGE, rangerClass.getOtherDamageTypesDebuff());
	}
}
