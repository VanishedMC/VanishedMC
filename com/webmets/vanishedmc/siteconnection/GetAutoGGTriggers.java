package com.webmets.vanishedmc.siteconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webmets.vanishedmc.utils.JsonUtils;

public class GetAutoGGTriggers {

	public static List<String> getTriggers(){
		List<String> result = new ArrayList<String>();
		JsonObject object = (JsonObject) JsonUtils.jsonParser.parse(grabData());
		JsonArray array = object.get("autogg-triggers").getAsJsonArray();
		for(int i = 0; i < array.size(); i++) {
			result.add(array.get(i).getAsString());
		}
		return result;
	}
	
	private static String grabData() {
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
