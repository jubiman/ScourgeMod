package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.stat.Stat;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;

public class PacketSyncStat extends Packet {
	public final Stat.StatType stat;
	public final int level;

	public PacketSyncStat(byte[] data) {
		super(data);

		PacketReader reader = new PacketReader(this);
		this.stat = reader.getNextEnum(Stat.StatType.class);
		this.level = reader.getNextInt();
	}

	public PacketSyncStat(Stat stat) {
		this.stat = stat.getStatType();
		this.level = stat.getLevel();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextEnum(this.stat);
		writer.putNextInt(this.level);
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgeClient scourgeClient = ScourgePlayersHandler.getClient();
		scourgeClient.setStatLevel(stat, level);
	}
}
