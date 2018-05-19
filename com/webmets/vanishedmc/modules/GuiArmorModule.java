package com.webmets.vanishedmc.modules;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

import com.google.gson.JsonObject;
import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.settings.Configurable;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiArmorModule implements Configurable {

	// Variables
	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer fr = mc.fontRendererObj;
	private VanishedMC client = VanishedMC.instance;

	// Settings
	private boolean enabled = true;
	private boolean showDurability = true;
	private boolean showHand = true;
	private boolean showArrows = true;
	private float scale = 1f;

	private Vector2f helmetPos = new Vector2f(0, 0);
	private Vector2f chestplatePos = new Vector2f(0, 15);
	private Vector2f leggingPos = new Vector2f(0, 30);
	private Vector2f bootsPos = new Vector2f(0, 45);
	private Vector2f handPos = new Vector2f(0, 50);
	private Vector2f arrowPos = new Vector2f(0, 65);

	public void render() {
		if (!enabled) {
			return;
		}

		// Item stacks
		ItemStack helmet = mc.thePlayer.inventory.armorInventory[3];
		ItemStack chestplate = mc.thePlayer.inventory.armorInventory[2];
		ItemStack leggings = mc.thePlayer.inventory.armorInventory[1];
		ItemStack boots = mc.thePlayer.inventory.armorInventory[0];
		ItemStack hand = mc.thePlayer.inventory.getCurrentItem();
		int arrowCount = 0;
		for (ItemStack item : mc.thePlayer.inventory.mainInventory) {
			if (item != null) {
				if (Item.getIdFromItem(item.getItem()) == 262) {
					arrowCount += item.stackSize;
				}
			}
		}
		ItemStack arrows = new ItemStack(Item.getByNameOrId("262"), arrowCount);

		// Draw item stacks
		if (helmet != null) {
			drawItemStack(helmet, helmetPos.x, helmetPos.y, scale);
		}
		if (chestplate != null) {
			drawItemStack(chestplate, chestplatePos.x, chestplatePos.y, scale);
		}
		if (leggings != null) {
			drawItemStack(leggings, leggingPos.x, leggingPos.y, scale);
		}
		if (boots != null) {
			drawItemStack(boots, bootsPos.x, bootsPos.y, scale);
		}
		if (hand != null && isShowHand()) {
			drawItemStack(hand, handPos.x, handPos.y, scale);
		}
		if (arrows.stackSize > 0 && isShowArrows()) {
			drawItemStack(arrows, arrowPos.x, arrowPos.y, scale);
		}
	}

	// Getters
	public boolean isShowArrows() {
		return showArrows;
	}

	public boolean isShowDurability() {
		return showDurability;
	}

	public boolean isShowHand() {
		return showHand;
	}

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

	public Vector2f getleggingPos() {
		return leggingPos;
	}

	public Vector2f getBootsPos() {
		return bootsPos;
	}

	public Vector2f getHandPos() {
		return handPos;
	}

	public Vector2f getArrowPos() {
		return arrowPos;
	}

	// Setters
	public void setShowArrows(boolean showArrows) {
		this.showArrows = showArrows;
	}

	public void setShowDurability(boolean showDurability) {
		this.showDurability = showDurability;
	}

	public void setShowHand(boolean showHand) {
		this.showHand = showHand;
	}

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

	public void setleggingPos(Vector2f leggingPos) {
		this.leggingPos = leggingPos;
	}

	public void setBootsPos(Vector2f bootsPos) {
		this.bootsPos = bootsPos;
	}

	public void setHandPos(Vector2f handPos) {
		this.handPos = handPos;
	}

	public void setArrowPos(Vector2f arrowPos) {
		this.arrowPos = arrowPos;
	}

	// Render methods
	private void drawItemStack(ItemStack stack, float x, float y, float scale) {
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
			if (showDurability) {
				mc.getRenderItem().func_175030_a(mc.fontRendererObj, stack, 0, 0);
			}
			GL11.glPopMatrix();
			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
		GL11.glPopMatrix();
	}

	@Override
	public JsonObject getSettings() {
		JsonObject armorhud = new JsonObject();
		armorhud.addProperty("enabled", isEnabled());
		armorhud.addProperty("durability", isShowDurability());
		armorhud.addProperty("showHand", isShowHand());
		armorhud.addProperty("showArrows", isShowArrows());
		armorhud.addProperty("scale", getScale());

		armorhud.addProperty("helmetPos", Utils.serializeVector(helmetPos));
		armorhud.addProperty("chestplatePos", Utils.serializeVector(chestplatePos));
		armorhud.addProperty("leggingsPos", Utils.serializeVector(leggingPos));
		armorhud.addProperty("bootsPos", Utils.serializeVector(bootsPos));
		armorhud.addProperty("handPos", Utils.serializeVector(handPos));
		armorhud.addProperty("arrowPos", Utils.serializeVector(arrowPos));
		return armorhud;
	}

	@Override
	public void loadSettings(JsonObject json) {
		setEnabled(json.get("enabled").getAsBoolean());
		setShowDurability(json.get("durability").getAsBoolean());
		setShowHand(json.get("showHand").getAsBoolean());
		setShowArrows(json.get("showArrows").getAsBoolean());
		setScale(json.get("scale").getAsFloat());

		setHelmetPos(Utils.deSerializeVector(json.get("helmetPos").getAsString()));
		setChestplatePos(Utils.deSerializeVector(json.get("chestplatePos").getAsString()));
		setleggingPos(Utils.deSerializeVector(json.get("leggingsPos").getAsString()));
		setBootsPos(Utils.deSerializeVector(json.get("bootsPos").getAsString()));
		setHandPos(Utils.deSerializeVector(json.get("handPos").getAsString()));
		setArrowPos(Utils.deSerializeVector(json.get("arrowPos").getAsString()));
	}

	@Override
	public String getKey() {
		return "armorhud";
	}
}
