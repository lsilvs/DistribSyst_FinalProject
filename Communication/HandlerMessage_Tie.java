package Communication;


/**
* Communication/HandlerMessage_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 12:29:54 AM IST
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

  public void registerCB (Communication.ClientOps c, org.omg.CORBA.Any message)
  {
    _impl.registerCB(c, message);
  } // registerCB

  private Communication.HandlerMessageOperations _impl;

} // class HandlerMessage_Tie