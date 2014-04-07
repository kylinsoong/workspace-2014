package com.kylin.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.kylin.labs.view.Popup;

public class Runner {

	public static void main(String[] args) {
		
		if(args.length == 1) {
			try {
				if(args[0].equals("-c")) {
					cliRunning();
				} else if (args[0].equals("-u")) {
					uiRunning();
				} else if (args[0].equals("-h")) {
					help();
				}else {
					help();
				}
			} catch (Exception e) {
				e.printStackTrace();
				help();
			}
		
		} else {
			cliRunning();
		}
		
	}

	private static void uiRunning() {

		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
				}
				new Popup();
			}
		});
	}

	private static void cliRunning() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("        Select Cheapest Hotel");
		try {
			while(true) {
				System.out.print("INPUT: ");
				String input = br.readLine();
				System.out.println("OUTPUT: " + Reservation.getInstance().getCheapest(input));
			}
		} catch (Exception e) {
			help();
		}
	}

	private static void help() {
		System.out.println();
		System.out.println("Usage: hotel-reservation.jar [args...]");
		System.out.println("    -u    " + "Run hotel-reservation.jar with UI, for example: java -jar hotel-reservation.jar -u, after the application started, in the poped Windows, ");
		System.out.println("                0. Select a Customer Type");
		System.out.println("                1. Select a started Date");
		System.out.println("                2. Select a ended Date");
		System.out.println("                3. Click the Submit Button");
		System.out.println();
		System.out.println("    -c    " + "Run hotel-reservation.jar with Command Line, for example: java -jar hotel-reservation.jar -c, after the application started, set the input like:");
		System.out.println("                Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)");
		System.out.println("                Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)");
		System.out.println("                Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)");
		Runtime.getRuntime().exit(0);
	}

}
