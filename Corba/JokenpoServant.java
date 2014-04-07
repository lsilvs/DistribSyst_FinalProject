package Corba;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import Jokenpo.HandlerGameOperations;
import Jokenpo.JokenpoOps;

class JokenpoServant implements HandlerGameOperations {
	
	private Hashtable<Integer, JokenpoOps> allCBs = new Hashtable<Integer, JokenpoOps>();
	private Hashtable<Integer, ArrayList<Integer>> chats = new Hashtable<Integer, ArrayList<Integer>>();
	private JokenpoOps cbDetails;
	private static Integer ID = 1;

	public void registerCB (JokenpoOps c, int userId) {
		allCBs.put(userId, c);
		System.out.println("User registred to communication: " + userId);
	}

	public int createGame (int user1, int user2) {
		ArrayList<Integer> users = new ArrayList<Integer>();
		users.add(user1);
		users.add(user2);
		chats.put(ID, users);
		
		cbDetails = allCBs.get(user2);
		
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string("Fulano invited you to a new game");
	  	cbDetails.callBackCreateGame(ID, user1, anyMessage);
		
		return ID++;
	}
	
	public void sendAction (int chatId, int senderId, org.omg.CORBA.Any message) {		
		ArrayList<Integer> chat = chats.get(chatId);

		for (Integer user : chat) {
			cbDetails = allCBs.get(user);
			Any anyMessage = ORB.init().create_any();
			anyMessage.insert_string(message.extract_string());
		  	cbDetails.callBackShowdResult(chatId, senderId, anyMessage);
		}
	}

}