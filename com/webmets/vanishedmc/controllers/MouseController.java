package com.webmets.vanishedmc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.webmets.vanishedmc.settings.GuiHudCPSView;

public class MouseController {

	private List<Long> leftClicks, rightClicks;

	public MouseController() {
		leftClicks = new ArrayList<>();
		rightClicks = new ArrayList<>();
	}
	
	public String getCps(GuiHudCPSView cpsView) {
		String cps = "";
		switch (cpsView) {
		case COMBINED:
			cps = (getLeftCPS() + getRightCPS()) + "";
			break;
		case LEFT:
			cps = (getLeftCPS()) + "";
			break;
		case RIGHT:
			cps = (getRightCPS()) + "";
			break;
		case SEPARATE:
			cps = (getLeftCPS()) + " : " + (getRightCPS());
			break;
		}
		return cps;
	}

	public int getLeftCPS() {
		long time = System.currentTimeMillis();
		Iterator<Long> iterator = leftClicks.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() + 1000L < time) {
				iterator.remove();
			}
		}
		iterator = null;
		return this.leftClicks.size();
	}

	public int getRightCPS() {
		long time = System.currentTimeMillis();
		Iterator<Long> iterator = rightClicks.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() + 1000L < time) {
				iterator.remove();
			}
		}
		iterator = null;
		return this.rightClicks.size();
	}

	public void leftClick() {
		leftClicks.add(System.currentTimeMillis());
	}

	public void rightClick() {
		rightClicks.add(System.currentTimeMillis());
	}

}
