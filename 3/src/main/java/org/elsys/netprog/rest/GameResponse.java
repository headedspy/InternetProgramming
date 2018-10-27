package org.elsys.netprog.rest;

public class GameResponse {

	public String gameId;
	public int cowsNumber;
	public int bullsNumber;
	public int turnsCount;
	public boolean success;
	
	public GameResponse(String givenId, int givenCows, int givenBulls, int givenTurns, boolean givenSuccess) {
		gameId = givenId;
		cowsNumber = givenCows;
		bullsNumber = givenBulls;
		turnsCount = givenTurns;
		success = givenSuccess;
	}
}
