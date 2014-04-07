package Corba;
import java.util.Hashtable;

import Communication.ClientOps;
import Jokenpo.HandlerGameOperations;

class JokenpoServant implements HandlerGameOperations {
	
	private Hashtable<Integer, Jokenpo.ClientOps> allUsers = new Hashtable<Integer, Jokenpo.ClientOps>();

	public void registerCB (Jokenpo.ClientOps c, int userId) {
		allUsers.put(userId, c);
		System.out.println("User registred to the game: " + userId);
	}

	public void createGame (int playerOne, int playerTwo) {

	}

  public void sendAction (int gameId, int playerId, org.omg.CORBA.Any option) {
  	
  }

}