package com.jubiman.scourgemod.buff.playerclass.mage;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.mage.SummonerClass;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;

public class SummonerBuff extends MageBuff {
	@Override
	protected void buffs(ActiveBuff buff, ScourgePlayer player) {
		super.buffs(buff, player);
		SummonerClass summonerClass = (SummonerClass) player.getPlayerClass();
		buff.setModifier(BuffModifiers.MAX_SUMMONS, summonerClass.getMaxSummonsBuff());
		buff.setModifier(BuffModifiers.SUMMON_DAMAGE, summonerClass.getSummonDamageBuff());

		buff.setModifier(BuffModifiers.MAX_MANA, summonerClass.getMaxManaBuff());
	}
}
