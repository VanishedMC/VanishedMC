package com.webmets.vanishedmc.utils;

import java.awt.Color;

public class EffectUtils {

	private static float speed = 1000.0f;
	
	private static int getColorForY(float offset) {
		speed = 700.0f;
		return Color.HSBtoRGB(1 + (System.currentTimeMillis() % (int)speed / speed) + offset, 1f, 1);
	}
	
	public static int getColorForY(int y, float offset) {
		return getColorForY((float) (y+offset/3f)/100);
	}
	
}
