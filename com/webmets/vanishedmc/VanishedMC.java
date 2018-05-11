package com.webmets.vanishedmc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.lwjgl.opengl.Display;

import com.webmets.vanishedmc.settings.BasicSettings;
import com.webmets.vanishedmc.siteconnection.UpdateChecker;

public class VanishedMC {

	// Variables
	private UpdateChecker updateChecker = new UpdateChecker();
	private BasicSettings basicSettings = new BasicSettings();

	// Constructor
	public void start() {
		Display.setTitle("VanishedMC Client");
	}

	// Getters and setters
	public UpdateChecker getUpdateChecker() {
		return updateChecker;
	}

	public BasicSettings getBasicSettings() {
		return basicSettings;
	}
	
	// Static shit
	public static VanishedMC instance = new VanishedMC();
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public static void log(String message) {
		System.out.println("[" + sdf.format(new Date()) + "] [VanishedMC] " + message);
	}

}
