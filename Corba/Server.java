package Corba;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import AddressBook.AddressPOA;
import AddressBook.AddressPOATie;
import Communication.HandlerMessagePOA;
import Communication.HandlerMessagePOATie;

public class Server {

	public static void main (String args[]) {
		try {

			// At unix based system the port 900 is used for something, that is
			// the reason that I choose to use the port 1050
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// create and initialize the ORB
			ORB orb = ORB.init(args, props);

			org.omg.CORBA.Object objRefPOA = orb.resolve_initial_references("RootPOA");
			POA rootPOA = POAHelper.narrow(objRefPOA);

			// Delegation model for creating a servant
			AddressPOA addressRef = new AddressPOATie(new AddressBookServant());
			org.omg.CORBA.Object addressServantObj = rootPOA.servant_to_reference(addressRef);
			
			HandlerMessagePOA messageRef = new HandlerMessagePOATie(new CommunicationServant());
			org.omg.CORBA.Object communicationServantObj = rootPOA.servant_to_reference(messageRef);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			NamingContext rootCtx = NamingContextHelper.narrow(objRef);
			
			NameComponent nc[] = new NameComponent[1];
			nc[0] = new NameComponent("FinalProject", "Context");
			NamingContext projectCtx = rootCtx.bind_new_context(nc);

			nc[0] = new NameComponent("AddressBook", "Object");
			projectCtx.rebind(nc, addressServantObj);
			
			nc[0] = new NameComponent("Communication", "Object");
			projectCtx.rebind(nc, communicationServantObj);

			rootPOA.the_POAManager().activate();
			System.out.println("Server has been started.");
			
			orb.run();

		} catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
		}
	}
}