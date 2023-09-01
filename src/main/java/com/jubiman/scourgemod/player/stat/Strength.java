package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public class Strength extends Stat {
	public Strength() {
		super();
	}

	public Strength(int level) {
		super(level);
	}

	@Override
	public void addGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost += gemstoneQuality.strength;
	}

	@Override
	public void removeGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost -= gemstoneQuality.strength;
	}

	public int getStrengthAPBoost() {
		return (int) (0.1 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getStrengthDMGBoost() {
		return (float) (0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getStrengthKBRBoost() {
		return (float) -(0.001 * getTotalLevel() * (getTotalLevel() - 1));
	}
}
