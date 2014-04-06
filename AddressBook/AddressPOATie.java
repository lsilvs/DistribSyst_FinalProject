package AddressBook;


/**
* AddressBook/AddressPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 5:26:33 PM IST
*/

public class AddressPOATie extends AddressPOA
{

  // Constructors

  public AddressPOATie ( AddressBook.AddressOperations delegate ) {
      this._impl = delegate;
  }
  public AddressPOATie ( AddressBook.AddressOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public AddressBook.AddressOperations _delegate() {
      return this._impl;
  }
  public void _delegate (AddressBook.AddressOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public void insert (org.omg.CORBA.Any addressAccountDetailsIn, org.omg.CORBA.AnyHolder addressAccountDetailsOut)
  {
    _impl.insert(addressAccountDetailsIn, addressAccountDetailsOut);
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
  private org.omg.PortableServer.POA _poa;

} // class AddressPOATie
