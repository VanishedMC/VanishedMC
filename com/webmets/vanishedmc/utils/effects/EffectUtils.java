package com.webmets.vanishedmc.utils.effects;

import java.awt.Color;

import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;

public class EffectUtils implements Configurable {

	private float rainbowSpeed = 1000.0f;
	private float rainbowSize = 3f;
	private EffectMode mode = EffectMode.RAINBOW;
	private Color staticColor = new Color(255, 255, 255);

	private int getRainbowColorForY(float offset) {
		return Color.HSBtoRGB(1 + (System.currentTimeMillis() % (int) rainbowSpeed / rainbowSpeed) + offset, 1f, 1);
	}

	/**
	 * Method to get color value for any Y level, depending on current mode and settings
	 * 
	 * @return
	 * The color value for given Y and offset
	 * 
	 * */
	public int getColorForY(int y, float offset) {
		switch (mode) {
		case RAINBOW:
			return getRainbowColorForY((float) (y + offset / rainbowSize) / 100);
		case STATIC:
			return staticColor.getRGB();
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

	public Color getStaticColor() {
		return staticColor;
	}

	public void setStaticColorRed(int red) {
		this.staticColor = new Color(red, staticColor.getGreen(), staticColor.getBlue());
	}

	public void setStaticColorGreen(int green) {
		this.staticColor = new Color(staticColor.getRed(), green, staticColor.getBlue());
	}

	public void setStaticColorBlue(int blue) {
		this.staticColor = new Color(staticColor.getRed(), staticColor.getGreen(), blue);
	}

	public void setStaticColor(int rgb) {
		this.staticColor = new Color(rgb);
	}

	public void setRainbowSize(float rainbowSize) {
		this.rainbowSize = rainbowSize;
	}

	public void setRainbowSpeed(float rainbowSpeed) {
		this.rainbowSpeed = (2000 - rainbowSpeed);
	}
	
	public void loadRainbowSpeed(float rainbowSpeed) {
		this.rainbowSpeed = rainbowSpeed;
	}
	
	@Override
	public String getKey(){
		return "effects";
	}
	
	@Override
	public JsonObject getSettings(){
		JsonObject effects = new JsonObject();
		effects.addProperty("mode", getMode().toString());
		effects.addProperty("rainbowSpeed", getRainbowSpeed());
		effects.addProperty("rainbowSize", getRainbowSize());
		effects.addProperty("staticColorRGB", getStaticColor().getRGB());
		return effects;
	}
	
	@Override
	public void loadSettings(JsonObject json) {
		setMode(EffectMode.valueOf(json.get("mode").getAsString()));
		setRainbowSpeed(json.get("rainbowSpeed").getAsFloat());
		setRainbowSize(json.get("rainbowSize").getAsFloat());
		setStaticColor(json.get("staticColorRGB").getAsInt());
	}
}
