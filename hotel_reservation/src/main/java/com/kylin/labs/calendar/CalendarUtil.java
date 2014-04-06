package com.kylin.labs.calendar;


/**
 * Some additional Calendar constants used in JPopupCalendar. This can be
 * should be updated if the move is made to internationalize the calendar.
 * The arrays containing the month and weekday names should be set using the
 * current locale settings.
 */
public class CalendarUtil {

    private static String[] monthName = {
                                 "January", "February", "March", "April",
                                 "May", "June", "July", "August", "September",
                                 "October", "November",
                                 "December"};

    private static String[] weekdayName = {
                                   "Sunday", "Monday", "Tuesday", "Wednesday",
                                   "Thursday", "Friday",
                                   "Saturday"};

    private static String[] weekdayNameShort = {
                                        "S", "M", "T", "W", "T", "F", "S"};


    public static String getMonthName(int month) {
        return monthName[month];
    }

    public static String getWeekdayName(int dayOfTheWeek) {
        return weekdayName[dayOfTheWeek];
    }

    public static String[] weekdayNames() {
        return weekdayName;
    }

    public static String[] weekdayNamesShort() {
        return weekdayNameShort;
    }

}
