package com.webmets.vanishedmc.utils.effects;

import java.awt.Color;

public class EffectUtils {

	public EffectUtils(){
		
	}
	private float rainbowSpeed = 1000.0f;
	private float rainbowSize = 3f;
	private EffectMode mode = EffectMode.RAINBOW;
	private int staticColor = 0x00ffffff;
	
	private int getRainbowColorForY(float offset) {
		return Color.HSBtoRGB(1 + (System.currentTimeMillis() % (int) rainbowSpeed / rainbowSpeed) + offset, 1f, 1);
	}
	
	public int getColorForY(int y, float offset) {
		switch(mode) {
		case RAINBOW:
			return getRainbowColorForY((float) (y+offset/rainbowSize)/100);
		case STATIC:
			return staticColor;		
		}
		return -1;
	}
	
	public EffectMode getMode() {
		return mode;
	}
	
	public void setMode(EffectMode mode) {
		this.mode = mode;
	}
	
	public float getRainbowSpeed() {
		return rainbowSpeed;
	}
	
	public float getRainbowSize() {
		return rainbowSize;
	}
	
	public int getStaticColor() {
		return staticColor;
	}
	
	public void setStaticColor(int staticColor) {
		this.staticColor = staticColor;
	}
	
	public void setRainbowSize(float rainbowSize) {
		this.rainbowSize = rainbowSize;
	}
	
	public void setRainbowSpeed(float rainbowSpeed) {
		this.rainbowSpeed = rainbowSpeed;
	}
}
