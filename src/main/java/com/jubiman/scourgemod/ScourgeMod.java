package com.jubiman.scourgemod;

import com.jubiman.customplayerlib.CustomPlayerRegistry;
import com.jubiman.scourgemod.buff.VitalityBuff;
import com.jubiman.scourgemod.command.ScourgeVitality;
import com.jubiman.scourgemod.player.ScourgePlayers;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.BuffRegistry;

@ModEntry
public class ScourgeMod {
	public void init() {
		CustomPlayerRegistry.register(ScourgePlayers.name, new ScourgePlayers());

		BuffRegistry.registerBuff("scourge_vitality", new VitalityBuff());
	}

	public void postInit() {
		CommandsManager.registerServerCommand(new ScourgeVitality());
	}
}
