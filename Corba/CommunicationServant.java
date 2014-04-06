package Corba;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import Communication.ClientOps;
import Communication.HandlerMessageOperations;

class CommunicationServant implements HandlerMessageOperations {

	ClientOps client;

	private Hashtable<Integer, ClientOps> allUsers = new Hashtable<Integer, ClientOps>();
	private ClientOps cbDetails;
	private static Integer ID = 1;

	public void registerCB (Communication.ClientOps c, int userId) {
		allUsers.put(userId, c);
		System.out.println("User registred: " + userId);
	}

	public void addUserToChat (int chatId, int userId) {

	}

  public void sendMessage (int userId, org.omg.CORBA.Any message) {
  	cbDetails = allUsers.get(userId);

  	Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message.extract_string());

  	cbDetails.callBack(anyMessage);
  }

}