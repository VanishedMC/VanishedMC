package com.webmets.vanishedmc.modules.chat;

import java.util.Arrays;

import com.webmets.vanishedmc.utils.AutoMessageThread;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.util.EnumChatFormatting;

public class ModuleAutoWho extends ChatTriggeredModule{

	public ModuleAutoWho() {
		super(Arrays.asList("Teaming is not allowed on Ranked mode!"), Arrays.asList("/who"));
		setDelay(100);
	}
	
	@Override
	public void chatEvent(String text) {
		if (!enabled || isRunning() || !check()) {
			return;
		}
		message = text;
		message = EnumChatFormatting.getTextWithoutFormattingCodes(message);
		if (triggers.stream().anyMatch(trigger -> message.contains(trigger.toString()))) {
			thread.submit(new AutoMessageThread(this, messages, delay));
			setRunning(true);
		}
	}

	@Override
	protected boolean check() {
		return Utils.onHypixel();
	}
}
