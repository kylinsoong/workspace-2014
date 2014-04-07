package com.kylin.labs.calendar;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import com.kylin.labs.view.calendar.JPopupCalendar;

import java.awt.FlowLayout;
import java.util.GregorianCalendar;

public class SimpleExample1 extends JFrame {
	JButton selectDate = new JButton();
	JButton submit = new JButton();
	JPopupCalendar popupCal = new JPopupCalendar();
	SimpleDateFormat df = new SimpleDateFormat("EEE MM/dd/yy");

	public SimpleExample1() {
		// popupCal.SAVE_SELECTED_DATE = false;
		// popupCal.RESET_AT_SHOW = true;
		popupCal.setDate(new GregorianCalendar(2006, 1, 1));

		// JButton to trigger the JPopupCalendar
		selectDate.setText("Select Date");
		selectDate.setPreferredSize(new Dimension(120, 20));
		selectDate.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent mouseEvent) {
				Component comp = mouseEvent.getComponent();
				int xCoord = 0;
				int yCoord = comp.getHeight();
				popupCal.show(comp, xCoord, yCoord);
			}
		});

		// JButton to trigger the JPopupCalendar
		submit.setText("Submit");
		submit.setPreferredSize(new Dimension(80, 20));
		submit.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent mouseEvent) {
				if (popupCal.getDate() != null) {
					JOptionPane.showMessageDialog(
							null,
							"Date Selected: "
									+ df.format(popupCal.getDate().getTime()),
							"Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No Date Selected!",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// main frame parameters
		// popupCal.forceSerializeCopy(true);
		// popupCal.packInThread();
		popupCal.pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Example 1");
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(selectDate);
		getContentPane().add(submit);
	}

	public static void main(String[] args) {
		SimpleExample1 example = new SimpleExample1();
		example.pack();
		example.setLocation(100, 100);
		example.setVisible(true);
	}
}
