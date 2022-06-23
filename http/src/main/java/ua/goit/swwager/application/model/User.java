package ua.goit.swwager.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private Long id;

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String phone;

	private Integer userStatus;

}
