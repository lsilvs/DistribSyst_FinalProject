package Corba;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnyHolder;
import org.omg.CORBA.ORB;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import Communication.ClientOps;
import Communication.HandlerMessageOperations;

class CommunicationServant implements HandlerMessageOperations {

	ClientOps client;

	private Hashtable<Integer, ClientOps> allUsers = new Hashtable<Integer, ClientOps>();
	private Hashtable<Integer, ArrayList<Integer>> chats = new Hashtable<Integer, ArrayList<Integer>>();
	private ClientOps cbDetails;
	private static Integer ID = 1;

	public void registerCB (Communication.ClientOps c, int userId) {
		allUsers.put(userId, c);
		System.out.println("User registred to communication: " + userId);
	}

	public int createChat (int user1, int user2) {
		ArrayList<Integer> users = new ArrayList<Integer>();
		users.add(user1);
		users.add(user2);
		chats.put(ID, users);
		
		cbDetails = allUsers.get(user2);
		
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string("Fulano invited you to a new game");
	  	cbDetails.callBackCreateChat(ID, user1, anyMessage);
		
		return ID++;
	}
	
	public void addUserToChat (int chatId, int userId) {
//		chat.put(chatId, userId);
	}
	
	public void sendMessageToChat (int chatId, int senderId, org.omg.CORBA.Any message) {		
		ArrayList<Integer> chat = chats.get(chatId);

		for (Integer user : chat) {
			cbDetails = allUsers.get(user);
			Any anyMessage = ORB.init().create_any();
			anyMessage.insert_string(message.extract_string());
		  	cbDetails.callBackShowdMessage(chatId, senderId, anyMessage);
		}
	}

	public void sendMessage (int userId, org.omg.CORBA.Any message) {
	  	cbDetails = allUsers.get(userId);

	  	Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message.extract_string());

//	  	cbDetails.callBack(anyMessage);
	}

}