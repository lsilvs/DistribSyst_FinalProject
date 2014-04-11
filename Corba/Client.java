package Corba;
import java.util.Properties;

import javax.swing.JTextPane;

import jokenpoGUI.InviteContactUI;
import jokenpoGUI.RegistrationUI;
import jokenpoGUI.View;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnyHolder;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import AddressBook.Address;
import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import AddressBook.AddressBookOps;
import AddressBook.AddressBookOps_Tie;
import AddressBook.AddressHelper;
import Communication.CommunicationOps;
import Communication.CommunicationOps_Tie;
import Communication.HandlerMessage;
import Communication.HandlerMessageHelper;
import Jokenpo.HandlerGame;
import Jokenpo.HandlerGameHelper;
import Jokenpo.JokenpoOps;
import Jokenpo.JokenpoOps_Tie;

public class Client {

	// Declare some variables used by the methods
	private static Address addressBookRef;
	private static HandlerMessage communicationRef;
	private static HandlerGame jokenpoRef;
	private static AddressAccountDetails addressAccountDetails;
	private static InviteContactUI invite = new InviteContactUI(new javax.swing.JFrame(), false);
	private static View view = new View();
	private static int chatId;
	private static int gameId;
	
	// Create gets and setters to be used by the callback methods
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
			// At unix based system the ports below 1024 are reserved, 
			// so we changed it to use the port 1050
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// Change localhost to the server IP address when running 
			// in different machines
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");

			// create and initialize the ORB
			ORB orb = ORB.init(args, props);

			// Get the Name service Object reference
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContext rootCtx = NamingContextHelper.narrow(objRef);

			// Get the AddressBook object reference
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("AddressBook", "Object");
			org.omg.CORBA.Object objRefFP = rootCtx.resolve(nc);
			addressBookRef = AddressHelper.narrow(objRefFP);
			
			// Get the Communication object reference
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("Communication", "Object");
			objRefFP = rootCtx.resolve(nc);
			communicationRef = HandlerMessageHelper.narrow(objRefFP);
			
			// Get the Jokenpo object reference
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("Jokenpo", "Object");
			objRefFP = rootCtx.resolve(nc);
			jokenpoRef = HandlerGameHelper.narrow(objRefFP);

			// Show the GUI with the registration form
			RegistrationUI dialog = new RegistrationUI(new javax.swing.JFrame(), true);
			dialog.setVisible(true);
			
		} catch (Exception e) {
		  System.out.println("ERROR : " + e) ;
		  e.printStackTrace(System.out);
		}
	}

	public static void register(RegistrationUI registrationUI) {
		// Instantiate a new AddressAccountDetails with the registered data
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
		
		// Insert the new user to the AddressBook
		AnyHolder aad = new AnyHolder();
		addressBookRef.insert(anyAccount, aad);
		addressAccountDetails = AddressAccountDetailsHelper.extract(aad.value);
		
		// Register the AddressBook callback object of the user to the AddressBook list
		AddressBookOps callBackAddressBookRef = new AddressBookOps_Tie(new ClientOpsAddressBookImpl());
		addressBookRef.registerCB(callBackAddressBookRef, addressAccountDetails.id);
		
		// Register the Communication callback object of the user to the Communication list
		CommunicationOps callBackCommunicationRef = new CommunicationOps_Tie(new ClientOpsCommunicationImpl());
		communicationRef.registerCB(callBackCommunicationRef, addressAccountDetails.id);
		
		// Register the Jokenpo callback object of the user to the Jokenpo list
		JokenpoOps callBackJokenpoRef = new JokenpoOps_Tie(new ClientOpsJokenpoImpl());
        jokenpoRef.registerCB(callBackJokenpoRef, addressAccountDetails.id);
		
        // Dispose the registration window
		registrationUI.dispose();
		invite();
	}

	private static void invite() {
		// Show the invite window with available users
		addressBookRef.displaysAvailableUsers();
		invite.setVisible(true);
	}

	public static void view(InviteContactUI invite) {
		// Set the guest chosen by the user
		AddressAccountDetails guest = (AddressAccountDetails) invite.getjComboBox1().getSelectedItem();
		
		// Remove the guest from the available list
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		addressBookRef.get(guest.id, addressAccountDetailsOut);
		addressBookRef.remove(addressAccountDetailsOut.value, addressAccountDetailsOut);
		
		// Remove the user from the available list
		addressBookRef.get(addressAccountDetails.id, addressAccountDetailsOut);
		addressBookRef.remove(addressAccountDetailsOut.value, addressAccountDetailsOut);
		
		// Update the the available list to all available users
		addressBookRef.displaysAvailableUsers();

		JTextPane jTextPane1 = view.getJTextPane1();
        jTextPane1.setText("");

        // Set the gameId for the user
		int gameId = jokenpoRef.createGame(addressAccountDetails.id, guest.id);
		setGameId(gameId);
		// Set the chatId for the user
		int chatId = communicationRef.createChat(addressAccountDetails.id, guest.id);
		setChatId(chatId);

		// Show the game window and dispose invite window
		view.setVisible(true);
        invite.dispose();

	}

	// Method to send message to a chat
	public static void sendMessage(String message) {
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(addressAccountDetails.name + ": " + message);
		communicationRef.sendMessageToChat(chatId, addressAccountDetails.id, anyMessage);
	}
	
	// Method to send the option to a game
	public static void chooseOption(String message) {
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message);
		jokenpoRef.sendAction(gameId, addressAccountDetails.id, anyMessage);
	}

	// Method to quit the game
	public static void quitGame() {
		// Put the user back to the available list
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		addressBookRef.get(addressAccountDetails.id, addressAccountDetailsOut);
		addressBookRef.insert(addressAccountDetailsOut.value, addressAccountDetailsOut);
		
		// Communicate the other user the first one left the room
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(addressAccountDetails.name + " left the game");
		communicationRef.sendMessageToChat(chatId, addressAccountDetails.id, anyMessage);

		addressBookRef.displaysAvailableUsers();

		// Dispose the game window and show the invite window back
		view.dispose();
        invite.setVisible(true);
	}

}
