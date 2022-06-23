package ua.goit.swwager.application.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

	PLACED,
	APPROVED,
	DELIVERED;

	@JsonValue
	public String getValue() {
		return name().toLowerCase();
	}
}
