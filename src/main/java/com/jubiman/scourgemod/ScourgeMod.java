package com.jubiman.scourgemod;

import com.jubiman.customplayerlib.CustomPlayerRegistry;
import com.jubiman.scourgemod.buff.playerclass.hunter.AssassinBuff;
import com.jubiman.scourgemod.buff.playerclass.hunter.HunterBuff;
import com.jubiman.scourgemod.buff.playerclass.hunter.RangerBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.MageBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.SummonerBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.WarlockBuff;
import com.jubiman.scourgemod.buff.playerclass.tank.GuardianBuff;
import com.jubiman.scourgemod.buff.playerclass.tank.TankBuff;
import com.jubiman.scourgemod.buff.playerclass.warrior.PaladinBuff;
import com.jubiman.scourgemod.buff.playerclass.warrior.WarriorBuff;
import com.jubiman.scourgemod.buff.stat.*;
import com.jubiman.scourgemod.command.ScourgeClassCommand;
import com.jubiman.scourgemod.command.ScourgeStatCommand;
import com.jubiman.scourgemod.item.ManaDebugItem;
import com.jubiman.scourgemod.item.projectile.arrow.TerminatorArrowProjectile;
import com.jubiman.scourgemod.item.weapon.bow.Terminator;
import com.jubiman.scourgemod.network.packet.SyncLevelPacket;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.mana.Mana;
import com.jubiman.scourgemod.projectile.DebugManaProjectile;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.BuffRegistry;
import necesse.engine.registries.ItemRegistry;
import necesse.engine.registries.PacketRegistry;
import necesse.engine.registries.ProjectileRegistry;

@ModEntry
public class ScourgeMod {
	public ScourgeMod() throws ClassNotFoundException {
		Class.forName("com.jubiman.scourgemod.modifier.ScourgeBuffModifiers");
	}

	public void init() {
		CustomPlayerRegistry.register(ScourgePlayersHandler.name, new ScourgePlayersHandler());

		// Register Stat buffs
		BuffRegistry.registerBuff("scourge_vitality", new VitalityBuff());
		BuffRegistry.registerBuff("scourge_strength", new StrengthBuff());
		BuffRegistry.registerBuff("scourge_dexterity", new DexterityBuff());
		BuffRegistry.registerBuff("scourge_intelligence", new IntelligenceBuff());
		BuffRegistry.registerBuff("scourge_charisma", new CharismaBuff());

		// Register PlayerClass buffs
		BuffRegistry.registerBuff("scourge_hunter", new HunterBuff());
		BuffRegistry.registerBuff("scourge_ranger", new RangerBuff());
		BuffRegistry.registerBuff("scourge_assassin", new AssassinBuff());
		BuffRegistry.registerBuff("scourge_mage", new MageBuff());
		BuffRegistry.registerBuff("scourge_summoner", new SummonerBuff());
		BuffRegistry.registerBuff("scourge_warlock", new WarlockBuff());
		BuffRegistry.registerBuff("scourge_tank", new TankBuff());
		BuffRegistry.registerBuff("scourge_guardian", new GuardianBuff());
		BuffRegistry.registerBuff("scourge_warrior", new WarriorBuff());
		BuffRegistry.registerBuff("scourge_paladin", new PaladinBuff());

		// Register items
		ItemRegistry.registerItem("scourge_manadebug", new ManaDebugItem(), 0, false);
		ItemRegistry.registerItem("scourge_terminator", new Terminator(), 9127, true);

		// Register projectiles
		ProjectileRegistry.registerProjectile("scourge_manadebugprojectile", DebugManaProjectile.class, "scourge_manadebugprojectile", "scourge_manadebugprojectile_shadow");
		ProjectileRegistry.registerProjectile("scourge_terminatorarrowprojectile", TerminatorArrowProjectile.class, "scourge_terminatorarrowprojectile", "scourgeterminatorparrowrojectile_shadow");

		// Register packets
		PacketRegistry.registerPacket(SyncLevelPacket.class);
	}

	public void initResources() {
		Mana.loadTextures();
	}

	public void postInit() {
		CommandsManager.registerServerCommand(new ScourgeStatCommand());
		CommandsManager.registerServerCommand(new ScourgeClassCommand());
	}
}
