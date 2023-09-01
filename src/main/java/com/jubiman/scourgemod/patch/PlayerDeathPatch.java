package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.GameLog;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.Attacker;
import necesse.entity.mobs.PlayerMob;
import net.bytebuddy.asm.Advice;

import java.util.HashSet;

@ModMethodPatch(target = PlayerMob.class, name = "onDeath", arguments = {Attacker.class, HashSet.class})
public class PlayerDeathPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.This PlayerMob self) {
		if (self.isServerClient()) {
			ScourgePlayersHandler.getPlayer(self.getServerClient().authentication).onDeath();
		} else if (self.isClientClient()) {
			ScourgePlayersHandler.getPlayer(self.getClientClient().authentication).onDeath();
		} else {
			GameLog.err.println("PlayerMob is neither server nor client client!");
		}
	}
}
