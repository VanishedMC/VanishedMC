package com.webmets.vanishedmc.gui.settings.menu;

import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;

public class MenuHud extends Menu{

	@Override
	public void initGui() {
		super.initGui();
		((ToggleButton)buttonList.get(0)).setToggled(true);
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawString(fontRendererObj, "Hud", 140, 35, -1);
	}
}
