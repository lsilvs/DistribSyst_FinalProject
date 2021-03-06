package Communication;


/**
* Communication/_CommunicationOpsImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:06:57 PM IST
*/

public abstract class _CommunicationOpsImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements Communication.CommunicationOps, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _CommunicationOpsImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("callBackCreateChat", new java.lang.Integer (0));
    _methods.put ("callBackShowdMessage", new java.lang.Integer (1));
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
       case 0:  // Communication/CommunicationOps/callBackCreateChat
       {
         int chatId = in.read_long ();
         int senderId = in.read_long ();
         org.omg.CORBA.Any message = in.read_any ();
         this.callBackCreateChat (chatId, senderId, message);
         out = $rh.createReply();
         break;
       }

       case 1:  // Communication/CommunicationOps/callBackShowdMessage
       {
         int chatId = in.read_long ();
         int senderId = in.read_long ();
         org.omg.CORBA.Any message = in.read_any ();
         this.callBackShowdMessage (chatId, senderId, message);
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
    "IDL:Communication/CommunicationOps:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _CommunicationOpsImplBase
