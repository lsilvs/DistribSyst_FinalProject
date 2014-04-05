package AddressBook;


/**
* AddressBook/Address_Tie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 8:41:04 PM IST
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

  public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.IntHolder addressId)
  {
    _impl.insert(addressAccountDetailsIn, addressId);
  } // insert

  public void get (int addressId, org.omg.CORBA.AnyHolder addressAccountDetailsOut)
  {
    _impl.get(addressId, addressAccountDetailsOut);
  } // get

  public void getList (org.omg.CORBA.AnyHolder addressBook)
  {
    _impl.getList(addressBook);
  } // getList

  private AddressBook.AddressOperations _impl;

} // class Address_Tie
