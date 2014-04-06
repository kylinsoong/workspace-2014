package com.kylin.labs.calendar;

import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Static class to help clone objects using serialization. Used for
 * cloning pre-1.5 release objects. Is slow, but that can be hidden in the
 * setup.
 * @author Based on an example in JavaWorld:
 * http://www.javaworld.com/javaworld/javatips/jw-javatip76.html
 */
public class SerializeClone {

  public static Object clone(Object cloneThis) throws Exception {
    ObjectOutputStream os = null;
    ObjectInputStream is = null;

    try {
      ByteArrayOutputStream bc = new ByteArrayOutputStream();
      os = new ObjectOutputStream(bc);
      os.writeObject(cloneThis);
      os.flush();
      ByteArrayInputStream bin = new ByteArrayInputStream(bc.toByteArray());
      is = new ObjectInputStream(bin);
      return (is.readObject());

    }
    catch (Exception e) {
      System.out.println("Exception: " + e);
      throw (e);
    }
    finally {
      os.close();
      is.close();
    }
  }

}
