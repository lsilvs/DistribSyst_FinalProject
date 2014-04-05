import java.io.*;
import java.lang.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;

import java.net.*;

import AddressBook.*;
import Communication.*;
import Jokenpo.*;

public class Client {

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
			Address addressBookRef = AddressHelper.narrow(objRefFP);

			
			nc[0] = new NameComponent("FinalProject", "Context");
			nc[1] = new NameComponent("Communication", "Object");
			objRefFP = rootCtx.resolve(nc);
			HandlerMessage communicationRef = HandlerMessageHelper.narrow(objRefFP);

			Communication.ClientOps callBackCommunicationRef = new Communication.ClientOps_Tie(new ClientOpsCommunicationImpl());
			Any callBackRefCommunicationAny = ORB.init().create_any();
			Communication.ClientOpsHelper.insert(callBackRefCommunicationAny, callBackCommunicationRef);

			Jokenpo.ClientOps callBackJokenpoRef = new Jokenpo.ClientOps_Tie(new ClientOpsJokenpoImpl());
			Any callBackRefJokenpoAny = ORB.init().create_any();
			Jokenpo.ClientOpsHelper.insert(callBackRefJokenpoAny, callBackJokenpoRef);

			AddressAccountDetails accountDetails1 = new AddressAccountDetails(
				"Lucas Silvestre", // name
				"NCI on Campus", // address
				"0831747645", // phoneNumber
				"lukas.silvestre@gmail.com", // email
				callBackRefCommunicationAny, // email
				callBackRefJokenpoAny // email
			);

			Any anyAccount = ORB.init().create_any();
			IntHolder uniqueId1 = new IntHolder();
			IntHolder uniqueId2 = new IntHolder();

			try {
				AddressAccountDetailsHelper.insert(anyAccount, accountDetails1);
			} catch (SystemException se) {
				System.out.println("insert any error\n" + "Unexpected exception:\n" + se.toString ());
			}

			addressBookRef.insert(anyAccount, uniqueId1);

			AddressAccountDetails accountDetails2 = new AddressAccountDetails(
				"Antonio Marques", // name
				"NCI on Campus", // address
				"0831701720", // phoneNumber
				"antoioricardojr@gmail.com", // email
				callBackRefCommunicationAny, // email
				callBackRefJokenpoAny // email
			);

			try {
				AddressAccountDetailsHelper.insert(anyAccount, accountDetails2);
			} catch (SystemException se) {
				System.out.println("insert any error\n" + "Unexpected exception:\n" + se.toString ());
			}

			addressBookRef.insert(anyAccount, uniqueId2);

			System.out.println(uniqueId1.value);
			System.out.println(uniqueId2.value);
			
			Any anyMessage = ORB.init().create_any();
			anyMessage.insert_string("anyMessage");

			// communicationRef.registerCB(callBackRef, anyMessage);

		} catch (Exception e) {
		  System.out.println("ERROR : " + e) ;
		  e.printStackTrace(System.out);
		}
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
