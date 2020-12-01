package com.awsiottron.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Position {

	private final Logger logger = LoggerFactory.getLogger(Event.class);

	public String protocol;
	public String serverTime;
	public String deviceTime;
	public String fixTime;
	public boolean outdated;
	public boolean valid;
	public double lat;
	public double lon;
	public double altitude; // value in meters
	public double speed; // value in mph
	public double course;
	public String address;
	public double accuracy;
	public double bearing;
	public String network;

	public Position() {
	}

	public static final String KEY_ORIGINAL = "raw";
	public static final String KEY_INDEX = "index";
	public static final String KEY_HDOP = "hdop";
	public static final String KEY_VDOP = "vdop";
	public static final String KEY_PDOP = "pdop";
	public static final String KEY_SATELLITES = "sat"; // in use
	public static final String KEY_SATELLITES_VISIBLE = "satVisible";
	public static final String KEY_RSSI = "rssi";
	public static final String KEY_GPS = "gps";
	public static final String KEY_ROAMING = "roaming";
	public static final String KEY_EVENT = "event";
	public static final String KEY_ALARM = "alarm";
	public static final String KEY_STATUS = "status";
	public static final String KEY_ODOMETER = "odometer"; // meters
	public static final String KEY_ODOMETER_SERVICE = "serviceOdometer"; // meters
	public static final String KEY_ODOMETER_TRIP = "tripOdometer"; // meters
	public static final String KEY_HOURS = "hours";
	public static final String KEY_STEPS = "steps";
	public static final String KEY_HEART_RATE = "heartRate";
	public static final String KEY_INPUT = "input";
	public static final String KEY_OUTPUT = "output";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_VIDEO = "video";
	public static final String KEY_AUDIO = "audio";

	// The units for the below four KEYs currently vary.
	// The preferred units of measure are specified in the comment for each.
	public static final String KEY_POWER = "power"; // volts
	public static final String KEY_BATTERY = "battery"; // volts
	public static final String KEY_BATTERY_LEVEL = "batteryLevel"; // percentage
	public static final String KEY_FUEL_LEVEL = "fuel"; // liters
	public static final String KEY_FUEL_USED = "fuelUsed"; // liters
	public static final String KEY_FUEL_CONSUMPTION = "fuelConsumption"; // liters/hour

	public static final String KEY_VERSION_FW = "versionFw";
	public static final String KEY_VERSION_HW = "versionHw";
	public static final String KEY_TYPE = "type";
	public static final String KEY_IGNITION = "ignition";
	public static final String KEY_FLAGS = "flags";
	public static final String KEY_ANTENNA = "antenna";
	public static final String KEY_CHARGE = "charge";
	public static final String KEY_IP = "ip";
	public static final String KEY_ARCHIVE = "archive";
	public static final String KEY_DISTANCE = "distance"; // meters
	public static final String KEY_TOTAL_DISTANCE = "totalDistance"; // meters
	public static final String KEY_RPM = "rpm";
	public static final String KEY_VIN = "vin";
	public static final String KEY_APPROXIMATE = "approximate";
	public static final String KEY_THROTTLE = "throttle";
	public static final String KEY_MOTION = "motion";
	public static final String KEY_ARMED = "armed";
	public static final String KEY_GEOFENCE = "geofence";
	public static final String KEY_ACCELERATION = "acceleration";
	public static final String KEY_DEVICE_TEMP = "deviceTemp"; // celsius
	public static final String KEY_COOLANT_TEMP = "coolantTemp"; // celsius
	public static final String KEY_ENGINE_LOAD = "engineLoad";
	public static final String KEY_OPERATOR = "operator";
	public static final String KEY_COMMAND = "command";
	public static final String KEY_BLOCKED = "blocked";
	public static final String KEY_DOOR = "door";
	public static final String KEY_AXLE_WEIGHT = "axleWeight";
	public static final String KEY_G_SENSOR = "gSensor";
	public static final String KEY_ICCID = "iccid";
	public static final String KEY_PHONE = "phone";

	public static final String KEY_DTCS = "dtcs";
	public static final String KEY_OBD_SPEED = "obdSpeed"; // knots
	public static final String KEY_OBD_ODOMETER = "obdOdometer"; // meters

	public static final String KEY_RESULT = "result";

	public static final String KEY_DRIVER_UNIQUE_ID = "driverUniqueId";

	// Start with 1 not 0
	public static final String PREFIX_TEMP = "temp";
	public static final String PREFIX_ADC = "adc";
	public static final String PREFIX_IO = "io";
	public static final String PREFIX_COUNT = "count";
	public static final String PREFIX_IN = "in";
	public static final String PREFIX_OUT = "out";

	public static final String ALARM_GENERAL = "general";
	public static final String ALARM_SOS = "sos";
	public static final String ALARM_VIBRATION = "vibration";
	public static final String ALARM_MOVEMENT = "movement";
	public static final String ALARM_LOW_SPEED = "lowspeed";
	public static final String ALARM_OVERSPEED = "overspeed";
	public static final String ALARM_FALL_DOWN = "fallDown";
	public static final String ALARM_LOW_POWER = "lowPower";
	public static final String ALARM_LOW_BATTERY = "lowBattery";
	public static final String ALARM_FAULT = "fault";
	public static final String ALARM_POWER_OFF = "powerOff";
	public static final String ALARM_POWER_ON = "powerOn";
	public static final String ALARM_DOOR = "door";
	public static final String ALARM_LOCK = "lock";
	public static final String ALARM_UNLOCK = "unlock";
	public static final String ALARM_GEOFENCE = "geofence";
	public static final String ALARM_GEOFENCE_ENTER = "geofenceEnter";
	public static final String ALARM_GEOFENCE_EXIT = "geofenceExit";
	public static final String ALARM_GPS_ANTENNA_CUT = "gpsAntennaCut";
	public static final String ALARM_ACCIDENT = "accident";
	public static final String ALARM_TOW = "tow";
	public static final String ALARM_IDLE = "idle";
	public static final String ALARM_HIGH_RPM = "highRpm";
	public static final String ALARM_ACCELERATION = "hardAcceleration";
	public static final String ALARM_BRAKING = "hardBraking";
	public static final String ALARM_CORNERING = "hardCornering";
	public static final String ALARM_LANE_CHANGE = "laneChange";
	public static final String ALARM_FATIGUE_DRIVING = "fatigueDriving";
	public static final String ALARM_POWER_CUT = "powerCut";
	public static final String ALARM_POWER_RESTORED = "powerRestored";
	public static final String ALARM_JAMMING = "jamming";
	public static final String ALARM_TEMPERATURE = "temperature";
	public static final String ALARM_PARKING = "parking";
	public static final String ALARM_SHOCK = "shock";
	public static final String ALARM_BONNET = "bonnet";
	public static final String ALARM_FOOT_BRAKE = "footBrake";
	public static final String ALARM_FUEL_LEAK = "fuelLeak";
	public static final String ALARM_TAMPERING = "tampering";
	public static final String ALARM_REMOVING = "removing";

	// eospy
	public static final String KEY_TEMP = "temp";
	public static final String KEY_IR_TEMP = "ir_temp";
	public static final String KEY_HUMIDITY = "humidity";
	public static final String KEY_MBAR = "mbar";

	public static final String KEY_ACCEL_X = "accel_x";
	public static final String KEY_ACCEL_Y = "accel_y";
	public static final String KEY_ACCEL_Z = "accel_z";
	public static final String KEY_GYRO_X = "gyro_x";
	public static final String KEY_GYRO_Y = "gyro_y";
	public static final String KEY_GYRO_Z = "gyro_z";
	public static final String KEY_MAGNET_X = "magnet_x";
	public static final String KEY_MAGNET_Y = "magnet_y";
	public static final String KEY_MAGNET_Z = "magnet_z";

	public static final String KEY_LIGHT = "light";
	public static final String KEY_KEYPRESS = "keypress";

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public String getDeviceTime() {
		return deviceTime;
	}

	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}

	public String getFixTime() {
		return fixTime;
	}

	public void setFixTime(String fixTime) {
		this.fixTime = fixTime;
	}

	public boolean isOutdated() {
		return outdated;
	}

	public void setOutdated(boolean outdated) {
		this.outdated = outdated;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getCourse() {
		return course;
	}

	public void setCourse(double course) {
		this.course = course;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getBearing() {
		return bearing;
	}

	public void setBearing(double bearing) {
		this.bearing = bearing;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Logger getLogger() {
		return logger;
	}

}
