package com.kylin.labs.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.*;
import javax.swing.*;

import com.kylin.labs.model.ReservationModel;
import com.kylin.labs.view.calendar.DaySelectorStyle;
import com.kylin.labs.view.calendar.JPopupCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = -801433318533014068L;
	
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu jMenuFile = new JMenu();
	JMenuItem jMenuFileExit = new JMenuItem();
	JPanel jPanel1 = new JPanel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JEditorPane jEditorPane1 = new JEditorPane();

	// calendar stuff
	Calendar popup2Date = Calendar.getInstance();
	JPopupCalendar popup1 = null;
	JPopupCalendar popup2 = null;

	SimpleDateFormat dateformat = new SimpleDateFormat("EEE MM/dd/yy");
	
	
	JComboBox<String> comb = new JComboBox<String>(new String[] {"Regular","Rewards"});
	
	JTextField startDate = new JTextField();
	JTextField endDate = new JTextField();
	
	JButton jSubmitButton = new JButton();
	
	private ReservationModel model;

	public MainFrame(ReservationModel model) {
		
		this.model = model;
		model.addObserver(this);
		
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void init() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(borderLayout1);
		setSize(new Dimension(450, 180));
		setTitle("Select Cheapest Hotel");
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		jMenuBar1.add(jMenuFile);
		jMenuFile.add(jMenuFileExit);
		contentPane.add(jPanel1, java.awt.BorderLayout.NORTH);
		
		comb.setSelectedIndex(0);
		
		startDate.setText("select start date");
		startDate.setMaximumSize(new Dimension(120, 30));
		startDate.setMinimumSize(new Dimension(120, 30));
		startDate.setPreferredSize(new Dimension(120, 30));
		startDate.setEditable(false);
		startDate.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) {
				Component comp = e.getComponent();
				int xCoord = 0;
				int yCoord = comp.getHeight();
				popup1.show(comp, xCoord, yCoord);
			}});
		
		endDate.setText("select end date");
		endDate.setMaximumSize(new Dimension(120, 30));
		endDate.setMinimumSize(new Dimension(120, 30));
		endDate.setPreferredSize(new Dimension(120, 30));
		endDate.setEditable(false);
		endDate.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) {
				Component comp = e.getComponent();
				int xCoord = 0;
				int yCoord = comp.getHeight();
				popup2.show(comp, xCoord, yCoord);
			}});
		
		jSubmitButton.setText("Submit");
		jSubmitButton.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e) {
				
				String type = comb.getSelectedItem().toString();
				Date startTime, endTime;
				
				try {
					startTime = dateformat.parse(startDate.getText());
					endTime = dateformat.parse(endDate.getText());
				} catch (ParseException e1) {
					startTime = new Date();
					endTime = new Date();
				}
				
				model.doPost(type, startTime, endTime);
			}
		});
		
		jPanel1.add(comb);
		jPanel1.add(startDate);
		jPanel1.add(endDate);
		jPanel1.add(jSubmitButton);

		// added a dummy JScrollPanel just for demo
		contentPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jEditorPane1);
		setJMenuBar(jMenuBar1);

		// set up the first popup menu - this one doesn't have a registered date
		popup1 = new JPopupCalendar(Locale.CHINA);
		popup1.setDaySelectorStyle(new CustomDaySelectorStyle());
		popup1.getDaySelectorStyle().setWeekdayNames(new String[] { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" });
		popup1.pack();

		// add a listener to JPopupCalendar. The little JTextField next to
		// the popup calendar button will show the selected date
		popup1.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (popup1.getDate() != null) {
					startDate.setText(dateformat.format(popup1.getDate().getTime()));
				}
			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});

		// setup the second popup menu - this one does have a registered date
		popup2 = new JPopupCalendar(Locale.CHINA);
		popup2.setDaySelectorStyle(new CustomDaySelectorStyle());
		popup2.getDaySelectorStyle().setWeekdayNames(new String[] { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" });
		popup2.pack();

		// add a listener to this popup menue too
		popup2.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (popup2.getDate() != null) {
					endDate.setText(dateformat.format(popup2.getDate().getTime()));
				}
			}

			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
	}
	
	public void update(Observable o, Object obj) {

		jEditorPane1.setText("");
		jEditorPane1.setText(model.getCheapestName());
	}
	

	private class CustomDaySelectorStyle extends DaySelectorStyle {
		
		protected void initOverride() {
		    dayLabelTemplate.setBackground(java.awt.Color.cyan);
		    dayLabelTemplate.setOpaque(true);
		    dayLabelTemplate.setPreferredSize(new Dimension(25, 25));
		  };
	}

	
	
}


