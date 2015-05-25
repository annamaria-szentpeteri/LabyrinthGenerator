package userinterface;

import implementation.Labyrinth;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private Labyrinth labyrinth = new Labyrinth();

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
	 * @param panel
	 */
	private void drawLabyrinth(JPanel panel){
		panel.removeAll();
		
		/**
		 * Calculating the width and height of fields.
		 */
		int width = panel.getWidth() / labyrinth.getWidth();
		int height = panel.getHeight() / labyrinth.getHeight();
		
//		/**
//		 * Resizing the panel to fit the fields.
//		 */
//		int pWidth = panel.getWidth() - (panel.getWidth() % labyrinth.getWidth());
//		int pHeight = panel.getHeight() - (panel.getHeight() % labyrinth.getHeight());
//		panel.setBounds(panel.getX(), panel.getY(), pWidth, pHeight);
		
		/**
		 * Generating dynamically the fields.
		 */
		for (int y = 0; y < labyrinth.getHeight(); y++){
			for (int x = 0; x < labyrinth.getWidth(); x++){
				JPanel fieldpanel = new JPanel();				
				fieldpanel.setBounds(x*width, y*height, width, height);
				
				ArrayList<Integer> borders = new ArrayList<Integer>();
				
				for (Boolean border: labyrinth.getFieldBorders(x, y)){
					if(border){
						borders.add(1);
					}
					else{
						borders.add(0);
					}
				}
				
				fieldpanel.setBorder(new MatteBorder(borders.get(0), borders.get(1), borders.get(2), borders.get(3),
						             (Color) new Color(0, 0, 0))
				                    );
				
				fieldpanel.setVisible(true);
				panel.add(fieldpanel);
			}
		}
		
		panel.repaint();
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
		
		final JPanel pLabyrinth = new JPanel();
		pLabyrinth.setBounds(174, 11, 350, 300);
		frmMain.getContentPane().add(pLabyrinth);
		pLabyrinth.setLayout(null);
		
		JPanel pSettings = new JPanel();
		pSettings.setBorder(new EmptyBorder(0, 0, 0, 0));
		pSettings.setBackground(SystemColor.scrollbar);
		pSettings.setBounds(10, 22, 148, 75);
		frmMain.getContentPane().add(pSettings);
		
		JLabel lblHeight = new JLabel("Magasság:");
		
		JLabel lblWidth = new JLabel("Szélesség:");
		
		final JSpinner spHeight = new JSpinner();
		spHeight.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		
		final JSpinner spWidth = new JSpinner();
		spWidth.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		
		JButton btnGenerate = new JButton("Generálás");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Integer height = (Integer)(spHeight.getValue());
					Integer width = (Integer)(spWidth.getValue());
					
					labyrinth = new Labyrinth(width, height);
					labyrinth.Generate();
					
					drawLabyrinth(pLabyrinth);
				} catch (Exception exept) {
					exept.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(30, 117, 101, 23);
		frmMain.getContentPane().add(btnGenerate);
		
		JButton btnSave = new JButton("Mentés");
		btnSave.setBounds(30, 165, 101, 23);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!labyrinth.SaveToFile()){
					// TODO 
					// Felugró ablak az üzenettel: "Mentés sikertelen. A fájlt nem módosítható vagy nem lehet létrehozni."
					JOptionPane.showMessageDialog(frmMain, "Mentés sikertelen. A fájlt nem módosítható vagy nem lehet létrehozni.");
				}
			}
		});		
		frmMain.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Betöltés");
		btnLoad.setBounds(30, 199, 101, 23);
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (labyrinth.LoadFromFile()){
					drawLabyrinth(pLabyrinth);
				}
				else{
					// TODO 
					// Felugró ablak az üzenettel: "Betöltés sikertelen. A fájl nem létezik vagy nem olvasható."
					JOptionPane.showMessageDialog(frmMain, "Betöltés sikertelen. A fájl nem létezik vagy nem olvasható.");
				}
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
