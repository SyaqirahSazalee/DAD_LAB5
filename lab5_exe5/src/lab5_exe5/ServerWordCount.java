package lab5_exe5;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerWordCount {

	
	public static void main(String[] args) {
		
		final int SERVERPORT = 6666;
		
		int bufferSize = 9999;
		
		System.out.println("Server-Side Application");
		
		try {
			// for receiving the data from client
			DatagramSocket serverSocket = new DatagramSocket(SERVERPORT);// create a socket to react with this port
			
			byte[] inData = new byte[bufferSize];// create a buffer to hold the data 
			
			DatagramPacket inputPacket = new DatagramPacket(	inData,			// create a packet to get the inpput data
																inData.length);
			
			System.out.println("Ready to receive connection...");
			
			serverSocket.receive(inputPacket);// receive the data
			
			String data = new String(inputPacket.getData());// unpack the packet
			
			System.out.println("Data from client: "+ data);
			// to count the word
			String wordCounted = Integer.toString(countWords(data));
			// to send it back
			byte outDataBuffer[] = new byte[bufferSize];// create a variable to hold data
			
			outDataBuffer = wordCounted.getBytes();
			
			// Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
			DatagramPacket outputPacket = new DatagramPacket(outDataBuffer, 
					outDataBuffer.length, senderAddress,senderPort);
			
			System.out.println("Sending back to client");
			serverSocket.send(outputPacket);
	
			serverSocket.close();// close the socket 
			
		}catch (Exception ex) {
			System.out.println("Something went wront");
			ex.printStackTrace();
		}finally{
			
			System.out.println("done with the server side");
		}
		
	}
	
	public static int countWords(String text) {
		
		String trim = text.trim();
        if (trim.isEmpty())
                return 0;
        return trim.split("\s+").length; // separate string around spaces
        
	}
}