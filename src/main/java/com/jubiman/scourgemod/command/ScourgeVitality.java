package com.jubiman.scourgemod.command;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayers;
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

public class ScourgeVitality extends ModularChatCommand {
	public ScourgeVitality() {
		super("scourgevitality", "Change someone's vitality", PermissionLevel.MODERATOR, true, new CmdParameter("set|add|remove", new StringParameterHandler()), new CmdParameter("player", new ServerClientParameterHandler(true, false), true), new CmdParameter("value", new IntParameterHandler(1), true));
	}

	@Override
	public void runModular(Client client, Server server, ServerClient serverClient, Object[] args, String[] strings, CommandLog commandLog) {
		ScourgePlayer player = ScourgePlayers.getPlayer(((ServerClient) args[1]).authentication);
		switch ((String) args[0]) {
			case "set":
				player.setVitality((int) args[2]);
				break;
			case "add":
				player.setVitality(player.getVitality() + (int) args[2]);
				break;
			case "remove":
				player.setVitality(player.getVitality() - (int) args[2]);
				break;
			case "get":
				commandLog.add("Vitality: " + player.getVitality());
				return;
		}
		commandLog.add("Vitality set to " + player.getVitality());

	}
}
