package com.kylin.labs.calendar;

import java.util.Vector;
import java.util.Iterator;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

/**
 * Select a day for a given month using the classic calendar view of a day
 * with days arranged in a grid.
 * If a day is selected DaySelector fires an ActionEvent with an event id
 * DateChooserAction.calendarButtonSelected. The selected day itself can
 * be retrieved by using getSource to get a reference to the DaySelector
 * instance and then looking at selectedDay. For a detailed example please
 * look at DateChooser.
 */
public class DaySelector extends DateChooserPanel {

  private DateChooserLabel[] dayLabel = new DateChooserLabel[31];
  private DateChooserLabel[] weekdayLabel = new DateChooserLabel[7];
  private DateChooserLabel todayLabel = null;
  private Vector blankLabel = new Vector();
  private int firstDayOfWeekOffset = 0;

  /**
   * The style definitions used for drawing the day selector
   */
  private DaySelectorStyle style = new DaySelectorStyle();

  /**
   * The selected day.
   */
  public int selectedDay = -1;


  /**
   * The common instance of the mouse adapter used for all days.
   */
  private DaySelectorButton_mouseAdapter dayMouseListener =
      new DaySelectorButton_mouseAdapter(this);

  /**
   * Default Constructor.
   */
  public DaySelector() {
    init();
  }

  /**
   * Constructor overriding the default style.
   * @param newStyle DaySelectorStyle
   */
  public DaySelector(DaySelectorStyle newStyle) {
    style = newStyle;
    init();
  }

  private void init() {
    setLayout(new GridLayout(0, 7, 1, 1));
    setBackground(Color.white);

    // initialize weekday components
    String[] weekDay = style.getWeekdayNames();
    for (int iDay = 0; iDay < 7; iDay++) {
      weekdayLabel[iDay] =
          (DateChooserLabel)style.weekdayLabelTemplate.clone();
      weekdayLabel[iDay].setText(weekDay[iDay]);
    }

    // initialize days
    todayLabel = (DateChooserLabel) style.todayLabelTemplate.clone();
    todayLabel.addMouseListener(dayMouseListener);
    for (int iDay = 1; iDay <= 31; iDay++) {
      dayLabel[iDay - 1] =
          (DateChooserLabel) style.dayLabelTemplate.clone();
      dayLabel[iDay-1].setText("" + iDay);
      dayLabel[iDay-1].addMouseListener(dayMouseListener);
    }

    // initialize the blank labels - max is 14
    for (int i = 0; i<14; i++) {
      DateChooserLabel newBlankLabel =
          (DateChooserLabel) style.blankLabelTemplate.clone();
      blankLabel.add(newBlankLabel);
    }
  }

  /**
   * Draw the day selector in a grid layout.
   * @param currentDate Calendar
   */
  public void paintCalendarPane(Calendar currentDate) {
    selectedDay = -1;
    firstDayOfWeekOffset = currentDate.getFirstDayOfWeek();
    removeAll(); //clean up old stuff

    // thisMonth is a utility calendar instance to build the
    // calendar panel around the currently selected calendar value
    Calendar thisMonth = (Calendar) currentDate.clone();
    int lastDay = thisMonth.getActualMaximum(java.util.Calendar.
                                             DAY_OF_MONTH);
    // last day of last month
    thisMonth.add(java.util.Calendar.MONTH, -1);
    int lastDayOfLastMonth = thisMonth.getActualMaximum(java.util.Calendar.
        DAY_OF_MONTH);

    // reset thisMonth to the original value
    thisMonth.add(java.util.Calendar.MONTH, +1);

    // get the weekday of the first of this month
    thisMonth.set(java.util.Calendar.DAY_OF_MONTH, 1);
    int firstOfMonth = thisMonth.get(java.util.Calendar.DAY_OF_WEEK);

    if (style.showWeekdays) {
      paintWeekdayLabels();
    }

    // add blanks until the first of the month...
    Iterator blankLabelIterator = blankLabel.iterator();
    int labelsToDraw = firstOfMonth - firstDayOfWeekOffset;
    if (labelsToDraw < 0) {
      labelsToDraw = labelsToDraw+7;
    }
    if (labelsToDraw > 0) {
      int startDay = lastDayOfLastMonth - labelsToDraw - 1;
      addBlankLabels(startDay, labelsToDraw, blankLabelIterator);
    }

    // now start the counting for the current month
    Calendar calNow = Calendar.getInstance();

    boolean isCurrentMonth = false;
    if ( (calNow.get(Calendar.YEAR) == thisMonth.get(Calendar.YEAR)) &&
        (calNow.get(Calendar.MONTH) == thisMonth.get(Calendar.MONTH))) {
      isCurrentMonth = true;
    }

    for (int iDay = 1; iDay <= lastDay; iDay++) {
      if (isCurrentMonth &&
          iDay == calNow.get(Calendar.DAY_OF_MONTH)) {
        todayLabel.setText("" + iDay);
        add(todayLabel);
      }
      else {
        add(dayLabel[iDay-1]);
      }
    }

    // add remaining blanks - max. days in calendar is 42 (6 weeks)
    int noOfDays = 42 - lastDay - labelsToDraw;
    addBlankLabels(1, noOfDays, blankLabelIterator);
  }


    /**
     * Add the weekday labels
     */
    private void paintWeekdayLabels() {
      // write the weekday labels
      for (int iDay = 0; iDay < 7; iDay++) {
        int index = iDay + firstDayOfWeekOffset - 1;
        if (index > 6) {
          index = index-7;
        }
        add(weekdayLabel[index]);
      }
    }


    /**
     * Add the blank labels before and after the days of the current month.
     * @param firstDay int
     * @param noOfDays int
     * @param blankLabelIterator Iterator
     */

    private void addBlankLabels(int firstDay, int noOfDays,
                                Iterator blankLabelIterator) {
      int currentDay = firstDay;
      for (int iDay = 1; iDay <= noOfDays; iDay++) {
        DateChooserLabel jLabel;
        if (blankLabelIterator.hasNext()) {
          jLabel = (DateChooserLabel) blankLabelIterator.next();
          jLabel.setText("" + currentDay);
          add(jLabel);
        }
        currentDay++;
      }
    }


    /**
     * Action taken when a day is selected. This fires an ActionEvent with
     * id DateChooserAction.calendarButtonSelected.
     * @param mouseEvent MouseEvent
     */
    public void daySelectorButton_mouseReleased(MouseEvent mouseEvent) {
      int iDay = -1;
      try {
        iDay = Integer.parseInt( ( (JLabel) mouseEvent.getSource()).getText().
                                trim());
      }
      catch (NumberFormatException e) {
        // ignore
      }
      selectedDay = iDay;
      fireActionEvent(
          new ActionEvent(this, DateChooserAction.calendarButtonSelected,
                          "Day Button Selected"));
    }

}


  /**
   * Common mouse adapter class used by all selectable day labels.
   * @author Will Roethel
   * @version $Revision: 1.4 $
   */
  class DaySelectorButton_mouseAdapter extends MouseAdapter {

    private DaySelector adaptee;

    DaySelectorButton_mouseAdapter(DaySelector adaptee) {
      this.adaptee = adaptee;
    }

    public void mouseReleased(MouseEvent mouseEvent) {
      adaptee.daySelectorButton_mouseReleased(mouseEvent);
    }

  }

//////
