package com.jubiman.scourgemod.player.playerclass;

import com.jubiman.scourgemod.player.ScourgePlayer;

public class EmptyClass extends PlayerClass {
	public EmptyClass(ScourgePlayer player) {
		super("", "empty", player, "EmptyClass");
	}

	public EmptyClass(String buffStringId, String displayName, ScourgePlayer player, String className) {
		super(buffStringId, displayName, player, className);
	}

	@Override
	public float getOtherDamageTypesDebuff() {
		return 0;
	}
}
