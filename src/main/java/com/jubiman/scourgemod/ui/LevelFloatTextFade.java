package com.jubiman.scourgemod.ui;

import necesse.engine.util.GameRandom;
import necesse.gfx.gameFont.FontOptions;
import necesse.level.maps.hudManager.floatText.FloatTextFade;

import java.awt.Color;

public class LevelFloatTextFade extends FloatTextFade {
	public LevelFloatTextFade(int x, int y, long value) {
		this(x, y, "+" + value + " exp");
	}

	public LevelFloatTextFade(int x, int y) {
		this(x, y, "Level up!");
	}

	private LevelFloatTextFade(int x, int y, String text) {
		super((int)(x + GameRandom.globalRandom.nextGaussian() * 8), (int)(y + GameRandom.globalRandom.nextGaussian() * 4), new FontOptions(16).outline().color(Color.GREEN));
		this.avoidOtherText = true;
		this.hoverTime = 1000;
		setText(text);
	}
}
