package com.webmets.vanishedmc.siteconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.webmets.vanishedmc.VanishedMC;

public class UpdateChecker {

	public void init() {
		VanishedMC.log("Initializing update checker..");
		VanishedMC.log(grabData());
	}
	
	public void run(){
		
	}
	
	private String grabData(){
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
