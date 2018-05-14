package com.webmets.vanishedmc.gui.settings;

import java.io.IOException;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.menu.MenuHud;
import com.webmets.vanishedmc.gui.settings.menu.MenuKeypad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class Menu extends GuiScreen {

	protected VanishedMC client = VanishedMC.instance;
	
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
		this.drawRect(25, 25, width - 25, height - 25, 0xbb1c1c1c);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
