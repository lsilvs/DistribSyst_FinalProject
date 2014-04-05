import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import org.omg.CORBA.*;

import AddressBook.*;

class AddressBookServant implements AddressOperations {

	private Hashtable<Integer, AddressAccountDetails> allAccounts = new Hashtable<Integer, AddressAccountDetails>();
	private AddressAccountDetails accountDetails;
	private static Integer ID = 1;

	public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.IntHolder addressId) {
		accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsIn);
		allAccounts.put(ID, accountDetails);

		addressId.value = ID;
		ID++;
	}

  public void get (int addressId, org.omg.CORBA.AnyHolder addressAccountDetailsOut) {
  	accountDetails = allAccounts.get(addressId);

  	Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressAccountDetailsOut.value = anyAccount;

  }

  public void getList (org.omg.CORBA.AnyHolder addressBook) {
  	accountDetails = allAccounts.get(1);

  	Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressBook.value = anyAccount;
  }

}