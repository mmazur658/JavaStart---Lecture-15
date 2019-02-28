package pl.mazurmarcin.javastart.lecture15.homework;

import java.util.Comparator;

public enum ComparatorType {
	SORT_BY_NAME(1, new OrderNameComparator()), 
	SORT_BY_PRICE(2, new OrderPriceComparator()), 
	SORT_BY_STATUS(3, new OrderStatusComparator());

	private final int index;
	private final Comparator comparator;

	private ComparatorType(int index, Comparator comparator) {
		this.index = index;
		this.comparator = comparator;
	}

	public int getIndex() {
		return index;
	}

	public Comparator getComparator() {
		return comparator;
	}
	
	public static ComparatorType findByIndex(int index) {
		
		for (ComparatorType comparatorType : ComparatorType.values()) {
			if(comparatorType.getIndex() == index)
				return  comparatorType;
		}
		
		return null;
		
	}
}
