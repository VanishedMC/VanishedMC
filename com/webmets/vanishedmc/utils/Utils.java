package com.webmets.vanishedmc.utils;

import org.lwjgl.Sys;

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

}
