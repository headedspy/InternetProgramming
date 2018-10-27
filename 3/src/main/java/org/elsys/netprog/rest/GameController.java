package org.elsys.netprog.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("game")
public class GameController {
	
	private ArrayList<Game> games = new ArrayList<Game>();
	
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		
		Game newGame = new Game();
		games.add(newGame);
		
		return Response.created(new URI("/games")).entity(newGame.gameID).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		
		//-----------------------
		
		@SuppressWarnings("unused")
		int parsed;
		
		try{
			parsed = Integer.parseInt(guess);
		}catch(NumberFormatException e){
			return Response.status(400).build();
		}
		
		if(guess.length() != 4){
			return Response.status(400).build();
		}
		
		String nums = "01234567890";

		int counter = 0;
		
		for(char num : nums.toCharArray()){
			if(guess.indexOf(num) != -1){
				counter++;
			}
		}
		
		if(counter != 4) return Response.status(400).build();
		
		//-----------------------
		
		for(Game game : games){
			if(gameId.equals(game.gameID)){
				GameResponse res = game.makeGuess(guess);
				return Response.status(200).entity(res).build();
			}
		}
		
		return Response.status(404).entity(games.size()+"TESTIN").build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		return Response.status(200).entity(games).build();
	}
}