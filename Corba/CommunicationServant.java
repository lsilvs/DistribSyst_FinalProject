package Corba;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import Communication.CommunicationOps;
import Communication.HandlerMessageOperations;

class CommunicationServant implements HandlerMessageOperations {

	private Hashtable<Integer, CommunicationOps> allCBs = new Hashtable<Integer, CommunicationOps>();
	private Hashtable<Integer, ArrayList<Integer>> chats = new Hashtable<Integer, ArrayList<Integer>>();
	private CommunicationOps cbDetails;
	private static Integer ID = 1;

	public void registerCB (CommunicationOps c, int userId) {
		allCBs.put(userId, c);
		System.out.println("User registred to communication: " + userId);
	}

	public int createChat (int user1, int user2) {
		ArrayList<Integer> users = new ArrayList<Integer>();
		users.add(user1);
		users.add(user2);
		chats.put(ID, users);
		
		cbDetails = allCBs.get(user2);
		
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string("new_game");
	  	cbDetails.callBackCreateChat(ID, user1, anyMessage);
		
		return ID++;
	}
	
	public void sendMessageToChat (int chatId, int senderId, org.omg.CORBA.Any message) {		
		ArrayList<Integer> chat = chats.get(chatId);

		for (Integer user : chat) {
			cbDetails = allCBs.get(user);
			Any anyMessage = ORB.init().create_any();
			anyMessage.insert_string(message.extract_string());
		  	cbDetails.callBackShowdMessage(chatId, senderId, anyMessage);
		}
	}

}