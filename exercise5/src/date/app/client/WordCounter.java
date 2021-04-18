package date.app.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class WordCounter {
	
	
	private ServerConnector connector;
	
	
	public WordCounter () {
		
		connector = new ServerConnector();
	}
	
	public int countWord (String text) throws Exception {
		
		// Closeable objects that makes TCP happen
		Socket socket = null;
		DataOutputStream outToServer = null;
		DataInputStream inFromServer = null;
		
		
		int totalWords = 0;
		
		try {
			
			// Get connection
			socket =  connector.getConnection();
			
			// Send data to server
			outToServer = new DataOutputStream(socket.getOutputStream());
			outToServer.writeUTF(text);
			
			// Get total words from socket
			inFromServer = new DataInputStream(socket.getInputStream());
			totalWords = inFromServer.readInt();
			
					
		} catch (Exception exception ) {
			
			throw exception;
			
			
		} finally {
			// Regardsless what happen, close all opened connection
			
			if (outToServer != null)
				outToServer.close();
			
			if (inFromServer != null)
				inFromServer.close();
			
			if (socket != null)
				socket.close();
			
		}
		
		return totalWords;
		
	}
	
	

}
