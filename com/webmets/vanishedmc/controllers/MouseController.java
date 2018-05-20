package com.webmets.vanishedmc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.webmets.vanishedmc.VanishedMC;
import com.webmets.vanishedmc.gui.ChatGui;
import com.webmets.vanishedmc.settings.GuiHudCPSView;

import net.minecraft.client.Minecraft;

public class MouseController {

	private List<Long> leftClicks, rightClicks;
	private VanishedMC client = VanishedMC.instance;
	
	public MouseController() {
		leftClicks = new ArrayList<>();
		rightClicks = new ArrayList<>();
	}

	public String getCps(GuiHudCPSView cpsView) {
		String cps = "";
		switch (cpsView) {
			case COMBINED:
				cps = String.valueOf(getLeftCPS() + getRightCPS());
				break;
			case LEFT:
				cps = String.valueOf(getLeftCPS());
				break;
			case RIGHT:
				cps = String.valueOf(getRightCPS());
				break;
			case SEPARATE:
				cps = getLeftCPS() + " : " + getRightCPS();
				break;
		}
		return cps;
	}

	public int getLeftCPS() {
		return getCPS(this.leftClicks);
	}

	public int getRightCPS() {
		return getCPS(this.rightClicks);
	}

	private int getCPS(List<Long> clicks) {
		clearCPS(clicks);
		return clicks.size();
	}

	private void clearCPS(List<Long> clicks) {
		long time = System.currentTimeMillis();
		Iterator<Long> iterator = clicks.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() + 1000L < time) {
				iterator.remove();
			}
		}
	}

	public void leftClick() {
		leftClicks.add(System.currentTimeMillis());
	}

	public void rightClick() {
		rightClicks.add(System.currentTimeMillis());
	}

}
