package com.webmets.vanishedmc.gui.settings.menu.chat;

import java.io.IOException;
import java.util.List;

import com.webmets.vanishedmc.chat.ChatManager;
import com.webmets.vanishedmc.chat.ChatTab;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.CustomTextField;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class MenuChatSettings extends Menu {

	private String ip;
	private CustomTextField name;

	public MenuChatSettings(String ip) {
		this.ip = ip;
	}

	@Override
	public void initGui() {
		// Variables
		super.initGui();
		((ToggleButton) buttonList.get(6)).setToggled(true);
		ChatManager chat = client.getChatManager();
		List<ChatTab> tabs = chat.getTabsForServer(ip);

		// Initialize buttons
		int i = 30;
		for (ChatTab tab : tabs) {
			final ToggleButton button = new ToggleButton(0, 140, i, 100, 20, tab.getName());
			final ToggleButton delete = new ToggleButton(0, 245, i, 20, 20, "§4§lX");
			button.addAction(new ButtonAction() {
				@Override
				public void execute() {
					Minecraft.getMinecraft().displayGuiScreen(new MenuChatTabSettings(tab));
				}
			});
			delete.addAction(new ButtonAction() {
				@Override
				public void execute() {
					chat.removeTabFromServer(ip, tab);
					mc.displayGuiScreen(new MenuChatSettings(ip));
				}
			});
			this.buttonList.add(delete);
			this.buttonList.add(button);
			i += 25;
		}
		final ToggleButton create = new ToggleButton(0, 245, i, 100, 20, "Create");
		name = new CustomTextField(0, mc.fontRendererObj, 140, i, 100, 20);

		// Actions
		create.addAction(new ButtonAction() {
			@Override
			public void execute() {
				if(!name.getText().trim().isEmpty()) {
					chat.addTabToServer(ip, new ChatTab(name.getText()));
					mc.displayGuiScreen(new MenuChatSettings(ip));
				}
			}
		});

		// Adding buttons
		this.buttonList.add(create);
		// this.buttonList.add(name);
	}

	@Override
	public void keyTyped(char c, int k) throws IOException {
		super.keyTyped(c, k);
		if(name.isFocused()) {
			name.textboxKeyTyped(c, k);
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		name.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		name.drawTextBox();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		name.updateCursorCounter();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

}
