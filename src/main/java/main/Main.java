package main;
/**
 * 
 */


import java.awt.EventQueue;

import userinterface.AppWindow;

/**
 * @author Mari
 *
 */
public class Main {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
