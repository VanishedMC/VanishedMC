package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;

import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.SelectorButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.utils.effects.EffectUtils;

import net.minecraft.client.gui.GuiButton;

public class MenuEffect extends Menu {

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(4)).setToggled(true);
		EffectUtils hudEffect = client.getHudModule().getEffectUtils();
		EffectUtils keypadEffect = client.getKeypadModule().getEffectUtils();
		
		// Initialize buttons
		ToggleButton hudButton = new ToggleButton(0, 140, 30, 100, 20, "Hud");
		ToggleButton keypadButton = new ToggleButton(0, 140, 55, 100, 20, "Keypad");
		
		// Actions
		hudButton.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new MenuEffectSettings(hudEffect));
			}
		});
		
		keypadButton.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mc.displayGuiScreen(new MenuEffectSettings(keypadEffect));
			}
		});
		
		// Adding to list
		buttonList.add(hudButton);
		buttonList.add(keypadButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
		if (button instanceof SelectorButton) {
			((SelectorButton) button).press();
		}
	}
}
