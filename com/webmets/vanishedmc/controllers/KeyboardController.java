package com.webmets.vanishedmc.controllers;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.modules.Module;
import com.webmets.vanishedmc.modules.ModuleManager;

public class KeyboardController {

	private VanishedMC client;

	public void keyTyped(int key) {
		if(client == null) {
			client = VanishedMC.instance;
			return;
		}
		for (Module m : client.getModuleManager().getModules()) {
			if (m.getBind() == key) {
				m.toggle();
			}
		}
	}

}
