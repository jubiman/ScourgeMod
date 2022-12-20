package com.jubiman.scourgemod.player.playerclass.mage;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class SummonerClass extends MageClass {
	public SummonerClass(ScourgePlayer player) {
		super("scourge_summoner", "summoner", player, "SummonerClass");
	}

	public int getMaxSummonsBuff() {
		return (int) (0.4 * getLevel());
	}

	public float getSummonDamageBuff() {
		return (float) (0.01 * getLevel() * (getLevel() - 1));
	}

	@Override
	public float getAttackSpeedBuff() {
		return super.getAttackSpeedBuff() / 10;
	}

	@Override
	public float getSummonsSpeedBuff() {
		return super.getSummonsSpeedBuff() * 3;
	}

	@Override
	public float getSummonsTargetRangeBuff() {
		return super.getSummonsTargetRangeBuff() * 2;
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.3f;
	}
}
