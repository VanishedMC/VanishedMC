package com.webmets.vanishedmc.gui.buttons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;

public class RGBSelectorButton {

	private int red = 0, green = 0, blue = 0;
	private SliderButton sliderRed, sliderGreen, sliderBlue;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible = true;
	private ButtonAction action = null;

	public RGBSelectorButton(int x, int y) {
		this(x, y, 100, 20);
	}

	public RGBSelectorButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.sliderRed = new SliderButton(0, x, y, width, height, 0, 255, "Red");
		this.sliderGreen = new SliderButton(0, x, y + 25, width, height, 0, 255, "Green");
		this.sliderBlue = new SliderButton(0, x, y + 50, width, height, 0, 255, "Blue");

		sliderRed.setValue(0);
		sliderGreen.setValue(0);
		sliderBlue.setValue(0);

		sliderRed.addAction(new ButtonAction() {
			@Override
			public void execute() {
				red = (int) sliderRed.getDisplayValue();
				update();
				if (action != null) {
					action.execute();
				}
			}
		});

		sliderGreen.addAction(new ButtonAction() {
			@Override
			public void execute() {
				green = (int) sliderGreen.getDisplayValue();
				update();
				if (action != null) {
					action.execute();
				}
			}
		});

		sliderBlue.addAction(new ButtonAction() {
			@Override
			public void execute() {
				blue = (int) sliderBlue.getDisplayValue();
				update();
				if (action != null) {
					action.execute();
				}
			}
		});
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		for (GuiButton button : getSliders()) {
			button.visible = visible;
		}
	}

	public boolean isVisible() {
		return visible;
	}

	private void update() {
		sliderRed.setColor(new Color(red, green, blue));
		sliderGreen.setColor(new Color(red, green, blue));
		sliderBlue.setColor(new Color(red, green, blue));
	}

	public void setAction(ButtonAction action) {
		this.action = action;
	}

	public List<GuiButton> getSliders() {
		List<GuiButton> sliders = new ArrayList<>();
		sliders.add(sliderRed);
		sliders.add(sliderGreen);
		sliders.add(sliderBlue);
		return sliders;
	}

	public void setRed(int red) {
		this.red = red;
		sliderRed.setValue(red);
		update();
	}
	
	public void setGreen(int green) {
		this.green = green;
		sliderGreen.setValue(green);
		update();
	}
	
	public void setBlue(int blue) {
		this.blue = blue;
		sliderBlue.setValue(blue);
		update();
	}
	
	public int getRed() {
		return red;
	}

	public int getBlue() {
		return blue;
	}

	public int getGreen() {
		return green;
	}
}
