package com.jubiman.scourgemod.player.stat;

public class Charisma extends Stat {
	public Charisma() {
		super();
	}

	public Charisma(int level) {
		this.level = level;
	}

	@Override
	public StatType getStatType() {
		return StatType.CHARISMA;
	}

	public float getCharismaTRBoost() {
		return (float) Math.min((0.0001 * getTotalLevel() * (getTotalLevel() - 1)), 0.33);
	}
}
