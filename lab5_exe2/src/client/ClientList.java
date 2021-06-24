package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import itemProduct.ItemProduct;


@SuppressWarnings("unused")
public class ClientList {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		System.out.println("ClientSideApp: Demo to process a list of objects on TCP \n");

		// Request data
		ItemProduct itemProduct1 = new ItemProduct();
		itemProduct1.setName("BIg Mac");
		
		ItemProduct itemProduct2 = new ItemProduct();
		itemProduct2.setName("Cheese Burger");
		
		ItemProduct itemProduct3 = new ItemProduct();
		itemProduct3.setName("Chicken McDeluxe");
		
		// Add into list
		List<ItemProduct> itemProducts = new ArrayList<ItemProduct>();
		itemProducts.add(itemProduct1);
		itemProducts.add(itemProduct2);
		itemProducts.add(itemProduct3);

		try {

			// Data to establish connection to server
			int portNo = 6666;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 6666
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + itemProducts);
			objectOS.writeObject(itemProducts);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream, cast and display details
			itemProducts = (ArrayList<ItemProduct>) objectIS.readObject();
			for (ItemProduct itemProduct:itemProducts)
				System.out.println ("Id for " + itemProduct.getName() + " is " + itemProduct.getItemProductId());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nClientSideApp: End of application.\n");

	}

}

