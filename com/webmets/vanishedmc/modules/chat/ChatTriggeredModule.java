package com.webmets.vanishedmc.modules.chat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webmets.vanishedmc.modules.Module;
import com.webmets.vanishedmc.utils.AutoMessageThread;
import com.webmets.vanishedmc.utils.Utils;

import net.minecraft.util.EnumChatFormatting;

public class ChatTriggeredModule extends Module {

	protected boolean running = false;
	protected boolean enabled = true;
	protected int delay = 500;
	protected ExecutorService thread = Executors.newCachedThreadPool();
	protected List<String> triggers;
	protected List<String> messages;
	protected String message;
	
	public ChatTriggeredModule(List<String> triggers, List<String> messages) {
		super("ChatTriggeredModule", -1);
		this.triggers = triggers;
		this.messages = messages;
	}
	
	public void chatEvent(String text) {
		if (!enabled || isRunning() || !check()) {
			return;
		}
		message = text;
		message = EnumChatFormatting.getTextWithoutFormattingCodes(message);
		if (triggers.stream().anyMatch(trigger -> message.contains(trigger.toString())) && message.startsWith(" ")) {
			thread.submit(new AutoMessageThread(this, messages, delay));
			setRunning(true);
		}
	}
	
	protected boolean check(){
		return true;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean getEnabled(){
		return this.enabled;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}

}
