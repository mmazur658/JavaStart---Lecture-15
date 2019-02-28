package pl.mazurmarcin.javastart.lec15.homework;

import java.util.Comparator;

public class OrderStatusComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return Integer.compare(o1.getStatus().ordinal(), o2.getStatus().ordinal());
	}

}
