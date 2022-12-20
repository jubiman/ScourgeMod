package com.jubiman.scourgemod.player.stat;

public class Charisma extends Stat {
	public Charisma() {
		super();
	}

	public Charisma(int level) {
		this.level = level;
	}

	public float getCharismaTRBoost() {
		return (float) Math.min((0.00001 * level * (level - 1)), 0.33);
	}
}
