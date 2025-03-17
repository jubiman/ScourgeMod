package com.jubiman.scourgemod.patch;

import com.jubiman.scourgemod.Textures;
import com.jubiman.scourgemod.ui.form.ScourgeFormManager;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.MainGameFormManager;
import necesse.gfx.forms.components.FormContentIconButton;
import necesse.gfx.forms.components.FormInputSize;
import necesse.gfx.ui.ButtonColor;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = MainGameFormManager.class, name = "setup", arguments = {})
public class AddStatFormButtonPatch {
	@Advice.OnMethodExit
	static void onExit(@Advice.This MainGameFormManager self, @Advice.FieldValue("client") Client client) {
		setup(self, client);
	}

	public static void setup(MainGameFormManager self, Client client) {
		FormContentIconButton button = new FormContentIconButton(0, 0,
				FormInputSize.SIZE_32,
				ButtonColor.BASE,
				Textures.UI.stat_icon,
				new LocalMessage("ui", "stat_form_button"));
		button.onClicked(e -> {
			if (self.hasComponent(ScourgeFormManager.statForm)) {
				self.removeComponent(ScourgeFormManager.statForm);
			} else {
				self.addComponent(ScourgeFormManager.statForm);
				ScourgeFormManager.statForm.open(client);
			}
		});

		ScourgeFormManager.statForm.setFormManager(self);

		self.rightQuickbar.addButton(button);
		self.rightQuickbar.updateButtons(true);
	}
}
