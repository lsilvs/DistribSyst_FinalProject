import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import org.omg.CORBA.*;

import Jokenpo.*;

class JokenpoServant implements HandlerGameOperations {

	public void registerCB (Jokenpo.ClientOps c, org.omg.CORBA.Any option) {
		

	}

	public void newGame (int gameId, int playerOne, int playerTwo) {

	}

  public void sendAction (int gameId, int playerId, org.omg.CORBA.Any option) {
  	
  }

}