import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.*;
 
public class Main {
    private String url = "http://localhost:8080/Server/hash/";
    private String hash = null;
    private int length = 0;
   
    public static void main(String[] args) {
       Main main = new Main();
       main.start();
    }
    
    private void start(){
    	StringBuffer sb = null;
        String pass = null;
       
        try {
            sb = sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        try {
            JSONObject obj = new JSONObject(sb.toString());
            
            length = obj.getInt("length");
            hash = obj.getString("hash");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
        
        pass = generateString();
        int code = sendPost(pass);
    	
        while(code != 200){
            pass = generateString();
            System.out.println("Trying with string \"" + pass + "\"");
            code = sendPost(pass);
        }
        System.out.println("STRING FOUND: " + pass);
        
        long num = System.currentTimeMillis()-start;
        
        System.out.println("Took " + num + " miliseconds");
    }
   
    private StringBuffer sendGet() throws Exception {
       
        URL obj = new URL(url + "getHash");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
        con.setRequestMethod("GET");
        System.out.println("Sending 'GET' request");
 
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
 
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
 
        System.out.println(response.toString());
        return response;
    }
   
    private int sendPost(String str){
    	int code = 0;
    	String query_url = url + "guessHash";
    	
    	String json = "{" +
                "\"hash\": \""+hash+"\", " +
                "\"input\": \""+str+"\"" +
                "}";
        try{
	        URL url = new URL(query_url);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        conn.setRequestMethod("POST");
	        OutputStream os = conn.getOutputStream();
	        os.write(json.getBytes("UTF-8"));
	        os.close();
	        
	        code = conn.getResponseCode();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return code;
    }
   
    public String generateHash(int stringLength) {
        String testString = RandomStringUtils.randomAlphabetic(stringLength).toLowerCase();
       
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
    	byte[] hashInBytes = md.digest(testString.getBytes(StandardCharsets.UTF_8));
    	
    	StringBuilder sb = new StringBuilder();
    	for(byte b : hashInBytes){
    		sb.append(String.format("%02x", b));
    	}
    	
    	return sb.toString();
    }
    
    public String generateString(){
    	return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }
}