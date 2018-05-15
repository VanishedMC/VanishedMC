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
	private static long nextUpdate = 0;
	private static int delay = 1000;

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
		if (System.currentTimeMillis() < nextUpdate) {
			return;
		}
		ServerData current = Minecraft.getMinecraft().getCurrentServerData();
		if (current == null) {
			pingString = "0ms";
			return;
		}
		ping = executor.submit((Callable<Long>) new Ping(current.serverIP));
		nextUpdate = System.currentTimeMillis() + delay;
	}
	
	public static int getDelay() {
		return delay;
	}
	
	public static void setDelay(int delay) {
		PingUtils.delay = delay;
	}
	
	public static String getPing(){
		tick();
		return pingString;
	}
}
