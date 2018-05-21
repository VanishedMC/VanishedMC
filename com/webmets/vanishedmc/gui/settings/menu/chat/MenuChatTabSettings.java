package com.webmets.vanishedmc.gui.settings.menu.chat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.webmets.vanishedmc.chat.ChatManager;
import com.webmets.vanishedmc.chat.ChatTab;
import com.webmets.vanishedmc.chat.ChatTrigger;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.SelectorButton;
import com.webmets.vanishedmc.gui.buttons.SliderButton;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;
import com.webmets.vanishedmc.utils.FilterRequirements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class MenuChatTabSettings extends Menu {

	private ChatTab tab;

	public MenuChatTabSettings(ChatTab tab) {
		this.tab = tab;
	}

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(6)).setToggled(true);
		ChatManager chat = client.getChatManager();
		List<ChatTrigger> triggers = tab.getTriggers();

		// Initialize buttons
		int y = 30;
		int i = 1;
		for (ChatTrigger trigger : triggers) {
			final ToggleButton button = new ToggleButton(0, 140, y, 100, 20, "Trigger " + i);
			final ToggleButton delete = new ToggleButton(0, 245, y, 20, 20, "§4§lX");

			button.addAction(new ButtonAction() {
				@Override
				public void execute() {
					Minecraft.getMinecraft().displayGuiScreen(new MenuChatTriggerSettings(trigger));
				}
			});
			delete.addAction(new ButtonAction() {
				@Override
				public void execute() {
					tab.removeTrigger(trigger);
					mc.displayGuiScreen(new MenuChatTabSettings(tab));
				}
			});

			this.buttonList.add(button);
			this.buttonList.add(delete);
			y += 25;
			i++;
		}
		final SelectorButton requirements = new SelectorButton(0, 140, y, 100, 20, "",
				Arrays.asList("Require one", "Require all"));
		final ToggleButton create = new ToggleButton(0, 140, y + 25, 100, 20, "Create");

		// Set states
		requirements.setCurrent(tab.getRequirements().toString().replaceAll("_", " ").toLowerCase());

		// Actions
		create.addAction(new ButtonAction() {
			@Override
			public void execute() {
				tab.addChatTrigger(new ChatTrigger());
				Minecraft.getMinecraft().displayGuiScreen(new MenuChatTabSettings(tab));
			}
		});

		requirements.addAction(new ButtonAction() {
			@Override
			public void execute() {
				FilterRequirements require = FilterRequirements
						.valueOf(requirements.displayString.replace(" ", "_").toUpperCase().substring(1));
				tab.setRequirements(require);
			}
		});

		// Adding buttons
		this.buttonList.add(create);
		this.buttonList.add(requirements);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button instanceof SelectorButton) {
			((SelectorButton) button).press();
		}
	}

}
