package com.jubiman.scourgemod.player;


import com.jubiman.customdatalib.player.CustomPlayerRegistry;
import com.jubiman.customdatalib.player.CustomPlayersHandler;
import necesse.entity.mobs.PlayerMob;

public class ScourgePlayersHandler extends CustomPlayersHandler<ScourgePlayer> {
	public static final String name = "scourgeplayers";
	public static final String[] scourgeBuffs = new String[] {"vitality", "strength", "dexterity", "intelligence", "charisma"};

	public ScourgePlayersHandler() {
		super(ScourgePlayer.class, name);
	}

	public static ScourgePlayersHandler getInstance() {
		return (ScourgePlayersHandler) CustomPlayerRegistry.INSTANCE.get(name);
	}

	public static ScourgePlayer getPlayer(long auth) {
		return getInstance().get(auth);
	}

	/**
	 * Gets the player from the ServerClient or the ClientClient.
	 * @param player The player to get the ScourgePlayer from.
	 * @return The ScourgePlayer.
	 */
	public static ScourgePlayer getPlayer(PlayerMob player) {
		if (player.isServerClient()) {
			return getPlayer(player.getServerClient().authentication);
		} else {
			return getPlayer(player.getClientClient().authentication);
		}
	}
}
