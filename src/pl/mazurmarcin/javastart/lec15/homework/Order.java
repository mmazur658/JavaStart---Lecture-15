package pl.mazurmarcin.javastart.lec15.homework;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 8698691913976134775L;
	private int id;
	private String name;
	private double price;
	private OrderStatus status;

	public Order(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = OrderStatus.COLLECTED;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " id: "+id+", nazwa:  "+name+", cena: "+price+", status: " +status;
	}

}
