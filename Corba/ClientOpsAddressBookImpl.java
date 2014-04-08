package Corba;

import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.AnyHolder;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;
import AddressBook.AddressBookOpsOperations;

public class ClientOpsAddressBookImpl implements AddressBookOpsOperations {

	@Override
	public void callBackSetInviteList(String availableList) {
		AnyHolder addressBook = new AnyHolder();
		Client.getAddressBookRef().getList(addressBook);

		System.out.println("Message via callBack from server is " + addressBook.value.extract_string());

		List<String> items = Arrays.asList(addressBook.value.extract_string().split("\\s*,\\s*"));
		
		Client.getInvite().getjComboBox1().removeAllItems();
		Client.getInvite().getjComboBox1().addItem("Choose a friend to play");
		
		for (String temp : items) {
			int foo = Integer.parseInt(temp);
			if(foo != Client.getAddressAccountDetails().id) {
				AnyHolder account = new AnyHolder();
				Client.getAddressBookRef().get(foo, account);
				AddressAccountDetails accountDetails = AddressAccountDetailsHelper.extract(account.value);
				Client.getInvite().getjComboBox1().addItem(accountDetails);
			}
		}

		/* Add this code into AddressAccountDetails.java after compile the idlfile.idl
		@Override
		public String toString() {
		  return this.name;
		}
		*/
	}
}
