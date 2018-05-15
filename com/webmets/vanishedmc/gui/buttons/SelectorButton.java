package com.webmets.vanishedmc.gui.buttons;

import java.util.ArrayList;
import java.util.List;

import io.netty.util.internal.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StringUtils;

public class SelectorButton extends GuiButton {

	private List<ButtonAction> actions;
	private List<String> options;
	private String current = "";
	private String name;

	public void drawBorderedRect(int x, int y, int x1, int y1, int size, int borderC, int insideC) {
		drawRect(x + size, y + size, x1 - size, y1 - size, insideC);
		drawRect(x + size, y + size, x1, y, borderC);
		drawRect(x, y, x + size, y1, borderC);
		drawRect(x1, y1, x1 - size, y + size, borderC);
		drawRect(x, y1 - size, x1, y1, borderC);
	}

	public SelectorButton(int id, int x, int y, String s, List<String> options) {
		this(id, x, y, 200, 20, s, options);
	}

	public SelectorButton(int id, int x, int y, int l, int i1, String s, List<String> options) {
		super(id, x, y, l, i1, s);
		this.actions = new ArrayList<>();
		this.options = options;
		this.name = s;
	}

	public void next() {
		int i = options.indexOf(getCurrent());
		i++;
		if (i >= options.size()) {
			i = 0;
		}
		setCurrent(options.get(i));
		displayString = name + " " + getCurrent();
	}

	public List<String> getOptions() {
		return options;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current.substring(0, 1).toUpperCase() + current.substring(1);

		displayString = name + " " + getCurrent();
	}

	public void press() {
		next();
		for (ButtonAction action : actions) {
			action.execute();
		}
	}

	public void addAction(ButtonAction action) {
		actions.add(action);
	}

	protected int getHoverState(boolean flag) {
		byte byte0 = 1;
		if (!enabled) {
			byte0 = 0;
		} else if (flag) {
			byte0 = 2;
		}
		return byte0;
	}

	public void drawButton(Minecraft mc, int mx, int my) {
		if (!visible) {
			return;
		}
		FontRenderer fontrenderer = mc.fontRendererObj;
		boolean flag = mx >= xPosition && my >= yPosition && mx < xPosition + width && my < yPosition + height; // Flag,
		// button
		if (flag) { // Hover Action
			drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0xFF000000, 0x80000000);
			drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2,
					0xff666666);
		} else { // Normal
			drawBorderedRect(xPosition, yPosition, xPosition + width, yPosition + height, 1, 0x900d0d0d, 0x90262626);
			drawCenteredString(fontrenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2,
					0xFFCCCCCC);
		}
	}

}
