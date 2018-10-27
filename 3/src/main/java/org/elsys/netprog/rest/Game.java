package org.elsys.netprog.rest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {

	public String gameID;
	public int turnsCount;
	public String secret;
	public boolean success;
	
	private String key;
	
	public Game() {
		gameID = GameIdGenerator();
		turnsCount = 0;
		success = false;
		secret = getSecret();
		
		key = GameSecretGenerator();
	}
	
	public GameResponse makeGuess(String guess){
		int tempCows = 0;
		int tempBulls = 0;
		
		turnsCount++;
		
		for(char c : guess.toCharArray()){
			if(key.indexOf(c) != -1){
				if(guess.indexOf(c) == key.indexOf(c)){
					tempBulls++;
				}else{
					tempCows++;
				}
			}
		}
		
		if(tempBulls == 4){
			success = true;
			secret = key;
		}
		
		return new GameResponse(gameID, tempCows, tempBulls, turnsCount, success);
	}
	
	public String getSecret(){
		if(success)return key;
		else return "****";
	}
	
	private String GameIdGenerator(){
		String allowedChars = "1234567890abcdef";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		
		for(int i=0; i< 36; i++){
			sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
			if(i==8 || i==13 || i==18 || i==23)sb.append('-');
		}
		
		return sb.toString();
	}
	
	private String GameSecretGenerator(){
		Set<Integer> rnd = new HashSet<Integer>();
		int[] allowedIntegers = {0,1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		
		while(rnd.size() < 4){
			rnd.add(allowedIntegers[rand.nextInt(allowedIntegers.length)]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(Integer i : rnd){
			sb.append(i.toString());
		}
		
		return sb.toString();
	}

}
