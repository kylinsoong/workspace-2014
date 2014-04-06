package com.kylin.labs.calendar;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;


/**
 * Utility class providing static methods to get the locale dependent weekday
 * and month names.
 */
public class CalendarNamesUtil {

  public CalendarNamesUtil() {
  }

  /**
   * Get an array of month names for a given locale.
   * @param locale Locale
   * @return String[]
   */
  public static String[] getMonthNames(Locale locale) {
    // get a norm calendar using the default locale
    SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM", locale);
    String[] monthName = new String[12];
    GregorianCalendar cal = new GregorianCalendar(2005,
                                                  java.util.Calendar.JANUARY,
                                                  1);
    for (int iMonth = 0; iMonth<12; iMonth++) {
      monthName[iMonth] = monthFormatter.format(cal.getTime());
      cal.add(java.util.Calendar.MONTH, 1);
    }
    return monthName;
  }

  /**
   * Get an array with month names for the default locale.
   * @return String[]
   */
  public static String[] getMonthNames() {
    return getMonthNames(Locale.getDefault());
  }


  /**
   * Get an array of weekday names for a given locale.
   * @param locale Locale
   * @return String[]
   */
  public static String[] getWeekdayNames(Locale locale) {
    // get a norm calendar using the default locale
    SimpleDateFormat dayFormatter = new SimpleDateFormat("E", locale);
    String[] weekdayName = new String[7];
    GregorianCalendar cal = new GregorianCalendar(2005,
                                                  java.util.Calendar.JANUARY,
                                                  1);
    int currentWeekday = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysUntilNextSunday = java.util.Calendar.SATURDAY - currentWeekday + 1;
    cal.add(java.util.Calendar.DAY_OF_MONTH, daysUntilNextSunday);
    for (int iDay = 0; iDay<7; iDay++) {
      weekdayName[iDay] = dayFormatter.format(cal.getTime()).subSequence(0, 1).toString();
      cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
    }
    return weekdayName;
  }

  /**
   * Get an array of weekday names for the default locale.
   * @return String[]
   */
  public static String[] getWeekdayNames() {
    return getWeekdayNames(Locale.getDefault());
  }


}
