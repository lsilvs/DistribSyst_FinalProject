package Corba;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JTextPane;

import jokenpoGUI.InviteContactUI;
import jokenpoGUI.RegistrationUI;
import jokenpoGUI.View;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnyHolder;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import AddressBook.Address;
import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import AddressBook.AddressHelper;
import Communication.HandlerMessage;
import Communication.HandlerMessageHelper;
import Jokenpo.HandlerGame;
import Jokenpo.HandlerGameHelper;

public class Client {

	private static Address addressBookRef;
	private static HandlerMessage communicationRef;
	private static HandlerGame jokenpoRef;
	private static AddressAccountDetails addressAccountDetails;
	private static InviteContactUI invite = new InviteContactUI(new javax.swing.JFrame(), false);
	private static View view = new View();
	private static int chatId;
	private static int gameId;
	
	public static AddressAccountDetails getAddressAccountDetails() {
		return addressAccountDetails;
	}
	
	public static InviteContactUI getInvite() {
		return invite;
	}
	
	public static View getView() {
		return view;
	}
	
	public static Address getAddressBookRef() {
		return addressBookRef;
	}
	
	public static void setChatId(int id) {
		chatId = id;
	}
	
	public static void setGameId(int id) {
		gameId = id;
	}

	public static void main(String args[]) {
		try {
			NameComponent nc[] = new NameComponent[2];
			// At unix based system the port 900 is used for something, that is
			// the reason that I choose to use the port 1050
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			
			// create and initialize the ORB
			ORB orb = ORB.init(args, props);

			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("AddressBook", "Object");
			org.omg.CORBA.Object objRefFP = rootCtx.resolve(nc);
			addressBookRef = AddressHelper.narrow(objRefFP);
			
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("Communication", "Object");
			objRefFP = rootCtx.resolve(nc);
			communicationRef = HandlerMessageHelper.narrow(objRefFP);
			
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("Jokenpo", "Object");
			objRefFP = rootCtx.resolve(nc);
			jokenpoRef = HandlerGameHelper.narrow(objRefFP);

			RegistrationUI dialog = new RegistrationUI(new javax.swing.JFrame(), true);
			dialog.setVisible(true);
			
		} catch (Exception e) {
		  System.out.println("ERROR : " + e) ;
		  e.printStackTrace(System.out);
		}
	}

	public static void register(RegistrationUI registrationUI) {
		AddressAccountDetails accountDetails1 = new AddressAccountDetails(
			0, // id
			registrationUI.getUsername(), // name
			registrationUI.getAddress(), // address
			registrationUI.getPhone(), // phoneNumber
			registrationUI.getEmail() // email
		);

		Any anyAccount = ORB.init().create_any();
		
		try {
			AddressAccountDetailsHelper.insert(anyAccount, accountDetails1);
		} catch (SystemException se) {
			System.out.println("insert any error\n" + "Unexpected exception:\n" + se.toString ());
		}

		AnyHolder aad = new AnyHolder();
		addressBookRef.insert(anyAccount, aad);
		addressAccountDetails = AddressAccountDetailsHelper.extract(aad.value);
		
		Communication.CommunicationOps callBackCommunicationRef = new Communication.CommunicationOps_Tie(new ClientOpsCommunicationImpl());
		communicationRef.registerCB(callBackCommunicationRef, addressAccountDetails.id);
		
		Jokenpo.JokenpoOps callBackJokenpoRef = new Jokenpo.JokenpoOps_Tie(new ClientOpsJokenpoImpl());
        jokenpoRef.registerCB(callBackJokenpoRef, addressAccountDetails.id);
		
		registrationUI.dispose();
		invite();
	}

	private static void invite() {
		AnyHolder addressBook = new AnyHolder();
		addressBookRef.getList(addressBook);

		System.out.println("Message via callBack from server is " + addressBook.value.extract_string());

		List<String> items = Arrays.asList(addressBook.value.extract_string().split("\\s*,\\s*"));
		
		for (String temp : items) {
			int foo = Integer.parseInt(temp);
			AnyHolder account = new AnyHolder();
			addressBookRef.get(foo, account);
			AddressAccountDetails accountDetails = AddressAccountDetailsHelper.extract(account.value);
			invite.getjComboBox1().addItem(accountDetails);
		}

		/* Add this code into AddressAccountDetails.java after compile the idlfile.idl
		@Override
		public String toString() {
		  return this.name;
		}
		*/

		invite.setVisible(true);
	}

	public static void view(InviteContactUI invite) {
		
		AddressAccountDetails guest = (AddressAccountDetails) invite.getjComboBox1().getSelectedItem();
		
		int gameId = jokenpoRef.createGame(addressAccountDetails.id, guest.id);
		setGameId(gameId);
		int chatId = communicationRef.createChat(addressAccountDetails.id, guest.id);
		setChatId(chatId);

		view.setVisible(true);
        invite.dispose();

	}

	public static void sendMessage(String message) {
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message);
		communicationRef.sendMessageToChat(chatId, addressAccountDetails.id, anyMessage);
	}
	
	public static void chooseOption(String message) {
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message);
		jokenpoRef.sendAction(gameId, addressAccountDetails.id, anyMessage);
	}
}

class ClientOpsCommunicationImpl implements Communication.CommunicationOpsOperations {
	public void callBackCreateChat(int chatId, int senderId, org.omg.CORBA.Any message) {
		Client.setChatId(chatId);

		View view = Client.getView();
			
		view.setVisible(true);
        Client.getInvite().dispose();
        
        AnyHolder anyAccount = new AnyHolder();
        Client.getAddressBookRef().get(senderId, anyAccount);
        AddressAccountDetails sender = AddressAccountDetailsHelper.extract(anyAccount.value); 		
        
		JTextPane jTextPane1 = view.getJTextPane1();
        jTextPane1.setText(sender.name + " invited you to a game");
			
		System.out.println("Message via callBack from server is " + message.extract_string());
	}

	public void callBackShowdMessage(int chatId, int senderId, Any message) {
		View view = Client.getView();
		
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		Client.getAddressBookRef().get(senderId, addressAccountDetailsOut);
		AddressAccountDetails accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsOut.value);

		JTextPane jTextPane1 = view.getJTextPane1();
        String oldMessage = jTextPane1.getText() + "\n";
        jTextPane1.setText(oldMessage + 
        					accountDetails.name + ": " + 
			        		message.extract_string());
			
		System.out.println("Message via callBack from server is " + message.extract_string());
	}
}

class ClientOpsJokenpoImpl implements Jokenpo.JokenpoOpsOperations {
	@Override
	public void callBackCreateGame(int gameId, int senderId, Any message) {
		Client.setGameId(gameId);
	}

	@Override
	public void callBackShowdResult(int player1, int player2, Any message) {
		View view = Client.getView();
		
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		Client.getAddressBookRef().get(player1, addressAccountDetailsOut);
		AddressAccountDetails accountDetails1 = AddressAccountDetailsHelper.extract(addressAccountDetailsOut.value);
		
		Client.getAddressBookRef().get(player2, addressAccountDetailsOut);
		AddressAccountDetails accountDetails2 = AddressAccountDetailsHelper.extract(addressAccountDetailsOut.value);

		String result = message.extract_string().replace("$user1", accountDetails1.name)
												.replace("$user2", accountDetails2.name);
		
		
		JTextPane jTextPane1 = view.getJTextPane1();
        String oldMessage = jTextPane1.getText() + "\n";
        jTextPane1.setText(oldMessage + result);
			
		System.out.println("Message via callBack from server is " + message.extract_string());
		
	}
}
