package com.jubiman.scourgemod.buff.playerclass.warrior;

import com.jubiman.scourgemod.buff.ScourgePassiveBuff;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.warrior.WarriorClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class WarriorBuff extends ScourgePassiveBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		WarriorClass warriorClass = (WarriorClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.MELEE_DAMAGE, warriorClass.getDamageBuff());
		buff.setModifier(BuffModifiers.MELEE_ATTACK_SPEED, warriorClass.getAttackSpeedBuff());
		buff.setModifier(BuffModifiers.ARMOR_FLAT, warriorClass.getArmorBuff());

		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, warriorClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, warriorClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.RANGED_DAMAGE, warriorClass.getOtherDamageTypesDebuff());
	}
}
