package com.jubiman.scourgemod.player.stat;

public class Intelligence extends Stat {
	public Intelligence() {
		super();
	}

	public Intelligence(int level) {
		super(level);
	}

	@Override
	public StatType getStatType() {
		return StatType.INTELLIGENCE;
	}

	public float getIntelligenceMDBoost() {
		return 0.007f * getTotalLevel() * (getTotalLevel() - 1);
	}

	public float getIntelligenceMASBoost() {
		return 0.0002f * getTotalLevel() * (getTotalLevel() - 1);
	}

	public int getIntelligenceMSBoost() {
		return (int) (0.2 * getTotalLevel()); // +1 max summon per 5 lvls
	}

	public float getIntelligenceMMBoost() {
		return 0.05f * getTotalLevel() * (getTotalLevel() - 1);
	}
}
