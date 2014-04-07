package AddressBook;


/**
* AddressBook/_AddressStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Monday, April 7, 2014 10:55:08 PM IST
*/

public class _AddressStub extends org.omg.CORBA.portable.ObjectImpl implements AddressBook.Address
{

  public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder addressAccountDetailsOut)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("insert", true);
                $out.write_any (addressAccountDetailsIn);
                $in = _invoke ($out);
                addressAccountDetailsOut.value = $in.read_any ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                insert (addressAccountDetailsIn, addressAccountDetailsOut        );
            } finally {
                _releaseReply ($in);
            }
  } // insert

  public void get (int addressId, org.omg.CORBA.AnyHolder addressAccountDetailsOut)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get", true);
                $out.write_long (addressId);
                $in = _invoke ($out);
                addressAccountDetailsOut.value = $in.read_any ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                get (addressId, addressAccountDetailsOut        );
            } finally {
                _releaseReply ($in);
            }
  } // get

  public void getList (org.omg.CORBA.AnyHolder addressBook)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getList", true);
                $in = _invoke ($out);
                addressBook.value = $in.read_any ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                getList (addressBook        );
            } finally {
                _releaseReply ($in);
            }
  } // getList

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:AddressBook/Address:1.0"};

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
} // class _AddressStub
