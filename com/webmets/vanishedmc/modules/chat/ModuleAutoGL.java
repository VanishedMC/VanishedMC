package com.webmets.vanishedmc.modules.chat;

import java.util.Arrays;

import com.webmets.vanishedmc.siteconnection.GetAutoGGTriggers;
import com.webmets.vanishedmc.utils.AutoMessageThread;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.util.EnumChatFormatting;

public class ModuleAutoGL extends ChatTriggeredModule{

	public ModuleAutoGL() {
		super(Arrays.asList("The game starts in 1 second!"), Arrays.asList("glhf"));
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
		return Utils.onHypixel() && !Utils.playingRankedSkywars();
	}
	
}
