package com.jubiman.scourgemod.ui.form;

import necesse.gfx.forms.FormManager;

public class ScourgeFormManager extends FormManager {
	private static StatForm statForm;

	/**
	 * Create a new StatForm. Needed because MainGameFormManager is recreated on every game world load.
	 * @return A new StatForm
	 */
	public static StatForm newStatForm() {
		return statForm = new StatForm();
	}

	/**
	 * Get the StatForm. If it doesn't exist, create a new one. Shouldn't be used before the game world is loaded, but just to be safe.
	 * @return The StatForm
	 */
	public static StatForm getStatForm() {
		return statForm == null ? newStatForm() : statForm;
	}
}
