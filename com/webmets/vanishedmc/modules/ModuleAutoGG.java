package com.webmets.vanishedmc.modules;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webmets.vanishedmc.siteconnection.GetAutoGGTriggers;
import com.webmets.vanishedmc.utils.AutoGGThread;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.EnumChatFormatting;

public class ModuleAutoGG extends Module {

	private boolean running = false;
	private boolean enabled = true;
	private int delay = 500;
	private ExecutorService thread = Executors.newCachedThreadPool();
	private List<String> triggers;
	private String message;

	public ModuleAutoGG() {
		super("AutoGG", -1);
		triggers = GetAutoGGTriggers.getTriggers();
	}

	public void chatEvent(S02PacketChat packetIn) {
		if (!getEnabled() || isRunning() || !Utils.onHypixel()) {
			return;
		}
		message = packetIn.func_148915_c().getUnformattedText();
		message = EnumChatFormatting.getTextWithoutFormattingCodes(message);
		if (triggers.stream().anyMatch(trigger -> message.contains(trigger.toString())) && message.startsWith(" ")) {
			thread.submit(new AutoGGThread());
			setRunning(true);
		}
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean getEnabled(){
		return this.enabled;
	}
	
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}

	@Override
	public void tick() {

	}

	@Override
	public void onEnable() {
		System.out.println("enabled autogg");
	}

	@Override
	public void onDisable() {

	}

}
