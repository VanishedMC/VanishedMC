package com.webmets.vanishedmc.controllers;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.Module;
import com.webmets.vanishedmc.modules.chat.ChatTriggeredModule;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGG;

public class ChatController {

	private VanishedMC client = VanishedMC.instance;
	
	public void messageReceived(String message) {		
		for(Module m : client.getModuleManager().getModules()) {
			if(m instanceof ChatTriggeredModule) {
				((ChatTriggeredModule)m).chatEvent(message);
			}
		}
	}
	
}
