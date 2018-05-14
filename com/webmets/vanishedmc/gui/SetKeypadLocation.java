package com.webmets.vanishedmc.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.GuiHudKeypadModule;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SetKeypadLocation extends GuiScreen {

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		((GuiIngameHook) Minecraft.getMinecraft().ingameGUI).setKeypadLocation(mouseX, mouseY);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		((GuiIngameHook) Minecraft.getMinecraft().ingameGUI).setKeypadLocation(mouseX, mouseY);
	}

	protected void keyTyped(char c, int i) throws IOException {
		super.keyTyped(c, i);
		GuiIngameHook hook = (GuiIngameHook) Minecraft.getMinecraft().ingameGUI;
		if (i == 200) {
			hook.setKeypadLocation(hook.getKeyPadX(), hook.getKeyPadY() - 1);
		} else if (i == 203) {
			hook.setKeypadLocation(hook.getKeyPadX() - 1, hook.getKeyPadY());
		} else if (i == 208) {
			hook.setKeypadLocation(hook.getKeyPadX(), hook.getKeyPadY() + 1);
		} else if (i == 205) {
			hook.setKeypadLocation(hook.getKeyPadX() + 1, hook.getKeyPadY());
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawRect(0, 0, Display.getWidth(), Display.getHeight(), 0x50ffffff);
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

}
