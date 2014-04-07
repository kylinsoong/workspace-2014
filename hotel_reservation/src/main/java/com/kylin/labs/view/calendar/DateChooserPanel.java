package com.kylin.labs.view.calendar;

import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DateChooserPanel is an abstract class used as container for the three
 * DateChooser component classes. If the components are used by DateChooser,
 * the are registered with an ActionListener and depending on the ActionEvent
 * the corresponding actions are taken.
 * <pre>   daySelector = new DaySelector();
 *   daySelector.addActionListener(new ActionListener() {
 *      public void actionPerformed(ActionEvent e) {
 *         ... do something...
 *      }
 *    });</pre>
 *
 */
public abstract class DateChooserPanel extends JPanel {
	
	EventListenerList actionListeners = new EventListenerList();

	public DateChooserPanel() {
	}

  /**
   * Add an action listener.
   * @param listener ActionListener
   */
  public void addActionListener(ActionListener listener) {
    actionListeners.add(ActionListener.class, listener);
  }

  /**
   * Remove an action listener.
   * @param listener ActionListener
   */
  public void removeActionListener(ActionListener listener) {
    actionListeners.remove(ActionListener.class, listener);
  }

  /**
   * Fire an ActionEvent. Listeners are notified in reverse order.
   * @param e ActionEvent
   */
  protected void fireActionEvent(ActionEvent e) {
    Object[] listeners = actionListeners.getListenerList();
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == ActionListener.class) {
        if (e == null)
          e = new ActionEvent(this, DateChooserAction.nullEvent, "Null Event");
        ( (ActionListener) listeners[i + 1]).actionPerformed(e);
      }
    }
  }
}
