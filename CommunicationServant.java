import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import org.omg.CORBA.*;

import Communication.*;

class CommunicationServant implements HandlerMessageOperations {

	ClientOps client;

	public void registerCB(Communication.ClientOps c, org.omg.CORBA.Any message) {
		client = c;
		System.out.println("Message received: " + message.extract_string());
		client.callBack(message);
	}

}