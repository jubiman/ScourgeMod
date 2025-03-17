package com.jubiman.scourgemod.ui.form;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.GameResources;
import necesse.gfx.forms.components.FormTextButton;
import necesse.gfx.gameFont.FontManager;
import necesse.gfx.gameFont.FontOptions;
import necesse.gfx.shader.FormShader;
import necesse.gfx.ui.ButtonState;

import java.awt.*;

/**
 * A simple wrapper around FormTextButton that takes an integer default value and updates the text to show the change
 * when the level is changed.
 * <p>
 *     Example:
 *     	"Vitality: 5 -> 6"
 *     	"Skill Points: 10 -> 5"
 *     Where the initial text is "Vitality" and "Skill Points" respectively.
 *     The initial value is 5 and 10 respectively.
 */
public class FormApplyTextButton extends FormTextButton {
	private final String initialText;
	private int initialValue;
	public FormApplyTextButton(int initialValue, String text, String tooltip, int x, int y, int width) {
		super(text, tooltip, x, y, width);
		this.initialValue = initialValue;
		this.initialText = text;
	}

	public void setValue(int value) {
		if (value == initialValue) setText(initialText + ": " + value);
		else setText(initialText + ": " + initialValue + " -> " + value);
	}

	public void reset(int newInitialValue) {
		this.initialValue = newInitialValue;
		setText(initialText + ": " + newInitialValue);
	}

	/**
	 * Draws the button with the given tickManager, perspective, and renderBox. Overridden to draw text left-aligned.
	 * @param tickManager The tickManager.
	 * @param perspective The player perspective.
	 * @param renderBox The renderBox.
	 */
	@Override
	public void draw(TickManager tickManager, PlayerMob perspective, Rectangle renderBox) {
		Color drawCol = this.getDrawColor();
		ButtonState state = this.getButtonState();
		int textOffset = 0;
		boolean useDownTexture = this.isDown() && this.isHovering();
		if (useDownTexture) {
			this.size.getButtonDownDrawOptions(this.color, state, this.getX(), this.getY(), this.getWidth(), drawCol).draw();
			textOffset = this.size.buttonDownContentDrawOffset;
		} else {
			this.size.getButtonDrawOptions(this.color, state, this.getX(), this.getY(), this.getWidth(), drawCol).draw();
		}

		Rectangle contentRect = this.size.getContentRectangle(this.getWidth());
		FormShader.FormShaderState textState = GameResources.formShader.startState(new Point(this.getX(), this.getY()), new Rectangle(contentRect.x, contentRect.y, contentRect.width, contentRect.height));

		try {
			FontOptions fontOptions = this.size.getFontOptions().color(this.getTextColor());
			String drawText = this.getDrawText();
//			int drawX = this.width / 2 - FontManager.bit.getWidthCeil(drawText, fontOptions) / 2;
			// left align
			int drawX = 10;
			FontManager.bit.drawString((float)drawX, (float)(textOffset + this.size.fontDrawOffset), drawText, fontOptions);
		} finally {
			textState.end();
		}

		if (useDownTexture) {
			this.size.getButtonDownEdgeDrawOptions(this.color, state, this.getX(), this.getY(), this.getWidth(), drawCol).draw();
		} else {
			this.size.getButtonEdgeDrawOptions(this.color, state, this.getX(), this.getY(), this.getWidth(), drawCol).draw();
		}

		if (this.isHovering()) {
			this.addTooltips(perspective);
		}
	}

	public int getInitialValue() {
		return initialValue;
	}
}
