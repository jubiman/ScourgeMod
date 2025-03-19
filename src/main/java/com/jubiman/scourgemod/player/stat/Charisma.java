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

	public float getCharismaSDBoost() {
		return 0.01f * (getTotalLevel() - 1);
	}

	public float getCharismaSASBoost() {
		return 0.001f * (getTotalLevel() - 1);
	}

	public float getCharismaSCCBoost() {
		return 0.005f * (getTotalLevel() - 1);
	}

	public float getCharismaSCDBoost() {
		return 0.01f * (getTotalLevel() - 1);
	}

	public int getCharismaMSBoost() {
		return Math.max(getTotalLevel() / 25, 0);
	}

	public float getCharismaSSBoost() {
		return (float) (0.005 * (getTotalLevel() - 1));
	}
}
