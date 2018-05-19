package com.webmets.vanishedmc.modules;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGG;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGL;
import com.webmets.vanishedmc.modules.chat.ModuleAutoWho;

public class ModuleManager implements Configurable{

	/**
	 * Module manager, basic ArrayList with all the modules, and methods to get them.
	 * */
	private List<Module> modules;

	private ModuleAutoGG autoGG = new ModuleAutoGG();
	private ModuleAutoGL autoGL = new ModuleAutoGL();
	private ModuleAutoWho autoWho = new ModuleAutoWho();
	private SprintModule sprint = new SprintModule();
	private GuiModule gui = new GuiModule();
	
	public ModuleManager() {
		modules = new ArrayList<>();
		
		modules.add(autoGG);
		modules.add(autoGL);
		modules.add(autoWho);
		modules.add(sprint);
		modules.add(gui);
	}

	public List<Module> getModules() {
		return modules;
	}
	
	public void tick(){
		for(Module m : modules){
			m.tick();
		}
	}

	public Module getModule(Class<?> type) {
		for (Module m : modules) {
			if (m.getClass() == type) {
				return m;
			}
		}
		return null;
	}
	
	@Override
	public String getKey(){
		return "modules";
	}
	
	@Override
	public JsonObject getSettings(){
		JsonObject modules = new JsonObject();
		modules.addProperty("autogg-enabled", autoGG.getEnabled());
		modules.addProperty("autogg-delay", autoGG.getDelay());
		modules.addProperty("sprint-enabled", sprint.isEnabled());
		modules.addProperty("sprint-bind", sprint.getBind());
		modules.addProperty("gui-bind", gui.getBind());
		modules.addProperty("autogl-enabled", autoGL.getEnabled());
		modules.addProperty("autowho-enabled", autoWho.getEnabled());
		return modules;
	}
	
	@Override
	public void loadSettings(JsonObject json) {
		autoGG.setEnabled(json.get("autogg-enabled").getAsBoolean());
		autoGG.setDelay(json.get("autogg-delay").getAsInt());
		if(json.get("sprint-enabled").getAsBoolean()) {
			sprint.enable();
		}
		sprint.setBind(json.get("sprint-bind").getAsInt());
		gui.setBind(json.get("gui-bind").getAsInt());
		autoGL.setEnabled(json.get("autogl-enabled").getAsBoolean());
		autoWho.setEnabled(json.get("autowho-enabled").getAsBoolean());
	}

}
