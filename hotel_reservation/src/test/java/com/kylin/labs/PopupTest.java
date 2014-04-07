package com.kylin.labs;

import java.awt.Toolkit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.kylin.labs.model.ReservationModel;
import com.kylin.labs.view.MainFrame;

import java.awt.Dimension;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PopupTest {
    boolean packFrame = false;

    /**
     * Construct and show the application.
     */
    public PopupTest() {
        MainFrame frame = new MainFrame(new ReservationModel());
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    /**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new PopupTest();
            }
        });
    }



}
