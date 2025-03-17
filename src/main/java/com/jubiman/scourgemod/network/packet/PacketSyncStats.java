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

public class PacketSyncStats extends Packet {
	public final int charisma;
	public final int dexterity;
	public final int intelligence;
	public final int strength;
	public final int vitality;

	public PacketSyncStats(ScourgeClient player) {
		this.charisma = player.charisma.getLevel();
		this.dexterity = player.dexterity.getLevel();
		this.intelligence = player.intelligence.getLevel();
		this.strength = player.strength.getLevel();
		this.vitality = player.vitality.getLevel();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextInt(charisma);
		writer.putNextInt(dexterity);
		writer.putNextInt(intelligence);
		writer.putNextInt(strength);
		writer.putNextInt(vitality);
	}

	public PacketSyncStats(ScourgePlayer player) {
		this.charisma = player.getCharisma();
		this.dexterity = player.getDexterity();
		this.intelligence = player.getIntelligence();
		this.strength = player.getStrength();
		this.vitality = player.getVitality();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextInt(charisma);
		writer.putNextInt(dexterity);
		writer.putNextInt(intelligence);
		writer.putNextInt(strength);
		writer.putNextInt(vitality);
	}

	public PacketSyncStats(byte[] data) {
		super(data);
		PacketReader reader = new PacketReader(this);
		this.charisma = reader.getNextInt();
		this.dexterity = reader.getNextInt();
		this.intelligence = reader.getNextInt();
		this.strength = reader.getNextInt();
		this.vitality = reader.getNextInt();
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgeClient scourgeClient = ScourgePlayersHandler.getClient();
		scourgeClient.charisma.setLevel(charisma);
		scourgeClient.dexterity.setLevel(dexterity);
		scourgeClient.intelligence.setLevel(intelligence);
		scourgeClient.strength.setLevel(strength);
		scourgeClient.vitality.setLevel(vitality);
	}

	@Override
	public void processServer(NetworkPacket packet, Server server, ServerClient client) {
		ScourgePlayer scourgePlayer = ScourgePlayersHandler.getPlayer(client.authentication);
		// Calculate the total amount of skill points used and check if we have enough skill points to use
		int totalStatPointsUsed = charisma + dexterity + intelligence + strength + vitality;
		totalStatPointsUsed -= scourgePlayer.getCharisma() + scourgePlayer.getDexterity() + scourgePlayer.getIntelligence() + scourgePlayer.getStrength() + scourgePlayer.getVitality();
		if (totalStatPointsUsed <= scourgePlayer.getStatPoints()) {
			scourgePlayer.setCharisma(charisma);
			scourgePlayer.setDexterity(dexterity);
			scourgePlayer.setIntelligence(intelligence);
			scourgePlayer.setStrength(strength);
			scourgePlayer.setVitality(vitality);
			scourgePlayer.setStatPoints(scourgePlayer.getStatPoints() - totalStatPointsUsed);
		}
		client.sendPacket(new PacketSyncStats(scourgePlayer));
	}
}
