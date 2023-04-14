package ru.antonsibgatulin.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;

import org.json.JSONException;
import org.json.JSONObject;

import ru.antonsibgatulin.server.Server;

public class Main {

	public static Server server = null;

	public static void main(String... args) throws Exception {

		InetAddress IP = InetAddress.getLocalHost();
		String host = "localhost";// IP.getHostAddress();
		int port = parsePort("config/configure.cfg");
		server = new Server(host, port);

	}

	public static int parsePort(String filepath) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepath)));
			String all = "";
			String pie = null;
			while ((pie = bufferedReader.readLine()) != null) {
				all += pie;

			}
			JSONObject jsonObject = new JSONObject(all);

			return jsonObject.getInt("port");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return 8080;
	}
}
