package Jokenpo;


/**
* Jokenpo/_JokenpoOpsStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public class _JokenpoOpsStub extends org.omg.CORBA.portable.ObjectImpl implements Jokenpo.JokenpoOps
{

  public void callBackCreateGame (int gameId, int senderId, org.omg.CORBA.Any message)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("callBackCreateGame", true);
                $out.write_long (gameId);
                $out.write_long (senderId);
                $out.write_any (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                callBackCreateGame (gameId, senderId, message        );
            } finally {
                _releaseReply ($in);
            }
  } // callBackCreateGame

  public void callBackShowdResult (int playerId1, int playerId2, org.omg.CORBA.Any message)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("callBackShowdResult", true);
                $out.write_long (playerId1);
                $out.write_long (playerId2);
                $out.write_any (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                callBackShowdResult (playerId1, playerId2, message        );
            } finally {
                _releaseReply ($in);
            }
  } // callBackShowdResult

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Jokenpo/JokenpoOps:1.0"};

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
} // class _JokenpoOpsStub
