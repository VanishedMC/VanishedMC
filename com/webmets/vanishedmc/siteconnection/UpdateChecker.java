package com.webmets.vanishedmc.siteconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map.Entry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.utils.JsonUtils;
import com.webmets.vanishedmc.utils.Utils;

public class UpdateChecker {

	/**
	 * The update checker, grabs a direct string from http://files.vanishedmc.com/clientdata.php, parses the JSON with the GSON library
	 * */
	public void init() {
		boolean update = isUpdateAvailable();
		VanishedMC.log("Initializing update checker.. " + (update ? "update found" : "no update found"));
		VanishedMC.instance.setUpdateAvailable(update);
	}

	/**
	 * Method to check if an update for the client is available
	 * 
	 * @return
	 * true if an update was found
	 * */
	public boolean isUpdateAvailable() {
		try {
			JsonObject json = (JsonObject) JsonUtils.jsonParser.parse(Utils.grabData("http://files.vanishedmc.com/clientdata.php"));
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();

			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				String key = entry.getKey();
				if (key.equalsIgnoreCase("latest-version")) {
					String versionString = entry.getValue().toString().replaceAll("\"", "");
					float current = VanishedMC.instance.getCurrentVersion();
					float latest = Float.parseFloat(versionString);
					return latest > current;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	
}
