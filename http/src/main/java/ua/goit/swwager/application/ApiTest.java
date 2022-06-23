package ua.goit.swwager.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ua.goit.swwager.application.model.Category;
import ua.goit.swwager.application.model.OrderStatus;
import ua.goit.swwager.application.model.Pet;
import ua.goit.swwager.application.model.Status;
import ua.goit.swwager.application.model.Store;
import ua.goit.swwager.application.model.Tag;
import ua.goit.swwager.application.model.User;
import ua.goit.swwager.application.service.PetService;
import ua.goit.swwager.application.service.StoreService;
import ua.goit.swwager.application.service.UserService;

public class ApiTest {
	public static void main(String[] args) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String helpMessage = "Write 'HELP' for instruction or 'EXIT' to exit";
			System.out.println(helpMessage);
			while (true) {
				String input = reader.readLine();

				if (input.equals("EXIT")) {
					break;
				} else if (input.equals("HELP")) {
					String text = Files.readString(Paths.get("src\\main\\resources\\Instruction.txt"));
					System.out.println(text);
					System.out.println(helpMessage);
					continue;
				}

				try {
					int number = Integer.parseInt(input);
					switch (number) {
						case 1 -> createPet(777, "My digger", Status.AVAILABLE);
						case 2 -> uploadImage("src\\main\\resources\\cat.jpg");
						case 3 -> updatePet(9, "Donatello", Status.AVAILABLE);
						case 4 -> findByStatus(Status.AVAILABLE);
						case 5 -> findByID(9);
						case 6 -> updatePetByID(9, "Mike", Status.PENDING);
						case 7 -> deletePetByID(777);
						case 8 -> orderPurchasingPet(1, 9, 1, "1010-06-17T10:28:02.812Z", OrderStatus.PLACED, true);
						case 9 -> findOrderById(1);
						case 10 -> deleteOrderById(1);
						case 11 -> orderInventory();
						case 12 -> createUserWithList();
						case 13 -> findByUserName("Karl");
						case 14 -> updateByUsername("Karl", "ZDV", "42");
						case 15 -> deleteByUsername("ZXC");
						case 16 -> login("Karl", "passwo223232rd");
						case 17 -> logout();
						case 18 -> createdUser("New", "12345");
						default -> System.err.println("There is no such request!");
					}
				} catch (NumberFormatException e) {
					System.err.println("It is not a number");
				}
			}
		}
	}

	//Pet
	private static void uploadImage(String filePath) {
		File file = new File(filePath);
		System.out.println(new PetService().uploadImage(55, file));
	}

	private static void createPet(long id, String name, Status status) {
		PetService petService = new PetService();
		Category category = new Category();
		category.setId(1L);
		category.setName("New Category");
		Tag tag = new Tag();
		tag.setId(1L);
		tag.setName("New tag");
		Tag tag2 = new Tag();
		tag2.setId(2L);
		tag2.setName("Second Tag");
		Pet pet = new Pet();
		pet.setId(id);
		pet.setName(name);
		pet.setCategory(category);
		pet.setTags(List.of(tag, tag2));
		pet.setStatus(status);
		System.out.println(petService.createPet(pet));
	}

	private static void updatePet(long id, String name, Status status) {
		PetService petService = new PetService();
		Category category = new Category();
		category.setId(1L);
		category.setName("New Category");
		Tag tag = new Tag();
		tag.setId(1L);
		tag.setName("New tag");
		Tag tag2 = new Tag();
		tag2.setId(2L);
		tag2.setName("Second Tag");
		Pet pet = new Pet();
		pet.setId(id);
		pet.setName(name);
		pet.setCategory(category);
		pet.setTags(List.of(tag, tag2));
		pet.setStatus(status);
		System.out.println(petService.updateExistingPet(pet));
	}

	public static void findByStatus(Status status) {
		PetService petService = new PetService();
		List<Pet> byStatus = petService.findByStatus(status);
		for (int i = 0; i < 3; i++) {
			System.out.println(byStatus.get(i));
		}
	}

	public static void findByID(int id) {
		PetService petService = new PetService();
		Pet petByID = petService.findPetByID(id);
		System.out.println(petByID);
	}

	private static void updatePetByID(long id, String name, Status status) {
		PetService petService = new PetService();
		Category category = new Category();
		category.setId(1L);
		category.setName("New Category");
		Tag tag = new Tag();
		tag.setId(1L);
		tag.setName("New tag");
		Tag tag2 = new Tag();
		tag2.setId(2L);
		tag2.setName("Second Tag");
		Pet pet = new Pet();
		pet.setId(id);
		pet.setName(name);
		pet.setCategory(category);
		pet.setTags(List.of(tag, tag2));
		pet.setStatus(status);
		System.out.println(petService.updateByID(pet));
	}

	public static void deletePetByID(int id) {
		PetService petService = new PetService();
		petService.deleteByID(id);
	}

	//Store
	public static void orderPurchasingPet(
			long id, long petId, long quantity, String shipDate, OrderStatus status, boolean complete
	) {
		StoreService storeService = new StoreService();
		Store store = new Store();
		store.setId(id);
		store.setPetId(petId);
		store.setQuantity(quantity);
		store.setShipDate(shipDate);
		store.setStatus(status);
		store.setComplete(complete);
		System.out.println(storeService.placeOrder(store));
	}

	public static void findOrderById(int id) {
		StoreService storeService = new StoreService();
		System.out.println(storeService.findOrderById(id));
	}

	public static void deleteOrderById(int id) {
		StoreService storeService = new StoreService();
		storeService.deleteOrderByID(id);
	}

	public static void orderInventory() {
		StoreService storeService = new StoreService();
		System.out.println(storeService.inventory());
	}

	//User
	public static void createUserWithList() {
		UserService userService = new UserService();
		User user1 = new User();
		User user2 = new User();
		user1.setId(0L);
		user1.setUsername("Karl");
		user1.setFirstName("John");
		user1.setLastName("Shepard");
		user1.setEmail("Normandy@spectr.com");
		user1.setPassword("passwo223232rd");
		user1.setPhone("~#&_054-482-45-65");
		user1.setUserStatus(0);
		user2.setId(1L);
		user2.setUsername("Nice one");
		user2.setFirstName("Nina!");
		user2.setLastName("Shepard");
		user2.setEmail("Joker@spectr.com");
		user2.setPassword("jchvhkjv");
		user2.setPhone("~#&_054-482");
		user2.setUserStatus(0);
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		System.out.println(userService.createWithList(users));
	}

	public static void findByUserName(String username) {
		UserService userService = new UserService();
		System.out.println(userService.findUserByUserName(username));
	}

	public static void updateByUsername(String upDateUsername, String username, String password) {
		UserService userService = new UserService();
		User user = new User();
		user.setId(0L);
		user.setUsername(username);
		user.setFirstName("John");
		user.setLastName("Shepard");
		user.setEmail("Normandy@spectr.com");
		user.setPassword(password);
		user.setPhone("~#&_054-482-45-65");
		user.setUserStatus(0);
		System.out.println(userService.updateUserByUsername(upDateUsername, user));
	}

	public static void login(String username, String password) {
		UserService userService = new UserService();
		System.out.println(userService.loginUser(username, password));
	}

	public static void deleteByUsername(String username) {
		UserService userService = new UserService();
		userService.deleteUserByUsername(username);
	}

	public static void logout() {
		UserService userService = new UserService();
		userService.logout();
	}

	public static void createdUser(String username, String password) {
		UserService userService = new UserService();
		User user1 = new User();
		user1.setId(0L);
		user1.setUsername(username);
		user1.setFirstName("John");
		user1.setLastName("Shepard");
		user1.setEmail("Normandy@spectr.com");
		user1.setPassword(password);
		user1.setPhone("~#&_054-482-45-65");
		user1.setUserStatus(0);
		System.out.println(userService.createdUser(user1));
	}
}
