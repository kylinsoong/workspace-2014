package com.kylin.labs.calendar;

import javax.swing.JButton;

/**
 * <p>A publicly cloneable version of JButton used by
 * DateChooserStyle and DateChooser to define the various
 * customizable buttons used in DateChooser.</p>
 * @see DateChooserStyle
 * @see DateChooser
 */
public class DateChooserButton extends JButton implements Cloneable {

  public boolean FORCE_SERIALIZE_COPY = false;

  public DateChooserButton() {
  }

  /**
   * <p>Clone this instance of DateChooserButton</p>
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
      // debug
      try {
        DateChooserButton copy = (DateChooserButton)super.clone();
        return copy;
      }
      catch (CloneNotSupportedException e) {
        // This should never happen unless you have a hacked version of java
        // or sun changes the cloning policies on swing objects.
        throw new Error("Label is not cloneable.");
      }
    }
  }
}
