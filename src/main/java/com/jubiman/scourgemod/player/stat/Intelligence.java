package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public class Intelligence extends Stat {
	public Intelligence() {
		super();
	}

	public Intelligence(int level) {
		super(level);
	}

	@Override
	public void addGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost += gemstoneQuality.intelligence;
	}

	@Override
	public void removeGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost -= gemstoneQuality.intelligence;
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
		return 0.5f * getTotalLevel() * (getTotalLevel() - 1);
	}
}
