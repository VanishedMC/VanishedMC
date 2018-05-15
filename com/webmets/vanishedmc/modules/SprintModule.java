package com.webmets.vanishedmc.modules;

import org.lwjgl.input.Keyboard;

public class SprintModule extends Module {

	public SprintModule() {
		super("Sprint", Keyboard.KEY_Z);
	}

	@Override
	public void tick() {
		if(this.isEnabled()) {
			if(!(mc.thePlayer.isCollidedHorizontally) && mc.thePlayer.moveForward > 0.0f) {
				mc.thePlayer.setSprinting(true);
			} else {
				mc.thePlayer.setSprinting(false);
			}
		}
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}

}
