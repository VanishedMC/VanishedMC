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

public class UpdateChecker {

	public void init() {
		boolean update = isUpdateAvailable();
		VanishedMC.log("Initializing update checker.. " + (update ? "update found" : "no update found"));
		VanishedMC.instance.setUpdateAvailable(update);
	}

	public boolean isUpdateAvailable() {
		try {
			JsonObject json = (JsonObject) JsonUtils.jsonParser.parse(grabData());
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

	private String grabData() {
		try {
			URL url = new URL("http://files.vanishedmc.com/clientdata.php");
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String code = "", line = "";

			while ((line = br.readLine()) != null) {
				code = code + line;
			}
			
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
