package Jokenpo;


/**
* Jokenpo/JokenpoOps_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:06:57 PM IST
*/

public class JokenpoOps_Tie extends _JokenpoOpsImplBase
{

  // Constructors
  public JokenpoOps_Tie ()
  {
  }

  public JokenpoOps_Tie (Jokenpo.JokenpoOpsOperations impl)
  {
    super ();
    _impl = impl;
  }

  public void callBackCreateGame (int gameId, int senderId, org.omg.CORBA.Any message)
  {
    _impl.callBackCreateGame(gameId, senderId, message);
  } // callBackCreateGame

  public void callBackShowdResult (int playerId1, int playerId2, org.omg.CORBA.Any message)
  {
    _impl.callBackShowdResult(playerId1, playerId2, message);
  } // callBackShowdResult

  private Jokenpo.JokenpoOpsOperations _impl;

} // class JokenpoOps_Tie
