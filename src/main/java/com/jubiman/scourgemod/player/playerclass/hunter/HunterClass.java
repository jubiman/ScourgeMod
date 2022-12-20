package com.jubiman.scourgemod.player.playerclass.hunter;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;

public class HunterClass extends PlayerClass {
	public HunterClass(ScourgePlayer player) {
		super("scourge_hunter", "hunter", player, "hunter.HunterClass");
	}

	public HunterClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	public float getAttackSpeedBuff() {
		return (float) (0.0001 * getLevel() * (getLevel() - 1));
	}

	public float getCritDamageBuff() {
		return (float) (0.001 * getLevel() * (getLevel() - 1));
	}

	public float getArmorPenBuff() {
		return (float) (0.0005 * getLevel() * (getLevel() - 1));
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.7f;
	}
}
