package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import itemProduct.ItemProduct;

public class ClientSideApp {

	public static void main(String[] args) {

		System.out.println("ClientSideApp: Demo of Object Stream\n");
		
		// Request data
		ItemProduct itemProduct = new ItemProduct();
		itemProduct.setName("Big Mac");
		
		try 
		{
			
			int portNo = 6666;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// Connect to the server at localhost, port 6666
			@SuppressWarnings("resource")
			Socket socket = new Socket(serverAddress, portNo);
			
			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
			
			// Send request to server 
			System.out.println("Send object to server: "+ itemProduct);
			objectOS .writeObject(itemProduct);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			itemProduct = (ItemProduct) objectIS.readObject();
			System.out.println("Id for " + itemProduct.getName() + " is " + itemProduct.getItemProductId() + " at " + itemProduct.getPrice());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
		}
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("\nClientSideApp: End of application.");
	}

}
