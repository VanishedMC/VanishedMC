package com.webmets.vanishedmc.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.GuiIngameHook;
import com.webmets.vanishedmc.modules.GuiHudKeypadModule;
import com.webmets.vanishedmc.modules.GuiHudModule;
import com.webmets.vanishedmc.settings.GuiHudCOORDSView;
import com.webmets.vanishedmc.settings.GuiHudCPSView;
import com.webmets.vanishedmc.utils.ping.PingUtils;

import net.minecraft.client.Minecraft;

public class FileManager {

	private File clientDir = new File(Minecraft.getMinecraft().mcDataDir, "VanishedMC");
	private File settingsFile = new File(clientDir, "settings.json");

	public FileManager() {
		if (!clientDir.exists()) {
			clientDir.mkdirs();
		}
		if (!settingsFile.exists()) {
			saveSettings();
		}
		load();
	}

	private void load() {
		VanishedMC client = VanishedMC.instance;
		GuiHudModule hud = client.getHudModule();
		GuiHudKeypadModule keypad = client.getKeypadModule();
		GuiIngameHook hook = (GuiIngameHook) Minecraft.getMinecraft().ingameGUI;

		try {
			BufferedReader load;
			load = new BufferedReader(new FileReader(settingsFile));
			JsonObject json = (JsonObject) new JsonParser().parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				String key = entry.getKey();
				JsonObject obj = (JsonObject) entry.getValue();
				Iterator<Entry<String, JsonElement>> list = obj.entrySet().iterator();
				while (list.hasNext()) {
					Entry<String, JsonElement> entry2 = list.next();
					String key2 = entry2.getKey();
					JsonElement elm = entry2.getValue();
					if (key.equalsIgnoreCase("hud")) {
						if (key2.equalsIgnoreCase("fps")) {
							hud.setShowFPS(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("cps")) {
							hud.setShowCPS(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("cpsView")) {
							hud.setCpsView(GuiHudCPSView.valueOf(elm.getAsString()));
						} else if (key2.equalsIgnoreCase("coords")) {
							hud.setShowCOORDS(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("coordsView")) {
							hud.setCoordsView(GuiHudCOORDSView.valueOf(elm.getAsString()));
						} else if (key2.equalsIgnoreCase("coordsOneLine")) {
							hud.setCoordsOneLine(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("ping")) {
							hud.setShowPING(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("pingDelay")) {
							PingUtils.setDelay(elm.getAsInt());
						}
					} else if (key.equalsIgnoreCase("keypad")) {
						if (key2.equalsIgnoreCase("mouseButtons")) {
							keypad.setShowMouseButtons(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("cps")) {
							keypad.setShowCps(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("cpsInLine")) {
							keypad.setCpsInLine(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("buttonScale")) {
							keypad.setSize(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("textScale")) {
							keypad.setScale(elm.getAsFloat());
						} else if (key2.equalsIgnoreCase("spacebar")) {
							keypad.setSpaceBar(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("distance")) {
							keypad.setDistanceBetween(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("locationX")) {
							hook.setKeyPadX(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("locationY")) {
							hook.setKeyPadY(elm.getAsInt());
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void saveSettings() {
		VanishedMC client = VanishedMC.instance;
		GuiHudModule hud = client.getHudModule();
		GuiHudKeypadModule keypad = client.getKeypadModule();
		GuiIngameHook hook = (GuiIngameHook) Minecraft.getMinecraft().ingameGUI;

		try {
			JsonObject object = new JsonObject();
			{ // Hud
				JsonObject hudObject = new JsonObject();
				hudObject.addProperty("fps", hud.isShowFPS());
				hudObject.addProperty("cps", hud.isShowCPS());
				hudObject.addProperty("cpsView", hud.getCpsView().toString());
				hudObject.addProperty("coords", hud.isShowCOORDS());
				hudObject.addProperty("coordsOneLine", hud.isCoordsOneLine());
				hudObject.addProperty("coordsView", hud.getCoordsView().toString());
				hudObject.addProperty("ping", hud.isShowPING());
				hudObject.addProperty("pingDelay", PingUtils.getDelay());
				object.add("hud", hudObject);
			}

			{ // Keypad
				JsonObject keyPadObject = new JsonObject();
				keyPadObject.addProperty("mouseButtons", keypad.isShowMouseButtons());
				keyPadObject.addProperty("cps", keypad.isShowCps());
				keyPadObject.addProperty("cpsInLine", keypad.isCpsInLine());
				keyPadObject.addProperty("buttonScale", keypad.getSize());
				keyPadObject.addProperty("textScale", keypad.getScale());
				keyPadObject.addProperty("spacebar", keypad.isSpaceBar());
				keyPadObject.addProperty("distance", keypad.getDistanceBetween());
				keyPadObject.addProperty("locationX", hook.getKeyPadX());
				keyPadObject.addProperty("locationY", hook.getKeyPadY());
				object.add("keypad", keyPadObject);
			}

			PrintWriter save = new PrintWriter(new FileWriter(settingsFile));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			save.print(gson.toJson(object));
			save.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
