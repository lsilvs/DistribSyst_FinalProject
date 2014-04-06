package Corba;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import jokenpoGUI.InviteContactUI;
import jokenpoGUI.RegistrationUI;

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

public class Client {

	private static Address addressBookRef;

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
			HandlerMessage communicationRef = HandlerMessageHelper.narrow(objRefFP);


			RegistrationUI dialog = new RegistrationUI(new javax.swing.JFrame(), true);
			dialog.setVisible(true);
			
			while(true){
				
			}


//			Communication.ClientOps callBackCommunicationRef = new Communication.ClientOps_Tie(new ClientOpsCommunicationImpl());
//			Jokenpo.ClientOps callBackJokenpoRef = new Jokenpo.ClientOps_Tie(new ClientOpsJokenpoImpl());
//			communicationRef.registerCB(callBackCommunicationRef, uniqueId.value);

//			System.out.println(uniqueId.value);			

		} catch (Exception e) {
		  System.out.println("ERROR : " + e) ;
		  e.printStackTrace(System.out);
		}
	}

	public static void register(RegistrationUI registrationUI) {
		System.out.println("Message via callBack from server is " + registrationUI.getPhone());
		AddressAccountDetails accountDetails1 = new AddressAccountDetails(
			0, // id
			registrationUI.getName(), // name
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

		IntHolder uniqueId = new IntHolder();
		addressBookRef.insert(anyAccount, uniqueId);
		invite(registrationUI);
	}

	private static void invite(RegistrationUI registrationUI) {
		InviteContactUI invite = new InviteContactUI(new javax.swing.JFrame(), true);
		
		AnyHolder addressBook = new AnyHolder();
		addressBookRef.getList(addressBook);
		
		Hashtable<Integer, AddressAccountDetails> allAccounts = new Hashtable<Integer, AddressAccountDetails>();
		
		allAccounts = (Hashtable<Integer, AddressAccountDetails>) addressBook.value.extract_Object();
		
		Iterator<AddressAccountDetails> it = allAccounts.values().iterator();

		while (it.hasNext()) {
			invite.getjComboBox1().addItem(it);
		}

		registrationUI.dispose();
        invite.setVisible(true);

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
