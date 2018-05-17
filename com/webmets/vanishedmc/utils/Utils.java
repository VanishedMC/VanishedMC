package com.webmets.vanishedmc.utils;

import org.lwjgl.Sys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;

public class Utils {

	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	private static boolean first = true;
	private static long currentFrame;
	private static long timeSinceLast;
	private static long lastFrame;
	
	public static float getTimeSinceLast() {
		if (first) {
			currentFrame = getTime();
			first = false;
			timeSinceLast = 1;
		}

		else {
			lastFrame = currentFrame;
			currentFrame = getTime();
			timeSinceLast = currentFrame - lastFrame;
		}

		return timeSinceLast;
	}

	public static float getFrameTime() {
		return timeSinceLast * 1f;
	}

	public static void updateClock() {
		getTimeSinceLast();
	}
	
	public static boolean onHypixel() {
		ServerData server = Minecraft.getMinecraft().getCurrentServerData();
		ServerAddress address = ServerAddress.func_78860_a(server.serverIP);
		return address.getIP().contains("hypixel");
	}

}
