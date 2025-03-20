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
		return (float) Math.min((0.005 * (getTotalLevel() - 1)), 0.33);
	}

	public float getCharismaSCCBoost() {
		return 0.01f * (getTotalLevel() - 1);
	}

	public float getCharismaSCDBoost() {
		return 0.03f * (getTotalLevel() - 1);
	}

	public int getCharismaMSBoost() {
		int x = getTotalLevel() - 1;
		return Math.round((10f * x) / (x + 20));
	}

	public float getCharismaSSBoost() {
		return (float) (0.005 * (getTotalLevel() - 1));
	}
}
