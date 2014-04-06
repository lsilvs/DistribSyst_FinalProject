package Corba;
import java.util.Hashtable;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.TypeCode;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import AddressBook.AddressOperations;

class AddressBookServant implements AddressOperations {

	private Hashtable<Integer, AddressAccountDetails> allAccounts = new Hashtable<Integer, AddressAccountDetails>();
	private AddressAccountDetails accountDetails;
	private static Integer ID = 1;

	public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.IntHolder addressId) {
		accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsIn);
		accountDetails.id = ID;
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
  	Any anyAccount = ORB.init().create_any();
  	
	anyAccount.insert_Object((Object) allAccounts);
	addressBook.value = anyAccount;
  }

}