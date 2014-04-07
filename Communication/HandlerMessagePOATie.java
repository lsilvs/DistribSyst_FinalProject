package Communication;


/**
* Communication/HandlerMessagePOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 12:24:57 AM IST
*/

public class HandlerMessagePOATie extends HandlerMessagePOA
{

  // Constructors

  public HandlerMessagePOATie ( Communication.HandlerMessageOperations delegate ) {
      this._impl = delegate;
  }
  public HandlerMessagePOATie ( Communication.HandlerMessageOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public Communication.HandlerMessageOperations _delegate() {
      return this._impl;
  }
  public void _delegate (Communication.HandlerMessageOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
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
  private org.omg.PortableServer.POA _poa;

} // class HandlerMessagePOATie
