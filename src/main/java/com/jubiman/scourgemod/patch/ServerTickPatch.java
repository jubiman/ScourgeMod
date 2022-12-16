package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.player.ScourgePlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.Server;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = Server.class, name = "tick", arguments = {})
public class ServerTickPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.This Server server) {
		for (int i = 0; i < server.getPlayersOnline(); ++i) {
			PlayerMob player = server.getPlayer(i);
			if (player.isServerClient())
				ScourgePlayers.getPlayer(player.getServerClient().authentication).tick(server);
		}
	}
}
