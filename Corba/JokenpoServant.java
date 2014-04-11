package Corba;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import Jokenpo.HandlerGameOperations;
import Jokenpo.JokenpoOps;

class JokenpoServant implements HandlerGameOperations {
	
	private Hashtable<Integer, JokenpoOps> allCBs = new Hashtable<Integer, JokenpoOps>(); //stores all the callbacks related with the game.
	private Hashtable<Integer, Hashtable<Integer, String>> games = new Hashtable<Integer, Hashtable<Integer, String>>(); // stores all the games created.
	private JokenpoOps cbDetails; //
	private static Integer ID = 1; // ID is used to references a game.

	
	//register a callback in the hashtable allCBS.
	public void registerCB (JokenpoOps c, int userId) {
		allCBs.put(userId, c);
		System.out.println("User registred to communication: " + userId);
	}

	//creates a new game and puts into the users hashtable.
	public int createGame (int user1, int user2) {
		Hashtable<Integer, String> users = new Hashtable<Integer, String>();
		users.put(user1, "");
		users.put(user2, "");
		games.put(ID, users);
		
		
		cbDetails = allCBs.get(user1);
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string("$user2 joined to the game");
	  	cbDetails.callBackCreateGame(ID, user1, anyMessage);
	  	
	  	cbDetails = allCBs.get(user2);
		anyMessage = ORB.init().create_any();
		anyMessage.insert_string("$user1 invited you to a new game");
	  	cbDetails.callBackCreateGame(ID, user2, anyMessage);
		
		return ID++;
	}
	
	//Send an action of a player in the game. The action represents the move of a player.
	public void sendAction (int gameId, int senderId, org.omg.CORBA.Any message) {	
		Integer counter = 0;
		Integer answers = 0;
		Hashtable<Integer, String> game = games.get(gameId);
		game.put(senderId, message.extract_string());
		
		games.put(gameId, game);
		
		Integer[] userId = new Integer[2];
		String[] option = new String[2];

		//verify if the both players already played.
		Enumeration<Integer> enumKey = game.keys();
		while(enumKey.hasMoreElements()) {
			userId[counter] = enumKey.nextElement();
			option[counter] = game.get(userId[counter]);
		    if(game.get(userId[counter]) != "") {
		    	answers++;
		    }
		    counter++;
		}
		
		String result = "";

		//checks the moves os the players and decide who will wins.
		if(answers == 2) {
			if(option[0].equals("paper")) {
				if(option[1].equals("paper")) {
					result = "$user1 (paper) drawn $user2 (paper)";
				} else if(option[1].equals("rock")) {
					result = "$user1 (paper) won $user2 (rock)";
				} else if(option[1].equals("scissors")) {
					result = "$user2 (scissors) won $user1 (paper)";
				}
				
			} else if(option[0].equals("rock")) {
				if(option[1].equals("paper")) {
					result = "$user2 (paper) won $user1 (rock)";
				} else if(option[1].equals("rock")) {
					result = "$user1 (rock) drawn $user2 (rock)";
				} else if(option[1].equals("scissors")) {
					result = "$user1 (rock) won $user2 (scissors)";
				}

			} else if(option[0].equals("scissors")) {
				if(option[1].equals("paper")) {
					result = "$user1 (scissors) won $user2 (paper)";
				} else if(option[1].equals("rock")) {
					result = "$user2 (rock) won $user1 (scissors)";
				} else if(option[1].equals("scissors")) {
					result = "$user1 (scissors) drawn $user2 (scissors)";
				}
			}

			Any anyMessage = ORB.init().create_any();
			anyMessage.insert_string(result);

			//show the result to the players.
			for (Integer user : userId) {
				cbDetails = allCBs.get(user);
				cbDetails.callBackShowdResult(userId[0], userId[1], anyMessage);
			}
			game.put(userId[0], "");
			game.put(userId[1], "");
		}
	}
}