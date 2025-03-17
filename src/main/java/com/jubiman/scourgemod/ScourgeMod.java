package com.jubiman.scourgemod;

import com.jubiman.customdatalib.client.CustomClientRegistry;
import com.jubiman.customdatalib.player.CustomPlayerRegistry;
import com.jubiman.scourgemod.buff.playerclass.hunter.AssassinBuff;
import com.jubiman.scourgemod.buff.playerclass.hunter.HunterBuff;
import com.jubiman.scourgemod.buff.playerclass.hunter.RangerBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.MageBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.SummonerBuff;
import com.jubiman.scourgemod.buff.playerclass.mage.WarlockBuff;
import com.jubiman.scourgemod.buff.playerclass.tank.GuardianBuff;
import com.jubiman.scourgemod.buff.playerclass.tank.TankBuff;
import com.jubiman.scourgemod.buff.playerclass.warrior.BerserkerBuff;
import com.jubiman.scourgemod.buff.playerclass.warrior.PaladinBuff;
import com.jubiman.scourgemod.buff.playerclass.warrior.WarriorBuff;
import com.jubiman.scourgemod.buff.stat.*;
import com.jubiman.scourgemod.command.ScourgeClassCommand;
import com.jubiman.scourgemod.command.ScourgeStatCommand;
import com.jubiman.scourgemod.item.consumable.EssenceOfRebirth;
import com.jubiman.scourgemod.item.magic.ManaDebugItem;
import com.jubiman.scourgemod.item.projectile.DebugManaProjectile;
import com.jubiman.scourgemod.item.projectile.arrow.TerminatorArrowProjectile;
import com.jubiman.scourgemod.item.weapon.bow.Terminator;
import com.jubiman.scourgemod.network.packet.*;
import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;

@ModEntry
public class ScourgeMod {
	public ScourgeMod() throws ClassNotFoundException {
		Class.forName("com.jubiman.scourgemod.modifier.ScourgeBuffModifiers");
	}

	public void init() throws ClassNotFoundException {
		// Register Tiles

		// Register Objects

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
		BuffRegistry.registerBuff("scourge_berserker", new BerserkerBuff());
		BuffRegistry.registerBuff("scourge_paladin", new PaladinBuff());

		// Register items
		ItemRegistry.registerItem("scourge_manadebug", new ManaDebugItem(), 0, false);
		ItemRegistry.registerItem("scourge_terminator", new Terminator(), 9127, true);
		ItemRegistry.registerItem("scourge_essence_of_rebirth", new EssenceOfRebirth(), 1000, true);

		// Gemstone items
//		ItemRegistry.registerItem("scourge_unrefined_topaz", new TopazUnrefinedGemstoneMatItem(500, Item.Rarity.UNCOMMON), 0, true);
//		// Topaz gemstones (STR)
//		ItemRegistry.registerItem("scourge_rough_topaz_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.ROUGH, GemstoneType.STR, Item.Rarity.COMMON), 50, true);
//		ItemRegistry.registerItem("scourge_flawed_topaz_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWED, GemstoneType.STR, Item.Rarity.UNCOMMON), 100, true);
//		ItemRegistry.registerItem("scourge_fine_topaz_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FINE, GemstoneType.STR, Item.Rarity.RARE), 200, true);
//		ItemRegistry.registerItem("scourge_flawless_topaz_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWLESS, GemstoneType.STR, Item.Rarity.EPIC), 400, true);
//		ItemRegistry.registerItem("scourge_perfect_topaz_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.PERFECT, GemstoneType.STR, Item.Rarity.LEGENDARY), 1600, true);
//		// Amethyst gemstones (INT)
//		ItemRegistry.registerItem("scourge_rough_amethyst_gemstone", new GemstoneItem(Intelligence.class, GemstoneQuality.ROUGH, GemstoneType.INT, Item.Rarity.COMMON), 50, true);
//		ItemRegistry.registerItem("scourge_flawed_amethyst_gemstone", new GemstoneItem(Intelligence.class, GemstoneQuality.FLAWED, GemstoneType.INT, Item.Rarity.UNCOMMON), 100, true);
//		ItemRegistry.registerItem("scourge_fine_amethyst_gemstone", new GemstoneItem(Intelligence.class, GemstoneQuality.FINE, GemstoneType.INT, Item.Rarity.RARE), 200, true);
//		ItemRegistry.registerItem("scourge_flawless_amethyst_gemstone", new GemstoneItem(Intelligence.class, GemstoneQuality.FLAWLESS, GemstoneType.INT, Item.Rarity.EPIC), 400, true);
//		ItemRegistry.registerItem("scourge_perfect_amethyst_gemstone", new GemstoneItem(Intelligence.class, GemstoneQuality.PERFECT, GemstoneType.INT, Item.Rarity.LEGENDARY), 1600, true);
//		// Ruby gemstones (VIT)
//		ItemRegistry.registerItem("scourge_rough_ruby_gemstone", new GemstoneItem(Vitality.class, GemstoneQuality.ROUGH, GemstoneType.VIT, Item.Rarity.COMMON), 50, true);
//		ItemRegistry.registerItem("scourge_flawed_ruby_gemstone", new GemstoneItem(Vitality.class, GemstoneQuality.FLAWED, GemstoneType.VIT, Item.Rarity.UNCOMMON), 100, true);
//		ItemRegistry.registerItem("scourge_fine_ruby_gemstone", new GemstoneItem(Vitality.class, GemstoneQuality.FINE, GemstoneType.VIT, Item.Rarity.RARE), 200, true);
//		ItemRegistry.registerItem("scourge_flawless_ruby_gemstone", new GemstoneItem(Vitality.class, GemstoneQuality.FLAWLESS, GemstoneType.VIT, Item.Rarity.EPIC), 400, true);
//		ItemRegistry.registerItem("scourge_perfect_ruby_gemstone", new GemstoneItem(Vitality.class, GemstoneQuality.PERFECT, GemstoneType.VIT, Item.Rarity.LEGENDARY), 1600, true);
//		// Emerald gemstones (DEX)
//		ItemRegistry.registerItem("scourge_rough_emerald_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.ROUGH, GemstoneType.DEX, Item.Rarity.COMMON), 50, true);
//		ItemRegistry.registerItem("scourge_flawed_emerald_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWED, GemstoneType.DEX, Item.Rarity.UNCOMMON), 100, true);
//		ItemRegistry.registerItem("scourge_fine_emerald_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FINE, GemstoneType.DEX, Item.Rarity.RARE), 200, true);
//		ItemRegistry.registerItem("scourge_flawless_emerald_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWLESS, GemstoneType.DEX, Item.Rarity.EPIC), 400, true);
//		ItemRegistry.registerItem("scourge_perfect_emerald_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.PERFECT, GemstoneType.DEX, Item.Rarity.LEGENDARY), 1600, true);
//		// Jade gemstones (CHA)
//		ItemRegistry.registerItem("scourge_rough_jade_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.ROUGH, GemstoneType.CHA, Item.Rarity.COMMON), 50, true);
//		ItemRegistry.registerItem("scourge_flawed_jade_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWED, GemstoneType.CHA, Item.Rarity.UNCOMMON), 100, true);
//		ItemRegistry.registerItem("scourge_fine_jade_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FINE, GemstoneType.CHA, Item.Rarity.RARE), 200, true);
//		ItemRegistry.registerItem("scourge_flawless_jade_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.FLAWLESS, GemstoneType.CHA, Item.Rarity.EPIC), 400, true);
//		ItemRegistry.registerItem("scourge_perfect_jade_gemstone", new GemstoneItem(Charisma.class, GemstoneQuality.PERFECT, GemstoneType.CHA, Item.Rarity.LEGENDARY), 1600, true);


		// Register projectiles
		ProjectileRegistry.registerProjectile("scourge_manadebugprojectile", DebugManaProjectile.class, "scourge_manadebugprojectile", "scourge_manadebugprojectile_shadow");
		ProjectileRegistry.registerProjectile("scourge_terminatorarrowprojectile", TerminatorArrowProjectile.class, "scourge_terminatorarrowprojectile", "scourgeterminatorparrowrojectile_shadow");

		// Register containers

		// Register world gen

		// Register packets
		PacketRegistry.registerPacket(PacketSyncLevel.class);
		PacketRegistry.registerPacket(PacketShowHealingTip.class);
		PacketRegistry.registerPacket(PacketSyncStats.class);
		PacketRegistry.registerPacket(PacketSyncStat.class);
		PacketRegistry.registerPacket(PacketSyncScourgePlayer.class);

		// Register all packets in the network.packet package and any subpackages


		// Register players handler
		CustomPlayerRegistry.registerClass(ScourgePlayersHandler.name, ScourgePlayersHandler.class);
		CustomClientRegistry.registerCustomClient(ScourgePlayersHandler.name, ScourgeClient.class);
	}

	public void initResources() {
		Textures.initTextures();
	}

	public void postInit() {
		// Register recipes
		Recipes.registerModRecipe(new Recipe(
				"scourge_terminator",
				1,
				RecipeTechRegistry.FALLEN_ANVIL,
				new Ingredient[]{
						new Ingredient("nightsteelbar", 66),
						new Ingredient("soulmetalbar", 66),
						new Ingredient("soulcoreitem", 66),
						new Ingredient("phantomdust", 66),
						new Ingredient("demonicbar", 66),
						new Ingredient("pearlescentdiamond", 66),
						new Ingredient("slimematter", 66),
						new Ingredient("slimeum", 66),
				}
		).showBefore("slimegreatbow"));
		Recipes.registerModRecipe(new Recipe(
				"scourge_essence_of_rebirth",
				1,
				RecipeTechRegistry.FALLEN_ALCHEMY,
				new Ingredient[]{
						new Ingredient("alchemyshard", 16),
						new Ingredient("voidshard", 16),
						new Ingredient("upgradeshard", 16),
				}
		));


		// Register commands
		CommandsManager.registerServerCommand(new ScourgeStatCommand());
		CommandsManager.registerServerCommand(new ScourgeClassCommand());
	}
}
