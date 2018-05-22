package com.webmets.vanishedmc.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.settings.Configurable;
import com.webmets.vanishedmc.modules.GuiArmorModule;

import net.minecraft.client.Minecraft;

public class SettingsManager {

	private File clientDir = new File(Minecraft.getMinecraft().mcDataDir, "VanishedMC");
	private File settingsFile = new File(clientDir, "config.json");
	private List<Configurable> modules;
	private VanishedMC client = VanishedMC.instance;

	public SettingsManager() {
		modules = new ArrayList<>();
		modules.add(client.getArmorModule());
		modules.add(client.getHudModule());
		modules.add(client.getKeypadModule());
		modules.add(client.getModuleManager());
		modules.add(client.getChatManager());
		
		if (!clientDir.exists()) {
			clientDir.mkdirs();
		}
		if (!settingsFile.exists()) {
			saveSettings();
		}
		
		loadSettings();
	}

	public void saveSettings() {
		VanishedMC.log("Saving settings");
		try {
			JsonObject json = new JsonObject();
			
			for (Configurable config : modules) {
				json.add(config.getKey(), config.getSettings());
			}
			
			PrintWriter save = new PrintWriter(new FileWriter(settingsFile));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			save.print(gson.toJson(json));
			save.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadSettings() {
		try {
			BufferedReader load = new BufferedReader(new FileReader(settingsFile));
			JsonObject json = (JsonObject) new JsonParser().parse(load);
			load.close();
			
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();

			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				String key = entry.getKey();
				JsonObject obj = (JsonObject) entry.getValue();

				modules.iterator().forEachRemaining(m -> {
					if(m.getKey().equalsIgnoreCase(key)) {
						m.loadSettings(obj);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
