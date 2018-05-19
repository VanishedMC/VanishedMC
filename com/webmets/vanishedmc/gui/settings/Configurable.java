package com.webmets.vanishedmc.gui.settings;

import com.google.gson.JsonObject;

public interface Configurable {
	
	public JsonObject getSettings();

	public void loadSettings(JsonObject json);

	public String getKey();
}
