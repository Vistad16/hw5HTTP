package ua.goit.swwager.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {

	private Long id;

	private Long petId;

	private Long quantity;

	private String shipDate;

	private OrderStatus status;

	private boolean complete;

	@Override
	public String toString() {
		return "Store{" +
				"id=" + id +
				", petId=" + petId +
				", quantity=" + quantity +
				", shipDate='" + shipDate + '\'' +
				", status=" + status.name().toLowerCase() +
				", complete=" + complete +
				'}';
	}

}
