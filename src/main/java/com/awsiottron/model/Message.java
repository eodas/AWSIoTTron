package com.awsiottron.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message {

	private final Logger logger = LoggerFactory.getLogger(Event.class);

	public String id;
	public String type;
	public String message;

	public Message() {
	}

	/**
	 * Massages can be all static final String TYPE__ defined in Event or Command
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Logger getLogger() {
		return logger;
	}
}
