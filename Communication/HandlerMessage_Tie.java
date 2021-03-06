package Communication;


/**
* Communication/HandlerMessage_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:06:57 PM IST
*/

public class HandlerMessage_Tie extends _HandlerMessageImplBase
{

  // Constructors
  public HandlerMessage_Tie ()
  {
  }

  public HandlerMessage_Tie (Communication.HandlerMessageOperations impl)
  {
    super ();
    _impl = impl;
  }

  public void registerCB (Communication.CommunicationOps c, int userId)
  {
    _impl.registerCB(c, userId);
  } // registerCB

  public int createChat (int user1, int user2)
  {
    return _impl.createChat(user1, user2);
  } // createChat

  public void sendMessageToChat (int chatId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.sendMessageToChat(chatId, senderId, message);
  } // sendMessageToChat

  private Communication.HandlerMessageOperations _impl;

} // class HandlerMessage_Tie
