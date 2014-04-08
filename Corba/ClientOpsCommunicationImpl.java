package Corba;

import javax.swing.JTextPane;

import jokenpoGUI.View;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnyHolder;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;

class ClientOpsCommunicationImpl implements Communication.CommunicationOpsOperations {
	public void callBackCreateChat(int chatId, int senderId, org.omg.CORBA.Any message) {
		Client.setChatId(chatId);

		View view = Client.getView();
			
		view.setVisible(true);
        Client.getInvite().dispose();
        
        AnyHolder anyAccount = new AnyHolder();
        Client.getAddressBookRef().get(senderId, anyAccount);
        AddressAccountDetails sender = AddressAccountDetailsHelper.extract(anyAccount.value); 		
        
		JTextPane jTextPane1 = view.getJTextPane1();
        jTextPane1.setText(sender.name + " invited you to a game");
			
		System.out.println("Message via callBack from server is " + message.extract_string());
	}

	public void callBackShowdMessage(int chatId, int senderId, Any message) {
		View view = Client.getView();
		
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		Client.getAddressBookRef().get(senderId, addressAccountDetailsOut);

		JTextPane jTextPane1 = view.getJTextPane1();
        String oldMessage = jTextPane1.getText() + "\n";
        jTextPane1.setText(oldMessage +
			        		message.extract_string());
			
		System.out.println("Message via callBack from server is " + message.extract_string());
	}
}