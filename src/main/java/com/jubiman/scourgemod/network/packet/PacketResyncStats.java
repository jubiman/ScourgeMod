package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class PacketResyncStats extends Packet {
	public final int strength;
	public final int vitality;
	public final int dexterity;
	public final int intelligence;
	public final int charisma;
	public final int statPoints;
	public final long exp;

	public PacketResyncStats() {
		super();
		this.strength = 0;
		this.vitality = 0;
		this.dexterity = 0;
		this.intelligence = 0;
		this.charisma = 0;
		this.statPoints = -1;
		this.exp = -1;
	}

	public PacketResyncStats(ScourgePlayer scourgePlayer) {
		super();

		this.strength = scourgePlayer.getStrength();
		this.vitality = scourgePlayer.getVitality();
		this.dexterity = scourgePlayer.getDexterity();
		this.intelligence = scourgePlayer.getIntelligence();
		this.charisma = scourgePlayer.getCharisma();
		this.statPoints = scourgePlayer.getStatPoints();
		this.exp = scourgePlayer.getPlayerLevel().getExp();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextInt(strength);
		writer.putNextInt(vitality);
		writer.putNextInt(dexterity);
		writer.putNextInt(intelligence);
		writer.putNextInt(charisma);
		writer.putNextInt(statPoints);
		writer.putNextLong(exp);
	}

	public PacketResyncStats(byte[] data) {
		super(data);

		PacketReader reader = new PacketReader(this);
		this.strength = reader.getNextInt();
		this.vitality = reader.getNextInt();
		this.dexterity = reader.getNextInt();
		this.intelligence = reader.getNextInt();
		this.charisma = reader.getNextInt();
		this.statPoints = reader.getNextInt();
		this.exp = reader.getNextLong();
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgeClient scourgeClient = ScourgePlayersHandler.getClient();
		scourgeClient.strength.setLevel(strength);
		scourgeClient.vitality.setLevel(vitality);
		scourgeClient.dexterity.setLevel(dexterity);
		scourgeClient.intelligence.setLevel(intelligence);
		scourgeClient.charisma.setLevel(charisma);
		scourgeClient.setStatPoints(statPoints);
		scourgeClient.level.setExp(exp);
	}

	@Override
	public void processServer(NetworkPacket packet, Server server, ServerClient client) {
		ScourgePlayer scourgePlayer = ScourgePlayersHandler.getPlayer(client.authentication);
		client.sendPacket(new PacketResyncStats(scourgePlayer));
	}
}
