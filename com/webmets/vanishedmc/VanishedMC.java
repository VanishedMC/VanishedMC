package com.webmets.vanishedmc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.controllers.ChatController;
import com.webmets.vanishedmc.controllers.KeyboardController;
import com.webmets.vanishedmc.controllers.MouseController;
import com.webmets.vanishedmc.files.FileManager;
import com.webmets.vanishedmc.modules.GuiHudArmorModule;
import com.webmets.vanishedmc.modules.GuiHudKeypadModule;
import com.webmets.vanishedmc.modules.GuiHudModule;
import com.webmets.vanishedmc.modules.ModuleManager;
import com.webmets.vanishedmc.settings.BasicSettings;
import com.webmets.vanishedmc.siteconnection.UpdateChecker;
import com.webmets.vanishedmc.utils.AccountUtils;

public class VanishedMC {

	/**
	 * Main class for the client that starts everything up, loads settings and
	 * prepares modules
	 */

	// Variables
	private final float version = 0.3f;
	private boolean updateAvailable = false;
	
	private UpdateChecker updateChecker = new UpdateChecker();
	private BasicSettings basicSettings;
	
	private KeyboardController keyboardController;
	private MouseController mouseController;
	private ChatController chatController;
	
	private ModuleManager moduleManager;
	private GuiHudModule hudModule;
	private GuiHudKeypadModule keypadModule;
	private GuiHudArmorModule armorModule;
	
	private FileManager fileManager;
	
	// Constructor
	public void start() {
		Display.setTitle("VanishedMC Client (v" + version + ")");
		basicSettings = new BasicSettings();

		moduleManager = new ModuleManager();
		hudModule = new GuiHudModule();
		keypadModule = new GuiHudKeypadModule();
		armorModule = new GuiHudArmorModule();
		
		keyboardController = new KeyboardController();
		mouseController = new MouseController();
		chatController = new ChatController();
		
		fileManager = new FileManager();
		// Used to automate login without storing password in the code
		if(!AccountUtils.login.equalsIgnoreCase("null")) {
			AccountUtils.login(AccountUtils.login);
			AccountUtils.login = "null";
		}
	}

	// Getters and setters
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public GuiHudArmorModule getArmorModule() {
		return armorModule;
	}
	
	public ChatController getChatController() {
		return chatController;
	}
	
	public UpdateChecker getUpdateChecker() {
		return updateChecker;
	}

	public GuiHudKeypadModule getKeypadModule() {
		return keypadModule;
	}
	
	public MouseController getMouseController() {
		return mouseController;
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
	
	public KeyboardController getKeyboardController() {
		return keyboardController;
	}

	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	public void setUpdateAvailable(boolean updateAvailable) {
		this.updateAvailable = updateAvailable;
	}
	
	// Static shit
	public static VanishedMC instance = new VanishedMC();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public static void log(String message) {
		System.out.println("[" + sdf.format(new Date()) + "] [VanishedMC] " + message);
	}

}
