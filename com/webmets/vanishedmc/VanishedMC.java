package com.webmets.vanishedmc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.settings.BasicSettings;
import com.webmets.vanishedmc.siteconnection.UpdateChecker;

public class VanishedMC {

	// Variables
	private final float version = 0.1f;
	private UpdateChecker updateChecker = new UpdateChecker();
	private BasicSettings basicSettings = new BasicSettings();
	private boolean updateAvailable = false;

	// Constructor
	public void start() {
		Display.setTitle("VanishedMC Client (v" + version + ")");
	}

	// Getters and setters
	public UpdateChecker getUpdateChecker() {
		return updateChecker;
	}

	public BasicSettings getBasicSettings() {
		return basicSettings;
	}
	
	public float getCurrentVersion(){
		return version;
	}
	
	public boolean isUpdateAvailable() {
		return updateAvailable;
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
