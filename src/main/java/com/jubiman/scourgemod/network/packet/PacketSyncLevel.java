package com.jubiman.scourgemod.network.packet;

import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.ui.LevelFloatTextFade;
import com.jubiman.scourgemod.util.Logger;
import necesse.engine.network.NetworkPacket;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.client.Client;
import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameResources;

public class PacketSyncLevel extends Packet {
	public final long exp;
	public final long threshold;
	public final int statPoints;

	public PacketSyncLevel(byte[] data) {
		super(data);
		PacketReader reader = new PacketReader(this);
		this.exp = reader.getNextLong();
		this.threshold = reader.getNextLong();
		this.statPoints = reader.getNextInt();
	}

	public PacketSyncLevel(ScourgePlayer player) {
		this.exp = player.getPlayerLevel().getExp();
		this.threshold = player.getPlayerLevel().getThreshold();
		this.statPoints = player.getStatPoints();

		PacketWriter writer = new PacketWriter(this);
		writer.putNextLong(exp);
		writer.putNextLong(threshold);
		writer.putNextInt(statPoints);
	}

	@Override
	public void processClient(NetworkPacket packet, Client client) {
		ScourgeClient scourgeClient = ScourgePlayersHandler.getClient();
		long expGain = exp - scourgeClient.level.getExp();
		Logger.info("[PacketSyncLevel.processClient] Gained %d exp", expGain);


		// Add exp float text
		PlayerMob playerMob = client.getPlayer();
		LevelFloatTextFade levelFloatTextFade = new LevelFloatTextFade(playerMob.getX(), playerMob.getY(), expGain);
		client.getLevel().hudManager.addElement(levelFloatTextFade);
		if (exp > scourgeClient.level.getThreshold()) {
			Logger.info("[PacketSyncLevel.processClient] Level up!");
			levelFloatTextFade = new LevelFloatTextFade(playerMob.getX(), playerMob.getY());
			client.getLevel().hudManager.addElement(levelFloatTextFade);
			SoundManager.playSound(GameResources.jingle, SoundEffect.effect(playerMob));
		}

		// Update client level, used for rendering
		scourgeClient.level.setThreshold(threshold);
		scourgeClient.level.setExp(exp);
		scourgeClient.setStatPoints(statPoints);
	}
}
