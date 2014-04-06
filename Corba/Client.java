package Corba;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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


//			Communication.ClientOps callBackCommunicationRef = new Communication.ClientOps_Tie(new ClientOpsCommunicationImpl());
//			communicationRef.registerCB(callBackCommunicationRef, uniqueId.value);

//			System.out.println(uniqueId.value);			

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
		
		registrationUI.dispose();
		invite();
	}

	private static void invite() {
		InviteContactUI invite = new InviteContactUI(new javax.swing.JFrame(), true);
		
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
		Jokenpo.ClientOps callBackJokenpoRef = new Jokenpo.ClientOps_Tie(new ClientOpsJokenpoImpl());
        jokenpoRef.registerCB(callBackJokenpoRef, 1);
        
        View view = new View();
        view.setVisible(true);
        invite.dispose();

        
	}
}

class ClientOpsCommunicationImpl implements Communication.ClientOpsOperations {
	public void callBack(org.omg.CORBA.Any message) {
		System.out.println("Message via callBack from server is " + message.extract_string());
	}
}

class ClientOpsJokenpoImpl implements Jokenpo.ClientOpsOperations {
	public void callBack(org.omg.CORBA.Any message) {
		System.out.println("Message via callBack from server is " + message.extract_string());
	}
}
