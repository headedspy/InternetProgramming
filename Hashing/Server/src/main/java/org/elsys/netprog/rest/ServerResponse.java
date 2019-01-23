package org.elsys.netprog.rest;
 
public class ServerResponse {
   
    public String hash;
    public Integer length;
   
    public ServerResponse(String hash, Integer length) {
        this.hash = hash;
        this.length = length;
    }
}