package AddressBook;


/**
* AddressBook/AddressOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 5:26:33 PM IST
*/

public interface AddressOperations 
{
  void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder addressAccountDetailsOut);
  void get (int addressId, org.omg.CORBA.AnyHolder addressAccountDetailsOut);
  void getList (org.omg.CORBA.AnyHolder addressBook);
} // interface AddressOperations
