package com.webmets.vanishedmc.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

import com.google.common.collect.Lists;
import com.webmets.vanishedmc.VanishedMC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;

public class CustomMainMenu extends GuiMainMenu {

	/**
	 * The customized main menu, currently not touched. 
	 * Extending the base Minecraft class to minimize copied code.
	 * 
	 * The customized GUI can be toggled on or off with one boolean, and will have direct
	 * */
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GlStateManager.disableAlpha();
		this.renderSkybox(mouseX, mouseY, partialTicks);
		GlStateManager.enableAlpha();
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		short var6 = 274;
		int var7 = this.width / 2 - var6 / 2;
		byte var8 = 30;
		this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
		this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if ((double) this.updateCounter < 1.0E-4D) {
			this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 99, 44);
			this.drawTexturedModalRect(var7 + 99, var8 + 0, 129, 0, 27, 44);
			this.drawTexturedModalRect(var7 + 99 + 26, var8 + 0, 126, 0, 3, 44);
			this.drawTexturedModalRect(var7 + 99 + 26 + 3, var8 + 0, 99, 0, 26, 44);
			this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
		} else {
			this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 155, 44);
			this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
		}

		var5.func_178991_c(-1);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
		GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
		float var9 = 1.8F - MathHelper.abs(
				MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
		var9 = var9 * 100.0F / (float) (this.fontRendererObj.getStringWidth(this.splashText) + 32);
		GlStateManager.scale(var9, var9, var9);
		this.drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, -256);
		GlStateManager.popMatrix();
		String var10 = "VanishedMC - by Webmets "
				+ (VanishedMC.instance.isUpdateAvailable() ? "(Update available)" : "");

		if (this.mc.isDemo()) {
			var10 = var10 + " Demo";
		}

		this.drawString(this.fontRendererObj, var10, 2, this.height - 10, -1);
		String var11 = "Copyright Mojang AB. Do not distribute!";
		this.drawString(this.fontRendererObj, var11, this.width - this.fontRendererObj.getStringWidth(var11) - 2,
				this.height - 10, -1);

		String credits = "VanishedMC - by Webmets";

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
