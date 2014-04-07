package com.kylin.labs.view.calendar;

import javax.swing.JPopupMenu;
import java.util.Calendar;
import java.awt.Component;
import java.util.Locale;

/**
 * <p>A date picker popup window. JPopupCalendar is used just like any
 * other JPopupMenu. The general way to use it is to first create
 * the popup calendar:<br>
 * <pre>...
 * JPopupCalendar popupCalendar = new JPopupCalendar();
 * ...</pre>
 * and then call
 * <pre>...
 * popupCalendar.show();
 * ...</pre>
 * to display the calendar, e.g. in response to a mouse click. The selected
 * date can be retrieved by calling<br>
 * <pre>...
 * Calendar selectedDate = popupCalendar.getDate();
 * if (selectedDate != null) {
 *    ... do something
 * }
 * ...</pre>
 * It should be pointed out that in this way of using JPopupCalendar
 * the selectedDate can be undefined (null) if no date has been selected. So
 * a check on the returned date being defined should be made before further
 * use.</p>
 *
 * <h3>Look and Feel</h3>
 * <p>JPopupCalendar provides access to all the gui components used by
 * DateChooser to draw the calendar. That's a lot of options, but allows
 * for complete customization of the popup window. A description of the
 * DateChooser layout and the default settings is given if the user manual.</p>
 *
 * <p>The second way of customizing JPopupCalendar is to override the default swing
 * object directly with user defined ones. To do so create an instance of
 * either DateChooserButton, DateChooserLabel or DateChooserPanel and then
 * use the setter methods to override the defaults in the style definitions.</p>
 *
 * <p>For more extensive customization, a third option is useful.
 * Create a new class that extends DateChooserStyle, and either
 * avoid creating a constructor or make sure to make a call to
 * <tt>initDefaults</tt> in the constructor, and override
 * <tt>initOverride</tt> with new settings. Then use the <tt>setStyle()</tt>
 * method to use the new style file when creating the popup window.
 * The obvious advantage of this is that the new class can be easily be reused
 * for other projects without much cut-and-paste.</p>
 *
 * <h3>To <tt>pack</tt> or not to <tt>pack</tt></h3>
 * Since the look and feel settings of JPopupCalendar can configured, the
 * DateChooser instance representing the calendar is by default created at
 * the very last moment, i.e. when calling <tt>show</tt>. To speed up
 * execution DateChooser can be created earlier. This is done with
 * <tt>pack</tt> (which btw also calls the standart <tt>pack</tt> as well).
 * The call to <tt>pack</tt> of course has to be made after the style
 * definitions have been set.</p>
 *
 *
 */
public class JPopupCalendar extends JPopupMenu {
    private Calendar date = null;
    private DateChooser datePane = null;
    private boolean isPacked = false;
    private Thread packThread;
    private Locale locale = Locale.getDefault();

    /**
     * Saves the selected date between instances when the window is shown, i.e.
     * getDate() will return a previously selected value even though the last
     * time the popup window was shown no date was selected. Default is true.
     */
    public boolean SAVE_SELECTED_DATE = true;

    /**
     * Reset all dates between calls to show. Not only the selected date will
     * be reset but also the currently displayed date.
     */
    public boolean RESET_AT_SHOW = false;

    /**
     * Default Constructor.
     */
    public JPopupCalendar() {
      datePane = new DateChooser();
    }

    /**
     * Create a JPopupCalendar for a given locale.
     * @param locale Locale
     */
    public JPopupCalendar(Locale locale) {
      this.locale = locale;
      datePane = new DateChooser(locale);
    }


    // public methods
    /**
     * show the JPopupCalendar. Consistent with the original behavior of
     * <tt>show</tt>.
     * @param invoker Component
     * @param x int
     * @param y int
     */
    public void show(Component invoker,
                     int x, int y) {

      // ignore if thread is stuck.
      if (!isThreadDone()) return;

      if (! isPacked) {
        pack();
      }

      if (!SAVE_SELECTED_DATE) {
        datePane.resetSelectedDate();
      }

      if (RESET_AT_SHOW) {
        datePane.resetDates();
        datePane.update();
      }

       super.show(invoker, x, y);
    }


    /**
     * Create an instance of DateChooser before <tt>show</tt> is called and
     * can make the popup window pop up a bit quicker. Internally called by
     * <tt>show</tt>.
     */
    public void pack() {
        datePane.pack();
        add(datePane);
        super.pack();
        isPacked = true;
    }


    /**
     * Create an instance of DateChooser before <tt>show</tt> is called and
     * can make the popup window pop up a bit quicker. Internally called by
     * <tt>show</tt>.
     */
    public void packInThread() {
      packThread = new Thread( new Runnable() {
        public void run() {
          pack();
        }
      });
      packThread.start();
    }


    /**
     * Returns the selected date. If now date has been selected and
     * no Calendar instance has been registered with JPopupCalendar,
     * <tt>getDate</tt> can return null.
     * @return Calendar
     */
    public Calendar getDate() {
      return datePane.getDate();
    }

    /**
     * Show the Today Selector Button.
     * @param showSelector boolean
     */
    public void showTodaySelector(boolean showSelector) {
      datePane.showTodaySelector = showSelector;
    }

    /**
     * Show the month selector. Only useful if the month is required to stay
     * fixed otherwise the calendar will not allow to change months.
     * @param showSelector boolean
     */
    public void showMonthSelector(boolean showSelector) {
      datePane.showMonthSelector = showSelector;
    }

    /**
     * Show the weekday names in the calendar.
     * @param showSelector boolean
     */
    public void showWeekdayLabels(boolean showSelector) {
      getDaySelectorStyle().showWeekdays = showSelector;
    }

    // setters

    /**
     * Override the month selector with a user defined new selector.
     * @param newStyle MonthSelectorStyle
     */
    public void setMonthSelectorStyle(MonthSelectorStyle newStyle) {
        datePane.setMonthSelectorStyle(newStyle);
    }

    /**
     * Override the day selector styles with a user defined new selector.
     * @param newStyle DaySelectorStyle
     */
    public void setDaySelectorStyle(DaySelectorStyle newStyle) {
        datePane.setDaySelectorStyle(newStyle);
    }

    /**
     * Override the style settings for the today button with a set of user
     * defined settings.
     * @param newStyle TodaySelectorStyle
     */
    public void setTodaySelectorStyle(TodaySelectorStyle newStyle) {
        datePane.setTodaySelectorStyle(newStyle);
    }


    /**
     * Set the startup date of the popup calendar.
     * @param date Calendar
     */
    public void setDate(Calendar date) {
      datePane.setDate(date);
    }


    // getters

    /**
     * Get the class instance defining the style of the month selector.
     * @return MonthSelectorStyle
     */
    public MonthSelectorStyle getMonthSelectorStyle() {
      return datePane.getMonthSelectorStyle();
    }

    /**
     * Get the class instance defining the settings for the day selector.
     * @return DaySelectorStyle
     */
    public DaySelectorStyle getDaySelectorStyle() {
      return datePane.getDaySelectorStyle();
    }

    /**
     * Get access to the style definitions for the Today button.
     * @return TodaySelectorStyle
     */
    public TodaySelectorStyle getTodaySelectorStyle() {
      return datePane.getTodaySelectorStyle();
    }

    /**
     * Get the date panel itself, i.e the full calendar panel that sits
     * inside the popup window.
     * @return DateChooser
     */
    public DateChooser getDateChooser() {
      return datePane;
    }


    /**
     * Force a deep copy of the swing component templates instead of the
     * native clone, even if the clone method supports the use of it in
     * this context.
     * @param bool boolean
     */
    public void forceSerializeCopy(boolean bool) {
      datePane.forceSerializeCopy(bool);
    }

    /**
     * Check if the packing thread has completed.
     * @return boolean
     */
    private boolean isThreadDone() {
      if (packThread != null) {
        try {
          packThread.join();
          return true;
        }
        catch (Exception e) {
          System.err.println("Can't join pack thread.");
          return false;
        }
      }
      return true;
    }
}



