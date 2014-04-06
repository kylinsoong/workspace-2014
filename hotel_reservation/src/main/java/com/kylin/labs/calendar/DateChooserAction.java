package com.kylin.labs.calendar;

/**
 * Actions defined and used by the various components of DateChooser.
 */
public class DateChooserAction {

  /**
   * Empty event.
   */
  static final int nullEvent = 0;

  /**
   * Indicates that next month button of the month selector has been selected.
   */
  static final int nextMonthSelected = 1;

  /**
   * Indicates that the previous month button of the month selector has been
   * selected.
   */
  static final int previousMonthSelected = 2;

  /**
   * The 'Today' button of the today selector has been pressed.
   */
  static final int todaySelected = 3;

  /**
   * A day of the day selector has been selected.
   */
  static final int calendarButtonSelected = 4;
}
