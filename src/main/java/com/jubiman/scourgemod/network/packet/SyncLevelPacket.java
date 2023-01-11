package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;

public class SyncLevelPacket extends Packet {
	public final long exp;
	public final long threshold;

	public SyncLevelPacket(byte[] data) {
		super(data);
		PacketReader reader = new PacketReader(this);
		this.exp = reader.getNextLong();
		this.threshold = reader.getNextLong();
	}

	public SyncLevelPacket(ScourgePlayer player) {
		this.exp = player.getPlayerLevel().getExp();
		this.threshold = player.getPlayerLevel().getThreshold();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextLong(exp);
		writer.putNextLong(threshold);
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgePlayersHandler.getPlayer(client.getClient().authentication).getPlayerLevel().setExp(exp);
		ScourgePlayersHandler.getPlayer(client.getClient().authentication).getPlayerLevel().setThreshold(threshold);
	}
}
