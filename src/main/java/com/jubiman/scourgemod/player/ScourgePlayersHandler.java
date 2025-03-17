package com.jubiman.scourgemod.player;


import com.jubiman.customdatalib.client.CustomClientRegistry;
import com.jubiman.customdatalib.player.CustomPlayerRegistry;
import com.jubiman.customdatalib.player.CustomPlayersHandler;
import com.jubiman.scourgemod.network.packet.PacketSyncScourgePlayer;
import necesse.engine.GameEventListener;
import necesse.engine.GameEvents;
import necesse.engine.events.GameEvent;
import necesse.engine.events.ServerClientConnectedEvent;
import necesse.entity.mobs.PlayerMob;

public class ScourgePlayersHandler extends CustomPlayersHandler<ScourgePlayer> {
	public static final String name = "scourgeplayers";
	public static final String[] scourgeBuffs = new String[] {"vitality", "strength", "dexterity", "intelligence", "charisma"};

	public ScourgePlayersHandler() {
		super(ScourgePlayer.class, name);
	}

	static {
		GameEvents.addListener(ServerClientConnectedEvent.class, new GameEventListener<ServerClientConnectedEvent>() {
			@Override
			public void onEvent(ServerClientConnectedEvent serverClientConnectedEvent) {
				serverClientConnectedEvent.client.sendPacket(new PacketSyncScourgePlayer(getPlayer(serverClientConnectedEvent.client.authentication)));
			}
		});
	}

	public static ScourgePlayersHandler getInstance() {
		return (ScourgePlayersHandler) CustomPlayerRegistry.INSTANCE.get(name);
	}

	public static ScourgePlayer getPlayer(long auth) {
		return getInstance().get(auth);
	}

	/**
	 * Gets the player from the ServerClient.
	 * @param player The player to get the ScourgePlayer from.
	 * @return The ScourgePlayer.
	 */
	public static ScourgePlayer getPlayer(PlayerMob player) {
		return getPlayer(player.getServerClient().authentication);
	}

	public static ScourgeClient getClient() {
		return (ScourgeClient) CustomClientRegistry.get(name);
	}
}
