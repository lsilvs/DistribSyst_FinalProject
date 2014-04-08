package Corba;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import jokenpoGUI.View;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnyHolder;

import AddressBook.AddressAccountDetails;
import AddressBook.AddressAccountDetailsHelper;

class ClientOpsJokenpoImpl implements Jokenpo.JokenpoOpsOperations {
	@Override
	public void callBackCreateGame(int gameId, int senderId, Any message) {
		Client.setGameId(gameId);
		View view = Client.getView();
		view.getPaperButton().setEnabled(true);
        view.getPaperButton().setSelected(false);
        view.getRockButton().setEnabled(true);
        view.getRockButton().setSelected(false);
        view.getScissorsButton().setEnabled(true);
        view.getScissorsButton().setSelected(false);
	}

	@Override
	public void callBackShowdResult(int player1, int player2, Any message) {
		View view = Client.getView();
		
		AnyHolder addressAccountDetailsOut = new AnyHolder();
		Client.getAddressBookRef().get(player1, addressAccountDetailsOut);
		AddressAccountDetails accountDetails1 = AddressAccountDetailsHelper.extract(addressAccountDetailsOut.value);
		
		Client.getAddressBookRef().get(player2, addressAccountDetailsOut);
		AddressAccountDetails accountDetails2 = AddressAccountDetailsHelper.extract(addressAccountDetailsOut.value);

		String result = message.extract_string().replace("$user1", accountDetails1.name)
												.replace("$user2", accountDetails2.name);
		
		
		JTextPane jTextPane1 = view.getJTextPane1();
        String oldMessage = jTextPane1.getText() + "\n";
        jTextPane1.setText(oldMessage + result);
        
        view.getPaperButton().setEnabled(true);
        view.getPaperButton().setSelected(false);
        view.getRockButton().setEnabled(true);
        view.getRockButton().setSelected(false);
        view.getScissorsButton().setEnabled(true);
        view.getScissorsButton().setSelected(false);
			
		System.out.println("Message via callBack from server is " + message.extract_string());
		
	}
}