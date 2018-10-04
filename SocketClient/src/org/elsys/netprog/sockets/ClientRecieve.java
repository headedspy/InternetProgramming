package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientRecieve extends Thread{

	Socket socket;
	BufferedReader read;
	
	public ClientRecieve(Socket socket) throws IOException{
		this.socket = socket;
		this.read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run(){
		try{
			while(!socket.isClosed()){
				String readMsg = read.readLine();
				if(readMsg != null){
					System.out.println(readMsg);
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
