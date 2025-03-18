package com.jubiman.scourgemod.buff.stat;

import com.jubiman.scourgemod.buff.ScourgePassiveBuff;
import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayer;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameTexture.GameTexture;

public abstract class StatBuff extends ScourgePassiveBuff {
	public StatBuff() {
		super();
		this.isVisible = true;
	}

	@Override
	public void drawIcon(int x, int y, ActiveBuff buff) {
		GameTexture drawIcon = this.getDrawIcon(buff);
		drawIcon.initDraw().size(32, 32).draw(x, y);
		String text;
		int width;
		if (buff.owner instanceof PlayerMob) {
			PlayerMob playerMob = (PlayerMob) buff.owner;
			if (!playerMob.isClientClient())
				return;

			ScourgeClient player = ScourgePlayersHandler.getClient();

			text = Integer.toString(player.getStatFromBuffID(buff.buff.getStringID()));
			width = FontManager.bit.getWidthCeil(text, durationFontOptions);
			FontManager.bit.drawString((float) (x + 28 - width), (float) (y + 30 - FontManager.bit.getHeightCeil(text, durationFontOptions)), text, durationFontOptions);
		}
	}

	@Override
	public int getStackSize() {
		return 999;
	}
}
