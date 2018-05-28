package com.webmets.vanishedmc.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.gui.settings.SettingsGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiIngameHook extends GuiIngame {

	/**
	 * The main GUI shown in game, preparing and rendering all the modules
	 */

	private Minecraft mc;
	private FontRenderer fr;
	private VanishedMC client = VanishedMC.instance;

	private int keyPadX = Display.getWidth() / 2 - 112, keyPadY = Display.getHeight() / 2 - 122;

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
		mc = Minecraft.getMinecraft();
		fr = mc.fontRendererObj;
	}

	@Override
	public void func_175180_a(float p_175180_1_) {
		super.func_175180_a(p_175180_1_);
		if (mc.currentScreen instanceof SettingsGui || mc.currentScreen instanceof Menu) {
			return;
		}

		client.getHudModule().render(2, 3);
		client.getKeypadModule().render(keyPadX, keyPadY);
		client.getArmorModule().render();
	}

	public void setKeypadLocation(int x, int y) {
		this.keyPadX = x;
		this.keyPadY = y;
	}

	public void setKeyPadX(int keyPadX) {
		this.keyPadX = keyPadX;
	}

	public void setKeyPadY(int keyPadY) {
		this.keyPadY = keyPadY;
	}

	public int getKeyPadX() {
		return keyPadX;
	}

	public int getKeyPadY() {
		return keyPadY;
	}
}
