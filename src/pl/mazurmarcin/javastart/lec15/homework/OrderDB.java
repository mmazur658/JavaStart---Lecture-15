package pl.mazurmarcin.javastart.lec15.homework;

import java.util.List;

public class OrderDB {

	private FileUtils fileUtils = new FileUtils();
	private List<Order> orders;

	public OrderDB() {
		orders = fileUtils.importOrdersFromFile();		
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
