package com.jubiman.scourgemod.buff.playerclass.tank;

import com.jubiman.scourgemod.buff.ScourgePassiveBuff;
import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.tank.TankClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class TankBuff extends ScourgePassiveBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		TankClass tankClass = (TankClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.ALL_DAMAGE, tankClass.getOtherDamageTypesDebuff());
		buff.setModifier(BuffModifiers.MAX_HEALTH, tankClass.getMaxHealthBuff());
		buff.setModifier(BuffModifiers.ARMOR, tankClass.getArmorBuff());
		buff.setModifier(BuffModifiers.KNOCKBACK_INCOMING_MOD, tankClass.getKBIncBuff());
		buff.setModifier(BuffModifiers.SPEED, tankClass.getMovementSpeedDebuff());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {

	}
}
