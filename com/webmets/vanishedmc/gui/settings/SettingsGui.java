package com.webmets.vanishedmc.gui.settings;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.menu.MenuArmorHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuEffect;
import com.webmets.vanishedmc.gui.settings.menu.MenuHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuKeypad;
import com.webmets.vanishedmc.gui.settings.menu.MenuModules;
import com.webmets.vanishedmc.gui.settings.menu.MenuPotionHud;
import com.webmets.vanishedmc.gui.settings.menu.chat.MenuChat;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class SettingsGui extends GuiScreen {

	/**
	 * The main GUI for all settings, currently does open with RSHIFT but does
	 * not have functionality
	 * 
	 */

	private FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
	private boolean loaded = false;

	private int animation1X = 10;
	private int animation1Y = 10;

	private int animation2X = 10;
	private int animation2Y = Display.getHeight() / 2 - 10;

	private int animation3X = Display.getWidth() / 2 - 10;
	private int animation3Y = 10;

	private int animation4X = Display.getWidth() / 2 - 10;
	private int animation4Y = Display.getHeight() / 2 - 10;

	private void loadButtons() {
		if(this.buttonList.size() > 0) {
			return;
		}
		ToggleButton hud = new ToggleButton(0, 30, 30, 100, 20, "Hud");
		ToggleButton keypad = new ToggleButton(1, 30, 55, 100, 20, "Keypad");
		ToggleButton armorhud = new ToggleButton(2, 30, 80, 100, 20, "Armor Hud");
		ToggleButton potionhud = new ToggleButton(3, 30, 105, 100, 20, "Potion Hud");
		ToggleButton effects = new ToggleButton(4, 30, 130, 100, 20, "Effects");
		ToggleButton modules = new ToggleButton(5, 30, 155, 100, 20, "Modules");
		ToggleButton chat = new ToggleButton(5, 30, 180, 100, 20, "Chat");
		
		chat.addAction(new ButtonAction() {
			@Override
			public void execute() {
				Minecraft.getMinecraft().displayGuiScreen(new MenuChat());
			}
		});
		
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
				Minecraft.getMinecraft().displayGuiScreen(new MenuEffect());
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
		this.buttonList.add(chat);
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

		// Animation 1
		if (animation1X < width / 2) {
			animation1X += (1.5f * Utils.getFrameTime());
			loaded = false;
			if (animation1X > width / 2) {
				animation1X = width / 2;
			}
		} else {
			if (!loaded) {
				loaded = true;
				loadButtons();
			}
		}
		if (animation1Y < height / 2) {
			animation1Y += (1f * Utils.getFrameTime());
			loaded = false;
			if (animation1Y > height / 2) {
				animation1Y = height / 2;
			}
		}

		// Animation 2
		if (animation2X < width / 2) {
			animation2X += (1.5f * Utils.getFrameTime());
			loaded = false;
			if (animation2X > width / 2) {
				animation2X = width / 2;
			}
		}

		if (animation2Y > height / 2) {
			animation2Y -= (1f * Utils.getFrameTime());
			loaded = false;
			if (animation2Y < height / 2) {
				animation2Y = height / 2;
			}
		}

		// Animation 3
		if (animation3X > width / 2) {
			animation3X -= (1.5f * Utils.getFrameTime());
			loaded = false;
			if (animation3X < width / 2) {
				animation3X = width / 2;
			}
		}

		if (animation3Y < height / 2) {
			animation3Y += (1f * Utils.getFrameTime());
			loaded = false;
			if (animation3Y > height / 2) {
				animation3Y = height / 2;
			}
		}

		// Animation 4
		if (animation4X > width / 2) {
			animation4X -= (1.5f * Utils.getFrameTime());
			loaded = false;
			if (animation4X < width / 2) {
				animation4X = width / 2;
			}
		}

		if (animation4Y > height / 2) {
			animation4Y -= (1f * Utils.getFrameTime());
			loaded = false;
			if (animation4Y < height / 2) {
				animation4Y = height / 2;
			}
		}
		int border = 25;
		if (loaded) {
			this.drawGradientRect(25, 25, this.width-25, this.height-25, Color.red.getRGB(), 0x99222222);

			drawBorderedRect(23, 23, width-23, height-23, 2, 0xf0000000, 0);
		} else {
			this.drawRect(border, border, animation1X, animation1Y, 0x80ff0000);
			this.drawRect(border, height - border, animation2X, animation2Y, 0x80000000);
			this.drawRect(width - border, border, animation3X, animation3Y, 0x80ff0000);
			this.drawRect(width - border, height - border, animation4X, animation4Y, 0x80000000);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
		drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
		drawRect(x + size, y + size, x1, y, borderC);
		drawRect(x, y, x + size, y1, borderC);
		drawRect(x1, y1, x1 - size, y + size, borderC);
		drawRect(x, y1 - size, x1, y1, borderC);
	}
}
