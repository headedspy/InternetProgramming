package org.elsys.netprog.sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = null;
		
		ArrayList<String> messages = new ArrayList<>();
		
		Object mutex = new Object();
		
		ArrayList<PrintWriter> writers = new ArrayList<>();
		
		try{
			serverSocket = new ServerSocket(10001);
			new SendThread(mutex, messages, writers).start();
			
			while(true){
				Socket client = serverSocket.accept();
				writers.add(new PrintWriter(client.getOutputStream(), true));
				
				new RecieveThread(mutex, messages, client).start();
				System.out.println("Client connected from " + client.getInetAddress());
				
			}
			
		} catch (Throwable t){
			System.out.println(t.getMessage());
		} finally {
			if (serverSocket != null && !serverSocket.isClosed()){
				serverSocket.close();
			}
			System.out.println("Server closed!");
		}
	}
}
