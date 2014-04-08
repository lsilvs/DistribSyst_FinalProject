package Corba;
import java.util.Hashtable;
import java.util.Iterator;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import AddressBook.AddressBookOps;
import AddressBook.AddressOperations;
import Communication.CommunicationOps;
import Jokenpo.JokenpoOps;

class AddressBookServant implements AddressOperations {

	private Hashtable<Integer, AddressBookOps> allCBs = new Hashtable<Integer, AddressBookOps>();
	private Hashtable<Integer, AddressAccountDetails> allAccounts = new Hashtable<Integer, AddressAccountDetails>();
	private Hashtable<Integer, AddressAccountDetails> availableAccounts = new Hashtable<Integer, AddressAccountDetails>();
	private AddressAccountDetails accountDetails;
	private AddressBookOps cbDetails;
	private static Integer ID = 1;
	
	public void registerCB (AddressBookOps c, int userId) {
		allCBs.put(userId, c);
		System.out.println("User registred to communication: " + userId);
	}

	public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder addressAccountDetailsOut) {
		accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsIn);
		if(accountDetails.id == 0) {
			accountDetails.id = ID;
			ID++;
		}
		allAccounts.put(accountDetails.id, accountDetails);
		availableAccounts.put(accountDetails.id, accountDetails);
		Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressAccountDetailsOut.value = anyAccount;
	}

	public void remove (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder addressAccountDetailsOut) {
		accountDetails = AddressAccountDetailsHelper.extract(addressAccountDetailsIn);
		availableAccounts.remove(accountDetails.id);
		Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressAccountDetailsOut.value = anyAccount;
	}
	
	public void displaysAvailableUsers () {
		Iterator<AddressAccountDetails> itIds = availableAccounts.values().iterator();
		String str = new String();
		while (itIds.hasNext()) {
			str += itIds.next().id;
			if (itIds.hasNext()) {
				str += " , ";
			}
		}
		
		Iterator<AddressAccountDetails> itCB = availableAccounts.values().iterator();
		while (itCB.hasNext()) {
			cbDetails = allCBs.get(itCB.next().id);
			cbDetails.callBackSetInviteList(str);
		}
	
	}
	
	public void get (int addressId, org.omg.CORBA.AnyHolder addressAccountDetailsOut) {
  		accountDetails = allAccounts.get(addressId);

  		Any anyAccount = ORB.init().create_any();
		AddressAccountDetailsHelper.insert(anyAccount, accountDetails);
		addressAccountDetailsOut.value = anyAccount;

	}

	public void getList (org.omg.CORBA.AnyHolder addressBook) {
	  Iterator<AddressAccountDetails> it = availableAccounts.values().iterator();
	  String str = new String();
	  while (it.hasNext()) {
		  str += it.next().id;
		  if (it.hasNext()) {
			  str += " , ";
		  }
	  }
	  
	  Any anyAccount = ORB.init().create_any();
	  anyAccount.insert_string(str);
	  addressBook.value = anyAccount;
  }

}