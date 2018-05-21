package com.webmets.vanishedmc.gui.settings.menu.chat;

import java.io.IOException;
import java.util.List;

import com.webmets.vanishedmc.chat.ChatManager;
import com.webmets.vanishedmc.chat.ChatTab;
import com.webmets.vanishedmc.chat.ChatTrigger;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;

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
			button.addAction(new ButtonAction() {
				@Override
				public void execute() {
					Minecraft.getMinecraft().displayGuiScreen(new MenuChatTriggerSettings(trigger));
				}
			});
			this.buttonList.add(button);
			y += 25;
			i++;
		}
		final ToggleButton create = new ToggleButton(0, 140, y, 100, 20, "Create");

		// Actions
		create.addAction(new ButtonAction() {
			@Override
			public void execute() {
				tab.addChatTrigger(new ChatTrigger());
				Minecraft.getMinecraft().displayGuiScreen(new MenuChatTabSettings(tab));
			}
		});

		// Adding buttons
		this.buttonList.add(create);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

}
