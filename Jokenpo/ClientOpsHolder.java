package Jokenpo;

/**
* Jokenpo/ClientOpsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 5:26:33 PM IST
*/

public final class ClientOpsHolder implements org.omg.CORBA.portable.Streamable
{
  public Jokenpo.ClientOps value = null;

  public ClientOpsHolder ()
  {
  }

  public ClientOpsHolder (Jokenpo.ClientOps initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Jokenpo.ClientOpsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Jokenpo.ClientOpsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Jokenpo.ClientOpsHelper.type ();
  }

}
