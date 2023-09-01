package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Strength;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class StrengthBuff extends StatBuff {
	@Override
	public void buffs(ActiveBuff buff, ScourgePlayer player) {
		Strength strength = player.getStrengthObject();
		buff.setModifier(BuffModifiers.ARMOR_PEN_FLAT, strength.getStrengthAPBoost());
		buff.setModifier(BuffModifiers.MELEE_DAMAGE, strength.getStrengthDMGBoost());
		buff.setModifier(BuffModifiers.KNOCKBACK_INCOMING_MOD, strength.getStrengthKBRBoost());
	}
}
