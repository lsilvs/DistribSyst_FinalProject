package AddressBook;


/**
* AddressBook/Address_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 12:29:54 AM IST
*/

public class Address_Tie extends _AddressImplBase
{

  // Constructors
  public Address_Tie ()
  {
  }

  public Address_Tie (AddressBook.AddressOperations impl)
  {
    super ();
    _impl = impl;
  }

  public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder emailOut)
  {
    _impl.insert(addressAccountDetailsIn, emailOut);
  } // insert

  public void get (org.omg.CORBA.Any email, org.omg.CORBA.AnyHolder addressAccountDetailsOut)
  {
    _impl.get(email, addressAccountDetailsOut);
  } // get

  private AddressBook.AddressOperations _impl;

} // class Address_Tie