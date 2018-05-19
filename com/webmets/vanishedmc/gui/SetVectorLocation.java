package com.webmets.vanishedmc.gui;

import java.io.IOException;

import javax.vecmath.Vector2f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SetVectorLocation extends GuiScreen{

	private Vector2f vec;
	
	public SetVectorLocation(Vector2f vector2f){
		this.vec = vector2f;
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		vec.setX(mouseX);
		vec.setY(mouseY);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		vec.setX(mouseX);
		vec.setY(mouseY);
 	}

	protected void keyTyped(char c, int i) throws IOException {
		super.keyTyped(c, i);
		if (i == 200) {
			vec.setY(vec.getY()-1);
		} else if (i == 203) {
			vec.setX(vec.getX()-1);
		} else if (i == 208) {
			vec.setY(vec.getY()+1);
		} else if (i == 205) {
			vec.setX(vec.getX()+1);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(Minecraft.getMinecraft().theWorld == null) {
			this.drawDefaultBackground();
		}
		this.drawRect(0, 0, Display.getWidth(), Display.getHeight(), 0x50ffffff);
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	
}
