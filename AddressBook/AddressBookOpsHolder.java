package AddressBook;

/**
* AddressBook/AddressBookOpsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public final class AddressBookOpsHolder implements org.omg.CORBA.portable.Streamable
{
  public AddressBook.AddressBookOps value = null;

  public AddressBookOpsHolder ()
  {
  }

  public AddressBookOpsHolder (AddressBook.AddressBookOps initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AddressBook.AddressBookOpsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AddressBook.AddressBookOpsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AddressBook.AddressBookOpsHelper.type ();
  }

}
