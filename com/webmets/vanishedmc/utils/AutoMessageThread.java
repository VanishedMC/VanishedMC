package com.webmets.vanishedmc.utils;

import java.util.List;

import com.webmets.vanishedmc.modules.chat.ChatTriggeredModule;

import net.minecraft.client.Minecraft;

public class AutoMessageThread implements Runnable{

	private int delay;
	private List<String> messages;
	private ChatTriggeredModule holder;
	
	/**
	 * Thread to send delayed messages
	 * 
	 * @param
	 * ChatTriggeredModule holder 
	 * @param
	 * List messages
	 * @param
	 * Integer delay
	 * */
	public AutoMessageThread(ChatTriggeredModule holder, List<String> messages, int delay){
		this.delay = delay;
		this.messages = messages;
		this.holder = holder;
	}
	
	public void run(){
		try {
			Thread.sleep(delay);
			for(String s : messages) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage(s);
			}
			Thread.sleep(2000L);
			holder.setRunning(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
