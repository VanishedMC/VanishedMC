package com.webmets.vanishedmc.modules;

import com.webmets.vanishedmc.settings.GuiHudCOORDSView;
import com.webmets.vanishedmc.settings.GuiHudCPSView;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class GuiHudModule {

	/**
	 * The main HUD module, showing FPS,CPS, and similair game stats
	 * */
	
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private boolean showFPS = true;
	private boolean showCOORDS = true;
	private boolean showCPS = true;
	private boolean showPING = true;
	private GuiHudCPSView cpsView = GuiHudCPSView.SEPARATE;
	private GuiHudCOORDSView coordsView = GuiHudCOORDSView.COMPACT;

	public void render(int x, int y) {
		coordsView = GuiHudCOORDSView.COMPACT;
		int offset = 0;
		if (isShowFPS()) {
			int fps = Minecraft.debugFPS;
			fr.drawString("Fps " + fps, x, y, -1);
			offset += 10;
		}
		if (isShowCPS()) {
			// TODO working cps counter
			fr.drawString("Cps 0", x, y + offset, -1);
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
			fr.drawString("x " + xCoord, x, y + offset, -1);
			offset += 10;
			fr.drawString("y " + yCoord, x, y + offset, -1);
			offset += 10;
			fr.drawString("z " + zCoord, x, y + offset, -1);
			offset += 10;
		}

	}

	public boolean isShowCOORDS() {
		return showCOORDS;
	}

	public boolean isShowFPS() {
		return showFPS;
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

	public void setCpsView(GuiHudCPSView cpsView) {
		this.cpsView = cpsView;
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
