package userinterface;

import implementation.Labyrinth;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.UIManager;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * @author Szentpéteri Annamária
 *
 */
public class AppWindow {

	/** Main window. */
	private JFrame frmMain;
	/**	Used labyrinth. */
	private Labyrinth labyrinth = new Labyrinth();
	/** For logging purposes. */
	final static Logger logger = LoggerFactory.getLogger(AppWindow.class);
	private JTextField tfSave;
	private JTextField tfLoad;
	
	/**
	 * Launch the application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.info("Creating the application window.");
					
					AppWindow window = new AppWindow();
					window.frmMain.setVisible(true);
					window.frmMain.setResizable(false);
					
					logger.info("Application window created.");
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}
	
	/**
	 * @param panel the panel which contains the labyrinth
	 */
	private void drawLabyrinth(JPanel panel){
		logger.info("Drawing labyrinth starts.");
		
		panel.removeAll();
		
		/**
		 * Calculating the width and height of fields.
		 */
		int width = panel.getWidth() / labyrinth.getWidth();
		int height = panel.getHeight() / labyrinth.getHeight();
		
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
		
		logger.info("Drawing labyrinth done.");
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
		frmMain.getContentPane().setForeground(UIManager.getColor("CheckBox.background"));
		frmMain.setForeground(Color.GRAY);
		frmMain.setTitle("Labirintus generátor");
		frmMain.getContentPane().setBackground(UIManager.getColor("CheckBox.background"));
		frmMain.setBounds(100, 100, 740, 560);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		final JPanel pLabyrinth = new JPanel();
		pLabyrinth.setBackground(UIManager.getColor("CheckBox.background"));
		pLabyrinth.setBounds(210, 11, 500, 500);
		frmMain.getContentPane().add(pLabyrinth);
		pLabyrinth.setLayout(null);
		
		JPanel pSettings = new JPanel();
		pSettings.setBorder(new EmptyBorder(0, 0, 0, 0));
		pSettings.setBackground(SystemColor.scrollbar);
		pSettings.setBounds(25, 25, 148, 75);
		frmMain.getContentPane().add(pSettings);
		
		JLabel lblHeight = new JLabel("Magasság:");
		
		JLabel lblWidth = new JLabel("Szélesség:");
		
		final JSpinner spHeight = new JSpinner();
		spHeight.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		final JSpinner spWidth = new JSpinner();
		spWidth.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		JButton btnGenerate = new JButton("Generálás");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					logger.info("Generating new labyrinth starts.");
					
					Integer height = (Integer)(spHeight.getValue());
					Integer width = (Integer)(spWidth.getValue());
					
					labyrinth = new Labyrinth(width, height);
					labyrinth.Generate();
					
					drawLabyrinth(pLabyrinth);
					
					logger.info("New labyrinth generated.");
				} catch (Exception exept) {
					logger.error(exept.getMessage());
				}
			}
		});
		btnGenerate.setBounds(50, 150, 100, 40);
		frmMain.getContentPane().add(btnGenerate);
		
		JButton btnSave = new JButton("Mentés");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(50, 223, 100, 35);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				logger.info("Saving labyrinth starts.");
				
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(frmMain);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
					if (!labyrinth.SaveToJSON(chooser.getSelectedFile().getPath())){
						JOptionPane.showMessageDialog(frmMain, "Mentés sikertelen. A fájl nem módosítható vagy nem lehet létrehozni.");
						logger.error("Couldn't save labyrinth.");
					}
					else{
						logger.info("Saved with {}.", tfSave.getText());
					}
				}
				
				logger.info("Saving labyrinth ends.");
			}
		});		
		frmMain.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("Betöltés");
		btnLoad.setBounds(50, 279, 100, 35);
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				logger.info("Loading labyrinth starts.");
				
				JFileChooser chooser = new JFileChooser();
			    int returnVal = chooser.showOpenDialog(frmMain);
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
					if (labyrinth.LoadFromJSON(chooser.getSelectedFile().getPath())){
						drawLabyrinth(pLabyrinth);
						
						logger.info("Load with {}.", tfLoad.getText());
					}
					else{
						JOptionPane.showMessageDialog(frmMain, "Betöltés sikertelen. A fájl nem létezik, nem olvasható vagy nem megfelelő formátumú.");
						logger.error("Couldn't load labyrinth.");
					}
				}
				
				logger.info("Loading labyrinth ends.");
			}
		});
		frmMain.getContentPane().add(btnLoad);
		
		JButton btnExit = new JButton("Kilépés");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				logger.info("Exiting.");
				
				System.exit(0);
			}
		});
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExit.setBounds(50, 349, 100, 40);
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
