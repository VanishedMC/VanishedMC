package com.webmets.vanishedmc.utils.ping;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Callable;

import net.minecraft.client.multiplayer.ServerAddress;

public class Ping implements Callable<Long> {

	private SocketAddress address;

	public Ping(final String address) {
		final ServerAddress serveraddress = ServerAddress.func_78860_a(address);
		this.address = new InetSocketAddress(serveraddress.getIP(), serveraddress.getPort());
	}

	@Override
	public Long call() {
		try {
			final Socket socket = new Socket();
			final long time = System.currentTimeMillis();
			socket.connect(this.address);
			socket.close();
			return System.currentTimeMillis() - time;
		} catch (Exception e) {
			return 0L;
		}
	}

}
