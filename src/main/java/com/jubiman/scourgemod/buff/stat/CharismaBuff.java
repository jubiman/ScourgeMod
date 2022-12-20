package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Charisma;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class CharismaBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		Charisma charisma = player.getCharismaObject();
		buff.setModifier(BuffModifiers.TARGET_RANGE, -charisma.getCharismaTRBoost());
		buff.setModifier(BuffModifiers.CHASER_RANGE, charisma.getCharismaTRBoost());
	}
}
