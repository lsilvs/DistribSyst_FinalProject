package Communication;


/**
* Communication/_ClientOpsImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 2:49:05 AM IST
*/

public abstract class _ClientOpsImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements Communication.ClientOps, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _ClientOpsImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("callBack", new java.lang.Integer (0));
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
       case 0:  // Communication/ClientOps/callBack
       {
         org.omg.CORBA.Any message = in.read_any ();
         this.callBack (message);
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
    "IDL:Communication/ClientOps:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }


} // class _ClientOpsImplBase
