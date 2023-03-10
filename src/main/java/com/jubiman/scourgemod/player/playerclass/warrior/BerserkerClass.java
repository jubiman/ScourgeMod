package com.jubiman.scourgemod.player.playerclass.warrior;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class BerserkerClass extends WarriorClass {
	public BerserkerClass(ScourgePlayer player) {
		super("scourge_berserker", "berserker", player, "warrior.BerserkerClass");
	}

	public float getLifeStealBuff() {
		return (float) (0.00111 * getLevel() * (getLevel() - 1));
	}
}
