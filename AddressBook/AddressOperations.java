package AddressBook;


/**
* AddressBook/AddressOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 12:30:00 AM IST
*/

public interface AddressOperations 
{
  void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder emailOut);
  void get (org.omg.CORBA.Any email, org.omg.CORBA.AnyHolder addressAccountDetailsOut);
} // interface AddressOperations
