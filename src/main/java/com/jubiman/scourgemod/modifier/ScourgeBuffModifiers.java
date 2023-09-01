package com.jubiman.scourgemod.modifier;

import necesse.engine.modifiers.Modifier;
import necesse.engine.modifiers.ModifierLimiter;
import necesse.entity.mobs.buffs.BuffModifiers;

public class ScourgeBuffModifiers {
	public static final Modifier<Float> DAGGER_DAMAGE;
	public static final Modifier<Float> LIFE_STEAL;


	static {
		DAGGER_DAMAGE = new Modifier<>(BuffModifiers.LIST, 1f, 0f, Modifier.FLOAT_ADD_APPEND, v -> Math.max(0.0f, v),
				Modifier.NORMAL_PERC_PARSER("daggerdamage"), ModifierLimiter.PERC_LIMITER("daggerdamage"));
		LIFE_STEAL = new Modifier<>(BuffModifiers.LIST, 0f, 0f, Modifier.FLOAT_ADD_APPEND, v -> Math.max(0.0f, v),
				Modifier.NORMAL_PERC_PARSER("lifesteal"), ModifierLimiter.PERC_LIMITER("lifesteal"));

	}
}
