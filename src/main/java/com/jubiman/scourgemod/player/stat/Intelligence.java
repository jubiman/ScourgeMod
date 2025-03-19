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
		return 0.02f * (getTotalLevel() - 1);
	}

	public float getIntelligenceMASBoost() {
		return 0.007f * (getTotalLevel() - 1);
	}

	public float getIntelligenceMMBoost() {
		return 0.05f * (getTotalLevel() - 1);
	}
}
