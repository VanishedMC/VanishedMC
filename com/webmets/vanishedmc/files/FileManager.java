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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.GuiIngameHook;
import com.webmets.vanishedmc.modules.GuiKeypadModule;
import com.webmets.vanishedmc.modules.GuiHudModule;
import com.webmets.vanishedmc.modules.GuiModule;
import com.webmets.vanishedmc.modules.SprintModule;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGG;
import com.webmets.vanishedmc.modules.chat.ModuleAutoGL;
import com.webmets.vanishedmc.modules.chat.ModuleAutoWho;
import com.webmets.vanishedmc.settings.GuiHudCOORDSView;
import com.webmets.vanishedmc.settings.GuiHudCPSView;
import com.webmets.vanishedmc.utils.effects.EffectMode;
import com.webmets.vanishedmc.utils.effects.EffectUtils;
import com.webmets.vanishedmc.utils.ping.PingUtils;

import net.minecraft.client.Minecraft;

public class FileManager {

	private VanishedMC client = VanishedMC.instance;
	private GuiHudModule hud = client.getHudModule();
	private GuiKeypadModule keypad = client.getKeypadModule();
	private GuiIngameHook hook = (GuiIngameHook) Minecraft.getMinecraft().ingameGUI;
	private ModuleAutoGG autoGG = (ModuleAutoGG) client.getModuleManager().getModule(ModuleAutoGG.class);
	private ModuleAutoGL autoGl = (ModuleAutoGL) client.getModuleManager().getModule(ModuleAutoGL.class);
	private ModuleAutoWho autoWho = (ModuleAutoWho) client.getModuleManager().getModule(ModuleAutoWho.class);
	private SprintModule sprint = (SprintModule) client.getModuleManager().getModule(SprintModule.class);
	private GuiModule gui = (GuiModule) client.getModuleManager().getModule(GuiModule.class);
	private EffectUtils hudEffectUtils = client.getHudModule().getEffectUtils();
	private EffectUtils keypadEffectUtils = client.getKeypadModule().getEffectUtils();
	
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
					} else if (key.equalsIgnoreCase("modules")) {
						if (key2.equalsIgnoreCase("autogg-enabled")) {
							autoGG.setEnabled(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("autogg-delay")) {
							autoGG.setDelay(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("sprint-enabled")) {
							if (elm.getAsBoolean()) {
								sprint.enable();
							}
						} else if (key2.equalsIgnoreCase("sprint-bind")) {
							sprint.setBind(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("gui-bind")) {
							gui.setBind(elm.getAsInt());
						} else if (key2.equalsIgnoreCase("autogl-enabled")) {
							autoGl.setEnabled(elm.getAsBoolean());
						} else if (key2.equalsIgnoreCase("autowho-enabled")) {
							autoWho.setEnabled(elm.getAsBoolean());
						}
					} else if (key.equalsIgnoreCase("effects")) {
						if (key2.equalsIgnoreCase("hud")) {
							Iterator<Entry<String, JsonElement>> effectList = elm.getAsJsonObject().entrySet()
									.iterator();
							while (effectList.hasNext()) {
								Entry<String, JsonElement> effectEntry = effectList.next();
								if (effectEntry.getKey().equalsIgnoreCase("mode")) {
									hudEffectUtils.setMode(EffectMode
											.valueOf(effectEntry.getValue().getAsString().replaceAll("\"", "")));
								} else if (effectEntry.getKey().equalsIgnoreCase("rainbowSpeed")) {
									hudEffectUtils.loadRainbowSpeed(effectEntry.getValue().getAsFloat());
								} else if (effectEntry.getKey().equalsIgnoreCase("rainbowSize")) {
									hudEffectUtils.setRainbowSize(effectEntry.getValue().getAsFloat());
								} else if (effectEntry.getKey().equalsIgnoreCase("staticColorRGB")) {
									hudEffectUtils.setStaticColor(effectEntry.getValue().getAsInt());
								}
							}
						} else if (key2.equalsIgnoreCase("keypad")) {
							Iterator<Entry<String, JsonElement>> effectList = elm.getAsJsonObject().entrySet()
									.iterator();
							while (effectList.hasNext()) {
								Entry<String, JsonElement> effectEntry = effectList.next();
								if (effectEntry.getKey().equalsIgnoreCase("mode")) {
									keypadEffectUtils.setMode(EffectMode
											.valueOf(effectEntry.getValue().getAsString().replaceAll("\"", "")));
								} else if (effectEntry.getKey().equalsIgnoreCase("rainbowSpeed")) {
									keypadEffectUtils.loadRainbowSpeed(effectEntry.getValue().getAsFloat());
								} else if (effectEntry.getKey().equalsIgnoreCase("rainbowSize")) {
									keypadEffectUtils.setRainbowSize(effectEntry.getValue().getAsFloat());
								} else if (effectEntry.getKey().equalsIgnoreCase("staticColorRGB")) {
									keypadEffectUtils.setStaticColor(effectEntry.getValue().getAsInt());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void saveSettings() {
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

			{ // Modules
				JsonObject modules = new JsonObject();
				modules.addProperty("autogg-enabled", autoGG.getEnabled());
				modules.addProperty("autogg-delay", autoGG.getDelay());
				modules.addProperty("sprint-enabled", sprint.isEnabled());
				modules.addProperty("sprint-bind", sprint.getBind());
				modules.addProperty("gui-bind", gui.getBind());
				modules.addProperty("autogl-enabled", autoGl.getEnabled());
				modules.addProperty("autowho-enabled", autoWho.getEnabled());
				object.add("modules", modules);
			}

			{ // Effects
				JsonObject effects = new JsonObject();
				{ // Hud
					JsonObject hudEffects = new JsonObject();
					hudEffects.addProperty("mode", hudEffectUtils.getMode().toString());
					hudEffects.addProperty("rainbowSpeed", hudEffectUtils.getRainbowSpeed());
					hudEffects.addProperty("rainbowSize", hudEffectUtils.getRainbowSize());
					hudEffects.addProperty("staticColorRGB", hudEffectUtils.getStaticColor().getRGB());
					effects.add("hud", hudEffects);
				}
				{ // Keypad
					JsonObject keypadEffects = new JsonObject();
					keypadEffects.addProperty("mode", keypadEffectUtils.getMode().toString());
					keypadEffects.addProperty("rainbowSpeed", keypadEffectUtils.getRainbowSpeed());
					keypadEffects.addProperty("rainbowSize", keypadEffectUtils.getRainbowSize());
					keypadEffects.addProperty("staticColorRGB", keypadEffectUtils.getStaticColor().getRGB());
					effects.add("keypad", keypadEffects);
				}
				object.add("effects", effects);
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
