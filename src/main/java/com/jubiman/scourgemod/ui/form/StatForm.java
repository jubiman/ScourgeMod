package com.jubiman.scourgemod.ui.form;

import com.jubiman.scourgemod.network.packet.PacketSyncStats;
import com.jubiman.scourgemod.player.ScourgeClient;
import com.jubiman.scourgemod.player.ScourgePlayersHandler;
import com.jubiman.scourgemod.player.stat.Stat;
import com.jubiman.scourgemod.util.Logger;
import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.input.Control;
import necesse.engine.input.InputEvent;
import necesse.engine.localization.Localization;
import necesse.engine.network.client.Client;
import necesse.engine.window.GameWindow;

import necesse.engine.window.WindowManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.FormManager;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.FormTextButton;

public class StatForm extends Form {
	private FormManager formManager;
	private final FormApplyTextButton vitality;
	private final FormTextButton vitalityPlus;
	private final FormTextButton vitalityMinus;
	private final FormApplyTextButton strength;
	private final FormTextButton strengthPlus;
	private final FormTextButton strengthMinus;
	private final FormApplyTextButton intelligence;
	private final FormTextButton intelligencePlus;
	private final FormTextButton intelligenceMinus;
	private final FormApplyTextButton charisma;
	private final FormTextButton charismaPlus;
	private final FormTextButton charismaMinus;
	private final FormApplyTextButton dexterity;
	private final FormTextButton dexterityPlus;
	private final FormTextButton dexterityMinus;
	private final FormApplyTextButton statPoints;

	private final FormTextButton experience;
	private final FormTextButton level;

	private final FormTextButton applyButton;

	private Client client;
	private final ScourgeClient player;

	private boolean isShiftDown = false;

	public StatForm() {
		super("stat_form", 380, 290);
		final GameWindow windowManager = WindowManager.getWindow();
		setPosition(windowManager.getHudWidth() / 2 - getWidth() / 2, windowManager.getHudHeight() / 2 - getHeight() / 2);

		this.player = ScourgePlayersHandler.getClient();

		this.vitality = new FormApplyTextButton(player.vitality.getLevel(), Localization.translate("buff", "scourge_vitality"), Localization.translate("ui", "vitality_tip"), 10, 10, 270);
		this.strength = new FormApplyTextButton(player.strength.getLevel(), Localization.translate("buff", "scourge_strength"), Localization.translate("ui", "strength_tip"), 10, 50, 270);
		this.intelligence = new FormApplyTextButton(player.intelligence.getLevel(), Localization.translate("buff", "scourge_intelligence"), Localization.translate("ui", "intelligence_tip"), 10, 90, 270);
		this.charisma = new FormApplyTextButton(player.charisma.getLevel(), Localization.translate("buff", "scourge_charisma"), Localization.translate("ui", "charisma_tip"), 10, 130, 270);
		this.dexterity = new FormApplyTextButton(player.dexterity.getLevel(), Localization.translate("buff", "scourge_dexterity"), Localization.translate("ui", "dexterity_tip"), 10, 170, 270);
		this.statPoints = new FormApplyTextButton(player.getStatPoints(), "statpoints", Localization.translate("ui", "statpoints_tip"), 10, 210, 200);

		// Text is set in updateText()
		this.experience = new FormTextButton("", null, 120, 250, 250);
		this.level = new FormTextButton("", null, 10, 250, 100);

		// Plus buttons
		{
			this.vitalityPlus = new FormTextButton("+", Localization.translate("ui", "level_up"), 330, 10, 40);
			this.vitalityPlus.onClicked(event -> {
				levelStat(Stat.StatType.VITALITY);
			});
			this.strengthPlus = new FormTextButton("+", Localization.translate("ui", "level_up"), 330, 50, 40);
			this.strengthPlus.onClicked(event -> {
				levelStat(Stat.StatType.STRENGTH);
			});
			this.intelligencePlus = new FormTextButton("+", Localization.translate("ui", "level_up"), 330, 90, 40);
			this.intelligencePlus.onClicked(event -> {
				levelStat(Stat.StatType.INTELLIGENCE);
			});
			this.charismaPlus = new FormTextButton("+", Localization.translate("ui", "level_up"), 330, 130, 40);
			this.charismaPlus.onClicked(event -> {
				levelStat(Stat.StatType.CHARISMA);
			});
			this.dexterityPlus = new FormTextButton("+", Localization.translate("ui", "level_up"), 330, 170, 40);
			this.dexterityPlus.onClicked(event -> {
				levelStat(Stat.StatType.DEXTERITY);
			});
		}
		// Minus buttons
		{
			this.vitalityMinus = new FormTextButton("-", Localization.translate("ui", "level_down"), 290, 10, 40);
		this.vitalityMinus.onClicked(event -> {
			removeStat(Stat.StatType.VITALITY);
		});
		this.strengthMinus = new FormTextButton("-", Localization.translate("ui", "level_down"), 290, 50, 40);
		this.strengthMinus.onClicked(event -> {
			removeStat(Stat.StatType.STRENGTH);
		});
		this.intelligenceMinus = new FormTextButton("-", Localization.translate("ui", "level_down"), 290, 90, 40);
		this.intelligenceMinus.onClicked(event -> {
			removeStat(Stat.StatType.INTELLIGENCE);
		});
		this.charismaMinus = new FormTextButton("-", Localization.translate("ui", "level_down"), 290, 130, 40);
		this.charismaMinus.onClicked(event -> {
			removeStat(Stat.StatType.CHARISMA);
		});
		this.dexterityMinus = new FormTextButton("-", Localization.translate("ui", "level_down"), 290, 170, 40);
		this.dexterityMinus.onClicked(event -> {
			removeStat(Stat.StatType.DEXTERITY);
		});
		}
		// Apply button
		this.applyButton = new FormTextButton(Localization.translate("ui", "apply"), Localization.translate("ui", "apply_changes"), 220, 210, 150);
		this.applyButton.onClicked(event -> {
			this.client.network.sendPacket(new PacketSyncStats(this.player));
			updateText();
		});
	}

	private void levelStat(Stat.StatType statType) {
		int sp = this.player.getStatPoints();
		int amount = isShiftDown ? Math.min(5, sp) : 1;
		if (sp >= amount) {
			this.player.levelStat(statType, amount);
			this.statPoints.setValue(this.player.getStatPoints()); // refresh stat points
			componentFromStatType(statType).setValue(this.player.getStat(statType).getLevel());
		}
	}

	private void removeStat(Stat.StatType statType) {
		FormApplyTextButton component = componentFromStatType(statType);
		if (this.player.getStat(statType).getLevel() == component.getInitialValue()) {
			return;
		}
		int amount = isShiftDown ? Math.min(5, this.player.getStat(statType).getLevel() - component.getInitialValue()) : 1;
		this.player.removeStat(statType, amount);
		this.statPoints.setValue(this.player.getStatPoints());
		component.setValue(this.player.getStat(statType).getLevel());
	}

	@Override
	public void handleInputEvent(InputEvent event, TickManager tickManager, PlayerMob perspective) {
		super.handleInputEvent(event, tickManager, perspective);
		if (event.isKeyboardEvent()) {
			if (event.getID() == 256 // KEY_ESCAPE
				|| event.getID() == Control.INVENTORY.getKey()) {
				this.formManager.removeComponent(this);
			} else if (event.getID() == 340) { // KEY_SHIFT
				this.isShiftDown = event.state;
			}
		}
	}

	public void updateText() {
		this.vitality.reset(this.player.vitality.getLevel());
		this.strength.reset(this.player.strength.getLevel());
		this.intelligence.reset(this.player.intelligence.getLevel());
		this.charisma.reset(this.player.charisma.getLevel());
		this.dexterity.reset(this.player.dexterity.getLevel());
		this.statPoints.reset(this.player.getStatPoints());

		long previousThreshold = this.player.level.previousThreshold();
		Logger.debug("Previous threshold: %d, current threshold: %d", previousThreshold, this.player.level.getThreshold());
		this.experience.setText(Localization.translate("ui", "experience", "value", player.level.getExp() - previousThreshold, "next", player.level.getThreshold() - previousThreshold));
		this.level.setText(Localization.translate("ui", "level", "value", player.level.getLevel()));
	}

	public void open(Client client) {
		this.client = client;

		// Set text
		updateText();

		addComponent(this.vitality);
		addComponent(this.vitalityPlus);
		addComponent(this.vitalityMinus);

		addComponent(this.strength);
		addComponent(this.strengthPlus);
		addComponent(this.strengthMinus);

		addComponent(this.intelligence);
		addComponent(this.intelligencePlus);
		addComponent(this.intelligenceMinus);

		addComponent(this.charisma);
		addComponent(this.charismaPlus);
		addComponent(this.charismaMinus);

		addComponent(this.dexterity);
		addComponent(this.dexterityPlus);
		addComponent(this.dexterityMinus);

		addComponent(this.applyButton);

		addComponent(this.statPoints);
		addComponent(this.experience);
		addComponent(this.level);
	}

	private FormApplyTextButton componentFromStatType(Stat.StatType statType) {
		switch (statType) {
			case VITALITY:
				return this.vitality;
			case STRENGTH:
				return this.strength;
			case INTELLIGENCE:
				return this.intelligence;
			case CHARISMA:
				return this.charisma;
			case DEXTERITY:
				return this.dexterity;
		}
		return null;
	}

	public void setFormManager(MainGameFormManager self) {
		if (this.formManager == null) {
			this.formManager = self;
		}
	}
}
