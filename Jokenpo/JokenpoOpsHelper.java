package Jokenpo;


/**
* Jokenpo/JokenpoOpsHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 4:07:03 PM IST
*/

abstract public class JokenpoOpsHelper
{
  private static String  _id = "IDL:Jokenpo/JokenpoOps:1.0";

  public static void insert (org.omg.CORBA.Any a, Jokenpo.JokenpoOps that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Jokenpo.JokenpoOps extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Jokenpo.JokenpoOpsHelper.id (), "JokenpoOps");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Jokenpo.JokenpoOps read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_JokenpoOpsStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Jokenpo.JokenpoOps value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Jokenpo.JokenpoOps narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Jokenpo.JokenpoOps)
      return (Jokenpo.JokenpoOps)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Jokenpo._JokenpoOpsStub stub = new Jokenpo._JokenpoOpsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Jokenpo.JokenpoOps unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Jokenpo.JokenpoOps)
      return (Jokenpo.JokenpoOps)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Jokenpo._JokenpoOpsStub stub = new Jokenpo._JokenpoOpsStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
