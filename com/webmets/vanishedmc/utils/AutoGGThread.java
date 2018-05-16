package com.webmets.vanishedmc.utils;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.ModuleAutoGG;

import net.minecraft.client.Minecraft;

public class AutoGGThread implements Runnable{

	public void run(){
		ModuleAutoGG autoGG = (ModuleAutoGG) VanishedMC.instance.getModuleManager().getModule(ModuleAutoGG.class);
		try {
			Thread.sleep(autoGG.getDelay());
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/achat gg");
			Thread.sleep(2000L);
			autoGG.setRunning(false);;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
