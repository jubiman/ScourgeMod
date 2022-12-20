package com.jubiman.scourgemod.buff.playerclass.mage;

import com.jubiman.scourgemod.modifier.ScourgeBuffModifiers;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.mage.WarlockClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class WarlockBuff extends MageBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		WarlockClass warlockClass = (WarlockClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, 0f);
		buff.setModifier(ScourgeBuffModifiers.MAX_MANA, warlockClass.getMaxManaBuff());
	}
}
