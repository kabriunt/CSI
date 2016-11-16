package es.uca.gii.csi16.elrond.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;

import es.uca.gii.csi16.elrond.data.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrUsuario extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private Usuario _usuario = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IfrUsuario frame = new IfrUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IfrUsuario() {
		
		setClosable(true);
		setResizable(true);
		setTitle("Usuario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setBounds(12, 12, 70, 37);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(130, 10, 200, 39);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 79, 70, 37);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(130, 77, 200, 39);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 146, 90, 37);
		getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(130, 144, 200, 39);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (_usuario == null)
						_usuario = Usuario.Create(txtNombre.getText(), txtEmail.getText(), txtPassword.getText());
					else{
						_usuario.setNombre(txtNombre.getText());
						_usuario.setEmail(txtEmail.getText());
						_usuario.setPassword(txtPassword.getText());
						_usuario.Update();
					}
						
				} catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		
		butGuardar.setBounds(160, 231, 117, 25);
		getContentPane().add(butGuardar);
	}
}
