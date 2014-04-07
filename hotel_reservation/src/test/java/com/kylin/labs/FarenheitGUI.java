package com.kylin.labs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class FarenheitGUI extends TemperatureGUI{

	public FarenheitGUI(TemperatureModel model, int h, int v) {
		super("Farenheit Temperature", model, h, v);
		setDisplay(""+model.getF());
		addUpListener(new UpListener());
		addDownListener(new DownListener());
		addDisplayListener(new DisplayListener());
	}

	public void update(Observable o, Object obj) {
		
		System.out.println(o);
		System.out.println(obj);
		
		setDisplay("" + model().getF());
	}
	
	class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setF(model().getF() + 1.0);
		}
	}

	class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			model().setF(model().getF() - 1.0);
		}
	}

	class DisplayListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double value = getDisplay();
			model().setF(value);
		}
	}

}
