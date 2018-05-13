package com.webmets.vanishedmc.utils.ping;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

public class PingUtils {

	private static Future<Long> ping;
	private static String pingString = "";
	private static int nextUpdate = 0;

	private static ExecutorService executor = Executors.newSingleThreadExecutor();;

	private static void tick() {
		if (ping != null) {
			if (!ping.isDone()) {
				return;
			}
			try {
				pingString = ping.get() + "ms";
			} catch (InterruptedException | ExecutionException ex) {
				ex.printStackTrace();
			}
		}
		if (nextUpdate > 0) {
			--nextUpdate;
			return;
		}
		ServerData current = Minecraft.getMinecraft().getCurrentServerData();
		if (current == null) {
			pingString = "0ms";
			return;
		}
		ping = executor.submit((Callable<Long>) new Ping(current.serverIP));
		nextUpdate = 50;
	}
	
	public static String getPing(){
		tick();
		return pingString;
	}
}
