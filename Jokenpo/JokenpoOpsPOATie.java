package Jokenpo;


/**
* Jokenpo/JokenpoOpsPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 12:24:57 AM IST
*/

public class JokenpoOpsPOATie extends JokenpoOpsPOA
{

  // Constructors

  public JokenpoOpsPOATie ( Jokenpo.JokenpoOpsOperations delegate ) {
      this._impl = delegate;
  }
  public JokenpoOpsPOATie ( Jokenpo.JokenpoOpsOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public Jokenpo.JokenpoOpsOperations _delegate() {
      return this._impl;
  }
  public void _delegate (Jokenpo.JokenpoOpsOperations delegate ) {
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
  public void callBackCreateGame (int gameId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.callBackCreateGame(gameId, senderId, message);
  } // callBackCreateGame

  public void callBackShowdResult (int gameId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.callBackShowdResult(gameId, senderId, message);
  } // callBackShowdResult

  private Jokenpo.JokenpoOpsOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class JokenpoOpsPOATie
