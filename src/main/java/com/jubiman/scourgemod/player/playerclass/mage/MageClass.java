package com.jubiman.scourgemod.player.playerclass.mage;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;

public class MageClass extends PlayerClass {

	public MageClass(ScourgePlayer player) {
		super("scourge_mage", "mage", player, "mage.MageClass");
	}

	public MageClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.7f;
	}

	public float getDamageBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	public float getAttackSpeedBuff() {
		return (float) (0.003 * getLevel() * (getLevel() - 1));
	}

	public float getSummonsSpeedBuff() {
		return (float) (0.005 * getLevel() * (getLevel() - 1));
	}

	public float getSummonsTargetRangeBuff() {
		return (float) (0.0007 * getLevel() * (getLevel() - 1));
	}

	public float getMaxManaBuff() {
		return (float) (0.0033 * getLevel() * (getLevel() - 1));
	}
}
