package com.webmets.vanishedmc.modules;

import org.lwjgl.input.Keyboard;

import com.webmets.vanishedmc.gui.settings.SettingsGui;

import net.minecraft.client.Minecraft;

public class GuiModule extends Module{

	/**
	 * The module to open com.webmets.vanishedmc.gui.SettingsGui
	 * */
	
	public GuiModule(){
		super("gui", Keyboard.KEY_RSHIFT);
	}
	
	@Override
	public void tick() {
		
	}

	/**
	 * Module to open the main GUI if set keybind is pressed (default to right shift)
	 * */
	@Override
	public void onEnable() {
		this.mc.displayGuiScreen(new SettingsGui());
		disable();
	}

	@Override
	public void onDisable() {
		
	}
}
