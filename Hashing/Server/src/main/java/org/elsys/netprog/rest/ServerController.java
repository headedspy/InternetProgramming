package org.elsys.netprog.rest;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import com.sun.jersey.spi.resource.Singleton;
 
 
@Path("hash")
@Singleton
public class ServerController {
   
    private String hash = null;
    private int length = 1;
    private String pass;
   
    @POST
    @Path("/guessHash")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response postMethod(String input) throws URISyntaxException{
    	
    	try{
	    	JSONObject obj = new JSONObject(input);
	        
	        String givenHash = obj.getString("hash");
	        String givenInput = obj.getString("input");
        
	        if(pass.equals(givenInput) && hash.equals(givenHash)){
	    		System.out.println("!STRING GUESSED!");
	    		System.out.println("!GENERATING NEW!");
	    		generateNewHash();
	    		return Response.status(200).build();
	    	}
	        return Response.status(406).build();
        
        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    	System.out.println(input);
		return null;
    }
   
    @GET
    @Path("/getHash")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response getMethod() {
        if(hash == null){
            generateNewHash();
        }
        
        System.out.println(pass + " " + hash);
        
        return Response.status(200).entity(new ServerResponse(hash, length)).build();
    }
   
    public void generateNewHash() {
        pass = RandomStringUtils.randomAlphabetic(length).toLowerCase();
        
    	MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return;
		}
    	byte[] hashInBytes = md.digest(pass.getBytes(StandardCharsets.UTF_8));
    	
    	StringBuilder sb = new StringBuilder();
    	for(byte b : hashInBytes){
    		sb.append(String.format("%02x", b));
    	}
    	
    	hash = sb.toString();
    }
}