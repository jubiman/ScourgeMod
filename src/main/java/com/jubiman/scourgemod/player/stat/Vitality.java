package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public class Vitality extends Stat {
	public Vitality() {
		super();
	}

	public Vitality(int level) {
		super(level);
	}

	@Override
	public void addGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost += gemstoneQuality.vitality;
	}

	@Override
	public void removeGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost -= gemstoneQuality.vitality;
	}

	public float getVitalityHPBoost() {
		return (float) (0.01 * getTotalLevel() * (getTotalLevel() - 1));
	}

	public float getVitalityArmorBoost() {
		return (float) (0.005 * getTotalLevel() * (getTotalLevel() - 1));
	}
}
