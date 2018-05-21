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
import com.webmets.vanishedmc.utils.Utils;

public class GetAutoGGTriggers {

	/**
	 * Method to get a list of triggers for the AutoGG module
	 * 
	 * @return
	 * List of triggers
	 * */
	public static List<String> getTriggers(){
		List<String> result = new ArrayList<String>();
		JsonObject object = (JsonObject) JsonUtils.jsonParser.parse(Utils.grabData("http://files.vanishedmc.com/clientdata.php"));
		JsonArray array = object.get("autogg-triggers").getAsJsonArray();
		for(int i = 0; i < array.size(); i++) {
			result.add(array.get(i).getAsString());
		}
		return result;
	}
}
