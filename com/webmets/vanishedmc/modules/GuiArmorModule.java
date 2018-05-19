package com.webmets.vanishedmc.modules;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

import com.webmets.vanishedmc.VanishedMC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class GuiArmorModule {

	// Variables
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private VanishedMC client = VanishedMC.instance;

	// Settings
	private boolean enabled = true;
	private float scale = 1f;
	private Vector2f helmetPos = new Vector2f(0, 0);
	private Vector2f chestplatePos = new Vector2f(0, 15);
	private Vector2f legginPos = new Vector2f(0, 30);
	private Vector2f bootsPos = new Vector2f(0, 45);

	public void render(int x, int y) {
		if (!enabled) {
			return;
		}

		ItemStack helmet = mc.thePlayer.inventory.armorInventory[3];
		ItemStack chestplate = mc.thePlayer.inventory.armorInventory[2];
		ItemStack leggings = mc.thePlayer.inventory.armorInventory[1];
		ItemStack boots = mc.thePlayer.inventory.armorInventory[0];

		if (helmet != null) {
			drawItemStack(helmet, x + (int) helmetPos.x, y + (int) helmetPos.y, scale);
		}
		if (chestplate != null) {
			drawItemStack(chestplate, x + (int) chestplatePos.x, y + (int) chestplatePos.y, scale);
		}
		if (leggings != null) {
			drawItemStack(leggings, x + (int) legginPos.x, y + (int) legginPos.y, scale);
		}
		if (boots != null) {
			drawItemStack(boots, x + (int) bootsPos.x, y + (int) bootsPos.y, scale);
		}
	}

	// Getters
	public boolean isEnabled() {
		return enabled;
	}

	public float getScale() {
		return scale;
	}

	public Vector2f getHelmetPos() {
		return helmetPos;
	}

	public Vector2f getChestplatePos() {
		return chestplatePos;
	}

	public Vector2f getLegginPos() {
		return legginPos;
	}

	public Vector2f getBootsPos() {
		return bootsPos;
	}

	// Setters
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void setHelmetPos(Vector2f helmetPos) {
		this.helmetPos = helmetPos;
	}

	public void setChestplatePos(Vector2f chestplatePos) {
		this.chestplatePos = chestplatePos;
	}

	public void setLegginPos(Vector2f legginPos) {
		this.legginPos = legginPos;
	}

	public void setBootsPos(Vector2f bootsPos) {
		this.bootsPos = bootsPos;
	}

	// Render methods
	private void drawItemStack(ItemStack stack, int x, int y, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(scale, scale, scale);
		{
			GL11.glPushMatrix();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.translate(0, 0, 0);
			mc.getRenderItem().func_180450_b(stack, 0, 0);
			mc.getRenderItem().func_175030_a(mc.fontRendererObj, stack, 0, 0);
			GL11.glPopMatrix();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
		GL11.glPopMatrix();
	}

}
