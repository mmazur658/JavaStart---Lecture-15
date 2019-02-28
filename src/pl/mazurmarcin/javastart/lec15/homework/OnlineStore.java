package pl.mazurmarcin.javastart.lec15.homework;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OnlineStore {

	private OnlineStoreService onlineStoreService = new OnlineStoreService();
	private Scanner scanner = new Scanner(System.in);

	public void runOnlineStore() {

		int userInput = 999;
		System.out.println("Witaj w sklepie. Wybierz jedną z poniższych opcji: ");

		do {

			printMainOptions();
			userInput = getCorrectInt();
			OnlineStoreMenu onlineStoreMainMenu = OnlineStoreMenu.findByValue(userInput);

			if (onlineStoreMainMenu != null) {
				switch (onlineStoreMainMenu) {
				case SORT:
					sort();
					break;
				case CHANGE_STATUS:
					changeStatus();
					break;
				case ADD_ORDER:
					addNewOrder();
					break;
				case EXIT:
					sayGoodBye();
					break;
				default:
					System.out.println("Wybór nieznany - spóbuj ponownie");
				}
			} else
				System.out.println("Wybór nieznany - spóbuj ponownie");

		} while (userInput != OnlineStoreMenu.EXIT.getValue());

	}

	public void printMainOptions() {

		System.out.println(OnlineStoreMenu.SORT.getValue() + " - Sortowanie i  wyświetlenie listy");
		System.out.println(OnlineStoreMenu.CHANGE_STATUS.getValue() + " - Zmiana statusu");
		System.out.println(OnlineStoreMenu.ADD_ORDER.getValue() + " - Dodawanie zamówienia");
		System.out.println(OnlineStoreMenu.EXIT.getValue() + " - Koniec");

	}

	public void printSortOptinos() {

		System.out.println("\nWybierz typ sortowania: ");
		System.out.println(ComparatorType.SORT_BY_NAME.getIndex() + " - sortuj po nazwie");
		System.out.println(ComparatorType.SORT_BY_PRICE.getIndex() + " - sortuj po cenie");
		System.out.println(ComparatorType.SORT_BY_STATUS.getIndex() + " - sortuj po statusie");

	}

	public void printList(List<Order> orders) {
		for (int i = 0; i < orders.size(); i++)
			System.out.println((i + 1) + " - " + orders.get(i));
	}

	@SuppressWarnings("unchecked")
	private void sort() {
		printSortOptinos();
		ComparatorType comparator = getComparator();

		if (comparator != null) {
			List<Order> orders = onlineStoreService.getSortedList(comparator.getComparator());
			printList(orders);
		} else
			System.out.println("Typ sortowania nieznany");

	}

	private void addNewOrder() {

		System.out.println("Dodawanie nowego zamówienia . . .");

		System.out.println("Podaj id: ");
		int id = getUniqueId();

		getCorrectInt();

		scanner.nextLine();
		System.out.println("Podaj nazwę: ");
		String name = scanner.nextLine();

		System.out.println("Podaj cenę: ");
		double price = getCorrectDouble();

		onlineStoreService.createNewOrder(id, name, price);
		System.out.println("Zamówienie zostało dodane");

	}

	private int getUniqueId() {

		int userInput;
		boolean isIdUnique = false;

		do {

			userInput = getCorrectInt();
			isIdUnique = !onlineStoreService.orderExists(userInput);

			if (!isIdUnique)
				System.out.println("Podane id nie jest unikalne, wpisz ponownie . . . ");

		} while (!isIdUnique);

		return userInput;
	}

	private void sayGoodBye() {

		System.out.println("Do zobaczenia!");
		onlineStoreService.cleanTheFloorAndCloseTheDoor();
		scanner.close();

	}

	private void changeStatus() {

		System.out.println("Podaj id zamówienia: ");
		int id = getCorrectInt();

		if (onlineStoreService.orderExists(id)) {

			OrderStatus newOrderStatus = getNewOrderStatus();

			if (newOrderStatus == OrderStatus.CANCELLED) {

				if (onlineStoreService.canOrderStatusBeCancelled(id)) {
					onlineStoreService.changeStatus(id, newOrderStatus);
					System.out.println("Status został zmieniony.");
				} else
					System.out.println("Nie można zmienić statusu");

			} else {
				onlineStoreService.changeStatus(id, newOrderStatus);
				System.out.println("Status został zmieniony");
			}

		} else {
			System.out.println("Brak zamówienia o podanym indexie.");
		}

	}

	public int getCorrectInt() {

		int number = 0;
		boolean isNumberInt = false;

		do {
			try {
				number = scanner.nextInt();
				isNumberInt = true;
			} catch (InputMismatchException exception) {
				System.out.println("Nieprawidłowy format danych");
				scanner.nextLine();
			}
		} while (!isNumberInt);

		return number;

	}

	public double getCorrectDouble() {

		double number = 0;
		boolean isNumberDouble = false;

		do {
			try {
				number = scanner.nextDouble();
				isNumberDouble = true;
			} catch (InputMismatchException exception) {
				System.out.println("Nieprawidłowy format danych");
				scanner.nextLine();
			}
		} while (!isNumberDouble);

		return number;
	}

	public ComparatorType getComparator() {

		int userInput = getCorrectInt();
		ComparatorType comparatorType = ComparatorType.findByIndex(userInput);
		return comparatorType;

	}

	public OrderStatus getNewOrderStatus() {

		System.out.println("Dostępne statusy: (podaj number)");
		for (OrderStatus status : OrderStatus.values())
			System.out.println((status.ordinal() + 1) + " - " + status);

		OrderStatus orderStatus = null;
		int userInput = 0;

		do {
			userInput = getCorrectInt() - 1;

			for (OrderStatus status : OrderStatus.values()) {
				if (status.ordinal() == userInput)
					orderStatus = status;
			}

			if (orderStatus == null)
				System.out.println("Status nierozpoznany - wpisz ponownie");

		} while (orderStatus == null);

		return orderStatus;
	}

}
