package com.jubiman.scourgemod.command;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.level.LevelBase;
import necesse.engine.commands.CmdParameter;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.commands.parameterHandlers.IntParameterHandler;
import necesse.engine.commands.parameterHandlers.ServerClientParameterHandler;
import necesse.engine.commands.parameterHandlers.StringParameterHandler;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.gfx.GameColor;

public class ScourgeStatCommand extends ModularChatCommand {
	public ScourgeStatCommand() {
		super("scourgestat", "Change someone's attributes", PermissionLevel.MODERATOR, true, new CmdParameter("<attribute>", new StringParameterHandler()), new CmdParameter("set|add|remove", new StringParameterHandler()), new CmdParameter("player", new ServerClientParameterHandler(true, false), true), new CmdParameter("value", new IntParameterHandler(1), true));
	}

	@Override
	public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] strings, CommandLog commandLog) {
		ScourgePlayer player = ScourgePlayersHandler.getPlayer(((ServerClient) args[2]).authentication);
		if (player == null) {
			commandLog.add(GameColor.RED + "Player not found");
			return;
		}
		switch (((String) args[0]).toLowerCase()) {
			case "vit":
			case "vitality": {
				vitality(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "str":
			case "strength": {
				strength(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "dex":
			case "dexterity": {
				dexterity(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "int":
			case "intelligence": {
				intelligence(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "cha":
			case "charisma": {
				charisma(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "sp":
			case "skillpoint":
			case "skillpoints": {
				skillpoints(player, (String) args[1], (int) args[3], commandLog);
				break;
			}
			case "lvl":
			case "exp":
			case "level": {
				level(player, (String) args[1], (int) args[3], commandLog, (ServerClient) args[2]);
				break;
			}
		}
	}

	private static void vitality(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setVitality(value);
				break;
			case "add":
				player.setVitality(player.getVitality() + value);
				break;
			case "remove":
				player.setVitality(player.getVitality() - value);
				break;
			case "get":
				commandLog.add("Vitality: " + player.getVitality());
				return;
		}
		commandLog.add("Vitality set to " + player.getVitality());
	}

	private static void strength(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setStrength(value);
				break;
			case "add":
				player.setStrength(player.getStrength() + value);
				break;
			case "remove":
				player.setStrength(player.getStrength() - value);
				break;
			case "get":
				commandLog.add("Strength: " + player.getStrength());
				return;
		}
		commandLog.add("Strength set to " + player.getStrength());
	}

	private static void dexterity(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setDexterity(value);
				break;
			case "add":
				player.setDexterity(player.getDexterity() + value);
				break;
			case "remove":
				player.setDexterity(player.getDexterity() - value);
				break;
			case "get":
				commandLog.add("Dexterity: " + player.getDexterity());
				return;
		}
		commandLog.add("Dexterity set to " + player.getDexterity());
	}

	private static void intelligence(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setIntelligence(value);
				break;
			case "add":
				player.setIntelligence(player.getIntelligence() + value);
				break;
			case "remove":
				player.setIntelligence(player.getIntelligence() - value);
				break;
			case "get":
				commandLog.add("Intelligence: " + player.getIntelligence());
				return;
		}
		commandLog.add("Intelligence set to " + player.getIntelligence());
	}

	private static void charisma(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setCharisma(value);
				break;
			case "add":
				player.setCharisma(player.getCharisma() + value);
				break;
			case "remove":
				player.setCharisma(player.getCharisma() - value);
				break;
			case "get":
				commandLog.add("Charisma: " + player.getCharisma());
				return;
		}
		commandLog.add("Charisma set to " + player.getCharisma());
	}

	private static void skillpoints(ScourgePlayer player, String option, int value, CommandLog commandLog) {
		switch (option.toLowerCase()) {
			case "set":
				player.setSkillPoints(value);
				break;
			case "add":
				player.setSkillPoints(player.getSkillPoints() + value);
				break;
			case "remove":
				player.setSkillPoints(player.getSkillPoints() - value);
				break;
			case "get":
				commandLog.add("SkillPoints: " + player.getSkillPoints());
				return;
		}
		commandLog.add("SkillPoints set to " + player.getSkillPoints());
	}

	private static void level(ScourgePlayer player, String option, int value, CommandLog commandLog, ServerClient serverClient) {
		LevelBase level = player.getPlayerLevel();
		boolean leveledUp = false;
		switch (option.toLowerCase()) {
			case "set":
				leveledUp = level.setExp(value);
				break;
			case "add":
				leveledUp = level.addExp(level.getLevel() + value);
				break;
			case "remove":
				leveledUp = level.removeExp(level.getLevel() - value);
				break;
			case "get":
				commandLog.add("Exp: " + level.getExp() + ", which is level " + level.getLevel());
				return;
		}
		if (leveledUp)
			serverClient.sendChatMessage("You leveled up to level " + level.getLevel());
		commandLog.add("Player exp set to " + level.getExp() + ", which is level " + level.getLevel());
	}
}
