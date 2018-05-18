package com.webmets.vanishedmc.gui.settings.menu;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.webmets.vanishedmc.gui.buttons.BindButton;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.SliderButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.modules.GuiModule;
import com.webmets.vanishedmc.modules.ModuleManager;
import com.webmets.vanishedmc.modules.SprintModule;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGG;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGL;
import com.webmets.vanishedmc.modules.chat.ModuleAutoWho;

import net.minecraft.client.gui.GuiButton;

public class MenuModules extends Menu {

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(5)).setToggled(true);
		final ModuleManager modules = client.getModuleManager();
		final ModuleAutoGG autoGGModule = (ModuleAutoGG) modules.getModule(ModuleAutoGG.class);
		final ModuleAutoWho autoWhoModule = (ModuleAutoWho) modules.getModule(ModuleAutoWho.class);
		final ModuleAutoGL autoGLModule = (ModuleAutoGL) modules.getModule(ModuleAutoGL.class);
		final SprintModule sprintModule = (SprintModule) modules.getModule(SprintModule.class);
		final GuiModule guiModule = (GuiModule) modules.getModule(GuiModule.class);

		// Initialize buttons
		final ToggleButton autoGG = new ToggleButton(0, 140, 30, 100, 20, "AutoGG");
		final SliderButton autoGGDelay = new SliderButton(0, 245, 30, 100, 20, 1, 9, "Delay");

		final ToggleButton sprint = new ToggleButton(0, 140, 55, 100, 20, "Sprint");
		final BindButton sprintBind = new BindButton(1, 245, 55, 100, 20, sprintModule.getBind(), "Bind");

		final BindButton guiBind = new BindButton(1, 140, 80, 100, 20, guiModule.getBind(), "Gui");

		final ToggleButton autoGL = new ToggleButton(0, 140, 105, 100, 20, "AutoGL");
		final ToggleButton autoWho = new ToggleButton(0, 140, 130, 100, 20, "AutoWho");
		
		// Set states
		autoGG.setToggled(autoGGModule.getEnabled());
		autoGGDelay.setValue((autoGGModule.getDelay() / 100) - 1);
		autoGGDelay.visible = autoGG.isToggled();
		sprint.setToggled(sprintModule.isEnabled());
		sprintBind.visible = sprint.isToggled();
		autoWho.setToggled(autoWhoModule.getEnabled());
		autoGL.setToggled(autoGLModule.getEnabled());
		
		// Actions
		autoGG.addAction(new ButtonAction() {
			@Override
			public void execute() {
				autoGG.setToggled(!autoGG.isToggled());
				autoGGModule.setEnabled(autoGG.isToggled());
				autoGGDelay.visible = autoGG.isToggled();
			}
		});
		
		autoWho.addAction(new ButtonAction() {
			@Override
			public void execute() {
				autoWho.setToggled(!autoWho.isToggled());
				autoWhoModule.setEnabled(autoWho.isToggled());
			}
		});
		
		autoGL.addAction(new ButtonAction() {
			@Override
			public void execute() {
				autoGL.setToggled(!autoGL.isToggled());
				autoGLModule.setEnabled(autoGL.isToggled());
			}
		});

		autoGGDelay.addAction(new ButtonAction() {
			@Override
			public void execute() {
				autoGGModule.setDelay((int) autoGGDelay.getDisplayValue() * 100);
			}
		});

		sprint.addAction(new ButtonAction() {
			@Override
			public void execute() {
				sprint.setToggled(!sprint.isToggled());
				sprintModule.toggle();
				sprintBind.visible = sprint.isToggled();
			}
		});

		sprintBind.addAction(new ButtonAction() {
			@Override
			public void execute() {
				sprintBind.setToggled(!sprintBind.isToggled());
				if (sprintBind.getBind() == Keyboard.KEY_ESCAPE) {
					sprintBind.setBind(Keyboard.KEY_C);
				}
				sprintModule.setBind(sprintBind.getBind());
			}
		});

		guiBind.addAction(new ButtonAction() {
			@Override
			public void execute() {
				guiBind.setToggled(!guiBind.isToggled());
				if (guiBind.getBind() == Keyboard.KEY_ESCAPE) {
					guiBind.setBind(Keyboard.KEY_RSHIFT);
				}
				guiModule.setBind(guiBind.getBind());
			}
		});

		// Adding to list
		buttonList.add(autoGG);
		buttonList.add(autoGGDelay);
		buttonList.add(sprint);
		buttonList.add(sprintBind);
		buttonList.add(guiBind);
		buttonList.add(autoWho);
		buttonList.add(autoGL);
	}

	private BindButton currentButton = null;

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (currentButton == null) {
			super.keyTyped(typedChar, keyCode);
		} else {
			currentButton.keyTyped(keyCode);
			currentButton = null;
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof ToggleButton) {
			((ToggleButton) button).press();
		}
		if (button instanceof BindButton) {
			if(currentButton != null && currentButton != button) {
				currentButton.press();
			}
			((BindButton) button).press();
			if (((BindButton) button).isToggled()) {
				currentButton = (BindButton) button;
			} else {
				currentButton = null;
			}
		}
	}

}
