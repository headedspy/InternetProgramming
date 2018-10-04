package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;

public class RecieveThread extends Thread{
	
	Object mutex;
	ArrayList<String> messages;
	Socket socket;
	BufferedReader read;

	public RecieveThread(Object mutex, ArrayList<String> msgRef, Socket socket) throws IOException{
		this.mutex = mutex;
		this.messages = msgRef;
		this.socket = socket;
		this.read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run(){
		try{
			while(!socket.isClosed()){
				String clientMsg = read.readLine();
				synchronized(mutex){
					if(clientMsg != null){
							messages.add(clientMsg);
							System.out.println(clientMsg);
					}
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
