package Jokenpo;


/**
* Jokenpo/ClientOps_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 12:29:54 AM IST
*/

public class ClientOps_Tie extends _ClientOpsImplBase
{

  // Constructors
  public ClientOps_Tie ()
  {
  }

  public ClientOps_Tie (Jokenpo.ClientOpsOperations impl)
  {
    super ();
    _impl = impl;
  }

  public void callBack (org.omg.CORBA.Any message)
  {
    _impl.callBack(message);
  } // callBack

  private Jokenpo.ClientOpsOperations _impl;

} // class ClientOps_Tie