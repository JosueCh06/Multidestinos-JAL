package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mantenimiento.GestionHiloLoguin;
import mantenimiento.GestionUsuario;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblBienvenido;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JLabel lblUsuario;
	private JLabel lblContrasea;
	public static JButton btnIngresar;
	private JLabel lblSistemaDeRegistro;
	private JLabel lblEmpresaDeTaxis;
	private JLabel label_1;
	private JLabel label_2;
	public static JLabel lblElBotonSe;
	public static JLabel lbls;

	GestionUsuario gUser = new GestionUsuario();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Inicio de sesi\u00F3n ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 427);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.setOpaque(true);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setForeground(Color.WHITE);
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenido.setBackground(Color.BLACK);
		lblBienvenido.setBounds(10, 11, 487, 44);
		contentPane.add(lblBienvenido);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("\r\n");
		txtUsuario.setBounds(216, 220, 191, 36);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(216, 267, 191, 36);
		contentPane.add(txtContraseña);
		
		lblUsuario = new JLabel("USUARIO\r\n");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setOpaque(true);
		lblUsuario.setForeground(SystemColor.windowText);
		lblUsuario.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblUsuario.setBackground(SystemColor.activeCaption);
		lblUsuario.setBounds(86, 220, 120, 36);
		contentPane.add(lblUsuario);
		
		lblContrasea = new JLabel("CONTRASE\u00D1A\r\n");
		lblContrasea.setOpaque(true);
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblContrasea.setBackground(SystemColor.activeCaption);
		lblContrasea.setBounds(86, 267, 120, 36);
		contentPane.add(lblContrasea);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setHorizontalAlignment(SwingConstants.LEADING);
		btnIngresar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/login-square-arrow-button-outline_icon-icons.com_73220.png")));
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIngresar.setBounds(275, 314, 132, 36);
		contentPane.add(btnIngresar);
		
		lblSistemaDeRegistro = new JLabel("Sistema de registro de empleados - Todos los derechos reservados");
		lblSistemaDeRegistro.setFont(new Font("Sylfaen", Font.ITALIC, 10));
		lblSistemaDeRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeRegistro.setBounds(88, 363, 358, 14);
		contentPane.add(lblSistemaDeRegistro);
		
		lblEmpresaDeTaxis = new JLabel("\r\n");
		lblEmpresaDeTaxis.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresaDeTaxis.setIcon(new ImageIcon(Login.class.getResource("/imagenes/taxi_23733.png")));
		lblEmpresaDeTaxis.setBounds(186, 70, 132, 139);
		contentPane.add(lblEmpresaDeTaxis);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/1485477090-lock_78576.png")));
		label_1.setBounds(410, 267, 32, 36);
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Login.class.getResource("/imagenes/1485477097-avatar_78580.png")));
		label_2.setBounds(410, 220, 36, 36);
		contentPane.add(label_2);
		
		lblElBotonSe = new JLabel("El boton se activar\u00E1 en:");
		lblElBotonSe.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblElBotonSe.setBounds(50, 324, 137, 16);
		contentPane.add(lblElBotonSe);
		
		lbls = new JLabel("60s");
		lbls.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbls.setBounds(186, 324, 56, 16);
		contentPane.add(lbls);
		
		lblElBotonSe.setVisible(false);
		lbls.setVisible(false);
	}
	public void actionPerformed(final ActionEvent arg0) {
		if (arg0.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(arg0);
		}
	}
	protected void actionPerformedBtnIngresar(final ActionEvent arg0) {
		validarIngreso();
	}
	int intentos = 0;

	private void validarIngreso() {
		int usuario;
		String password;
		usuario=getUsuario();
		password=getPassword();
		
		if(usuario==-1 || password == null){
			mensaje("Error de autenticacion");
			if(usuario == -1){
				txtUsuario.setText("");
				txtUsuario.requestFocus();
			}else{
				txtContraseña.setText("");
				txtContraseña.requestFocus();
			}
		}else {
			//proceso
			Usuario u = gUser.validarIngreso(usuario, password);
			//salida
			if(u == null){
				intentos++;
				mensaje("Usuario y/o contraseña incorrectos"+"\nIntentos :"+intentos+"/3");
				if(intentos==3){
					btnIngresar.setEnabled(false);
					mostrarTiempo();
				}
				txtUsuario.setText("");
				txtUsuario.requestFocus();
				txtContraseña.setText("");
			}else{
				mensaje("Bienvenido al sistema");
				frmPrincipal frm = new frmPrincipal();
				frm.setVisible(true);
				frm.setLocationRelativeTo(this);
			    frm.lblBienvenida.setText("  Bienvenido : "+u.getNombre()+" "+u.getApellido()+"                 "
					+"¿Que haremos hoy?");
				dispose();
			}
		}		
	}

	private void mostrarTiempo() {
		//instanciar el hilo 
		GestionHiloLoguin hilo= new GestionHiloLoguin();
		//ejecutar
		hilo.start();
	}

	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

	private String getPassword() {
		String contraseña = null;
		if(String.valueOf(txtContraseña.getPassword()).trim().length()==0){
			mensaje("Campo vacio \nIngrese su contraseña");
		}else if(!String.valueOf(txtContraseña.getPassword()).matches("[A-Z a-z 0-9 áéíóú]{1,3}")){
			mensaje("La contrseña debe contener entre 8 y 45 caracteres");
			txtContraseña.setText("");
		}else{
			contraseña = String.valueOf(txtContraseña.getPassword());
		}
		
		return contraseña;
	}

	private int getUsuario() {
		int usuario = -1;
		if(txtUsuario.getText().trim().length()==0){
			mensaje("Campo vacio \nIngrese su usuario");
		}else if(!txtUsuario.getText().matches("[0-9]{1,}")){
			mensaje("Parece que ha ingresado un usuario con formato incorrecto"
					+"\n -Ingrese solo números");
		}else{
			usuario = Integer.parseInt(txtUsuario.getText());
		}
		return usuario;
	}
}
