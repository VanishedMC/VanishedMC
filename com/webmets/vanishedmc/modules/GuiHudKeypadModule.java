package com.webmets.vanishedmc.modules;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.GuiIngameHook;
import com.webmets.vanishedmc.utils.EffectUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class GuiHudKeypadModule {

	// Variables
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private VanishedMC client = VanishedMC.instance;

	// Settings
	private boolean showMouseButtons = true;
	private boolean spaceBar = true;
	private boolean showCps = true;
	private boolean cpsInLine = false;
	private float size = 35;
	private float scale = 1.5f;
	private int distanceBetween = 1;
	private int keyPressedColor = 0xaacccccc;
	private int keyReleasedColor = 0xaa1c1a1a;

	public void render(int x, int y) {
		// W
		drawCustomRect(x + getDistanceBetween() + getSize(), y, x + (getSize() * 2) + getDistanceBetween(),
				y + getSize(), getColorForKey(Keyboard.KEY_W));

		// A
		drawCustomRect(x, y + getDistanceBetween() + getSize(), x + getSize(),
				y + (getSize() * 2) + getDistanceBetween(), getColorForKey(Keyboard.KEY_A));
		// S
		drawCustomRect(x + getDistanceBetween() + getSize(), y + getDistanceBetween() + getSize(),
				x + (getSize() * 2) + getDistanceBetween(), y + (getSize() * 2) + getDistanceBetween(),
				getColorForKey(Keyboard.KEY_S));
		// D
		drawCustomRect(x + (getSize() * 2) + (getDistanceBetween() * 2), y + getDistanceBetween() + getSize(),
				x + (getSize() * 3) + (getDistanceBetween() * 2), y + (getSize() * 2) + getDistanceBetween(),
				getColorForKey(Keyboard.KEY_D));

		if (isSpaceBar()) {
			// SPACEBAR
			drawCustomRect(x, y + (getDistanceBetween() * 2) + (getSize() * 2),
					x + (getSize() * 3) + (getDistanceBetween() * 2),
					y + (getDistanceBetween() * 2) + (getSize() * 2) + (getSize() / 2),
					getColorForKey(Keyboard.KEY_SPACE));
		}

		if (isShowMouseButtons()) {
			float startHeight = isSpaceBar() ? y + (getDistanceBetween() * 3) + (getSize() * 3)
					: y + (getDistanceBetween() * 2) + (getSize() * 2);
			float endHeight = isSpaceBar() ? y + (getDistanceBetween() * 3) + (getSize() * 2) + (getSize() / 2)
					: y + (getDistanceBetween() * 2) + (getSize() * 2) + (getSize() / 2);
			// LMB
			drawCustomRect(x, startHeight, x + (getSize() * 2) - (getSize() / 2) + (getDistanceBetween() * 0.5f),
					endHeight, Mouse.isButtonDown(0) && mc.inGameHasFocus ? getKeyPressedColor() : getKeyReleasedColor());

			// RMB
			drawCustomRect(x + ((getSize() * 2) - (getSize() / 2)) + (getDistanceBetween() * 1.5f), startHeight,
					x + (getSize() * 3) + (getDistanceBetween() * 2), endHeight,
					Mouse.isButtonDown(1) && mc.inGameHasFocus ? getKeyPressedColor() : getKeyReleasedColor());
		}

		float wX = ((x + getDistanceBetween() + getSize()) + (x + (getSize() * 2) + getDistanceBetween())) / 2;
		float wY = ((y) + (y + getSize() / 1.5f)) / 2;

		float aX = ((x) + (x + getSize())) / 2;
		float aY = ((y + getDistanceBetween() + getSize() / 1.5f) + (y + (getSize() * 2) + getDistanceBetween())) / 2;

		float sX = ((x + getDistanceBetween() + getSize()) + (x + (getSize() * 2) + getDistanceBetween())) / 2;
		float sY = ((y + getDistanceBetween() + getSize() / 1.5f) + (y + (getSize() * 2) + getDistanceBetween())) / 2;

		float dX = ((x + (getSize() * 2) + (getDistanceBetween() * 2))
				+ (x + (getSize() * 3) + (getDistanceBetween() * 2))) / 2;
		float dY = ((y + getDistanceBetween() + getSize() / 1.5f) + (y + (getSize() * 2) + getDistanceBetween())) / 2;

		float startHeight = isSpaceBar() ? y + (getDistanceBetween() * 3) + (getSize() * 3 / 1.1f)
				: y + (getDistanceBetween() * 2) + (getSize() * 2 / 1.15f);
		float endHeight = isSpaceBar() ? y + (getDistanceBetween() * 3) + (getSize() * 2) + (getSize() / 2)
				: y + (getDistanceBetween() * 2) + (getSize() * 2) + (getSize() / 2);

		float spaceX = ((x) + (x + (getSize() * 3) + (getDistanceBetween() * 2))) / 2;
		float spaceY = ((y + (getDistanceBetween() * 2) + (getSize() * 2 / 1.2f)) + y + (getDistanceBetween() * 2)
				+ (getSize() * 2) + (getSize() / 2)) / 2;

		float leftMouseX = ((x) + (x + (getSize() * 2) - (getSize() / 2) + (getDistanceBetween() * 0.5f))) / 2;
		float leftMouseY = ((startHeight) + (endHeight)) / 2;

		float rightMouseX = ((x + ((getSize() * 2) - (getSize() / 2)) + (getDistanceBetween() * 1.5f))
				+ (x + (getSize() * 3) + (getDistanceBetween() * 2))) / 2;
		float rightMouseY = ((startHeight) + (endHeight)) / 2;

		GL11.glPushMatrix();
		GL11.glTranslatef(wX, wY, 0);
		GL11.glScalef(scale, scale, scale);
		drawCenteredString("W", 0, 0, EffectUtils.getColorForY(y, 0));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(aX, aY, 0);
		GL11.glScalef(scale, scale, scale);
		drawCenteredString("A", 0, 0, EffectUtils.getColorForY(y, 20));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(sX, sY, 0);
		GL11.glScalef(scale, scale, scale);
		drawCenteredString("S", 0, 0, EffectUtils.getColorForY(y, 20));
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef(dX, dY, 0);
		GL11.glScalef(scale, scale, scale);
		drawCenteredString("D", 0, 0, EffectUtils.getColorForY(y, 20));
		GL11.glPopMatrix();

		if (isSpaceBar()) {
			GL11.glPushMatrix();
			GL11.glTranslatef(spaceX, spaceY, 0);
			GL11.glScalef(scale, scale, scale);
			drawCenteredString("§m------", 0, 0, EffectUtils.getColorForY(y, 40));
			GL11.glPopMatrix();
		}

		if (isShowMouseButtons()) {
			String lmb = "LBM";
			String rmb = "RMB";
			
			if(isShowCps()) {
				if(isCpsInLine()) {
					lmb += " " + client.getMouseController().getLeftCPS();
					rmb += " " + client.getMouseController().getRightCPS();
				} else {
					lmb += "~" + client.getMouseController().getLeftCPS();
					rmb += "~" + client.getMouseController().getRightCPS();
				}
			}
			GL11.glPushMatrix();
			GL11.glTranslatef(leftMouseX, leftMouseY, 0);
			if(lmb.contains("~")) {
				GL11.glScalef(scale / 1.7f, scale / 1.7f, 0);
				drawCenteredString(lmb.split("~")[0], 0, -2, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
				GL11.glScalef(scale / 2f, scale / 2f, 0);
				drawCenteredString(lmb.split("~")[1], 0, 9, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
			} else {
				GL11.glScalef(scale / 1.5f, scale / 1.5f, 0);
				drawCenteredString(lmb, 0, 0, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
			}
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslatef(rightMouseX, rightMouseY, 0);
			if(rmb.contains("~")) {
				GL11.glScalef(scale / 1.7f, scale / 1.7f, 0);
				drawCenteredString(rmb.split("~")[0], 0, -2, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
				GL11.glScalef(scale / 2f, scale / 2f, 0);
				drawCenteredString(rmb.split("~")[1], 0, 9, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
			} else {
				GL11.glScalef(scale / 1.5f, scale / 1.5f, 0);
				drawCenteredString(rmb, 0, 0, EffectUtils.getColorForY(y, isSpaceBar() ? 60 : 40));
			}
			GL11.glPopMatrix();
		}
	}

	private int getColorForKey(int key) {
		if (Keyboard.isKeyDown(key) && mc.inGameHasFocus) {
			return getKeyPressedColor();
		} else {
			return getKeyReleasedColor();
		}
	}

	// Getters
	public boolean isShowMouseButtons() {
		return showMouseButtons;
	}

	public boolean isSpaceBar() {
		return spaceBar;
	}

	public boolean isShowCps() {
		return showCps;
	}

	public boolean isCpsInLine() {
		return cpsInLine;
	}

	public float getSize() {
		return size;
	}

	public int getDistanceBetween() {
		return distanceBetween;
	}

	public int getKeyPressedColor() {
		return keyPressedColor;
	}

	public int getKeyReleasedColor() {
		return keyReleasedColor;
	}
	
	public float getScale() {
		return scale;
	}

	// Setters
	public void setShowMouseButtons(boolean showMouseButtons) {
		this.showMouseButtons = showMouseButtons;
	}

	public void setSpaceBar(boolean spaceBar) {
		this.spaceBar = spaceBar;
	}

	public void setShowCps(boolean showCps) {
		this.showCps = showCps;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setDistanceBetween(int distanceBetween) {
		this.distanceBetween = distanceBetween;
	}

	public void setKeyPressedColor(int keyPressedColor) {
		this.keyPressedColor = keyPressedColor;
	}

	public void setKeyReleasedColor(int keyReleasedColor) {
		this.keyReleasedColor = keyReleasedColor;
	}
	
	public void setCpsInLine(boolean cpsInLine) {
		this.cpsInLine = cpsInLine;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}

	// Render methods
	private void drawCustomRect(float f, float i, float g, float h, int insideC) {
		GuiIngameHook.drawRect((int) f + 0, (int) (i + 0), (int) g - 0, (int) (h - 0), insideC);
		GuiIngameHook.drawRect((int) f + 0, (int) i + 0, (int) g, (int) i, 0);
		GuiIngameHook.drawRect((int) f, (int) i, (int) f + 0, (int) h, 0);
		GuiIngameHook.drawRect((int) g, (int) h, (int) g - 0, (int) i + 0, 0);
		GuiIngameHook.drawRect((int) f, (int) h - 0, (int) g, (int) h, 0);
	}

	public void drawCenteredString(String text, float f, float y, int color) {
		fr.func_175063_a(text, (float) (f - fr.getStringWidth(text) / 2), (float) y, color);
	}
}
