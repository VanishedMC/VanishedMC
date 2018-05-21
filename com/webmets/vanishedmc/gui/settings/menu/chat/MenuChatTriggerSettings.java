package com.webmets.vanishedmc.gui.settings.menu.chat;

import java.io.IOException;

import com.webmets.vanishedmc.chat.ChatTrigger;
import com.webmets.vanishedmc.gui.buttons.ButtonAction;
import com.webmets.vanishedmc.gui.buttons.CustomTextField;
import com.webmets.vanishedmc.gui.buttons.ToggleButton;
import com.webmets.vanishedmc.gui.settings.Menu;

public class MenuChatTriggerSettings extends Menu {

	private ChatTrigger trigger;
	private CustomTextField prefix, keyword;

	public MenuChatTriggerSettings(ChatTrigger trigger) {
		this.trigger = trigger;
	}

	@Override
	public void initGui() {
		super.initGui();
		prefix = new CustomTextField(0, mc.fontRendererObj, 140, 40, 100, 20);
		keyword = new CustomTextField(0, mc.fontRendererObj, 140, 75, 100, 20);
		prefix.setText(trigger.getPrefix().replaceAll("§", "&"));
		keyword.setText(trigger.getKeyWord().replaceAll("§", "&"));

		final ToggleButton mustMeetKey = new ToggleButton(0, 245, 75, 100, 20, "Must meet");
		final ToggleButton mustMeetPrefix = new ToggleButton(0, 245, 40, 100, 20, "Must meet");

		mustMeetKey.setToggled(trigger.mustMatchKey());
		mustMeetPrefix.setToggled(trigger.mustMatchPrefix());
		
		mustMeetKey.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mustMeetKey.setToggled(!mustMeetKey.isToggled());
				trigger.setMustMeetKey(mustMeetKey.isToggled());
			}
		});
		
		mustMeetPrefix.addAction(new ButtonAction() {
			@Override
			public void execute() {
				mustMeetPrefix.setToggled(!mustMeetPrefix.isToggled());
				trigger.setMustMeetPrefix(mustMeetPrefix.isToggled());
			}
		});
		
		buttonList.add(mustMeetPrefix);
		buttonList.add(mustMeetKey);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		prefix.drawTextBox();
		keyword.drawTextBox();
		fontRendererObj.drawString("Prefix", 140, 29, -1);
		fontRendererObj.drawString("Keyword", 140, 64, -1);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		prefix.updateCursorCounter();
		keyword.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if (prefix.isFocused()) {
			prefix.textboxKeyTyped(typedChar, keyCode);
			trigger.setPrefix(prefix.getText().replaceAll("&", "§"));
		}
		if (keyword.isFocused()) {
			keyword.textboxKeyTyped(typedChar, keyCode);
			trigger.setKeyWord(keyword.getText().replaceAll("&", "§"));
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		prefix.mouseClicked(mouseX, mouseY, mouseButton);
		keyword.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
