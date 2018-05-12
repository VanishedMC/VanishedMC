package com.webmets.vanishedmc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.controllers.KeyboardController;
import com.webmets.vanishedmc.modules.GuiHudModule;
import com.webmets.vanishedmc.modules.ModuleManager;
import com.webmets.vanishedmc.settings.BasicSettings;
import com.webmets.vanishedmc.siteconnection.UpdateChecker;

public class VanishedMC {

	/**
	 * Main class for the client that starts everything up, loads settings and
	 * prepares modules
	 */

	// Variables
	private final float version = 0.1f;
	private boolean updateAvailable = false;
	private UpdateChecker updateChecker = new UpdateChecker();
	private BasicSettings basicSettings;
	private KeyboardController keyboardController;
	private ModuleManager moduleManager;
	private GuiHudModule hudModule;

	// Constructor
	public void start() {
		Display.setTitle("VanishedMC Client (v" + version + ")");
		basicSettings = new BasicSettings();
		keyboardController = new KeyboardController();
		moduleManager = new ModuleManager();
		hudModule = new GuiHudModule();
	}

	// Getters and setters
	public UpdateChecker getUpdateChecker() {
		return updateChecker;
	}

	public GuiHudModule getHudModule() {
		return hudModule;
	}

	public BasicSettings getBasicSettings() {
		return basicSettings;
	}

	public float getCurrentVersion() {
		return version;
	}

	public boolean isUpdateAvailable() {
		return updateAvailable;
	}

	public void setUpdateAvailable(boolean updateAvailable) {
		this.updateAvailable = updateAvailable;
	}

	public KeyboardController getKeyboardController() {
		return keyboardController;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	// Static shit
	public static VanishedMC instance = new VanishedMC();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public static void log(String message) {
		System.out.println("[" + sdf.format(new Date()) + "] [VanishedMC] " + message);
	}

}
