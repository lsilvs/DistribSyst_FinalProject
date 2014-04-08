package Communication;


/**
* Communication/HandlerMessagePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public abstract class HandlerMessagePOA extends org.omg.PortableServer.Servant
 implements Communication.HandlerMessageOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerCB", new java.lang.Integer (0));
    _methods.put ("createChat", new java.lang.Integer (1));
    _methods.put ("sendMessageToChat", new java.lang.Integer (2));
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
       case 0:  // Communication/HandlerMessage/registerCB
       {
         Communication.CommunicationOps c = Communication.CommunicationOpsHelper.read (in);
         int userId = in.read_long ();
         this.registerCB (c, userId);
         out = $rh.createReply();
         break;
       }

       case 1:  // Communication/HandlerMessage/createChat
       {
         int user1 = in.read_long ();
         int user2 = in.read_long ();
         int $result = (int)0;
         $result = this.createChat (user1, user2);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 2:  // Communication/HandlerMessage/sendMessageToChat
       {
         int chatId = in.read_long ();
         int senderId = in.read_long ();
         org.omg.CORBA.Any message = in.read_any ();
         this.sendMessageToChat (chatId, senderId, message);
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
    "IDL:Communication/HandlerMessage:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public HandlerMessage _this() 
  {
    return HandlerMessageHelper.narrow(
    super._this_object());
  }

  public HandlerMessage _this(org.omg.CORBA.ORB orb) 
  {
    return HandlerMessageHelper.narrow(
    super._this_object(orb));
  }


} // class HandlerMessagePOA
