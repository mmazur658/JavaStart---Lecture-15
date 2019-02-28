package pl.mazurmarcin.javastart.lecture15.homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	private final String FILE_NAME = "orders.order";

	public List<Order> importOrdersFromFile() {

		List<Order> orders = new ArrayList<>();
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(FILE_NAME)))){
			
			Object object = null;
			while((object = ois.readObject() )!=null) {
				
				if(object instanceof Order )
					orders.add((Order) object);
	
			}			
			
		} catch (FileNotFoundException exception) {
			System.err.println("File does not exist");
		} catch (IOException exception) {
			System.err.println("Error during reading the data.");
		} catch (ClassNotFoundException e) {
			System.err.println("Invalid object type");
		} 

		return orders;
	}

	public void exportOrdersToFile(List<Order> orders) {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

			for (Order order : orders) {
				if(order!= null)
					oos.writeObject(order);
			}

		} catch (FileNotFoundException exception) {
			System.err.println("File does not exist");
		} catch (IOException exception) {
			System.err.println("Error during reading the data.");
		}

	}

}
