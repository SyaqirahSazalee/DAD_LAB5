package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import itemProduct.ItemProduct;

public class ServerSideApp {

	public static void main(String[] args) {

		try 
		{
			
			// Port to receive and respond to request
			int portNo = 6666;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			System.out.println("Waiting for request");
			
			// Server need to be alive forever thus the while(true)
			while(true)
			{
				// Accept client request for connection
				Socket socket = serverSocket.accept();
				
				// Create input stream to read object
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
				
				// Read object from stream and cast to Location
				ItemProduct itemProduct = (ItemProduct) objectIS.readObject();
				
				// Process object
				itemProduct.setItemProductId(1);
				
				// Create output stream to send object
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
				objectOS.writeObject(itemProduct);
				
				System.out.println("Waiting for next request");
				
				// Close all streams
				objectIS.close();
				objectOS.close();
				serverSocket.close();
			}
		}
		
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}

