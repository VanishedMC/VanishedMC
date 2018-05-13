package com.webmets.vanishedmc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MouseController {

	private List<Long> leftClicks, rightClicks;

	public MouseController() {
		leftClicks = new ArrayList<>();
		rightClicks = new ArrayList<>();
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
