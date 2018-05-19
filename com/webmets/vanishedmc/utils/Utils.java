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

	private static float getTimeSinceLast() {
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

	/**
	 * Get the frametime since last update
	 * */
	public static float getFrameTime() {
		return timeSinceLast * 1f;
	}

	/**
	 * Update the main animation clock
	 * */
	public static void updateClock() {
		getTimeSinceLast();
	}

	/** 
	 * Method to detect if user is in a game of ranked skywars
	 * 
	 * @return
	 * true only if playing ranked skywars on Hypixel
	 * */
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

	/**
	 * Method to detect if a user is in a normal game of ranked skywars
	 * 
	 * @return
	 * true if playing skywars on Hypixel </br>
	 * will also return true for ranked or experimental modes
	 * */
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

	/**
	 * Method to detect if a user is on Hypixel
	 * 
	 * @return
	 * true if connected to the hypixel network
	 * */
	public static boolean onHypixel() {
		ServerData server = Minecraft.getMinecraft().getCurrentServerData();
		if (server == null) {
			return false;
		}
		ServerAddress address = ServerAddress.func_78860_a(server.serverIP);
		return address.getIP().contains("hypixel");
	}

}
