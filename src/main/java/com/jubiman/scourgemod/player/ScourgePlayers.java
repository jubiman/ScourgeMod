package com.jubiman.scourgemod.player;

import com.jubiman.customplayerlib.CustomPlayerRegistry;
import com.jubiman.customplayerlib.CustomPlayersTickable;

public class ScourgePlayers extends CustomPlayersTickable<ScourgePlayer> {
	public static final String name = "scourgeplayers";
	public static final String[] scourgeBuffs = new String[] {"vitality", "strength", "dexterity", "intelligence", "charisma"};

	public ScourgePlayers() {
		super(ScourgePlayer.class, name);
	}

	public static ScourgePlayers getInstance() {
		return (ScourgePlayers) CustomPlayerRegistry.get(name);
	}

	public static ScourgePlayer getPlayer(long auth) {
		return getInstance().get(auth);
	}
}
