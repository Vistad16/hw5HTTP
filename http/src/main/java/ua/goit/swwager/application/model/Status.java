package ua.goit.swwager.application.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

	AVAILABLE,
	SOLD,
	PENDING;

	@JsonValue
	public String getValue() {
		return name().toLowerCase();
	}
}
