package com.webmets.vanishedmc.gui;

import com.webmets.vanishedmc.VanishedMC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class GuiIngameHook extends GuiIngame {

	/**
	 * The main GUI shown in game, preparing and rendering all the modules
	 * */
	
	private Minecraft mc;
	private FontRenderer fr;
	private VanishedMC client = VanishedMC.instance;
	
	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
		mc = Minecraft.getMinecraft();
		fr = mc.fontRendererObj;
	}

	@Override
	public void func_175180_a(float p_175180_1_) {
		super.func_175180_a(p_175180_1_);
		client.getHudModule().render(2, 3);
	}

}
