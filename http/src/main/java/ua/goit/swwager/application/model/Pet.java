package ua.goit.swwager.application.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pet {

	private Long id;

	private String name;

	private Status status;

	private List<String> photoUrls;

	private Category category;

	private List<Tag> tags;
}
