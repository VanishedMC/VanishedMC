package com.webmets.vanishedmc.utils;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class AccountUtils {

	public static String login = "null";
	
	/**
	 * Method to change Minecraft account ingame </br> </br>
	 * This method is only used during development, and can not be triggered when the client is compiled
	 * */
	public static String login(String login) {
		YggdrasilAuthenticationService authenticationService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
		YggdrasilUserAuthentication authentication = (YggdrasilUserAuthentication) authenticationService
				.createUserAuthentication(Agent.MINECRAFT);
		String[] args = login.split(":");
		authentication.setUsername(args[0]);
		authentication.setPassword(args[1]);
		String displayText;
		try {
			authentication.logIn();
			Minecraft.getMinecraft().session = new Session(authentication.getSelectedProfile().getName(),
					authentication.getSelectedProfile().getId().toString(), authentication.getAuthenticatedToken(),
					"mojang");
			return null;
		} catch (AuthenticationUnavailableException e) {
			displayText = "Cannot contact authentication server!";
		} catch (AuthenticationException e) {// wrong password account migrated
			if (e.getMessage().contains("Invalid username or password.")
					|| e.getMessage().toLowerCase().contains("account migrated"))
				displayText = "Wrong password!";
			else
				displayText = "Cannot contact authentication server!";
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			displayText = "4lWrong password!";
		}
		return displayText;
	}

	
}
