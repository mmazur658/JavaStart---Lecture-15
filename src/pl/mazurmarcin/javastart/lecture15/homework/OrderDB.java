package pl.mazurmarcin.javastart.lecture15.homework;

import java.util.List;

public class OrderDB {

	private FileUtils fileUtils = new FileUtils();
	private List<Order> orders;

	public OrderDB() {
		orders = fileUtils.importOrdersFromFile();
		
		/*for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getName().equals("Jedzenie dla kota")) {
				orders.get(i).setId(3);
			}
		}*/
		
	}

	public void exportOrdersToFile() {
		fileUtils.exportOrdersToFile(orders);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public Order getSingleOrderById(int id) {

		for (Order order : orders) {
			if(order.getId() == id)
				return order;
		}
		
		return null;
	}

}
