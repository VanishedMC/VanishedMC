package com.webmets.vanishedmc.gui.buttons;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;

public class SliderButton extends GuiButton {

	private List<ButtonAction> actions;

	public SliderButton(int buttonId, int x, int y, int min, int max, String buttonText) {
		this(buttonId, x, y, 200, 20, min, max, buttonText);
	}

	public SliderButton(int buttonId, int x, int y, int width, int height, int min, int max, String buttonText) {
		super(buttonId, x, y, width, height, buttonText);
		this.name = buttonText;
		this.max = max;
		this.min = min;
		actions = new ArrayList<>();
	}

	private float value = 0;
	private float displayValue = 0;
	private boolean dragging = false;
	private float min = 0;
	private float max = 1;
	private String name;

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(!visible){
			return;
		}
		this.mouseDragged(mc, mouseX, mouseY);
		boolean flag = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width
				&& mouseY < yPosition + height;

		if (flag || dragging) {
			drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0xFF000000, 0x80000000);
		} else {
			drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0x900d0d0d, 0x90262626);
		}

		drawCenteredString(mc.fontRendererObj, displayString, xPosition + width / 2, yPosition + (height - 8) / 2,
				0xFFCCCCCC);

		drawBorderedRect((int) (xPosition + this.value * (float) (this.width - 8)), this.yPosition,
				(int) (xPosition + this.value * (float) (this.width - 7)) + 8, this.yPosition + height, 1, 0xFF000000,
				0x80000000);
	}

	public void setValue(float value) {
		this.value = (value / max);
		this.displayValue = Float.parseFloat(String.format("%.0f", (this.value * max)));
		this.displayString = name + " " + (int) (min + displayValue);
	}

	public float getValue() {
		return value;
	}

	public float getDisplayValue() {
		return (int) (min + displayValue);
	}

	public void press() {
		for (ButtonAction action : actions) {
			action.execute();
		}
	}

	public void addAction(ButtonAction action) {
		actions.add(action);
	}

	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		if (this.dragging) {
			this.value = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);
			this.value = MathHelper.clamp_float(this.value, 0, 1);
			this.displayValue = Float.parseFloat(String.format("%.0f", (value * max)));
			this.displayString = name + " " + (int) (min + displayValue);
			press();
		}
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if (super.mousePressed(mc, mouseX, mouseY)) {
			// this.value = (float) (mouseX - (this.xPosition + 4)) / (float)
			// (this.width - 8);
			this.value = (float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8);
			this.value = MathHelper.clamp_float(this.value, 0, 1);
			this.displayValue = Float.parseFloat(String.format("%.0f", (value * max)));
			this.displayString = name + " " + (int) displayValue;
			this.dragging = true;
			press();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		dragging = false;
	}

	private void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
		drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
		drawRect(x + size, y + size, x1, y, borderC);
		drawRect(x, y, x + size, y1, borderC);
		drawRect(x1, y1, x1 - size, y + size, borderC);
		drawRect(x, y1 - size, x1, y1, borderC);
	}
}
