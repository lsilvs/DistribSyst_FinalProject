package AddressBook;

/**
* AddressBook/AddressAccountDetailsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 12:24:57 AM IST
*/

public final class AddressAccountDetailsHolder implements org.omg.CORBA.portable.Streamable
{
  public AddressBook.AddressAccountDetails value = null;

  public AddressAccountDetailsHolder ()
  {
  }

  public AddressAccountDetailsHolder (AddressBook.AddressAccountDetails initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AddressBook.AddressAccountDetailsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AddressBook.AddressAccountDetailsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AddressBook.AddressAccountDetailsHelper.type ();
  }

}
