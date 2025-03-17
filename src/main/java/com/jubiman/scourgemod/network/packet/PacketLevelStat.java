package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.stat.Stat;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.server.Server;
import necesse.engine.network.server.ServerClient;

public class PacketLevelStat extends Packet {
	public final Stat.StatType stat;

	public PacketLevelStat(Stat.StatType stat) {
		this.stat = stat;

		PacketWriter writer = new PacketWriter(this);
		writer.putNextEnum(this.stat);
	}

	public PacketLevelStat(byte[] data) {
		super(data);
		this.stat = new PacketReader(this).getNextEnum(Stat.StatType.class);
	}

	@Override
	public void processServer(NetworkPacket packet, Server server, ServerClient client) {
		ScourgePlayer scourgePlayer = ScourgePlayersHandler.getPlayer(client.authentication);
		if (scourgePlayer.levelStat(stat)) {
			client.sendPacket(new PacketSyncStat(scourgePlayer.getStat(stat)));
		}
	}
}
