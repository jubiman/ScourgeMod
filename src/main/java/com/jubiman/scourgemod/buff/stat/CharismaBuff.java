package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.stat.Charisma;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class CharismaBuff extends StatBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		buffs(buff, player.getCharismaObject());
	}

	@Override
	protected void buffs(ActiveBuff buff, ScourgeClient player) {
		buffs(buff, player.charisma);
	}

	private void buffs(ActiveBuff buff, Charisma charisma) {
		buff.setModifier(BuffModifiers.TARGET_RANGE, -charisma.getCharismaTRBoost());
		buff.setModifier(BuffModifiers.CHASER_RANGE, -charisma.getCharismaTRBoost());
	}
}
