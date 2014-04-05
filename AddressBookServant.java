import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import org.omg.CORBA.*;

import AddressBook.*;

class AddressBookServant implements AddressOperations {

	private Hashtable<String, AddressAccountDetails> allAccounts = new Hashtable<String, AddressAccountDetails>();
	private AddressAccountDetails accountDetails;

	public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder emailOut) {
		accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsIn);
		allAccounts.put(accountDetails.email, accountDetails);

		Any keyAux = ORB.init().create_any();
		keyAux.insert_string(accountDetails.email);
		emailOut.value = keyAux;
	}

  public void get (org.omg.CORBA.Any email, org.omg.CORBA.AnyHolder addressAccountDetailsOut) {
  	String anyEmail = email.extract_string();
  	accountDetails = allAccounts.get(anyEmail);

  	Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressAccountDetailsOut.value = anyAccount;

  }

}