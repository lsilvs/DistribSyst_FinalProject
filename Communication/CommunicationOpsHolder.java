package Communication;

/**
* Communication/CommunicationOpsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Tuesday, April 8, 2014 2:17:22 AM IST
*/

public final class CommunicationOpsHolder implements org.omg.CORBA.portable.Streamable
{
  public Communication.CommunicationOps value = null;

  public CommunicationOpsHolder ()
  {
  }

  public CommunicationOpsHolder (Communication.CommunicationOps initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Communication.CommunicationOpsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Communication.CommunicationOpsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Communication.CommunicationOpsHelper.type ();
  }

}
