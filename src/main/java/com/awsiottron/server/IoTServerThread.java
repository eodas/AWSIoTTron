package com.awsiottron.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awsiottron.brmsrules.jBRMSRules;
import com.awsiottron.model.Event;

public class IoTServerThread extends Thread {
	jBRMSRules jbpmRules;

	PrintStream out;
	String response = "";
	IoTServer ws = null;
	Socket socket = null;

	private final Logger logger = LoggerFactory.getLogger(IoTServerThread.class);

	public IoTServerThread(Socket socket, IoTServer ws, jBRMSRules jbpmRules) {
		this.ws = ws;
		this.socket = socket;
		this.jbpmRules = jbpmRules;
		setName("BRMSServer Thread");

		start();
	}

	@Override
	public void run() {
		try {
			String ipAddress = socket.getInetAddress().getHostAddress();
			System.out.println("> TRACE BRMSServer connected from IP " + ipAddress);

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
			String request = "";
			while (true) {
				request = in.readLine();
				System.out.println("> TRACE " + request);

				boolean moreLines = true;
				while (moreLines) {
					String reqtmp = in.readLine();

					if ((reqtmp.equals(null)) || (reqtmp.equals(""))) {
						break;
					}
				}

				Event event = new Event();
				String[] req = Pattern.compile(" ").split(request);

				if (req[0].equals("GET") || req[0].equals("POST")) {

					String arg = req[1].substring(req[1].indexOf('?') + 1);
					String[] tokens = arg.split("&");

					for (String token : tokens) {
						try {
							String key = token.substring(0, token.indexOf('='));
							String value = token.substring(token.indexOf('=') + 1);
							event.add(key, value);

						} catch (IndexOutOfBoundsException e) {
							System.err.println("Error: Unexpected exception caught: " + e.getMessage());
							e.printStackTrace();
						}
					}
					response = jbpmRules.receive(event);
					if ((response != null) && (response.length() > 0)) {
						out.println(response);
						System.out.println("> TRACE RSP " + response);
					}
					sendHttpTextResp(200, "OK");
				}
			}

		} catch (Exception localException3) {
			ws.decConnection();
		}
	}

	private void sendHttpTextResp(int status, String response) throws IOException {
		String headerText = "OK";

		switch (status) {
		case 200:
			headerText = "OK";
			break;
		case 404:
			headerText = "File not found";
			break;
		case 401:
			headerText = "Unauthorized";
			break;
		default:
			headerText = "OK";
		}

		out.println("HTTP/1.1 " + status + " " + headerText);
		out.println("Content-Length: 0");
		out.println(""); // do not forget this one

		out.flush();
		out.close();
		socket.close();
	}
}
