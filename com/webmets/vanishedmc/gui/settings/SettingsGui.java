package com.webmets.vanishedmc.gui.settings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class SettingsGui extends GuiScreen{

	/** 
	 * The main GUI for all settings, currently does open with RSHIFT but does not have functionality
	 * 
	 * */
	
	private FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	
	public SettingsGui(){
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
