package Communication;


/**
* Communication/CommunicationOpsPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 12:24:57 AM IST
*/

public class CommunicationOpsPOATie extends CommunicationOpsPOA
{

  // Constructors

  public CommunicationOpsPOATie ( Communication.CommunicationOpsOperations delegate ) {
      this._impl = delegate;
  }
  public CommunicationOpsPOATie ( Communication.CommunicationOpsOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public Communication.CommunicationOpsOperations _delegate() {
      return this._impl;
  }
  public void _delegate (Communication.CommunicationOpsOperations delegate ) {
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
  public void callBackCreateChat (int chatId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.callBackCreateChat(chatId, senderId, message);
  } // callBackCreateChat

  public void callBackShowdMessage (int chatId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.callBackShowdMessage(chatId, senderId, message);
  } // callBackShowdMessage

  private Communication.CommunicationOpsOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class CommunicationOpsPOATie