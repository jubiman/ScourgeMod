package com.jubiman.scourgemod.player.stat;

import com.jubiman.scourgemod.item.gemstone.GemstoneQuality;

public class Charisma extends Stat {
	public Charisma() {
		super();
	}

	public Charisma(int level) {
		this.level = level;
	}

	@Override
	public void addGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost += gemstoneQuality.charisma;
	}

	@Override
	public void removeGemstoneBoost(GemstoneQuality gemstoneQuality) {
		this.boost -= gemstoneQuality.charisma;
	}

	public float getCharismaTRBoost() {
		return (float) Math.min((0.00001 * getTotalLevel() * (getTotalLevel() - 1)), 0.33);
	}
}
