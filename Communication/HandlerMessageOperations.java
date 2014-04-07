package Communication;


/**
* Communication/HandlerMessageOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Monday, April 7, 2014 10:55:08 PM IST
*/

public interface HandlerMessageOperations 
{
  void registerCB (Communication.ClientOps c, int userId);
  int createChat (int user1, int user2);
  void addUserToChat (int chatId, int userId);
  void sendMessageToChat (int chatId, int senderId, org.omg.CORBA.Any message);
  void sendMessage (int userId, org.omg.CORBA.Any message);
} // interface HandlerMessageOperations
