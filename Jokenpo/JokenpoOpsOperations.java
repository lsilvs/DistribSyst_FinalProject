package Jokenpo;


/**
* Jokenpo/JokenpoOpsOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public interface JokenpoOpsOperations 
{
  void callBackCreateGame (int gameId, int senderId, org.omg.CORBA.Any message);
  void callBackShowdResult (int playerId1, int playerId2, org.omg.CORBA.Any message);
} // interface JokenpoOpsOperations
