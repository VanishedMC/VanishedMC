package com.webmets.vanishedmc.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;
import com.webmets.vanishedmc.utils.FilterRequirements;

public class ChatTab implements Configurable {

	private String name;
	private List<ChatTrigger> triggers;
	private Map<Long, Object> chat;
	private FilterRequirements requirements = FilterRequirements.REQUIRE_ALL;

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

	public ChatTab removeTrigger(ChatTrigger trigger) {
		this.triggers.remove(trigger);
		return this;
	}

	public FilterRequirements getRequirements() {
		return requirements;
	}

	public void setRequirements(FilterRequirements requirements) {
		this.requirements = requirements;
	}

	public boolean matches(String message) {
		int i = 1;
		boolean fail = false;
		for (ChatTrigger trigger : triggers) {
			if (trigger.matches(message)) {
				if (requirements == FilterRequirements.REQUIRE_ONE) {
					return true;
				}
				i++;
				continue;
			} else {
				if (requirements == FilterRequirements.REQUIRE_ALL) {
					return false;
				}
				fail = true;
			}
		}
		return !fail;
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
		object.addProperty("requirements", getRequirements().toString());
		return object;
	}

	@Override
	public void loadSettings(JsonObject json) {
		setRequirements(FilterRequirements.valueOf(json.get("requirements").getAsString()));
		JsonArray array = json.get("triggers").getAsJsonArray();
		for (int i = 0; i < array.size(); i++) {
			ChatTrigger trigger = new ChatTrigger();
			trigger.loadSettings(array.get(i).getAsJsonObject());
			addChatTrigger(trigger);
		}
	}

	public void setName(String name) {
		this.name = name;
	}
}
