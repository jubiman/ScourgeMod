package com.jubiman.scourgemod.player.playerclass.warrior;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;

public class WarriorClass extends PlayerClass {
	public WarriorClass(ScourgePlayer player) {
		super("scourge_warrior", "warrior", player, "warrior.WarriorClass");
	}

	public WarriorClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	public float getDamageBuff() {
		return (float) (0.007 * getLevel() * (getLevel() - 1));
	}

	public float getAttackSpeedBuff() {
		return (float) (0.003 * getLevel() * (getLevel() - 1));
	}

	public int getArmorBuff() {
		return 2 * getLevel();
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return -0.7f;
	}
}
