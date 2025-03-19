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

	public float getCharismaSDBoost() {
		return (float) (0.001 * getTotalLevel() * getTotalLevel());
	}

	public float getCharismaSASBoost() {
		return (float) (0.001 * getTotalLevel() * getTotalLevel());
	}

	public float getCharismaSCCBoost() {
		return (float) (0.0005 * getTotalLevel() * getTotalLevel());
	}

	public float getCharismaSCDBoost() {
		return (float) (0.001 * getTotalLevel() * getTotalLevel());
	}

	public int getCharismaMSBoost() {
		return getTotalLevel() / 25;
	}

	public float getCharismaSSBoost() {
		return (float) (0.0001 * getTotalLevel() * getTotalLevel());
	}
}
