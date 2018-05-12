package com.webmets.vanishedmc.modules;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

	/**
	 * Module manager, basic ArrayList with all the modules, and methods to get them.
	 * */
	
	private List<Module> modules;

	public ModuleManager() {
		modules = new ArrayList<>();
		
		modules.add(new GuiModule());
	}

	public List<Module> getModules() {
		return modules;
	}

	public Module getModule(Class<?> type) {
		for (Module m : modules) {
			if (m.getClass() == type) {
				return m;
			}
		}
		return null;
	}

}
