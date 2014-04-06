package com.kylin.labs.calendar;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2014</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TodaySelectorStyle extends DateChooserStyle {

  public DateChooserButton todayButtonTemplate;

  protected void initDefaults() {
    todayButtonTemplate = new DateChooserButton();
    todayButtonTemplate.setText("Today");
  }

  /**
   * Get the label template used for the Today Button.
   * @return DateChooserButton
   */
  public DateChooserButton getTodayButton() {
    return todayButtonTemplate;
  }

  public void forceSerializeCopy(boolean bool) {
    if (bool) {
      todayButtonTemplate.FORCE_SERIALIZE_COPY = true;
    }
  }
}
