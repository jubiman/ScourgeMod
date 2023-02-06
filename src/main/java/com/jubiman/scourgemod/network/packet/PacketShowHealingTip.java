package com.jubiman.scourgemod.network.packet;

import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;
import necesse.engine.util.GameRandom;
import necesse.gfx.gameFont.FontOptions;
import necesse.level.maps.hudManager.floatText.DmgText;

import java.awt.*;

// TODO: generalize?
public class PacketShowHealingTip extends Packet {
	public final int healing;
	public final int size;
	public final int x;
	public final int y;

	public PacketShowHealingTip(byte[] data) {
		super(data);
		PacketReader reader = new PacketReader(this);
		this.healing = reader.getNextInt();
		this.size = reader.getNextInt();
		this.x = reader.getNextInt();
		this.y = reader.getNextInt();
	}

	public PacketShowHealingTip(int x, int y, int healing, int size) {
		PacketWriter writer = new PacketWriter(this);
		this.healing = healing;
		this.size = size;
		this.x = x;
		this.y = y;

		writer.putNextInt(this.healing);
		writer.putNextInt(this.size);
		writer.putNextInt(this.x);
		writer.putNextInt(this.y);
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		// TODO: make setting to disable?
		client.getLevel().hudManager.addElement(new DmgText(x, y, healing, new FontOptions(size)
				.outline().color(Color.GREEN), GameRandom.globalRandom.getIntBetween(25, 45)));
	}
}
