package com.webmets.vanishedmc.controllers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.Module;
import com.webmets.vanishedmc.modules.chat.ChatTriggeredModule;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGG;

public class ChatController {

	private VanishedMC client = VanishedMC.instance;
	
	public void messageReceived(String message) {	
		if(message.equalsIgnoreCase(".record") && VanishedMC.DEBUGMODE) {
			try {
				Display.setDisplayMode(new DisplayMode(1280, 720));
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		for(Module m : client.getModuleManager().getModules()) {
			if(m instanceof ChatTriggeredModule) {
				((ChatTriggeredModule)m).chatEvent(message);
			}
		}
	}
	
}
