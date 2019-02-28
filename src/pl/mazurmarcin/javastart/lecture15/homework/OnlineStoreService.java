package pl.mazurmarcin.javastart.lecture15.homework;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OnlineStoreService {

	private OrderDB orderDB = new OrderDB();

	public void cleanTheFloorAndCloseTheDoor() {
		orderDB.exportOrdersToFile();
	}

	public void createNewOrder(int id, String name, double price) {
		Order order = new Order(id, name, price);
		orderDB.addOrder(order);
	}

	public List<Order> getSortedList(Comparator<Order> comparator) {
		List<Order> orders = orderDB.getOrders();
		Collections.sort(orders, comparator);
		return orders;
	}

	public boolean orderExists(int id) {

		for (Order order : orderDB.getOrders()) {
			if (order.getId() == id)
				return true;
		}

		return false;

	}

	public boolean canOrderStatusBeCancelled(int id) {
		OrderStatus os = orderDB.getSingleOrderById(id).getStatus();
		return os == OrderStatus.CANCELLED || os == OrderStatus.COLLECTED || os == OrderStatus.READY_TO_DISPATCH;
	}

	public void changeStatus(int id, OrderStatus newOrderStatus) {
		orderDB.getSingleOrderById(id).setStatus(newOrderStatus);
	}


}
