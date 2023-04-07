package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import mantenimiento.GestionCargo;
import mantenimiento.GestionEmpleado;
import mantenimiento.GestionSede;
import mantenimiento.GestionTurno;
import model.Cargo;
import model.Empleado;
import model.ListaEmpleado;
import model.Sede;
import model.Turno;

import java.awt.SystemColor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class frmMantenimientoEmpleado extends JDialog implements ActionListener, MouseListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JTable tblEmpleado;
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
	private JTextField txtCorreo;
	private JTextField txtCelular;
	private JLabel lblSueldo;
	private JLabel lblFechaIng;
	private JLabel lblSede;
	private JLabel lblCargo;
	private JLabel lblTurno;
	private JTextField txtSueldo;
	private JComboBox cboSede;
	private JComboBox cboCargo;
	private JComboBox cboTurno;
	private JDateChooser dtcFechaIng;
	private JButton btnEliminar;
	private JButton btnNuevo;
	private JButton btnGuardar;
	
	DefaultTableModel modelo = new DefaultTableModel();
	GestionEmpleado gEmp = new GestionEmpleado();
	GestionCargo gCargo = new GestionCargo();
	GestionSede gSede = new GestionSede();
	GestionTurno gTurno = new GestionTurno();
	Boolean tipo = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmMantenimientoEmpleado dialog = new frmMantenimientoEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmMantenimientoEmpleado() {
		setTitle("Mantenimiento de Empleados");
		setModal(true);
		setBounds(100, 100, 780, 729);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(415, 269, 136, 48);
		btnEliminar.setIcon(new ImageIcon(frmMantenimientoEmpleado.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(594, 269, 136, 48);
		btnCancelar.setIcon(new ImageIcon(frmMantenimientoEmpleado.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPanel.add(btnCancelar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 335, 740, 341);
		contentPanel.add(scrollPane);
		
		tblEmpleado = new JTable();
		tblEmpleado.addKeyListener(this);
		tblEmpleado.addMouseListener(this);
		tblEmpleado.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblEmpleado);
		
		lblMantenimientoCliente = new JLabel("Mantenimiento Empleado");
		lblMantenimientoCliente.setOpaque(true);
		lblMantenimientoCliente.setForeground(Color.WHITE);
		lblMantenimientoCliente.setBackground(Color.BLACK);
		lblMantenimientoCliente.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMantenimientoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoCliente.setBounds(10, 21, 744, 48);
		contentPanel.add(lblMantenimientoCliente);
		
		lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigo.setBounds(13, 102, 69, 14);
		contentPanel.add(lblCodigo);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(276, 103, 69, 14);
		contentPanel.add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblApellido.setBounds(540, 105, 69, 14);
		contentPanel.add(lblApellido);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDni.setBounds(13, 144, 69, 14);
		contentPanel.add(lblDni);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCelular.setBounds(540, 145, 69, 14);
		contentPanel.add(lblCelular);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(275, 143, 69, 14);
		contentPanel.add(lblCorreo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCodigo.setBounds(91, 100, 135, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNombre.setColumns(10);
		txtNombre.setBounds(354, 101, 135, 20);
		contentPanel.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtApellido.setColumns(10);
		txtApellido.setBounds(618, 102, 135, 20);
		contentPanel.add(txtApellido);
		
		txtDNI = new JTextField();
		txtDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDNI.setColumns(10);
		txtDNI.setBounds(91, 141, 136, 20);
		contentPanel.add(txtDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(353, 141, 136, 20);
		contentPanel.add(txtCorreo);
		
		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCelular.setColumns(10);
		txtCelular.setBounds(618, 142, 136, 20);
		contentPanel.add(txtCelular);
		
		lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSueldo.setBounds(13, 185, 69, 14);
		contentPanel.add(lblSueldo);
		
		lblFechaIng = new JLabel("Fecha Ing:");
		lblFechaIng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaIng.setBounds(276, 185, 100, 14);
		contentPanel.add(lblFechaIng);
		
		lblSede = new JLabel("Sede:");
		lblSede.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSede.setBounds(540, 185, 69, 14);
		contentPanel.add(lblSede);
		
		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCargo.setBounds(13, 229, 69, 14);
		contentPanel.add(lblCargo);
		
		lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTurno.setBounds(276, 229, 69, 14);
		contentPanel.add(lblTurno);
		
		txtSueldo = new JTextField();
		txtSueldo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSueldo.setColumns(10);
		txtSueldo.setBounds(91, 183, 136, 20);
		contentPanel.add(txtSueldo);
		
		cboSede = new JComboBox();
		cboSede.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboSede.setBounds(618, 183, 136, 20);
		contentPanel.add(cboSede);
		
		cboCargo = new JComboBox();
		cboCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboCargo.setBounds(90, 227, 136, 20);
		contentPanel.add(cboCargo);
		
		cboTurno = new JComboBox();
		cboTurno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboTurno.setBounds(353, 227, 136, 20);
		contentPanel.add(cboTurno);
		
		dtcFechaIng = new JDateChooser();
		dtcFechaIng.setBounds(354, 182, 135, 20);
		contentPanel.add(dtcFechaIng);
		
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("DNI");
		modelo.addColumn("Correo");
		modelo.addColumn("Celular");
		modelo.addColumn("Fecha Ingreso");
		modelo.addColumn("Sueldo");
		modelo.addColumn("Sede");
		modelo.addColumn("Cargo");
		modelo.addColumn("Turno");
		tblEmpleado.setModel(modelo);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setIcon(new ImageIcon(frmMantenimientoEmpleado.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.setBounds(27, 269, 136, 48);
		contentPanel.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setIcon(new ImageIcon(frmMantenimientoEmpleado.class.getResource("/imagenes/guardar.png")));
		btnGuardar.setBounds(212, 269, 136, 48);
		contentPanel.add(btnGuardar);
		
		llenarComboBoxSede();
		llenarComboBoxCargo();
		llenarComboBoxTurno();
		cargarData();
	    mostrarData(0);
		centrarDatos();
	}
	
	private void llenarComboBoxTurno() {
		ArrayList<Turno> listar = gTurno.listarTurno();
		cboTurno.addItem("Seleccionar turno");
		for (Turno turno : listar) {
			cboTurno.addItem(turno.getDescripcion());
		}
	}

	private void llenarComboBoxCargo() {
		ArrayList<Cargo> listar = gCargo.listarCargo();
		cboCargo.addItem("Seleccionar cargo");
		for (Cargo cargo : listar) {
			cboCargo.addItem(cargo.getDescripcion());
		}
	}

	private void llenarComboBoxSede() {
		ArrayList<Sede> listar = gSede.listarSede();
		cboSede.addItem("Seleccionar Sede");
		for (Sede sede : listar) {
			cboSede.addItem(sede.getNombre());
		}	
	}
	
	private void centrarDatos() {
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tblEmpleado.getDefaultRenderer(Object.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void mostrarData(int fila) {
		if(fila != -1) {
			tipo = false;
			String idEmpleado, nomEmpleado, apeEmpleado, dni, correo, celular, sueldo, sede, cargo, turno;
			Date fecha = null;
			idEmpleado = modelo.getValueAt(fila, 0).toString();
			nomEmpleado = modelo.getValueAt(fila, 1).toString();
			apeEmpleado = modelo.getValueAt(fila, 2).toString();
			dni = modelo.getValueAt(fila, 3).toString();
			correo = modelo.getValueAt(fila, 4).toString();
			celular = modelo.getValueAt(fila, 5).toString();
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(fila, 6));
			} catch (ParseException e) {
				e.printStackTrace();
			}		
			sueldo = modelo.getValueAt(fila, 7).toString();
			sede = modelo.getValueAt(fila, 8).toString();
			cargo = modelo.getValueAt(fila, 9).toString();
			turno = modelo.getValueAt(fila, 10).toString();
			txtCodigo.setText(idEmpleado);
			txtNombre.setText(nomEmpleado);
			txtApellido.setText(apeEmpleado);
			txtDNI.setText(dni);
			txtCorreo.setText(correo);
			txtCelular.setText(celular);
			dtcFechaIng.setDate(fecha);
			txtSueldo.setText(sueldo);
			cboSede.setSelectedItem(sede);
			cboCargo.setSelectedItem(cargo);
			cboTurno.setSelectedItem(turno);
		}
	}

	private void cargarData() {
		modelo.setRowCount(0);
		ArrayList<ListaEmpleado> lista = gEmp.listar();
		for (ListaEmpleado empleado : lista) {
			Object fila[] = { empleado.getIdEmpleado(),
					          empleado.getNombre(),
					          empleado.getApellido(),
					          empleado.getDNI(),
					          empleado.getCorreo(),
					          empleado.getCelular(),
					          empleado.getFechaInicio(),
					          empleado.getSueldo(),
					          empleado.getSede(),
					          empleado.getCargo(),
					          empleado.getTurno()
					        };
			modelo.addRow(fila);
		}	
	}
	
	private void mensaje(String str) {
		JOptionPane.showMessageDialog(this, str, "Alerta!!", 2);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
	}

	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		nuevoEmpleado();
	}
	
	private void nuevoEmpleado() {
		txtCodigo.setText("");
		txtCodigo.setEditable(false);
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtCorreo.setText("");
		txtCelular.setText("");
		dtcFechaIng.setDate(null);
		txtSueldo.setText("");
		cboSede.setSelectedIndex(0);
		cboCargo.setSelectedIndex(0);
		cboTurno.setSelectedIndex(0);
		tipo = true;
	}

	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		if(tipo == false) {
			actualizar();
		} else {
			registrar();
		}
		cargarData();
	}
	
	private void registrar() {
		String nomEmpleado, apeEmpleado, dni, correo, celular, fechaIng;
		double sueldo;
		int idSede, idCargo, idTurno;
		//Validaciones
		nomEmpleado = getNombre();
		apeEmpleado = getApellido();
		dni = getDni();
		correo = getCorreo();
		celular = getCelular();
		fechaIng = getFechaIng();
		sueldo = getSueldo();
		idSede = getIdSede();
		idCargo = getIdCargo();
		idTurno = getIdTurno();
		
		if(nomEmpleado == null || apeEmpleado == null || dni == null || correo == null || celular == null || fechaIng == null ||sueldo == -1 || idSede == 0 || idCargo == 0 || idTurno == 0) {
			mensaje("No se registró empleado!!");
		} else {
			Empleado e = new Empleado();
			e.setNombre(nomEmpleado);
			e.setApellido(apeEmpleado);
			e.setDNI(dni);
			e.setCorreo(correo);
			e.setCelular(celular);
			e.setFechaInicio(fechaIng);
			e.setSueldo(sueldo);
			e.setIdSede(idSede);
			e.setIdCargo(idCargo);
			e.setIdTurno(idTurno);

			int ok = gEmp.registrar(e);
			if(ok == 0) {
				mensaje("Error en el registro!!!");
				tipo = true;
			} else {
				mensaje("Registro correcto!");
				nuevoEmpleado();
			}
		}
	}

	private void actualizar() {
		// variables
		String nomEmpleado, apeEmpleado, dni, correo, celular, fechaIng;
		double sueldo;
		int idEmpleado, idSede, idCargo, idTurno;
		// entradas
		idEmpleado = getCodigo();
		nomEmpleado = getNombre();
		apeEmpleado = getApellido();
		dni = getDni();
		correo = getCorreo();
		celular = getCelular();
		fechaIng = getFechaIng();
		sueldo = getSueldo();
		idSede = getIdSede();
		idCargo = getIdCargo();
		idTurno = getIdTurno();	
		
		if(nomEmpleado == null || apeEmpleado == null || dni == null || correo == null || celular == null || fechaIng == null ||sueldo == -1 || idSede == 0 || idCargo == 0 || idTurno == 0) {
			mensaje("No se actualizó empleado!!");
		} else {
			// procesos
			Empleado e = new Empleado();
			e.setNombre(nomEmpleado);
			e.setApellido(apeEmpleado);
			e.setDNI(dni);
			e.setCorreo(correo);
			e.setCelular(celular);
			e.setFechaInicio(fechaIng);
			e.setSueldo(sueldo);
			e.setIdSede(idSede);
			e.setIdCargo(idCargo);
			e.setIdTurno(idTurno);
			e.setIdEmpleado(idEmpleado);
			// salidas
			int ok = gEmp.actualizar(e);
			if(ok == 0) {
				mensaje("Error al actualizar los datos!!!");
			}
			else {
				mensaje("Actualización de datos exitosa!!");
				nuevoEmpleado();
			}
		}
	}

	private int getIdTurno() {
		int idTurno = 0;
		idTurno = cboTurno.getSelectedIndex();
		if (idTurno == 0) {
			mensaje("Seleccione un turno!!");
		}
		return idTurno;
	}

	private int getIdCargo() {
		int idCargo = 0;
		idCargo = cboCargo.getSelectedIndex();
		if (idCargo == 0) {
			mensaje("Seleccione un cargo!!");
		}
		return idCargo;
	}

	private int getIdSede() {
		int idSede = 0;
		idSede = cboSede.getSelectedIndex();
		if (idSede == 0) {
			mensaje("Seleccione una sede!!");
		}
		return idSede;
	}

	private double getSueldo() {
		double sueldo = -1;
		if(txtSueldo.getText().trim().length() == 0) {
			mensaje("Ingrese sueldo!!");
		}
		else {
			try {
				sueldo = Double.parseDouble(txtSueldo.getText().trim());
			} catch (NumberFormatException e) {
				mensaje("Ingrese solo números!!");
			}
		}
		return sueldo;
	}

	private String getFechaIng() {
		Date fechaElegida = dtcFechaIng.getDate();
		String fecha = null;
		if(fechaElegida ==  null) {
			mensaje("Seleccione una fecha!!!");
		}
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fecha = sdf.format(fechaElegida);
		}
		return fecha;
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
		// Definir formato jpcueva@hotmail.com || agmiran@gmail.com
		String correo = null;
		if(txtCorreo.getText().trim().length()==0) {
			mensaje("Ingresar correo!!!");
			//txtCorreo.requestFocus();
		} else if(txtCorreo.getText().trim().matches("[a-zA-Z_0-9]{1,64}@[a-zA-Z_0-9]{1,63}[.][a-zA-Z]{2,63}|[a-zA-Z_0-9]{1,64}@[a-zA-Z_0-9]{1,63}[.][a-zA-Z]{2,10}[.][a-zA-Z]{2,10}")) {
			correo = txtCorreo.getText().trim();
		} else {
			mensaje("Correo no válido Ej. jpcueva@hotmail.com o agmiran@gmail.com");
			//txtCorreo.requestFocus();
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
	
	private int getCodigo() {
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
	
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int idEmpleado;
		idEmpleado = getCodigo();
		
		if(idEmpleado == -1) {
			mensaje("No se eliminó empleado!!");
		}
		else {
			int ok = gEmp.eliminar(idEmpleado);
			if(ok == 0) {
				mensaje("Error al eliminar!!!");
			} else {
				mensaje("Empleado eliminado!!");
				nuevoEmpleado();
				cargarData();
			}
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		dispose();
	}
	public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getSource() == tblEmpleado) {
			mouseClickedTblEmpleado(arg0);
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
	protected void mouseClickedTblEmpleado(final MouseEvent arg0) {
		int fila;
		fila = tblEmpleado.getSelectedRow();
		mostrarData(fila);
	}
	public void keyPressed(final KeyEvent arg0) {
	}
	public void keyReleased(final KeyEvent arg0) {
		if (arg0.getSource() == tblEmpleado) {
			keyReleasedTblEmpleado(arg0);
		}
	}
	public void keyTyped(final KeyEvent arg0) {
	}
	protected void keyReleasedTblEmpleado(final KeyEvent arg0) {
		int fila;
		fila = tblEmpleado.getSelectedRow();
		mostrarData(fila);
	}
}
