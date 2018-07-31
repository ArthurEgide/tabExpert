package tabExpert;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstGUI window = new FirstGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 10, 5));
		panel.setAlignmentX(100);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JPanel acordesAtuais = new JPanel();
		FlowLayout fl_acordesAtuais = (FlowLayout) acordesAtuais.getLayout();
		fl_acordesAtuais.setVgap(24);
		fl_acordesAtuais.setHgap(150);
		fl_acordesAtuais.setAlignment(FlowLayout.LEFT);
		panel_1.add(acordesAtuais);
		
		JSplitPane commandPanel = new JSplitPane();
		commandPanel.setToolTipText("");
		panel_1.add(commandPanel);
		commandPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		JButton btnMudarversao = new JButton("mudarVersao");
		JButton btnMudarmusica = new JButton("mudarMusica");
		commandPanel.setLeftComponent(btnMudarversao);
		commandPanel.setRightComponent(btnMudarmusica);

		btnMudarversao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Mudar Versão");
			}
			
		});
		
		btnMudarmusica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Mudar Musica");
			}
			
		});
		
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		
		Panel acordesShape = new Panel();
		FlowLayout fl_acordesShape = (FlowLayout) acordesShape.getLayout();
		fl_acordesShape.setVgap(95);
		fl_acordesShape.setHgap(32);
		panel_3.add(acordesShape);
		
		Panel musicFull = new Panel();
		frame.getContentPane().add(musicFull, BorderLayout.CENTER);
	    

	}
}
