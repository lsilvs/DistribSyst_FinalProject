import java.io.*;
import java.lang.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;
import java.util.*;

import java.net.*;
import java.util.Scanner;
import javax.swing.*;

import AddressBook.*;
import Communication.*;
import Jokenpo.*;

public class Client {

	public static void main(String args[]) {
		try {


			RegistrationUI dialog = new RegistrationUI(new javax.swing.JFrame(), true);
			dialog.setVisible(true);

			while(true) {

			}

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

			AddressAccountDetails accountDetails1 = new AddressAccountDetails(
				"Lucas Silvestre", // name
				"NCI on Campus", // address
				"0831747645", // phoneNumber
				"lukas.silvestre@gmail.com" // email
			);

			Any anyAccount = ORB.init().create_any();
			IntHolder uniqueId = new IntHolder();

			try {
				AddressAccountDetailsHelper.insert(anyAccount, accountDetails1);
			} catch (SystemException se) {
				System.out.println("insert any error\n" + "Unexpected exception:\n" + se.toString ());
			}

			addressBookRef.insert(anyAccount, uniqueId);

			Communication.ClientOps callBackCommunicationRef = new Communication.ClientOps_Tie(new ClientOpsCommunicationImpl());
			Jokenpo.ClientOps callBackJokenpoRef = new Jokenpo.ClientOps_Tie(new ClientOpsJokenpoImpl());
			communicationRef.registerCB(callBackCommunicationRef, uniqueId.value);

			System.out.println(uniqueId.value);

			Any anyMessage = ORB.init().create_any();

			boolean end_loop = false;
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your name: ");
			String name = scanner.nextLine();
			while (!end_loop) {
				String message = scanner.nextLine();
				if(message.length() != 0) {
					anyMessage.insert_string(message);
					communicationRef.sendMessage(1, anyMessage);
					communicationRef.sendMessage(2, anyMessage);
				} else {
					end_loop = true;
					System.out.println("Your application will be finished.");
				}
			}

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
