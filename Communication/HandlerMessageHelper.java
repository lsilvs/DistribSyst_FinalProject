package Communication;


/**
* Communication/HandlerMessageHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 12:24:57 AM IST
*/

abstract public class HandlerMessageHelper
{
  private static String  _id = "IDL:Communication/HandlerMessage:1.0";

  public static void insert (org.omg.CORBA.Any a, Communication.HandlerMessage that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Communication.HandlerMessage extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Communication.HandlerMessageHelper.id (), "HandlerMessage");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Communication.HandlerMessage read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HandlerMessageStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Communication.HandlerMessage value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Communication.HandlerMessage narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Communication.HandlerMessage)
      return (Communication.HandlerMessage)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Communication._HandlerMessageStub stub = new Communication._HandlerMessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Communication.HandlerMessage unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Communication.HandlerMessage)
      return (Communication.HandlerMessage)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Communication._HandlerMessageStub stub = new Communication._HandlerMessageStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
