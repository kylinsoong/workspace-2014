package com.kylin.labs.calendar;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

/**
 * DateChooser component to quickly select todays date. The TodaySelector is
 * comprised of only one button. If the button is pressed an ActionEvent with
 * event id DateChooserAction.todaySelected is fired.
 * @author Will Roethel, http://www.cwroethel.net
 * @version $Revision: 1.2 $
 */
public class TodaySelector extends DateChooserPanel {

  private DateChooserButton todayButton = null;
  private TodaySelectorStyle style = new TodaySelectorStyle();

  /**
   * Default constructor.
   */
  public TodaySelector() {
    init();
  }

  /**
   * Constructor overriding the default style.
   * @param newStyle TodaySelectorStyle
   */
  public TodaySelector(TodaySelectorStyle newStyle) {
    style = newStyle;
    init();
  }

  /**
   * Set up the gui components and draw the panel.
   */
  private void init() {
    setLayout(new FlowLayout());
    setBackground(Color.white);
    todayButton = (DateChooserButton)style.getTodayButton().clone();
    add(todayButton);
    todayButton.setText("Today");
    todayButton.addMouseListener( new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {
          fireActionEvent(
              new ActionEvent(this, DateChooserAction.todaySelected,
                              "Today Button Selected"));
        }
    });
  }
}

