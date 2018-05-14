package com.webmets.vanishedmc.gui.settings;

import java.io.IOException;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.CustomButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.menu.MenuHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuKeypad;
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

	private int animation1X = 10;
	private int animation1Y = 10;

	private int animation2X = 10;
	private int animation2Y = Display.getHeight() / 2 - 10;

	private int animation3X = Display.getWidth() / 2 - 10;
	private int animation3Y = 10;

	private int animation4X = Display.getWidth() / 2 - 10;
	private int animation4Y = Display.getHeight() / 2 - 10;

	private void loadButtons() {
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
			}
		});
		potionhud.addAction(new ButtonAction() {
			@Override
			public void execute() {
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

		// Animation 1
		if (animation1X < width / 2) {
			animation1X += (1.5f * Utils.getFrameTime());
			if (animation1X > width / 2) {
				animation1X = width / 2;
				loadButtons();
			}
		}
		if (animation1Y < height / 2) {
			animation1Y += (1f * Utils.getFrameTime());
			if (animation1Y > height / 2) {
				animation1Y = height / 2;
			}
		}

		// Animation 2
		if (animation2X < width / 2) {
			animation2X += (1.5f * Utils.getFrameTime());
			if (animation2X > width / 2) {
				animation2X = width / 2;
			}
		}

		if (animation2Y > height / 2) {
			animation2Y -= (1f * Utils.getFrameTime());
			if (animation2Y < height / 2) {
				animation2Y = height / 2;
			}
		}

		// Animation 3
		if (animation3X > width / 2) {
			animation3X -= (1.5f * Utils.getFrameTime());
			if (animation3X < width / 2) {
				animation3X = width / 2;
			}
		}

		if (animation3Y < height / 2) {
			animation3Y += (1f * Utils.getFrameTime());
			if (animation3Y > height / 2) {
				animation3Y = height / 2;
			}
		}

		// Animation 4
		if (animation4X > width / 2) {
			animation4X -= (1.5f * Utils.getFrameTime());
			if (animation4X < width / 2) {
				animation4X = width / 2;
			}
		}

		if (animation4Y > height / 2) {
			animation4Y -= (1f * Utils.getFrameTime());
			if (animation4Y < height / 2) {
				animation4Y = height / 2;
			}
		}

		int border = 25;

		this.drawRect(border, border, animation1X, animation1Y, 0xbb1c1c1c);
		this.drawRect(border, height - border, animation2X, animation2Y, 0xbb1c1c1c);
		this.drawRect(width - border, border, animation3X, animation3Y, 0xbb1c1c1c);
		this.drawRect(width - border, height - border, animation4X, animation4Y, 0xbb1c1c1c);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
