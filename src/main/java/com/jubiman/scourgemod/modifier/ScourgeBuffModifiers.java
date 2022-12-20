package com.jubiman.scourgemod.modifier;

import necesse.engine.modifiers.Modifier;
import necesse.engine.modifiers.ModifierLimiter;
import necesse.entity.mobs.buffs.BuffModifiers;

public class ScourgeBuffModifiers {
	public static final Modifier<Float> MAX_MANA;
	public static final Modifier<Float> DAGGER_DAMAGE;


	static {
		MAX_MANA = new Modifier<>(BuffModifiers.LIST, 1f, 0f, Modifier.FLOAT_ADD_APPEND, v -> Math.max(0.0f, v),
				Modifier.NORMAL_PERC_PARSER("maxmana"), ModifierLimiter.PERC_LIMITER("maxmana"));
		DAGGER_DAMAGE = new Modifier<>(BuffModifiers.LIST, 1f, 0f, Modifier.FLOAT_ADD_APPEND, v -> Math.max(0.0f, v),
				Modifier.NORMAL_PERC_PARSER("daggerdamage"), ModifierLimiter.PERC_LIMITER("daggerdamage"));

	}
}
