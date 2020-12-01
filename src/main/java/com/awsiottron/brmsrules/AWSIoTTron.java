package com.awsiottron.brmsrules;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.awsiottron.config.Config;
import com.awsiottron.database.DataManager;
import com.awsiottron.model.AgentsList;
import com.awsiottron.model.StateList;
import com.awsiottron.server.IoTServer;

/**
 * Executive Order Corporation we make Things Smart
 *
 * AWS IoT Tron AWS IoT BPMN/BRMS Server :: Internet of Things Drools-jBPM Expert System using AWS IoT Tron AWS IoT BPMN/BRMS Processing
 * AWS IoT Tron Drools-jBPM :: Executive Order AWS IoT Tron Sensor Processor MQTT AWS IoT Tron Server using BPMN/BRMS Drools-jBPM
 * Executive Order Corporation - AWS IoT Tron MQTT Telemetry Transport Machine-to-Machine(M2M)/Internet of Things(IoT)
 *
 * Executive Order Corporation
 * Copyright (c) 1978, 2019: Executive Order Corporation, All Rights Reserved
 */

/**
 * This is the main class for AWS IoT Tron AWS-IoT Tron BPMN/BRMS Drools-jBPM
 * Expert System Server
 */
public class AWSIoTTron {

	AgentsList agentsList;
	AWSIoTTron AWSIoTTron;
	private Config config;
	private static IoTServer iotServer = null;

	private String base_path = "";
	private String appVer = "1.01A";
	private String buildDate = "0304";
	private boolean is64bitJMV = false;

	private int port = 5055;
	private String knowledgeDebug = "none"; // none, debug
	private String kSessionType = ""; // createKieSession
	private String kSessionName = ""; // ksession-brmscontrol
	private String processID = ""; // com.BRMSControl
	private long startTime = 0; // Time the server started

	private DataManager dataManager;

	private final Logger logger = LoggerFactory.getLogger(AWSIoTTron.class);

	public AWSIoTTron(String configFile) {

		this.AWSIoTTron = this;
		System.out.println("AWS IoT Tron AI-IoTBPM Server :: Internet of Things Drools-jBPM Expert System"
				+ " using AWS IoT Tron AI-IoTBPM BPMN/BRMS Server Processing -version: " + appVer + " (" + buildDate
				+ ")");

		getIPAddress();
		readProperties(configFile);

		initDataManager();
		logSystemInfo();

		startTime = System.currentTimeMillis();
	}

	public void init(final boolean exitOnClose) {
		if (kSessionType == "") {
			kSessionType = "createKieSession"; // Default kSessionType=createKieSession
		}
		if (kSessionName == "") {
			System.err.println("Error: Must set a kSessionName == defined in awsiottron.xml file.");
			return;
		}
		if (processID == "") {
			System.err.println("Error: Must set a processID == defined in awsiottron.xml file.");
			return;
		}

		StateList stateList = new StateList();

		final jBRMSRules jbpmRules = new jBRMSRules(kSessionType, kSessionName, processID, knowledgeDebug); // devices,

		startIoTServer(jbpmRules);

		processConsole();
	}

	public void logSystemInfo() {
		if (knowledgeDebug.indexOf("none") == -1) {
			System.out.println("os.name: " + System.getProperty("os.name"));
			System.out.println("os.arch: " + System.getProperty("os.arch"));
			is64bitJMV = (System.getProperty("os.arch").indexOf("64") != -1);
			String result = (is64bitJMV == true) ? "64bit" : "32bit";

			System.out.println("java.home: " + System.getProperty("java.home"));
			System.out.println("java.vendor: " + System.getProperty("java.vendor"));
			System.out.println("java.version: " + System.getProperty("java.version") + " " + result);
			long maxHeapBytes = Runtime.getRuntime().maxMemory();
			System.out.println("Max heap memory: " + maxHeapBytes / 1024L / 1024L + "M");
			System.out.println("java.io.tmpdir: " + System.getProperty("java.io.tmpdir"));
			System.out.println("user.home: " + System.getProperty("user.home"));

			base_path = (System.getProperty("user.home") + File.separator);

			System.out.println("base_path: " + base_path);
			System.out.println("File.separator: " + File.separator);
			System.out.println("Local language: " + Locale.getDefault().getLanguage());
		}
	}

	// Process server commands from the console
	private void processConsole() {
		boolean runServer = true;
		do {
			Scanner input = new Scanner(System.in);
			System.out.print("Server > ");
			String command = input.nextLine();

			if (command.equals("help") || command.equals("?")) {
				System.out.println();
				System.out.println("help | ?    - display BRMS-BPM Server help");
				System.out.println("stat | info - display server status information");
				System.out.println("exit | quit - terminates BRMS-BPM Server");
				System.out.println();
			}
			if (command.equals("exit") || command.equals("quit")) {
				runServer = false;
			}
			if (command.equals("stat") || command.equals("info")) {
				long currentTime = System.currentTimeMillis() - startTime;

				int seconds = (int) (currentTime / 1000) % 60;
				int minutes = (int) (currentTime / 60000) % 60;
				int hours = (int) (currentTime / 3600000);
				int days = (int) (currentTime / 86400000);
				System.out.println("- BRMS-BPM has serviced " + iotServer.getTotalConnection() + " connections");
				System.out.println("- Number of threads running " + iotServer.getCurrentConnection() + " connections");
				System.out.print("- Server running for " + days + " days, " + hours + " hours, ");
				System.out.println(minutes + " minutes, " + seconds + " seconds");
				System.out.println();
			}

		} while (runServer);
		stopIoTServer();
	}

	public void readProperties(String configFile) {
		try {
			config = new Config(configFile);
		} catch (Exception e) {
			config = new Config();
			e.printStackTrace();
		}

		try {
			config = new Config(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String portStr = config.getString("server.port");
		port = Integer.parseInt(portStr);

		knowledgeDebug = config.getString("knowledge.debug");
		kSessionType = config.getString("kSession.type");
		kSessionName = config.getString("kSession.name");
		processID = config.getString("process.id");

		String agentDevice = config.getString("agent.device");
		if (agentDevice == null || agentDevice.isEmpty()) {
			System.out.println("agentDevice=[AgentName,http://10.0.0.2,...] not defined in awsiottron.xml file.");
		} else {
			agentsList.parseAgents(agentDevice);
		}
	}

	public void initDataManager() {
		try {
			dataManager = new DataManager(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startIoTServer(jBRMSRules jbpmRules) {
		iotServer = new IoTServer(jbpmRules, port);
		iotServer.start();
	}

	public static void stopIoTServer() {
		iotServer.killServer();
	}

	public void getIPAddress() {
		// Returns the instance of InetAddress containing local host name and address
		InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		System.out.print("System IP: " + (localhost.getHostAddress()).trim());

		// Find public IP address
		String systemipaddress = "";
		try {
			URL url_name = new URL("http://bot.whatismyipaddress.com");
			BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

			// reads system IPAddress
			systemipaddress = sc.readLine().trim();
		} catch (Exception e) {
			systemipaddress = "Cannot Execute Properly";
		}
		System.out.println("  Public IP: " + systemipaddress);
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		String configFile = "";

		System.out.println("AWS IoT Tron :: Executive Order AWS IoT Tron Server Processor System"
				+ " - AWS IoT Tron MQTT AI-IoTBPM BPMN/BRMS Server using AWS IoT Tron jBPM Drools-jBPM");

		if (args.length <= 0) {
			System.out.println("Configuration file is not provided, using default awsiottron.xml filename.");
			configFile = "awsiottron.xml";
		} else {
			configFile = args[args.length - 1];
		}

		new AWSIoTTron(configFile).init(true);
	}
}
