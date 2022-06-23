package ua.goit.swwager.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class APIResponse {

	private Integer code;

	private String type;

	private String message;

}