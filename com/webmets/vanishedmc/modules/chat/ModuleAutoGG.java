package com.webmets.vanishedmc.modules.chat;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webmets.vanishedmc.siteconnection.GetAutoGGTriggers;
import com.webmets.vanishedmc.utils.AutoMessageThread;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.util.EnumChatFormatting;

public class ModuleAutoGG extends ChatTriggeredModule {

	public ModuleAutoGG() {
		super(GetAutoGGTriggers.getTriggers(), Arrays.asList("/achat gg"));
	}

	@Override
	protected boolean check() {
		return Utils.onHypixel();
	}
	
}
