package ru.antonsibgatulin.server;

import java.net.InetSocketAddress;
import java.util.HashSet;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Server extends WebSocketServer {

	public HashSet<WebSocket> list = new HashSet<>();

	public Server(String host, int port) {
		super(new InetSocketAddress(host, port));

		System.out.println("Server was started on " + host + ":" + port);
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		System.out.println("[WEBSOCKET]: open connection " + webSocket.getRemoteSocketAddress().getHostString());
		list.add(webSocket);
	}

	@Override
	public void onClose(WebSocket webSocket, int i, String s, boolean b) {
		System.out.println("[WEBSOCKET]: close connection " + webSocket.getRemoteSocketAddress().getHostString());

		list.remove(webSocket);
	}

	@Override
	public void onMessage(WebSocket webSocket, final String s) {

		new Thread(new Runnable() {
			@Override
			public void run() {

				System.out.println(s);

			}
		}).start();

	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {

	}
}
