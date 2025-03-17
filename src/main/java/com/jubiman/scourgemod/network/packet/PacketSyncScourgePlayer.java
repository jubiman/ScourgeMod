package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;

/**
 * Gets send from server to client to sync the player's data.
 */
public class PacketSyncScourgePlayer extends Packet {
	public final long exp;
	public final int statpoints;
	public final int vitality;
	public final int strength;
	public final int dexterity;
	public final int intelligence;
	public final int charisma;

	public PacketSyncScourgePlayer(ScourgePlayer player) {
		this.exp = player.getPlayerLevel().getExp();
		this.statpoints = player.getStatPoints();
		this.vitality = player.getVitalityObject().getLevel();
		this.strength = player.getStrengthObject().getLevel();
		this.dexterity = player.getDexterityObject().getLevel();
		this.intelligence = player.getIntelligenceObject().getLevel();
		this.charisma = player.getCharismaObject().getLevel();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextLong(exp);
		writer.putNextInt(statpoints);
		writer.putNextInt(vitality);
		writer.putNextInt(strength);
		writer.putNextInt(dexterity);
		writer.putNextInt(intelligence);
		writer.putNextInt(charisma);
	}

	public PacketSyncScourgePlayer(byte[] data) {
		super(data);

		PacketReader reader = new PacketReader(this);
		this.exp = reader.getNextLong();
		this.statpoints = reader.getNextInt();
		this.vitality = reader.getNextInt();
		this.strength = reader.getNextInt();
		this.dexterity = reader.getNextInt();
		this.intelligence = reader.getNextInt();
		this.charisma = reader.getNextInt();
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgeClient scourgeClient = ScourgePlayersHandler.getClient();
		scourgeClient.level.setExp(this.exp);
		scourgeClient.setStatPoints(this.statpoints);
		scourgeClient.vitality.setLevel(this.vitality);
		scourgeClient.strength.setLevel(this.strength);
		scourgeClient.dexterity.setLevel(this.dexterity);
		scourgeClient.intelligence.setLevel(this.intelligence);
		scourgeClient.charisma.setLevel(this.charisma);
	}
}
