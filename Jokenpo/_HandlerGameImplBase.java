package Jokenpo;


/**
* Jokenpo/_HandlerGameImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 2:49:05 AM IST
*/

public abstract class _HandlerGameImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements Jokenpo.HandlerGame, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _HandlerGameImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerCB", new java.lang.Integer (0));
    _methods.put ("newGame", new java.lang.Integer (1));
    _methods.put ("sendAction", new java.lang.Integer (2));
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
       case 0:  // Jokenpo/HandlerGame/registerCB
       {
         Jokenpo.ClientOps c = Jokenpo.ClientOpsHelper.read (in);
         org.omg.CORBA.Any option = in.read_any ();
         this.registerCB (c, option);
         out = $rh.createReply();
         break;
       }

       case 1:  // Jokenpo/HandlerGame/newGame
       {
         int gameId = in.read_long ();
         int playerOne = in.read_long ();
         int playerTwo = in.read_long ();
         this.newGame (gameId, playerOne, playerTwo);
         out = $rh.createReply();
         break;
       }

       case 2:  // Jokenpo/HandlerGame/sendAction
       {
         int gameId = in.read_long ();
         int playerId = in.read_long ();
         org.omg.CORBA.Any option = in.read_any ();
         this.sendAction (gameId, playerId, option);
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
    "IDL:Jokenpo/HandlerGame:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _HandlerGameImplBase
