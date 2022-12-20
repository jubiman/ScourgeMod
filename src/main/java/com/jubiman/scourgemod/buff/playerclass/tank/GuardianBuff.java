package com.jubiman.scourgemod.buff.playerclass.tank;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.tank.GuardianClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class GuardianBuff extends TankBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		GuardianClass guardianClass = (GuardianClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.ARMOR, guardianClass.getArmorBuff());
		buff.setModifier(BuffModifiers.MAX_HEALTH, guardianClass.getMaxHealthBuff());
	}
}
