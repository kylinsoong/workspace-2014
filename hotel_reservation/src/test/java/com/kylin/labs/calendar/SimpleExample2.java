package com.kylin.labs.calendar;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.text.SimpleDateFormat;

public class SimpleExample2 extends JFrame {
	JButton selectDate = new JButton();
	JTextField showDate = new JTextField();
	JPopupCalendar popupCal = new JPopupCalendar();
	SimpleDateFormat df = new SimpleDateFormat("EEE MM/dd/yy");

	public SimpleExample2() {

		// JTextField to show the selected date
		showDate.setPreferredSize(new Dimension(90, 20));
		showDate.setEditable(false);

		// JButton to trigger the JPopupCalendar
		selectDate.setText("date");
		selectDate.setPreferredSize(new Dimension(60, 20));
		selectDate.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent mouseEvent) {
				Component comp = mouseEvent.getComponent();
				int xCoord = 0;
				int yCoord = comp.getHeight();
				popupCal.show(comp, xCoord, yCoord);
			}
		});

		// popup Calendar:
		popupCal.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (popupCal.getDate() != null) {
					showDate.setText(df.format(popupCal.getDate().getTime()));
				}
			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});

		// main frame parameters
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Simple JPopupMenuTest");
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(showDate);
		getContentPane().add(selectDate);
	}

	public static void main(String[] args) {
		java.util.Locale.setDefault(java.util.Locale.GERMAN);
		System.out.println("First day of week: "
				+ java.util.Calendar.getInstance().getFirstDayOfWeek());
		SimpleExample2 simpleTest = new SimpleExample2();
		simpleTest.pack();
		simpleTest.setLocation(100, 100);
		simpleTest.setVisible(true);
	}

}
