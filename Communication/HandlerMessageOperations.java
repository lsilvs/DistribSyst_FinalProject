package Communication;


/**
* Communication/HandlerMessageOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public interface HandlerMessageOperations 
{
  void registerCB (Communication.CommunicationOps c, int userId);
  int createChat (int user1, int user2);
  void sendMessageToChat (int chatId, int senderId, org.omg.CORBA.Any message);
} // interface HandlerMessageOperations
