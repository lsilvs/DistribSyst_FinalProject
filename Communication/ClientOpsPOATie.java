package Communication;


/**
* Communication/ClientOpsPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 7:11:56 PM IST
*/

public class ClientOpsPOATie extends ClientOpsPOA
{

  // Constructors

  public ClientOpsPOATie ( Communication.ClientOpsOperations delegate ) {
      this._impl = delegate;
  }
  public ClientOpsPOATie ( Communication.ClientOpsOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public Communication.ClientOpsOperations _delegate() {
      return this._impl;
  }
  public void _delegate (Communication.ClientOpsOperations delegate ) {
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
  public void callBack (org.omg.CORBA.Any message)
  {
    _impl.callBack(message);
  } // callBack

  private Communication.ClientOpsOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class ClientOpsPOATie
