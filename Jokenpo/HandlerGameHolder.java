package Jokenpo;

/**
* Jokenpo/HandlerGameHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from idlfile.idl
* Sunday, April 6, 2014 2:49:11 AM IST
*/

public final class HandlerGameHolder implements org.omg.CORBA.portable.Streamable
{
  public Jokenpo.HandlerGame value = null;

  public HandlerGameHolder ()
  {
  }

  public HandlerGameHolder (Jokenpo.HandlerGame initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Jokenpo.HandlerGameHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Jokenpo.HandlerGameHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Jokenpo.HandlerGameHelper.type ();
  }

}
