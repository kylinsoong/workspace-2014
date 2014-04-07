package com.kylin.labs.view.calendar;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * The month selector contains two buttons scroll a month forward or backward
 * separated by a label showing the currently selected month name and year.
 * If one of the buttons has been pressed the month selector fires an
 * ActionEvent with event id DateChooserAction.previousMonth, or
 * DateChooserAction.nextMonth (depending on the button pressed).
 * The style of the buttons and the label can be defined by either
 * providing a custom instance of the MonthSelectorStyle class or
 * by getting access to that class and changing the default values for the
 * gui components there.
 *
 */
public class MonthSelector extends DateChooserPanel {

  /**
   * The instance of MonthSelectorStyle to be used for drawing this
   * MonthSelector.
   */
  public MonthSelectorStyle style = new MonthSelectorStyle();

  public DateChooserButton previousMonthButton = null;
  public DateChooserButton nextMonthButton = null;
  public DateChooserLabel monthNameLabel = null;

  /**
   * Default constructor.
   */
  public MonthSelector() {
    init();
  }

  /**
   * Constructor overriding the default style with a provided style.
   * @param newStyle MonthSelectorStyle
   */
  public MonthSelector(MonthSelectorStyle newStyle) {
    style = newStyle;
    init();
  }

  /**
   * Sets up the gui objects and draws the panel.
   */
  private void init() {
    setLayout(new BorderLayout());
    setBackground(Color.LIGHT_GRAY);
    previousMonthButton = (DateChooserButton) style.previousMonthButtonTemplate.clone();
    if (previousMonthButton == null) {
      System.err.println("Cloning button failed.");
      throw new Error();
    }

    nextMonthButton = (DateChooserButton) style.nextMonthButtonTemplate.clone();
    monthNameLabel = (DateChooserLabel) style.monthNameLabelTemplate.clone();

    // add listeners
    // set up button for previous month
    ImageIcon icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(style.previousMonthImageName));
    if (icon != null) {
      previousMonthButton.setIcon(icon);
    }
    previousMonthButton.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent mouseEvent) {
        fireActionEvent(new ActionEvent(this, DateChooserAction.previousMonthSelected, "Previous Month Selected"));
      }
    });

    // set up button for next month
    icon = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(style.nextMonthImageName));
    if (icon != null) {
      nextMonthButton.setIcon(icon);
    }
    nextMonthButton.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent mouseEvent) {
        fireActionEvent(new ActionEvent(this, DateChooserAction.nextMonthSelected, "Previous Month Selected"));
      }
    });

    add(previousMonthButton, BorderLayout.WEST);
    add(monthNameLabel, BorderLayout.CENTER);
    add(nextMonthButton, BorderLayout.EAST);
  }

  /**
   * Set the text to be displayed in the month label.
   * @param monthName String
   */
  public void setMonthName(String monthName) {
    if (monthNameLabel != null) {
      monthNameLabel.setText(monthName);
    }
  }
  
  public static void main(String[] args) {
	  System.out.println(Thread.currentThread().getContextClassLoader().getResource("Next.gif"));
  }
  
}
