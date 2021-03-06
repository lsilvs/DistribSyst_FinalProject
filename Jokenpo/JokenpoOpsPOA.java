package Jokenpo;


/**
* Jokenpo/JokenpoOpsPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:07:03 PM IST
*/

public abstract class JokenpoOpsPOA extends org.omg.PortableServer.Servant
 implements Jokenpo.JokenpoOpsOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("callBackCreateGame", new java.lang.Integer (0));
    _methods.put ("callBackShowdResult", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Jokenpo/JokenpoOps/callBackCreateGame
       {
         int gameId = in.read_long ();
         int senderId = in.read_long ();
         org.omg.CORBA.Any message = in.read_any ();
         this.callBackCreateGame (gameId, senderId, message);
         out = $rh.createReply();
         break;
       }

       case 1:  // Jokenpo/JokenpoOps/callBackShowdResult
       {
         int playerId1 = in.read_long ();
         int playerId2 = in.read_long ();
         org.omg.CORBA.Any message = in.read_any ();
         this.callBackShowdResult (playerId1, playerId2, message);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Jokenpo/JokenpoOps:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public JokenpoOps _this() 
  {
    return JokenpoOpsHelper.narrow(
    super._this_object());
  }

  public JokenpoOps _this(org.omg.CORBA.ORB orb) 
  {
    return JokenpoOpsHelper.narrow(
    super._this_object(orb));
  }


} // class JokenpoOpsPOA
