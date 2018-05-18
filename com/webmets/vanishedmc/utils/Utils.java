package com.webmets.vanishedmc.utils;

import java.util.List;

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

	public static boolean playingRankedSkywars() {
		Boolean ranked = null;
		List<String> scoreboard = ScoreboardUtils.getScoreboardContent();
		if (scoreboard != null) {
			for (String s : scoreboard) {
				if (s.toLowerCase().contains("ranked")) {
					return playingSkywars();
				}
			}
		}
		return false;
	}

	public static boolean playingSkywars() {
		boolean onSkywars = false;
		boolean inLobby = false;
		List<String> scoreboard = ScoreboardUtils.getScoreboardContent();
		if (scoreboard != null) {
			for (String s : scoreboard) {
				if (s.toLowerCase().contains("skywars")) {
					onSkywars = true;
				}
				if (s.toLowerCase().contains("coins")) {
					inLobby = true;
				}
			}
		}

		return onSkywars && !inLobby;
	}

	public static boolean onHypixel() {
		ServerData server = Minecraft.getMinecraft().getCurrentServerData();
		if (server == null) {
			return false;
		}
		ServerAddress address = ServerAddress.func_78860_a(server.serverIP);
		return address.getIP().contains("hypixel");
	}

}
