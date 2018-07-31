package tabExpert;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		JPanel panel_1 = new JPanel();
		
		JTextArea txtpnAcordeAtual = new JTextArea();
		
		JSplitPane commandPanel = new JSplitPane();
		JButton btnMudarversao = new JButton("mudarVersao");
		JButton btnMudarmusica = new JButton("mudarMusica");
		JPanel panel = new JPanel();
		JPanel panel_3 = new JPanel();
		Panel musicFull = new Panel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		
		JPanel jpanListaArquivos = new JPanel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		JList<String> list;
		
		
		
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(jpanListaArquivos, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(jpanListaArquivos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
		);

		
		JTextArea txtpnFullmusic = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txtpnFullmusic);
		
		
		GroupLayout gl_musicFull = new GroupLayout(musicFull);
		gl_musicFull.setHorizontalGroup(
			gl_musicFull.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
		);
		gl_musicFull.setVerticalGroup(
			gl_musicFull.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
		);
		
		CifraEmTexto cet = new CifraEmTexto();
		
		
		
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnAcordeAtual, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(commandPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtpnAcordeAtual, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(commandPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15))
		);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 10, 5));
		panel.setAlignmentX(100);
		
		panel.add(panel_1);
		
		commandPanel.setToolTipText("");
		commandPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		commandPanel.setLeftComponent(btnMudarversao);
		commandPanel.setRightComponent(btnMudarmusica);
		
		txtpnAcordeAtual.setText("Acordes Atuais");
		panel_1.setLayout(gl_panel_1);
		
		String[] nameList = cet.getCifras();
        list = new JList(nameList);
        jpanListaArquivos.add(list);
        jpanListaArquivos.setVisible(true);
		
		
		btnMudarversao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtpnAcordeAtual.setText("Mudei xD");
				cet.lerMusica(txtpnFullmusic , list.getSelectedValue().toString());
				
			}
			
		});
		
		btnMudarmusica.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Fui clicado");
			}
			
		});
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(gl_panel_3);
		
		frame.getContentPane().add(musicFull, BorderLayout.CENTER);
		musicFull.setLayout(gl_musicFull);
	    
		


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
	