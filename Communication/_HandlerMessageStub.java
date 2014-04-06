package Communication;


/**
* Communication/_HandlerMessageStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 2:49:11 AM IST
*/

public class _HandlerMessageStub extends org.omg.CORBA.portable.ObjectImpl implements Communication.HandlerMessage
{

  public void registerCB (Communication.ClientOps c, int userId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("registerCB", true);
                Communication.ClientOpsHelper.write ($out, c);
                $out.write_long (userId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                registerCB (c, userId        );
            } finally {
                _releaseReply ($in);
            }
  } // registerCB

  public void addUserToChat (int chatId, int userId)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("addUserToChat", true);
                $out.write_long (chatId);
                $out.write_long (userId);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                addUserToChat (chatId, userId        );
            } finally {
                _releaseReply ($in);
            }
  } // addUserToChat

  public void sendMessage (int userId, org.omg.CORBA.Any message)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendMessage", true);
                $out.write_long (userId);
                $out.write_any (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                sendMessage (userId, message        );
            } finally {
                _releaseReply ($in);
            }
  } // sendMessage

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Communication/HandlerMessage:1.0"};

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
} // class _HandlerMessageStub
