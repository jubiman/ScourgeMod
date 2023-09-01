package com.jubiman.scourgemod.command;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.playerclass.PlayerClass;
import com.jubiman.scourgemod.player.playerclass.hunter.AssassinClass;
import com.jubiman.scourgemod.player.playerclass.hunter.HunterClass;
import com.jubiman.scourgemod.player.playerclass.hunter.RangerClass;
import com.jubiman.scourgemod.player.playerclass.mage.MageClass;
import com.jubiman.scourgemod.player.playerclass.mage.SummonerClass;
import com.jubiman.scourgemod.player.playerclass.mage.WarlockClass;
import com.jubiman.scourgemod.player.playerclass.tank.GuardianClass;
import com.jubiman.scourgemod.player.playerclass.tank.TankClass;
import com.jubiman.scourgemod.player.playerclass.warrior.BerserkerClass;
import com.jubiman.scourgemod.player.playerclass.warrior.PaladinClass;
import com.jubiman.scourgemod.player.playerclass.warrior.WarriorClass;
import necesse.engine.commands.CmdParameter;
import necesse.engine.commands.CommandLog;
import necesse.engine.commands.ModularChatCommand;
import necesse.engine.commands.PermissionLevel;
import necesse.engine.commands.parameterHandlers.ServerClientParameterHandler;
import necesse.engine.commands.parameterHandlers.StringParameterHandler;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameColor;

import java.lang.reflect.InvocationTargetException;

public class ScourgeClassCommand extends ModularChatCommand {
	public ScourgeClassCommand() {
		super("scourgeclass", "Change someone's class", PermissionLevel.MODERATOR, true, new CmdParameter("set|get", new StringParameterHandler()), new CmdParameter("player", new ServerClientParameterHandler(true, false), true), new CmdParameter("class", new StringParameterHandler(), true));
	}

	@Override
	public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] strings, CommandLog commandLog) {
		PlayerMob playerMob = ((ServerClient) args[1]).playerMob;
		ScourgePlayer player = ScourgePlayersHandler.getPlayer(((ServerClient) args[1]).authentication);
		if (player == null) {
			commandLog.add(GameColor.RED + "Player not found");
			return;
		}
		switch ((String) args[0]) {
			case "set": {
				try {
					player.setPlayerClass((PlayerClass) Class.forName("com.jubiman.scourgemod.player.playerclass."
							+ args[2] + "Class").getConstructor(ScourgePlayer.class).newInstance(player), playerMob);
					commandLog.add("Set class to " + player.getPlayerClass().getDisplayName());
					return;
				} catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
						 IllegalAccessException | NoSuchMethodException e) {
					System.out.println("Class not found, reverting to old switch case.");
				}
				switch ((String) args[2]) {
					case "empty": {
						player.setPlayerClass(null, playerMob);
						break;
					}
					case "hunter": {
						player.setPlayerClass(new HunterClass(player), playerMob);
						break;
					}
					case "assassin": {
						player.setPlayerClass(new AssassinClass(player), playerMob);
						break;
					}
					case "ranger": {
						player.setPlayerClass(new RangerClass(player), playerMob);
						break;
					}
					case "mage": {
						player.setPlayerClass(new MageClass(player), playerMob);
						break;
					}
					case "summoner": {
						player.setPlayerClass(new SummonerClass(player), playerMob);
						break;
					}
					case "warlock": {
						player.setPlayerClass(new WarlockClass(player), playerMob);
						break;
					}
					case "tank": {
						player.setPlayerClass(new TankClass(player), playerMob);
						break;
					}
					case "guardian": {
						player.setPlayerClass(new GuardianClass(player), playerMob);
						break;
					}
					case "warrior": {
						player.setPlayerClass(new WarriorClass(player), playerMob);
						break;
					}
					case "berserker": {
						player.setPlayerClass(new BerserkerClass(player), playerMob);
						break;
					}
					case "paladin": {
						player.setPlayerClass(new PaladinClass(player), playerMob);
						break;
					}
					default:
						commandLog.add("Invalid argument.");
						return;
				}
				commandLog.add("Set class to " + player.getPlayerClass().getDisplayName());
				break;
			}
			case "get": {
				commandLog.add("Current class of " + ((ServerClient) args[1]).getName() + ": " + player.getClassName());
				break;
			}
		}
	}
}
