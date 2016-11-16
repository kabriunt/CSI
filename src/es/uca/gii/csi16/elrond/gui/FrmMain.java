package es.uca.gii.csi16.elrond.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public FrmMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestion elrond");
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mitNuevo = new JMenu("Nuevo");
		menuBar.add(mitNuevo);
		
		JMenuItem mitNuevoUsuario = new JMenuItem("Usuario");
		mitNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IfrUsuario ifrUsuario = new IfrUsuario();
				ifrUsuario.setBounds(10, 27, 400, 300);
				frame.getContentPane().add(ifrUsuario);
				ifrUsuario.setVisible(true);
			}
		});
		mitNuevo.add(mitNuevoUsuario);
		
		JMenu mitBuscar = new JMenu("Buscar");
		menuBar.add(mitBuscar);
		
		JMenuItem mitBuscarUsuario = new JMenuItem("Usuario");
		mitBuscar.add(mitBuscarUsuario);
	}
}
