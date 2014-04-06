package Corba;
import Jokenpo.HandlerGameOperations;

class JokenpoServant implements HandlerGameOperations {

	public void registerCB (Jokenpo.ClientOps c, org.omg.CORBA.Any option) {
		

	}

	public void newGame (int gameId, int playerOne, int playerTwo) {

	}

  public void sendAction (int gameId, int playerId, org.omg.CORBA.Any option) {
  	
  }

}