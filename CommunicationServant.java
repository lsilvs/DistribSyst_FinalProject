import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import org.omg.CORBA.*;

import Communication.*;

class CommunicationServant implements HandlerMessageOperations {

	ClientOps client;

	private Hashtable<Integer, ClientOps> allChats = new Hashtable<Integer, ClientOps>();
	private ClientOps chatDetails;
	private static Integer ID = 1;

	public void registerCB(Communication.ClientOps c, org.omg.CORBA.Any message) {
		client = c;
		System.out.println("Message received: " + message.extract_string());
		client.callBack(message);
	}

	public void addUserToChat (int chatId, int userId) {

	}

  public void sendMessage (int chatId, int userId, org.omg.CORBA.Any message) {

  }

}