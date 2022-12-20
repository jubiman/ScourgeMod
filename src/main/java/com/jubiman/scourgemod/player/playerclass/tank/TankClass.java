package com.jubiman.scourgemod.player.playerclass.tank;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;

public class TankClass extends PlayerClass {
	public TankClass(ScourgePlayer player) {
		super("scourge_tank", "tank", player, "tank.TankClass");
	}

	public TankClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.3f;
	}

	public float getMaxHealthBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	public float getArmorBuff() {
		return (float) (0.003 * getLevel() * (getLevel() - 1));
	}

	public float getKBIncBuff() {
		return (float) -(0.001 * getLevel() * (getLevel() - 1));
	}

	public float getMovementSpeedDebuff() {
		return (float) Math.min((0.01 * getLevel() * (getLevel() - 1)) - 0.75, 0);
	}
}
