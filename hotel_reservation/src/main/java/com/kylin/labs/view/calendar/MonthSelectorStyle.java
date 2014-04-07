package com.kylin.labs.view.calendar;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;

/**
 * Class containing the style information to be used for drawing the
 * month selector.
 */
public class MonthSelectorStyle extends DateChooserStyle {

  // gui components
  public DateChooserButton previousMonthButtonTemplate;
  public DateChooserButton nextMonthButtonTemplate;
  public DateChooserLabel monthNameLabelTemplate;

  // button decoration
  /**
   * Icon to be used as decoration for the button used to select the
   * previous month.
   */
  public String previousMonthImageName = "Previous.gif";

  /**
   * Icon used as decoration for the button used to select the next month.
   */
  public String nextMonthImageName = "Next.gif";

  /**
   * Month names. Can be overridden using the setMonthNames method.
   */
  protected String[] monthName = null;
  protected String[] monthNameDefault = {
      "January", "February", "March", "April",
      "May", "June", "July", "August", "September",
      "October", "November",
      "December"};

  /**
   * Set the default values for the various swing components used by
   * DateChooser.
   */
  protected void initDefaults() {

    // gui components
    previousMonthButtonTemplate = new DateChooserButton();
    nextMonthButtonTemplate = new DateChooserButton();
    monthNameLabelTemplate = new DateChooserLabel();

    // style of the month name label
    monthNameLabelTemplate.setFont(new java.awt.Font("Verdana", Font.BOLD, 11));
    monthNameLabelTemplate.setHorizontalAlignment(SwingConstants.CENTER);
    monthNameLabelTemplate.setText("Month");

    // style of previousMonth button
    previousMonthButtonTemplate.setMaximumSize(new Dimension(16, 16));
    previousMonthButtonTemplate.setMinimumSize(new Dimension(16, 16));
    previousMonthButtonTemplate.setPreferredSize(new Dimension(16, 16));

    // style of nextMonth button
    nextMonthButtonTemplate.setMaximumSize(new Dimension(16, 16));
    nextMonthButtonTemplate.setMinimumSize(new Dimension(16, 16));
    nextMonthButtonTemplate.setPreferredSize(new Dimension(16, 16));

    // let subclasses override the default settings
    initOverride();
  }

  // getters

  /**
   * Get the button template that corresponds to the left button (previous
   * month) in the MonthSelector.
   * @return DateChooserButton
   */
  public DateChooserButton getPreviousMonthButton() {
    return previousMonthButtonTemplate;
  }

  /**
   * Get the button template that corresponds to the right button (next
   * month) in the MonthSelector.
   * @return DateChooserButton
   */
  public DateChooserButton getNextMonthButton() {
    return nextMonthButtonTemplate;
  }

  /**
   * Get label template located in the center of the MonthSelector.
   * @return DateChooserLabel
   */
  public DateChooserLabel getMonthNameLabel() {
    return monthNameLabelTemplate;
  }

  /**
   * Get the name of a month for a given index. The index corresponds to
   * the values of java.util.Calendar.MONTH, i.e. January == 0.
   * @param month int
   * @return String
   */
  public String getMonthName(int month) {
    if (monthName == null) {
      try {
        setMonthNames(CalendarNamesUtil.getMonthNames(locale));
      }
      catch (Exception e) {
        monthName = monthNameDefault;
      }
    }
    return monthName[month];
  }

  /**
   * Override the default weekday names with a new set. The array must be
   * of length 12 otherwise an exception with be thrown. The matching of
   * month names with index values follows the convention in
   * java.util.Calendar with January having index 0.
   * @param months String[]
   * @throws Exception
   */
  public void setMonthNames(String[] months) throws Exception {
    if (months.length != 12) {
      throw new Exception("Expected String[] with length 12. Found length "
                          + months.length + ". Keeping the old settings.");
    }
    monthName = months;
  }

  public void forceSerializeCopy(boolean bool) {
    if (bool) {
      previousMonthButtonTemplate.FORCE_SERIALIZE_COPY = true;
      nextMonthButtonTemplate.FORCE_SERIALIZE_COPY = true;
      monthNameLabelTemplate.FORCE_SERIALIZE_COPY = true;
    }
  }
}
