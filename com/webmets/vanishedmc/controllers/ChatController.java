package com.webmets.vanishedmc.controllers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.Module;
import com.webmets.vanishedmc.modules.chat.ChatTriggeredModule;

import net.minecraft.client.Minecraft;

public class ChatController {

	private VanishedMC client = VanishedMC.instance;
	
	public boolean messageReceived(String message) {	
		if(message.equalsIgnoreCase(".record") && VanishedMC.DEBUGMODE) {
			try {
				Minecraft mc = Minecraft.getMinecraft();
				mc.displayWidth = 1280;
				mc.displayHeight = 720;
				Display.setDisplayMode(new DisplayMode(1280, 720));
				return false;
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		for(Module m : client.getModuleManager().getModules()) {
			if(m instanceof ChatTriggeredModule) {
				((ChatTriggeredModule)m).chatEvent(message);
			}
		}
		return true;
	}
	
}
