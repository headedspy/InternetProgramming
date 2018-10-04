package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSend extends Thread{

	Socket socket;
	BufferedReader read;
	PrintWriter write;
	
	public ClientSend(Socket socket) throws IOException {
		this.socket = socket;
		this.read = new BufferedReader(new InputStreamReader(System.in));
		this.write = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void run(){
		try{
			String input;
			
			while((input = read.readLine()) != null){
				write.println(input);
				
				if(input.equals("exit"))socket.close();
			}
		} catch(Throwable t){
			System.out.println(t.getMessage());
		}
	}

}
