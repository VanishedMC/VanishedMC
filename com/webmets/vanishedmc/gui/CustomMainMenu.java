package com.webmets.vanishedmc.gui;

import java.awt.Color;
import java.io.IOException;
import java.net.URI;

import org.lwjgl.opengl.GL11;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.buttons.CustomButton;
import com.webmets.vanishedmc.gui.settings.SettingsGui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class CustomMainMenu extends GuiMainMenu {

	/**
	 * The customized main menu, currently not touched. Extending the base
	 * Minecraft class to minimize copied code.
	 * 
	 * The customized GUI can be toggled on or off with one boolean, and will
	 * have direct
	 */

	@Override
	protected void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
		int var3 = this.height / 4 + 48;
		int y = 2;
		this.buttonList.add(new CustomButton(0, 10, y + 5 + p_73969_2_ * 1, "Options"));
		this.buttonList.add(new CustomButton(1, this.width / 2 - 100, y + 5, "Singleplayer"));
		this.buttonList.add(new CustomButton(2, this.width / 2 - 100, y + 5 + p_73969_2_ * 1, "Multiplayer"));
		this.buttonList.add(new CustomButton(4, this.width - 205, y + 5 + p_73969_2_ * 1, "Quit Game"));
		this.buttonList.add(new CustomButton(5, 10, y + 5, "Settings"));
		this.buttonList.add(new CustomButton(6, this.width - 205, y + 5, "Website"));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 0) { // settings
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if (button.id == 5) { // settings
			// this.mc.displayGuiScreen(new ConfigGui(this, true));
			this.mc.displayGuiScreen(new SettingsGui());
		}

		if (button.id == 1) { // singleplayer
			this.mc.displayGuiScreen(new GuiSelectWorld(this));
		}

		if (button.id == 2) { // multiplayer
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
		}

		if (button.id == 4) { // exit
			this.mc.shutdown();
		}

		if (button.id == 6) { // Website
			try {
				Class var3 = Class.forName("java.awt.Desktop");
				Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
				var3.getMethod("browse", new Class[] { URI.class }).invoke(var4,
						new Object[] { new URI("https://vanishedmc.com/") });
			} catch (Throwable var5) {
				logger.error("Couldn\'t open link", var5);
			}
		}
	}

	@Override
	protected void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_) {
		this.mc.getFramebuffer().unbindFramebuffer();
		GlStateManager.viewport(0, 0, 256, 256);
		this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
		for (int i = 0; i < 21; i++) {
			rotateAndBlurSkybox(p_73971_3_);
		}
		this.mc.getFramebuffer().bindFramebuffer(true);
		GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		var5.startDrawingQuads();
		float var6 = this.width > this.height ? 120.0F / (float) this.width : 120.0F / (float) this.height;
		float var7 = (float) this.height * var6 / 256.0F;
		float var8 = (float) this.width * var6 / 256.0F;
		var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
		int var9 = this.width;
		int var10 = this.height;
		var5.addVertexWithUV(0.0D, (double) var10, (double) this.zLevel, (double) (0.5F - var7),
				(double) (0.5F + var8));
		var5.addVertexWithUV((double) var9, (double) var10, (double) this.zLevel, (double) (0.5F - var7),
				(double) (0.5F - var8));
		var5.addVertexWithUV((double) var9, 0.0D, (double) this.zLevel, (double) (0.5F + var7), (double) (0.5F - var8));
		var5.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, (double) (0.5F + var7), (double) (0.5F + var8));
		var4.draw();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.disableAlpha();
		this.renderSkybox(mouseX, mouseY, partialTicks);
		GlStateManager.enableAlpha();
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		short var6 = 274;
		int var7 = this.width / 2 - var6 / 2;
		byte var8 = 70;
		this.drawGradientRect(0, 0, this.width, this.height / 2, -2130706433, 16777215);
		this.drawGradientRect(this.width, this.height, 0, this.height / 3, Color.red.getRGB(), 16777215);
		this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		GL11.glPushMatrix();
		GL11.glScalef(4.5f, 4.5f, 1.0f);
		final String logo = "VanishedMC Client";
		this.drawCenteredString(this.fontRendererObj, logo, this.width / 9, this.height / 15, 16711680);
		GL11.glPopMatrix();

		var5.func_178991_c(-1);

		String var10 = "VanishedMC " + VanishedMC.instance.getCurrentVersion() + " - Made by Webmets";

		this.drawString(this.fontRendererObj, var10, 2, this.height - 10, 16711680);
		String var11 = "Copyright Mojang AB. Do not distribute!";
		this.drawString(this.fontRendererObj, var11, this.width - this.fontRendererObj.getStringWidth(var11) - 2,
				this.height - 10, 16711680);

		if (this.field_92025_p != null && this.field_92025_p.length() > 0) {
			drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1,
					1428160512);
			this.drawString(this.fontRendererObj, this.field_92025_p, this.field_92022_t, this.field_92021_u, -1);
			this.drawString(this.fontRendererObj, this.field_146972_A, (this.width - this.field_92024_r) / 2,
					((GuiButton) this.buttonList.get(0)).yPosition - 12, -1);
		}

		drawButtons(mouseX, mouseY);
	}

	// Some base minecraft code required to render buttons, because super method
	// can not be used.
	private void drawButtons(int mouseX, int mouseY) {
		int var4;

		for (var4 = 0; var4 < this.buttonList.size(); ++var4) {
			((GuiButton) this.buttonList.get(var4)).drawButton(this.mc, mouseX, mouseY);
		}

		for (var4 = 0; var4 < this.labelList.size(); ++var4) {
			((GuiLabel) this.labelList.get(var4)).drawLabel(this.mc, mouseX, mouseY);
		}
	}
}
