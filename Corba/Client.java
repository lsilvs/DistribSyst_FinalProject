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
		
		Communication.ClientOps callBackCommunicationRef = new Communication.ClientOps_Tie(new ClientOpsCommunicationImpl());
		communicationRef.registerCB(callBackCommunicationRef, addressAccountDetails.id);
		
		Jokenpo.ClientOps callBackJokenpoRef = new Jokenpo.ClientOps_Tie(new ClientOpsJokenpoImpl());
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
		
		int chatId = communicationRef.createChat(addressAccountDetails.id, guest.id);
		Client.setChatId(chatId);
//		Any anyMessage = ORB.init().create_any();
//		anyMessage.insert_string(addressAccountDetails.name + " invited you to a game");
//		communicationRef.sendMessageToChat(chatId, addressAccountDetails.id, anyMessage);

		view.setVisible(true);
        invite.dispose();

	}

	public static void sendMessage(String message) {
		Any anyMessage = ORB.init().create_any();
		anyMessage.insert_string(message);
		communicationRef.sendMessageToChat(chatId, addressAccountDetails.id, anyMessage);
	}
}

class ClientOpsCommunicationImpl implements Communication.ClientOpsOperations {
	public void callBackCreateChat(int chatId, int senderId, org.omg.CORBA.Any message) {
		Client.setChatId(chatId);

		if(senderId != Client.getAddressAccountDetails().id) {
			View view = Client.getView();
			
			view.setVisible(true);
	        Client.getInvite().dispose();
	        
			JTextPane jTextPane1 = view.getJTextPane1();
	        String oldMessage = jTextPane1.getText() + "\n";
	        jTextPane1.setText(oldMessage + message.extract_string());
		}
			
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

class ClientOpsJokenpoImpl implements Jokenpo.ClientOpsOperations {
	public void callBack(org.omg.CORBA.Any message) {
		System.out.println("Message via callBack from server is " + message.extract_string());
	}
}
