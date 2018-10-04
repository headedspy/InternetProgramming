package org.elsys.netprog.sockets;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SendThread extends Thread {

	Object mutex;
	ArrayList<String> messages;
	ArrayList<PrintWriter> clients;
	
	public SendThread(Object mutex, ArrayList<String> msgRef, ArrayList<PrintWriter> clients){
		this.mutex = mutex;
		this.messages = msgRef;
		this.clients = clients;
	}
	
	public void run(){
		try{
			int oldSize = messages.size();
			while(true){
				synchronized(mutex){
					if(oldSize != messages.size()){
						System.out.println("msg out");
							for(PrintWriter out : clients){
								out.println(messages.get(messages.size() - 1));
							}
							oldSize = messages.size();
					}
				}
			}
		} catch (Throwable t){
			System.out.println(t.getMessage());
		}
	}

}
