package com.webmets.vanishedmc.gui;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class ChatGui extends GuiNewChat {

	private int currentMenu = 0;
	private List guildChat = Lists.newArrayList();
	private List partyChat = Lists.newArrayList();
	private List privateChat = Lists.newArrayList();

	public ChatGui(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void drawChat(int p_146230_1_) {
		List chat = currentMenu == 0 ? this.field_146253_i
				: currentMenu == 1 ? guildChat : currentMenu == 2 ? partyChat : privateChat;
		if (!Utils.onHypixel()) {
			chat = this.field_146253_i;
		}
		if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
			int var2 = this.getLineCount();
			boolean var3 = false;
			int var4 = 0;
			int var5 = chat.size();
			float var6 = this.mc.gameSettings.chatOpacity * 0.9F + 0.1F;

			if (var5 > 0) {
				if (this.getChatOpen()) {
					var3 = true;
				}

				float var7 = this.getChatScale();
				int var8 = MathHelper.ceiling_float_int((float) this.getChatWidth() / var7);
				GlStateManager.pushMatrix();
				GlStateManager.translate(2.0F, 20.0F, 0.0F);
				GlStateManager.scale(var7, var7, 1.0F);
				int var9;
				int var11;
				int var14;

				for (var9 = 0; var9 + this.scrollPos < chat.size() && var9 < var2; ++var9) {
					ChatLine var10 = (ChatLine) chat.get(var9 + this.scrollPos);

					if (var10 != null) {
						var11 = p_146230_1_ - var10.getUpdatedCounter();

						if (var11 < 200 || var3) {
							double var12 = (double) var11 / 200.0D;
							var12 = 1.0D - var12;
							var12 *= 10.0D;
							var12 = MathHelper.clamp_double(var12, 0.0D, 1.0D);
							var12 *= var12;
							var14 = (int) (255.0D * var12);

							if (var3) {
								var14 = 255;
							}

							var14 = (int) ((float) var14 * var6);
							++var4;

							if (var14 > 3) {
								byte var15 = 0;
								int var16 = -var9 * 9;
								drawRect(var15, var16 - 9, var15 + var8 + 4, var16, var14 / 2 << 24);
								String var17 = var10.getChatComponent().getFormattedText();
								GlStateManager.enableBlend();
								this.mc.fontRendererObj.func_175063_a(var17, (float) var15, (float) (var16 - 8),
										16777215 + (var14 << 24));
								GlStateManager.disableAlpha();
								GlStateManager.disableBlend();
							}
						}
					}
				}
				if (var3) {
					var9 = this.mc.fontRendererObj.FONT_HEIGHT;
					GlStateManager.translate(-3.0F, 0.0F, 0.0F);
					int var18 = var5 * var9 + var5;
					var11 = var4 * var9 + var4;
					int var19 = this.scrollPos * var11 / var5;
					int var13 = var11 * var11 / var18;

					if (var18 != var11) {
						var14 = var19 > 0 ? 170 : 96;
						int var20 = this.isScrolled ? 13382451 : 3355562;
						drawRect(0, -var19, 2, -var19 - var13, var20 + (var14 << 24));
						drawRect(2, -var19, 1, -var19 - var13, 13421772 + (var14 << 24));
					}
				}

				GlStateManager.popMatrix();
			}
		}
		boolean betterChat = Utils.onHypixel();
		if(betterChat) {
			renderChatTabs(chat);
		} else {
			currentMenu = 0;
		}
	}

	private void renderChatTabs(List chat) {
		if (this.getChatOpen() && chat.size() > 0 || currentMenu != 0) {
			GL11.glPopMatrix();
			if (getChatOpen()) {
				int y = mc.displayHeight / 2 - 26;
				for (int i = 0; i < 4; i++) {
					this.drawRect(i * 50 + (i == 0 ? 2 : 1), y, i * 50 + 50, y + 10,
							currentMenu == i ? 0x80000000 : getHoveredButton() == i ? 0x80000000 : 0x90424242);
					String text = "";
					if (i == 0) {
						text = "All";
					} else if (i == 1) {
						text = "Guild";
					} else if (i == 2) {
						text = "Party";
					} else if (i == 3) {
						text = "Private";
					}
					mc.fontRendererObj.func_175063_a(text, (float) ((i * 50 + (i == 0 ? 2 : 1) + i * 50 + 50) / 2
							- mc.fontRendererObj.getStringWidth(text) / 2), (float) y + 1, -1);
				}
				clickEvent();
			}
			GL11.glPushMatrix();
		}
	}

	@Override
	public void setChatLine(IChatComponent p_146237_1_, int p_146237_2_, int p_146237_3_, boolean p_146237_4_) {
		if (p_146237_2_ != 0) {
			this.deleteChatLine(p_146237_2_);
		}

		int var5 = MathHelper.floor_float((float) this.getChatWidth() / this.getChatScale());
		List var6 = GuiUtilRenderComponents.func_178908_a(p_146237_1_, var5, this.mc.fontRendererObj, false, false);
		boolean var7 = this.getChatOpen();
		IChatComponent var9;

		for (Iterator var8 = var6.iterator(); var8.hasNext(); this.field_146253_i.add(0,
				new ChatLine(p_146237_3_, var9, p_146237_2_))) {
			var9 = (IChatComponent) var8.next();

			if (var7 && this.scrollPos > 0) {
				this.isScrolled = true;
				this.scroll(1);
			}
		}

		while (this.field_146253_i.size() > 100) {
			this.field_146253_i.remove(this.field_146253_i.size() - 1);
		}

		if (!p_146237_4_) {
			this.chatLines.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));
			if (p_146237_1_.getUnformattedText().startsWith("§2Guild > ")) {
				guildChat.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));
			}
			if (p_146237_1_.getUnformattedText().startsWith("From")
					|| p_146237_1_.getUnformattedText().startsWith("To")) {
				privateChat.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));
			}
			if (p_146237_1_.getUnformattedText().startsWith("§9Party > ")) {
				partyChat.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));
			}

			while (this.chatLines.size() > 100) {
				this.chatLines.remove(this.chatLines.size() - 1);
			}
		}
	}

	private void clickEvent() {
		if (Mouse.isButtonDown(0)) {
			if (getHoveredButton() >= 0) {
				currentMenu = getHoveredButton();
			}
		}
	}

	private int getHoveredButton() {
		int mx = Mouse.getX() / 2;
		int my = Mouse.getY() / 2;
		int y1 = 16;
		int y2 = y1 + 10;
		for (int i = 0; i < 4; i++) {
			int x1 = i * 50 + (i == 0 ? 2 : 1);
			int x2 = i * 50 + 50;
			if (mx >= x1 && mx <= x2) {
				if (my >= y1 && my <= y2) {
					return i;
				}
			}
		}
		return -1;
	}
}
