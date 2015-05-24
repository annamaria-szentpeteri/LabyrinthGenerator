package userinterface;

import implementation.Labyrinth;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SpinnerNumberModel;

public class AppWindow {

	private JFrame frmMain;
	private Labyrinth labyrinth;

	/**
	 * Launch the application.
	 * 
	 * @param args sdfsd
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
	
	
	/**
	 * @param visibility sdf
	 */
	public void setVisible(boolean visibility){
		frmMain.setVisible(visibility);
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
		
		JPanel pSettings = new JPanel();
		pSettings.setBorder(new EmptyBorder(0, 0, 0, 0));
		pSettings.setBackground(SystemColor.scrollbar);
		pSettings.setBounds(10, 22, 148, 75);
		frmMain.getContentPane().add(pSettings);
		
		JLabel lblHeight = new JLabel("Magasság:");
		
		JLabel lblWidth = new JLabel("Szélesség:");
		
		final JSpinner spHeight = new JSpinner();
		spHeight.setModel(new SpinnerNumberModel(1, 1, 150, 1));
		
		final JSpinner spWidth = new JSpinner();
		spWidth.setModel(new SpinnerNumberModel(1, 1, 125, 1));
		
		JButton btnGenerate = new JButton("Generálás");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* TODO */
				/* Hívja meg a labirintus generálást:
				 *   - generáljon labirintust
				 *   - töltse fel a pLabyrinth-t a generált labirintus alapján
				 *   - tárolja le valahogy a labirintust
				 *     (na de hogy? Üzleti logika váljon el minél jobban ugyebár!)
				 *   Kirakni az egészet egy külön mainbe?? JÓ ÖTLET! */
				/* Itt lehetne loggolást használni arra hogy lássam gombnyomáskor tényleg
				 * meghívódik ez a függvény! */
				try {
					Integer height = (Integer)(spHeight.getValue());
					Integer width = (Integer)(spWidth.getValue());
					
					labyrinth = new Labyrinth(height, width);
					labyrinth.Generate();
				} catch (Exception exept) {
					exept.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(30, 117, 101, 23);
		frmMain.getContentPane().add(btnGenerate);
		
		JButton btnSave = new JButton("Mentés");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* TODO */
				/* Hívja meg a labirintus mentést:
				 * */
				/* Itt lehetne loggolást használni arra hogy lássam gombnyomáskor tényleg
				 * meghívódik ez a függvény! */
			}
		});		
		btnSave.setBounds(30, 165, 101, 23);
		frmMain.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Betöltés");
		btnLoad.setBounds(30, 199, 101, 23);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* TODO */
				/* Hívja meg a labirintus betöltést:
				 * */
				/* Itt lehetne loggolást használni arra hogy lássam gombnyomáskor tényleg
				 * meghívódik ez a függvény! */
			}
		});
		frmMain.getContentPane().add(btnLoad);
		
		JButton btnExit = new JButton("Kilépés");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setBounds(30, 253, 101, 23);
		frmMain.getContentPane().add(btnExit);
		
		JPanel pLabyrinth = new JPanel();
		pLabyrinth.setBounds(174, 11, 350, 300);
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
		 * 
		 * ALTERNATÍVA
		 * 
		 * Canvas használata
		 * Canvas-ból származtatsz egy saját osztályt
		 * ( meg UI kódban átírod h azt példányosítsa )
		 * a paint metódust override-olod
		 * az kap argumentumnak egy Graphics példányt
		 * azzal lehet rajzolni, vagy kasztolhatod Graphics2D-re
		 * 
		 * Ekkor a jelenleg elképzelt adatszerkezeten kényelmesen
		 * végig tudnék menni.
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
		
		Canvas canvas = new Canvas();
		canvas.setBounds(68, 64, 100, 100);
		pLabyrinth.add(canvas);
		/*
		 * #MINTA-END#
		 * 
		 * 
		 * */		
		
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
