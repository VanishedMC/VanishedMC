package com.webmets.vanishedmc.modules;

import java.util.ArrayList;
import java.util.List;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.controllers.MouseController;
import com.webmets.vanishedmc.settings.GuiHudCOORDSView;
import com.webmets.vanishedmc.settings.GuiHudCPSView;
import com.webmets.vanishedmc.utils.EffectUtils;
import com.webmets.vanishedmc.utils.ping.PingUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class GuiHudModule {

	/**
	 * The main HUD module, showing FPS,CPS, and similair game stats
	 */

	// Variables
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private VanishedMC client = VanishedMC.instance;

	// Settings
	private String name = "&lVanishedMC";
	private boolean showFPS = true;
	private boolean showCOORDS = true;
	private boolean CoordsOneLine = false;
	private boolean showCPS = true;
	private boolean showPING = true;
	private boolean isLowerCase = false;
	private GuiHudCPSView cpsView = GuiHudCPSView.SEPARATE;
	private GuiHudCOORDSView coordsView = GuiHudCOORDSView.COMPACT;

	public void render(int x, int y) {
		setLowerCase(false);
		List<String> toRender = new ArrayList<>();
		int offset = 0;

		if (!name.isEmpty()) {
			toRender.add(name);
		}

		if (isShowFPS()) {
			int fps = Minecraft.debugFPS;
			toRender.add("Fps " + fps);
		}

		if (isShowCPS()) {
			MouseController mouse = client.getMouseController();
			String cps = client.getMouseController().getCps(cpsView);
			toRender.add("Cps " + cps);
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
				toRender.add("X " + xCoord + ", Y " + yCoord + ", Z " + zCoord);
			} else {
				toRender.add("X " + xCoord);
				toRender.add("Y " + yCoord);
				toRender.add("Z " + zCoord);
			}
		}

		if (isShowPING()) {
			toRender.add("Ping " + PingUtils.getPing());
		}

		for (String s : toRender) {
			if(isLowerCase()) {
				fr.drawString(s.replace("&", "§").toLowerCase(), x, y + offset, EffectUtils.getColorForY(y, offset));
			} else {
				fr.drawString(s.replace("&", "§"), x, y + offset, EffectUtils.getColorForY(y, offset));
			}
			offset += 10;
		}
		toRender = null;
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
	
	public boolean isLowerCase() {
		return isLowerCase;
	}

	// Setters
	public void setCpsView(GuiHudCPSView cpsView) {
		this.cpsView = cpsView;
	}
	
	public void setLowerCase(boolean isLowerCase) {
		this.isLowerCase = isLowerCase;
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
