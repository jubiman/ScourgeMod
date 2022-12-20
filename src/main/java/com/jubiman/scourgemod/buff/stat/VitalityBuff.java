package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Vitality;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class VitalityBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		Vitality vitality = player.getVitalityObject();
		buff.setModifier(BuffModifiers.MAX_HEALTH, vitality.getVitalityHPBoost());
		buff.setModifier(BuffModifiers.ARMOR, vitality.getVitalityArmorBoost());
	}
}
