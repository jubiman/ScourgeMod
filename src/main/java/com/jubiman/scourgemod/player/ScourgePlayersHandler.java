package com.jubiman.scourgemod.player;

import com.jubiman.customplayerlib.CustomPlayerRegistry;
import com.jubiman.customplayerlib.CustomPlayersHandlerTickable;

public class ScourgePlayersHandler extends CustomPlayersHandlerTickable<ScourgePlayer> {
	public static final String name = "scourgeplayers";
	public static final String[] scourgeBuffs = new String[] {"vitality", "strength", "dexterity", "intelligence", "charisma"};

	public ScourgePlayersHandler() {
		super(ScourgePlayer.class, name);
	}

	public static ScourgePlayersHandler getInstance() {
		return (ScourgePlayersHandler) CustomPlayerRegistry.get(name);
	}

	public static ScourgePlayer getPlayer(long auth) {
		return getInstance().get(auth);
	}
}
