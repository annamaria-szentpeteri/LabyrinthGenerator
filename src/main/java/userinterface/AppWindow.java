package userinterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.border.MatteBorder;

public class AppWindow {

	private JFrame frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmMain.setForeground(Color.GRAY);
		frmMain.setTitle("Labirintus generátor");
		frmMain.getContentPane().setBackground(SystemColor.activeCaptionBorder);
		frmMain.setBounds(100, 100, 551, 360);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JButton btnGenerate = new JButton("Generálás");
		btnGenerate.setBounds(30, 117, 101, 23);
		frmMain.getContentPane().add(btnGenerate);
		
		JButton btnSave = new JButton("Mentés");
		btnSave.setBounds(30, 165, 101, 23);
		frmMain.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Betöltés");
		btnLoad.setBounds(30, 199, 101, 23);
		frmMain.getContentPane().add(btnLoad);
		
		JButton btnExit = new JButton("Kilépés");
		btnExit.setBounds(30, 253, 101, 23);
		frmMain.getContentPane().add(btnExit);
		
		JPanel pLabyrinth = new JPanel();
		pLabyrinth.setBounds(174, 11, 351, 300);
		frmMain.getContentPane().add(pLabyrinth);
		pLabyrinth.setLayout(null);

		/*
		 * #MINTA-START#
		 * Ezt dinamikusan kell majd generálni a visszaadott labirintus alapján.
		 * 
		 * Be kell állítani a pLabyrinth méretét (x pixel / mező) és ez alapján
		 * létrehozni a pLabyrinth tagjaiként további JPaneleket, amiknek a
		 * borderjét és helyzetét megfelelően be kell állítani.
		 * 
		 * Mindezt a Labyrinth.java oldaláról úgy old meg, hogy ha az változna
		 * a grafikus felület ezen generálós részét ne kelljen változtatni.
		 * Vagyis kell a Labyrinth.java-nak egy olyan metódus, ami bemenetként
		 * megkapja, hogy melyik mezőről (sor, oszlop koordináta) kéri le a 4 
		 * fal információt (top, left, bottom, right) és ezt adja vissza.
		 * (0 - van fal, 1 - nincs fal) 
		 * */
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(0, 0, 10, 10);
		pLabyrinth.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(2, 0, 2, 2, (Color) new Color(0, 0, 0)));
		panel_3.setBounds(10, 0, 10, 10);
		pLabyrinth.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(2, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(20, 0, 10, 10);
		pLabyrinth.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(2, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_5.setBounds(30, 0, 10, 10);
		pLabyrinth.add(panel_5);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 2, 0, 2, (Color) new Color(0, 0, 0)));
		panel.setBounds(0, 10, 10, 10);
		pLabyrinth.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 10, 10, 10);
		pLabyrinth.add(panel_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		panel_6.setBounds(20, 10, 10, 10);
		pLabyrinth.add(panel_6);
		/*
		 * #MINTA-END#
		 * */		
	
		JPanel pSettings = new JPanel();
		pSettings.setBorder(new EmptyBorder(0, 0, 0, 0));
		pSettings.setBackground(SystemColor.scrollbar);
		pSettings.setBounds(10, 22, 148, 75);
		frmMain.getContentPane().add(pSettings);
		
		JLabel lblHeight = new JLabel("Magasság:");
		
		JLabel lblWidth = new JLabel("Szélesség:");
		
		JSpinner spHeight = new JSpinner();
		
		JSpinner spWidth = new JSpinner();
		
		GroupLayout gl_pSettings = new GroupLayout(pSettings);
		gl_pSettings.setHorizontalGroup(
			gl_pSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pSettings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pSettings.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblWidth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblHeight, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pSettings.createParallelGroup(Alignment.LEADING, false)
						.addComponent(spWidth)
						.addComponent(spHeight, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		gl_pSettings.setVerticalGroup(
			gl_pSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pSettings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pSettings.createParallelGroup(Alignment.LEADING, false)
						.addComponent(spHeight, Alignment.TRAILING)
						.addComponent(lblHeight, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
					.addGroup(gl_pSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pSettings.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWidth, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_pSettings.createSequentialGroup()
							.addGap(11)
							.addComponent(spWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		pSettings.setLayout(gl_pSettings);
	}
}
