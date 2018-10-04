package org.elsys.netprog.sockets;

import java.io.IOException;
import java.net.Socket;

public class SocketsClient {

	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket("localhost", 10001);
			    
			new ClientSend(clientSocket).start();
			new ClientRecieve(clientSocket).start();
			    
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
		}
	}
}
