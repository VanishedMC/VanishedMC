package com.webmets.vanishedmc.chat;

import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;

public class ChatTrigger implements Configurable{

	private String keyWord;
	private String prefix;
	private boolean mustMeetKey;
	private boolean mustMeetPrefix;

	public ChatTrigger() {
		prefix = "";
		keyWord = "";
		mustMeetKey = true;
		mustMeetPrefix = true;
	}

	public boolean matches(String message) {
		boolean hasKeyword = false;
		boolean hasPrefix = false;
				
		if (!keyWord.isEmpty()) {
			if (message.contains(keyWord)) {
				hasKeyword = true;
			}
		} else {
			hasKeyword = true;
		}
		if (!prefix.isEmpty()) {
			if (message.startsWith(prefix)) {
				hasPrefix = true;
			}
		} else {
			hasPrefix = true;
		}
		return (hasPrefix == mustMatchPrefix()) && (hasKeyword == mustMatchKey());
	}

	public String getPrefix() {
		return prefix;
	}

	public ChatTrigger setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public ChatTrigger setKeyWord(String keyWord) {
		this.keyWord = keyWord;
		return this;
	}

	public boolean mustMatchKey() {
		return mustMeetKey;
	}

	public boolean mustMatchPrefix() {
		return mustMeetPrefix;
	}

	public ChatTrigger setMustMeetPrefix(boolean mustMeet) {
		this.mustMeetPrefix = mustMeet;
		return this;
	}

	public ChatTrigger setMustMeetKey(boolean mustMeet) {
		this.mustMeetKey = mustMeet;
		return this;
	}

	@Override
	public String getKey() {
		return "";
	}
	
	@Override
	public JsonObject getSettings() {
		JsonObject object = new JsonObject();
		object.addProperty("keyword", getKeyWord());
		object.addProperty("prefix", getPrefix());
		object.addProperty("mustMeetPrefix", mustMatchPrefix());
		object.addProperty("mustMeetKey", mustMatchKey());
		return object;
	}
	
	@Override
	public void loadSettings(JsonObject json) {
		setKeyWord(json.get("keyword").getAsString());
		setPrefix(json.get("prefix").getAsString());
		setMustMeetKey(json.get("mustMeetKey").getAsBoolean());
		setMustMeetPrefix(json.get("mustMeetPrefix").getAsBoolean());
	}
	
}
