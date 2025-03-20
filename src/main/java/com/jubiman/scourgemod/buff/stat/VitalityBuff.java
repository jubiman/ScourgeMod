package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Vitality;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class VitalityBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		buffs(buff, player.getVitalityObject());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {
		buffs(buff, player.vitality);
	}

	private void buffs(ActiveBuff buff, Vitality vitality) {
		buff.setModifier(BuffModifiers.MAX_HEALTH_FLAT, vitality.getVitalityHPBoost());
		buff.setModifier(BuffModifiers.ARMOR_FLAT, vitality.getVitalityArmorBoost());
		buff.setModifier(BuffModifiers.COMBAT_HEALTH_REGEN_FLAT, vitality.getVitalityHRBoost());
	}
}
