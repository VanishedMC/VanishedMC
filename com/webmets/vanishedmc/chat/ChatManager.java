package com.webmets.vanishedmc.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webmets.vanishedmc.gui.settings.Configurable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;

public class ChatManager implements Configurable {

	private Map<String, List<ChatTab>> tabs;

	public ChatManager() {
		tabs = new HashMap<>();
	}

	/**
	 * Get a list of all chat tabs for the current server
	 * 
	 * @return List
	 */
	public List<ChatTab> getTabsForCurrentServer() {
		ServerData data = Minecraft.getMinecraft().getCurrentServerData();
		if (data == null) {
			return getTabsForServer("singleplayer");
		}
		return getTabsForServer(data.serverIP);
	}

	/**
	 * Get a list of all chat tabs for the given server
	 * 
	 * @return List
	 */
	public List<ChatTab> getTabsForServer(String ip) {
		if (tabs.containsKey(ip)) {
			return tabs.get(ip);
		} else {
			List<ChatTab> list = new ArrayList<>();
			tabs.put(ip, list);
			return list;
		}
	}

	/**
	 * Add the provided tab to a server
	 */
	public void addTabToServer(String server, ChatTab tab) {
		List<ChatTab> oldTabs = tabs.get(server);
		if (oldTabs == null) {
			oldTabs = new ArrayList<>();
		}
		oldTabs.add(tab);
		tabs.put(server, oldTabs);
	}

	public void removeTabFromServer(String ip, ChatTab tab) {
		List<ChatTab> tabs = null;
		for (String s : getList().keySet()) {
			if (s.equalsIgnoreCase(ip)) {
				tabs = getList().get(s);
			}
		}
		if (tabs == null) {
			return;
		}
		tabs.remove(tab);
		this.tabs.put(ip, tabs);
	}

	/**
	 * Get the full hashmap with server IP's and tab lists
	 */
	public Map<String, List<ChatTab>> getList() {
		return tabs;
	}

	/**
	 * Get only the chat tabs, with out server IP for quick access.
	 */
	public List<ChatTab> getTabs() {
		List<ChatTab> result = new ArrayList<>();
		for (String s : tabs.keySet()) {
			for (ChatTab tab : tabs.get(s)) {
				result.add(tab);
			}
		}
		return result;
	}

	@Override
	public String getKey() {
		return "chatmanager";
	}

	@Override
	public JsonObject getSettings() {
		JsonObject object = new JsonObject();
		for (String s : getList().keySet()) {
			JsonObject array = new JsonObject();
			for (ChatTab tab : getList().get(s)) {
				array.add(tab.getName(), tab.getSettings());
			}
			object.add(s, array);
		}
		return object;
	}

	@Override
	public void loadSettings(JsonObject json) {
		Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, JsonElement> entry = itr.next();
			JsonObject object = entry.getValue().getAsJsonObject();
			String ip = entry.getKey();
			Iterator<Entry<String, JsonElement>> iterator = object.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, JsonElement> entry2 = iterator.next();
				ChatTab tab = new ChatTab(entry2.getKey());
				tab.loadSettings(entry2.getValue().getAsJsonObject());
				addTabToServer(ip, tab);
			}
		}
	}
}
