package com.webmets.vanishedmc.modules;

import com.webmets.vanishedmc.VanishedMC;

import net.minecraft.client.Minecraft;

public abstract class Module {

	/**
	 * Module base class
	 * */
	
	public Module(String name, int bind) {
		this.name = name;
		this.bind = bind;
		mc = Minecraft.getMinecraft();
		client = VanishedMC.instance;
	}
	
	protected VanishedMC client;
	protected Minecraft mc;
	
	private int bind;
	private boolean enabled;
	private String name;

	public void enable() {
		enabled = true;
		onEnable();
	}

	public void disable() {
		enabled = false;
		onDisable();
	}

	public void toggle() {
		if (enabled) {
			disable();
		} else {
			enable();
		}
	}
	
	public int getBind() {
		return bind;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setBind(int bind) {
		this.bind = bind;
	}

	public abstract void tick();

	public abstract void onEnable();

	public abstract void onDisable();
}
