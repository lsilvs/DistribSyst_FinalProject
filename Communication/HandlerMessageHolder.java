package Communication;

/**
* Communication/HandlerMessageHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Saturday, April 5, 2014 8:41:09 PM IST
*/

public final class HandlerMessageHolder implements org.omg.CORBA.portable.Streamable
{
  public Communication.HandlerMessage value = null;

  public HandlerMessageHolder ()
  {
  }

  public HandlerMessageHolder (Communication.HandlerMessage initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Communication.HandlerMessageHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Communication.HandlerMessageHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Communication.HandlerMessageHelper.type ();
  }

}
