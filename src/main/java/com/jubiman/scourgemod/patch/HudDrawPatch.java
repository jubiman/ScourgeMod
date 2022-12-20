package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.player.ScourgePlayers;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.ui.HUD;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = HUD.class, name = "draw", arguments = {Level.class, GameCamera.class, PlayerMob.class, TickManager.class})
public class HudDrawPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.Argument(0) Level level, @Advice.Argument(2) PlayerMob player) {
		if (level.isClientLevel())
			if (player.isClientClient())
				ScourgePlayers.getPlayer(player.getClientClient().authentication).getMana().draw();
	}
}
