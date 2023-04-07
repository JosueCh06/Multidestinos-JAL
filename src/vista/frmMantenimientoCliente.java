package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionCliente;
import model.Cliente;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class frmMantenimientoCliente extends JDialog implements ActionListener, KeyListener, MouseListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTable tblCliente;
	private JScrollPane scrollPane;
	private JLabel lblMantenimientoCliente;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblCelular;
	private JLabel lblCorreo;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtCelular;
	private JTextField txtCorreo;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	GestionCliente gCli = new GestionCliente();
	
	boolean tipo = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmMantenimientoCliente dialog = new frmMantenimientoCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmMantenimientoCliente() {
		setTitle("Mantenimiento de Clientes");
		setModal(true);
		setBounds(100, 100, 780, 590);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(618, 325, 136, 48);
		btnNuevo.setIcon(new ImageIcon(frmMantenimientoCliente.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(618, 379, 136, 48);
		btnGuardar.setIcon(new ImageIcon(frmMantenimientoCliente.class.getResource("/imagenes/guardar.png")));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(618, 435, 136, 48);
		btnEliminar.setIcon(new ImageIcon(frmMantenimientoCliente.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(618, 491, 136, 48);
		btnCancelar.setIcon(new ImageIcon(frmMantenimientoCliente.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnCancelar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 515, 445);
		contentPanel.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.addMouseListener(this);
		tblCliente.addKeyListener(this);
		tblCliente.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCliente);
		
		lblMantenimientoCliente = new JLabel("Mantenimiento Cliente");
		lblMantenimientoCliente.setOpaque(true);
		lblMantenimientoCliente.setForeground(Color.WHITE);
		lblMantenimientoCliente.setBackground(Color.BLACK);
		lblMantenimientoCliente.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMantenimientoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoCliente.setBounds(10, 21, 744, 48);
		contentPanel.add(lblMantenimientoCliente);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(540, 107, 69, 14);
		contentPanel.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(540, 138, 69, 14);
		contentPanel.add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellido.setBounds(540, 170, 69, 14);
		contentPanel.add(lblApellido);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDni.setBounds(540, 200, 69, 14);
		contentPanel.add(lblDni);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular.setBounds(540, 230, 69, 14);
		contentPanel.add(lblCelular);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(540, 260, 69, 14);
		contentPanel.add(lblCorreo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setBounds(623, 105, 131, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(623, 136, 131, 20);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(623, 167, 131, 20);
		contentPanel.add(txtApellido);
		
		txtDNI = new JTextField();
		txtDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDNI.setColumns(10);
		txtDNI.setBounds(623, 197, 131, 20);
		contentPanel.add(txtDNI);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCelular.setColumns(10);
		txtCelular.setBounds(623, 227, 131, 20);
		contentPanel.add(txtCelular);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(623, 257, 131, 20);
		contentPanel.add(txtCorreo);
		
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("DNI");
		modelo.addColumn("Celular");
		modelo.addColumn("Correo");
		tblCliente.setModel(modelo);
		
		cargarData();
		mostrarData(0);
	}
	private void mostrarData(int fila) {
		if(fila != -1) {
			tipo=false;
			String  idCliente,nombre,apellido,dni,celular,correo;
			idCliente = modelo.getValueAt(fila, 0).toString();
			nombre = modelo.getValueAt(fila, 1).toString();
			apellido=modelo.getValueAt(fila, 2).toString();
			dni = modelo.getValueAt(fila, 3).toString();
			celular = modelo.getValueAt(fila, 4).toString();
			correo = modelo.getValueAt(fila, 5).toString();
			
			txtCodigo.setText(idCliente);
			txtNombre.setText(nombre);
			txtApellido.setText(apellido);
			txtDNI.setText(dni);
			txtCelular.setText(celular);
			txtCorreo.setText(correo);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		dispose();
	}

	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		nuevoCliente();
	}

	private void nuevoCliente() {
		txtCodigo.setText("");
		txtCodigo.setEditable(false);
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtCelular.setText("");
		txtCorreo.setText("");
		txtNombre.requestFocus();
		tipo = true; 
	}
	
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		if(tipo == false){
			actualizarCliente();
		}else{
			registrarCliente();
		}
		cargarData();
	}

	private void registrarCliente() {
		
		String nombre,apellido,dni,celular,correo;
		
		nombre=getNombre();
		apellido=getApellido();
		dni=getDni();
		celular=getCelular();
		correo=getCorreo();

		if(nombre == null || apellido== null || dni==null || celular==null || correo == null){
			mensaje("No se registro al cliente!!!");
		}else{

		Cliente c = new Cliente();
		 
		 c.setNombre(nombre);
		 c.setApellido(apellido);
		 c.setDNI(dni);
		 c.setCelular(celular);
		 c.setCorreo(correo);
		 
		 int ok = gCli.registrar(c);
		 if(ok == 0 )
			 mensaje("Error en el registro!!!");
		 else
			 mensaje("Registro OK");
	
		}
	}

	private String getCelular() {
		String cel = null;
		if(txtCelular.getText().trim().length() == 0) {
			mensaje("Ingrese número de celular!!");
		}
		else if(txtCelular.getText().trim().matches("[\\d]{9}")) {
			cel = txtCelular.getText().trim();
		} 
		else {
			mensaje("Número de celular no válido!! Ej. 934657890");
		}
		return cel;
	}

	private String getCorreo() {
		String correo = null;
		if(txtCorreo.getText().trim().length()==0) {
			mensaje("Ingresar correo!!!");
		} else if(txtCorreo.getText().trim().matches("[a-zA-Z_0-9]{1,64}@[a-zA-Z_0-9]{1,63}[.][a-zA-Z]{2,63}|[a-zA-Z_0-9]{1,64}@[a-zA-Z_0-9]{1,63}[.][a-zA-Z]{2,10}[.][a-zA-Z]{2,10}")) {
			correo = txtCorreo.getText().trim();
		} else {
			mensaje("Correo no válido Ej. jpcueva@hotmail.com o agmiran@gmail.com");
		}
		return correo;	
	}

	private String getDni() {
		String dni = null;
		if(txtDNI.getText().trim().length() == 0) {
			mensaje("Ingrese DNI!!");
		}
		else if(txtDNI.getText().trim().matches("[\\d]{8}")) {
			dni = txtDNI.getText().trim();
		} 
		else {
			mensaje("DNI no válido!! Ej. 73456789");
		}
		return dni;
	}

	private String getApellido() {
		String ape = null;
		if(txtApellido.getText().trim().length() == 0) {
			mensaje("Ingrese apellido!!");
		} 
		else if(txtApellido.getText().trim().matches("[a-zA-Z \\s]{3,25}")) {
			ape = txtApellido.getText().trim();
		}
		else {
			mensaje("Ingrese solo letras(3 a 25 caracteres)!!!");
		}
		return ape;
	}

	private String getNombre() {
		String nom = null;
		if(txtNombre.getText().trim().length() == 0) {
			mensaje("Ingrese nombre!!");
		} 
		else if(txtNombre.getText().trim().matches("[a-zA-Z \\s]{3,25}")) {
			nom = txtNombre.getText().trim();
		}
		else {
			mensaje("Ingrese solo letras(3 a 25 caracteres)!!!");
		}
		return nom;
	}

	private void actualizarCliente() {

		String nombre,apellido,dni,celular,correo;
		int idCliente;
	
		idCliente=getIdCliente();
		nombre=getNombre();
		apellido=getApellido();
		dni=getDni();
		celular=getCelular();
		correo=getCorreo();

		if(nombre == null || apellido== null || dni==null || celular==null || correo == null){
			return;
		}else{

		Cliente c = new Cliente();
		 
		 c.setNombre(nombre);
		 c.setApellido(apellido);
		 c.setDNI(dni);
		 c.setCelular(celular);
		 c.setCorreo(correo);
		 c.setIdCliente(idCliente);
		 
		 int ok = gCli.actualizar(c);
		 if(ok == 0 )
			 mensaje("Error en la actualización ");
		 else
			 mensaje("Datos Actualizados");
	
		}
	}

	private int getIdCliente() {
		int cod = -1;
		if(txtCodigo.getText().trim().length() == 0) {
			mensaje("Ingrese código!!!");
		} 
		else if(txtCodigo.getText().trim().matches("[\\d]+")) {
			cod = Integer.parseInt(txtCodigo.getText().trim());
		}
		else {
			mensaje("Código solo acepta números!!");
		}
		return cod;
	}

	private void cargarData() {
		modelo.setRowCount(0);
		
		ArrayList<Cliente> lista= gCli.listar();
		
		for(Cliente cliente:lista){
			Object fila[]={cliente.getIdCliente(),cliente.getNombre(),cliente.getApellido(),
						   cliente.getDNI(),cliente.getCelular(),cliente.getCorreo()	
			};
			modelo.addRow(fila);
		}
	}
	
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int idEmpleado;
		idEmpleado = getIdCliente();
		
		if(idEmpleado == -1) {
			mensaje("No se eliminó Cliente!!");
		}
		else {
			int ok = gCli.eliminar(idEmpleado);
			if(ok == 0) {
				mensaje("Error al eliminar!!!");
			} else {
				mensaje("Cliente eliminado!!");
				nuevoCliente();
				cargarData();
			}
		}
	}
	
	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string, "Alerta!!", 2);
	}
	public void keyPressed(final KeyEvent arg0) {
	}
	public void keyReleased(final KeyEvent arg0) {
		if (arg0.getSource() == tblCliente) {
			keyReleasedTblCliente(arg0);
		}
	}
	public void keyTyped(final KeyEvent arg0) {
	}
	protected void keyReleasedTblCliente(final KeyEvent arg0) {
		int fila;
		fila=tblCliente.getSelectedRow();
		mostrarData(fila);
	}
	public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getSource() == tblCliente) {
			mouseClickedTblCliente(arg0);
		}
	}
	public void mouseEntered(final MouseEvent arg0) {
	}
	public void mouseExited(final MouseEvent arg0) {
	}
	public void mousePressed(final MouseEvent arg0) {
	}
	public void mouseReleased(final MouseEvent arg0) {
	}
	protected void mouseClickedTblCliente(final MouseEvent arg0) {
		int fila;
		fila=tblCliente.getSelectedRow();
		mostrarData(fila);
	}
}
