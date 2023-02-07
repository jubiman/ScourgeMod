package com.jubiman.scourgemod.mob;

import necesse.entity.mobs.HumanTextureFull;

import static necesse.engine.registries.MobRegistry.Textures.humanTextureFull;

public class Textures {
	public static HumanTextureFull classSelector;

	static {
		classSelector = humanTextureFull("humans/classselector");
	}
}
