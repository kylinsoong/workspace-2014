package com.kylin.labs.view.calendar;

import javax.swing.JLabel;

/**
 * <p>A publicly cloneable version of JLabel used by
 * DateChooserStyle and DateChooser to define the various
 * customizable labels used in DateChooser.</p>
 * @see DateChooserStyle
 * @see DateChooser
 */
public class DateChooserLabel extends JLabel implements Cloneable {

  public boolean FORCE_SERIALIZE_COPY = false;

  /**
   * <p>Clone this instance of DateChooserLabel</p>
   * @return Object
   */
  public Object clone() {
    String version = System.getProperties().getProperty("java.class.version");
    float versionNo;
    try {
      versionNo = Float.parseFloat(version.trim());
    }
    catch (NumberFormatException e) {
      System.err.println("Failed to get class version. Assuming pre-1.5");
      versionNo = 0;
    }
    if (versionNo < 49.0 || this.FORCE_SERIALIZE_COPY) {
      // need to deep copy using serialization
      try {
        return SerializeClone.clone(this);
      }
      catch (Exception e) {
        return null;
      }
    }
    else {
     try {
        DateChooserLabel copy = (DateChooserLabel)super.clone();
        return copy;
      }
      catch (CloneNotSupportedException e) {
        throw new Error("Label is not cloneable.");
      }
    }
  }
}
