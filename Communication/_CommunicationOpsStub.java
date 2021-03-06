package Communication;


/**
* Communication/_CommunicationOpsStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:07:03 PM IST
*/

public class _CommunicationOpsStub extends org.omg.CORBA.portable.ObjectImpl implements Communication.CommunicationOps
{

  public void callBackCreateChat (int chatId, int senderId, org.omg.CORBA.Any message)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("callBackCreateChat", true);
                $out.write_long (chatId);
                $out.write_long (senderId);
                $out.write_any (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                callBackCreateChat (chatId, senderId, message        );
            } finally {
                _releaseReply ($in);
            }
  } // callBackCreateChat

  public void callBackShowdMessage (int chatId, int senderId, org.omg.CORBA.Any message)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("callBackShowdMessage", true);
                $out.write_long (chatId);
                $out.write_long (senderId);
                $out.write_any (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                callBackShowdMessage (chatId, senderId, message        );
            } finally {
                _releaseReply ($in);
            }
  } // callBackShowdMessage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Communication/CommunicationOps:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _CommunicationOpsStub
