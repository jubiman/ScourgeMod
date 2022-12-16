package com.jubiman.scourgemod.player;

import com.jubiman.customplayerlib.CustomPlayerRegistry;
import com.jubiman.customplayerlib.CustomPlayers;

public class ScourgePlayers extends CustomPlayers<ScourgePlayer> {
	public static final String name = "scourgeplayers";
	public static boolean forceBuffTick = false;

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
