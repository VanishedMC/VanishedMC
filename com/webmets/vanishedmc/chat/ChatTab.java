package com.webmets.vanishedmc.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;

public class ChatTab implements Configurable {

	private String name;
	private List<ChatTrigger> triggers;
	private Map<Long, Object> chat;

	public ChatTab(String name) {
		this.name = name;
		this.chat = new HashMap<>();
		triggers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public Map<Long, Object> getChat() {
		return chat;
	}

	public List<ChatTrigger> getTriggers() {
		return triggers;
	}

	public ChatTab addToChat(Object chat) {
		this.chat.put(System.currentTimeMillis(), chat);
		return this;
	}

	public ChatTab addChatTrigger(ChatTrigger trigger) {
		this.triggers.add(trigger);
		return this;
	}

	public boolean matches(String message) {
		int i = 1;
		for (ChatTrigger trigger : triggers) {
			if (trigger.matches(message)) {
				i++;
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getKey() {
		return "chattab";
	}

	@Override
	public JsonObject getSettings() {
		JsonObject object = new JsonObject();
		JsonArray array = new JsonArray();
		for (ChatTrigger trigger : triggers) {
			array.add(trigger.getSettings());
		}
		object.add("triggers", array);
		return object;
	}

	@Override
	public void loadSettings(JsonObject json) {
		System.out.println(json.toString());
		JsonArray array = json.get("triggers").getAsJsonArray();
		for (int i = 0; i < array.size(); i++) {
			ChatTrigger trigger = new ChatTrigger();
			trigger.loadSettings(array.get(i).getAsJsonObject());
			addChatTrigger(trigger);
		}
	}
}
