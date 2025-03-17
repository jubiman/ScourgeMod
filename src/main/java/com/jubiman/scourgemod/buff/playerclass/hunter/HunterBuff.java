package com.jubiman.scourgemod.buff.playerclass.hunter;

import com.jubiman.scourgemod.buff.ScourgePassiveBuff;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.hunter.HunterClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.BuffModifiers;

public class HunterBuff extends ScourgePassiveBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		HunterClass hunterClass = ((HunterClass) player.getPlayerClass());
		if (hunterClass.getBuffStringId().equals("hunter"))
			buff.setModifier(BuffModifiers.ATTACK_SPEED, hunterClass.getAttackSpeedBuff());
		buff.setModifier(BuffModifiers.CRIT_DAMAGE, hunterClass.getCritDamageBuff());
		buff.setModifier(BuffModifiers.ARMOR_PEN, hunterClass.getArmorPenBuff());

		buff.setModifier(BuffModifiers.MAGIC_DAMAGE, hunterClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, hunterClass.getOtherDamageTypesDebuff());
	}
}
