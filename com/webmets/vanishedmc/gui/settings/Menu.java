package com.webmets.vanishedmc.gui.settings;

import java.awt.Color;
import java.io.IOException;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.menu.MenuArmorHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuKeypad;
import com.webmets.vanishedmc.gui.settings.menu.MenuModules;
import com.webmets.vanishedmc.gui.settings.menu.MenuPotionHud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class Menu extends GuiScreen {

	protected VanishedMC client = VanishedMC.instance;
	private boolean rendering = false;
	
	@Override
	public void initGui() {
		ToggleButton hud = new ToggleButton(0, 30, 30, 100, 20, "Hud");
		ToggleButton keypad = new ToggleButton(1, 30, 55, 100, 20, "Keypad");
		ToggleButton armorhud = new ToggleButton(2, 30, 80, 100, 20, "Armor Hud");
		ToggleButton potionhud = new ToggleButton(3, 30, 105, 100, 20, "Potion Hud");
		ToggleButton effects = new ToggleButton(4, 30, 130, 100, 20, "Effects");
		ToggleButton modules = new ToggleButton(5, 30, 155, 100, 20, "Modules");
		hud.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuHud());
			}
		});
		keypad.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuKeypad());
			}
		});
		armorhud.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuArmorHud());
			}
		});
		potionhud.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuPotionHud());
			}
		});
		effects.addAction(new ButtonAction() {
			@Override
			public void execute() {
			}
		});
		modules.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuModules());
			}
		});
		this.buttonList.add(hud);
		this.buttonList.add(keypad);
		this.buttonList.add(armorhud);
		this.buttonList.add(potionhud);
		this.buttonList.add(effects);
		this.buttonList.add(modules);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if (mc.theWorld == null) {
			this.drawDefaultBackground();
		}
		if(!rendering) {
			rendering = true;
			this.drawGradientRect(25, 25, this.width-25, this.height-25, Color.red.getRGB(), 16777215);
			this.drawGradientRect(25, 25, this.width-25, this.height-25, 0, Integer.MIN_VALUE);
			rendering = false;
		}
		drawBorderedRect(23, 23, width-23, height-23, 2, 0xf0000000, 0);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		client.getFileManager().saveSettings();
	}
	
	protected void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
		drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
		drawRect(x + size, y + size, x1, y, borderC);
		drawRect(x, y, x + size, y1, borderC);
		drawRect(x1, y1, x1 - size, y + size, borderC);
		drawRect(x, y1 - size, x1, y1, borderC);
	}

}
