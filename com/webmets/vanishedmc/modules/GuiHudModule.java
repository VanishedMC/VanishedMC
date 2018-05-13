package com.webmets.vanishedmc.modules;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.controllers.MouseController;
import com.webmets.vanishedmc.settings.GuiHudCOORDSView;
import com.webmets.vanishedmc.settings.GuiHudCPSView;
import com.webmets.vanishedmc.utils.EffectUtils;
import com.webmets.vanishedmc.utils.ping.PingUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import optifine.CustomColors;

public class GuiHudModule {

	/**
	 * The main HUD module, showing FPS,CPS, and similair game stats
	 */

	// Variables
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private VanishedMC client = VanishedMC.instance;

	// Settings
	private String name = "&cVanishedMC";
	private boolean showFPS = true;
	private boolean showCOORDS = true;
	private boolean CoordsOneLine = false;
	private boolean showCPS = true;
	private boolean showPING = true;
	private GuiHudCPSView cpsView = GuiHudCPSView.SEPARATE;
	private GuiHudCOORDSView coordsView = GuiHudCOORDSView.COMPACT;

	public void render(int x, int y) {
		name = "&lVanishedMC";
		int offset = 0;
		if (!name.isEmpty()) {
			if (name.contains("&")) {
				fr.drawString(name.replace("&", "§"), x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
			} else {
				fr.drawString(name, x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
			}
		}
		if (isShowFPS()) {
			int fps = Minecraft.debugFPS;
			fr.drawString("Fps " + fps, x, y+offset, EffectUtils.getColorForY(y,offset));
			offset += 10;
		}
		if (isShowCPS()) {
			MouseController mouse = client.getMouseController();
			String cps = client.getMouseController().getCps(cpsView);
			fr.drawString("Cps " + cps, x, y + offset, EffectUtils.getColorForY(y,offset));
			offset += 10;
		}
		if (isShowCOORDS()) {
			String xCoord = "";
			String yCoord = "";
			String zCoord = "";

			switch (coordsView) {
			case SMALL:
				xCoord = String.format("%.0f", (float) Minecraft.getMinecraft().thePlayer.posX);
				yCoord = String.format("%.0f", (float) Minecraft.getMinecraft().thePlayer.posY);
				zCoord = String.format("%.0f", (float) Minecraft.getMinecraft().thePlayer.posZ);
				break;
			case COMPACT:
				xCoord = String.format("%.1f", (float) Minecraft.getMinecraft().thePlayer.posX);
				yCoord = String.format("%.1f", (float) Minecraft.getMinecraft().thePlayer.posY);
				zCoord = String.format("%.1f", (float) Minecraft.getMinecraft().thePlayer.posZ);
				break;
			case EXPANDED:
				xCoord = String.format("%.2f", (float) Minecraft.getMinecraft().thePlayer.posX);
				yCoord = String.format("%.2f", (float) Minecraft.getMinecraft().thePlayer.posY);
				zCoord = String.format("%.2f", (float) Minecraft.getMinecraft().thePlayer.posZ);
				break;
			}
			if (isCoordsOneLine()) {
				fr.drawString("x " + xCoord + ", y " + yCoord + ", z " + zCoord, x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
			} else {
				fr.drawString("x " + xCoord, x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
				fr.drawString("y " + yCoord, x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
				fr.drawString("z " + zCoord, x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
			}
			if (isShowPING()) {
				fr.drawString("Ping: " + PingUtils.getPing(), x, y + offset, EffectUtils.getColorForY(y,offset));
				offset += 10;
			}
			for(int i = 1; i <= 20; i++) {
				fr.drawString(i+"", x, y+offset, EffectUtils.getColorForY(y,offset));
				offset+=10;
			}
		}

	}

	// Getters
	public boolean isShowCOORDS() {
		return showCOORDS;
	}

	public boolean isShowFPS() {
		return showFPS;
	}

	public boolean isCoordsOneLine() {
		return CoordsOneLine;
	}

	public boolean isShowCPS() {
		return showCPS;
	}

	public boolean isShowPING() {
		return showPING;
	}

	public GuiHudCPSView getCpsView() {
		return cpsView;
	}

	public GuiHudCOORDSView getCoordsView() {
		return coordsView;
	}

	// Setters
	public void setCpsView(GuiHudCPSView cpsView) {
		this.cpsView = cpsView;
	}

	public void setCoordsOneLine(boolean coordsOneLine) {
		this.CoordsOneLine = coordsOneLine;
	}

	public void setCoordsView(GuiHudCOORDSView coordsView) {
		this.coordsView = coordsView;
	}

	public void setShowCOORDS(boolean showCOORDS) {
		this.showCOORDS = showCOORDS;
	}

	public void setShowCPS(boolean showCPS) {
		this.showCPS = showCPS;
	}

	public void setShowFPS(boolean showFPS) {
		this.showFPS = showFPS;
	}

	public void setShowPING(boolean showPING) {
		this.showPING = showPING;
	}
}
