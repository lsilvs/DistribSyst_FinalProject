package AddressBook;


/**
* AddressBook/AddressAccountDetailsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 2:49:11 AM IST
*/

abstract public class AddressAccountDetailsHelper
{
  private static String  _id = "IDL:AddressBook/AddressAccountDetails:1.0";

  public static void insert (org.omg.CORBA.Any a, AddressBook.AddressAccountDetails that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AddressBook.AddressAccountDetails extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [5];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "address",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "phoneNumber",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "email",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (AddressBook.AddressAccountDetailsHelper.id (), "AddressAccountDetails", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AddressBook.AddressAccountDetails read (org.omg.CORBA.portable.InputStream istream)
  {
    AddressBook.AddressAccountDetails value = new AddressBook.AddressAccountDetails ();
    value.id = istream.read_long ();
    value.name = istream.read_string ();
    value.address = istream.read_string ();
    value.phoneNumber = istream.read_string ();
    value.email = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AddressBook.AddressAccountDetails value)
  {
    ostream.write_long (value.id);
    ostream.write_string (value.name);
    ostream.write_string (value.address);
    ostream.write_string (value.phoneNumber);
    ostream.write_string (value.email);
  }

}
