package com.kylin.labs.calendar;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;

/**
 * Style Class containing the default style for the GUI components used in
 * the DaySelector as well as accessors to the component templates.
 */
public class DaySelectorStyle extends DateChooserStyle {
  public DateChooserLabel weekdayLabelTemplate;
  public DateChooserLabel blankLabelTemplate;
  public DateChooserLabel dayLabelTemplate;
  public DateChooserLabel todayLabelTemplate;

  /**
   * True if the weekday labels should be shown.
   */
  public boolean showWeekdays = true;

  /**
   * String[] containing the names of the weekdays to be shown.
   */
  protected String[] weekdayName = null;
  protected static String[] weekdayNameDefault = {
      "S", "M", "T", "W", "T", "F", "S"};

  /**
   * Set the default values
   */
  protected void initDefaults() {
    weekdayLabelTemplate = new DateChooserLabel();
    blankLabelTemplate = new DateChooserLabel();
    dayLabelTemplate = new DateChooserLabel();
    todayLabelTemplate = new DateChooserLabel();

    if (weekdayLabelTemplate == null) {
      System.out.println("parameter is null");
    }
    weekdayLabelTemplate.setFont(new java.awt.Font("Verdana", Font.BOLD, 11));
    weekdayLabelTemplate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    weekdayLabelTemplate.setMaximumSize(new Dimension(daySize, daySize));
    weekdayLabelTemplate.setMinimumSize(new Dimension(daySize, daySize));
    weekdayLabelTemplate.setPreferredSize(new Dimension(daySize, daySize));
    weekdayLabelTemplate.setBorder(BorderFactory.createEmptyBorder());

    blankLabelTemplate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    blankLabelTemplate.setBorder(BorderFactory.createEmptyBorder());
    blankLabelTemplate.setMaximumSize(new Dimension(daySize, daySize));
    blankLabelTemplate.setMinimumSize(new Dimension(daySize, daySize));
    blankLabelTemplate.setPreferredSize(new Dimension(daySize, daySize));
    blankLabelTemplate.setForeground(Color.lightGray);

    dayLabelTemplate.setFont(new java.awt.Font("Ariel", Font.PLAIN, 11));
    dayLabelTemplate.setPreferredSize(new Dimension(daySize, daySize));
    dayLabelTemplate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    dayLabelTemplate.setBorder(BorderFactory.createEtchedBorder(1));

    todayLabelTemplate.setFont(new java.awt.Font("Ariel", Font.PLAIN, 11));
    todayLabelTemplate.setPreferredSize(new Dimension(daySize, daySize));
    todayLabelTemplate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    todayLabelTemplate.setBorder(BorderFactory.createEtchedBorder(1));
    todayLabelTemplate.setBackground(Color.lightGray);
    todayLabelTemplate.setOpaque(true);
  }

  /**
   * Get the weekday names used for the weekday labels.
   * @param dayOfTheWeek int
   * @return String
   */
  public String getWeekdayName(int dayOfTheWeek) {
      return weekdayName[dayOfTheWeek];
  }

  /**
   * Get the array containing the weekday names.
   * @return String[]
   */
  public String[] getWeekdayNames() {
    if (weekdayName == null) {
      try {
        setWeekdayNames(CalendarNamesUtil.getWeekdayNames(locale));
      }
      catch (Exception e) {
        weekdayName = weekdayNameDefault;
      }
    }
    return weekdayName;
  }


  /**
   * Override the default weekday names with a new set. The array must be
   * of length 7 otherwise setWeekdayNames will throw an exception.
   * @param weekdays String[]
   * @throws Exception
   */

  public void setWeekdayNames(String[] weekdays) throws Exception {
    if (weekdays.length != 7) {
      throw new Exception("Expected String[] with length 7. Found length "
                          + weekdays.length + ". Keeping the old settings.");
    }
    weekdayName = weekdays;
  }

  /**
   * Get the label template used for showing the weekdays of the calendar
   * @return DateChooserLabel
   */
  public DateChooserLabel getWeekdayLabels() {
    return weekdayLabelTemplate;
  }

  /**
   * Get the label template used for showing the days of the previous and
   * next month. (These used to be 'blank', therefore the name...)
   * @return DateChooserLabel
   */
  public DateChooserLabel getBlankLabels() {
    return blankLabelTemplate;
  }

  /**
   * Get the label template used for displaying the days of the current month.
   * @return DateChooserLabel
   */
  public DateChooserLabel getDayLabels() {
    return dayLabelTemplate;
  }

  /**
   * Get the label template used for showing the weekdays of the calendar
   * @return DateChooserLabel
   */
  public DateChooserLabel getTodayLabel() {
    return todayLabelTemplate;
  }

  /**
   * Force a deep copy of the swing component templates instead of the
   * native clone, even if the clone method supports the use of it in
   * this context.
   * @param bool boolean
   */
  public void forceSerializeCopy(boolean bool) {
    if (bool) {
      System.out.println("Forcing serialization");
      weekdayLabelTemplate.FORCE_SERIALIZE_COPY = true;
      blankLabelTemplate.FORCE_SERIALIZE_COPY = true;
      dayLabelTemplate.FORCE_SERIALIZE_COPY = true;
      todayLabelTemplate.FORCE_SERIALIZE_COPY = true;
    }
  }
}
