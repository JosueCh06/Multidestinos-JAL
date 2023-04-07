package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionCategoriaVehiculo;
import mantenimiento.GestionConductor;
import mantenimiento.GestionSede;
import mantenimiento.GestionVehiculo;
import model.BuscarVehiculo;
import model.Conductor;
import model.ListaVehiculo;
import model.Sede;
import model.TipoVehiculo;
import model.Turno;
import model.Vehiculo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class frmMantenimientoVehiculo extends JDialog implements ActionListener, MouseListener, KeyListener {
	private JLabel label;
	private JLabel label_1;
	private JTextField txtNroPlaca;
	private JTextField txtMarca;
	private JLabel label_2;
	private JTextField txtModelo;
	private JLabel label_3;
	private JTextField txtColor;
	private JLabel label_4;
	private JTextField txtPrecio;
	private JLabel label_5;
	private JComboBox cboCategoria;
	private JLabel label_6;
	private JLabel lblConductor;
	private JScrollPane scrollPane;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JTable tblDatos;
	private JComboBox cboConductor;
	
	DefaultTableModel modelo = new DefaultTableModel();

	GestionConductor gCod = new GestionConductor();
	
	GestionCategoriaVehiculo gCatV = new GestionCategoriaVehiculo();
	
	GestionVehiculo gV = new GestionVehiculo();
	
	Boolean tipo = false;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmMantenimientoVehiculo dialog = new frmMantenimientoVehiculo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmMantenimientoVehiculo() {
		setTitle("Mantenimiento de vehiculos");
		setModal(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 1029, 523);
		getContentPane().setLayout(null);
		
		label = new JLabel("MANTENIMIENTO VEHICULO");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBackground(Color.BLACK);
		label.setBounds(10, 11, 989, 29);
		getContentPane().add(label);
		
		label_1 = new JLabel("Nro Placa:");
		label_1.setBounds(10, 61, 64, 14);
		getContentPane().add(label_1);
		
		txtNroPlaca = new JTextField();
		txtNroPlaca.setColumns(10);
		txtNroPlaca.setBounds(73, 58, 117, 20);
		getContentPane().add(txtNroPlaca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(73, 86, 117, 20);
		getContentPane().add(txtMarca);
		
		label_2 = new JLabel("Marca:");
		label_2.setBounds(10, 89, 64, 14);
		getContentPane().add(label_2);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(73, 114, 117, 20);
		getContentPane().add(txtModelo);
		
		label_3 = new JLabel("Modelo:");
		label_3.setBounds(10, 117, 64, 14);
		getContentPane().add(label_3);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		txtColor.setBounds(73, 145, 117, 20);
		getContentPane().add(txtColor);
		
		label_4 = new JLabel("Color:");
		label_4.setBounds(10, 148, 64, 14);
		getContentPane().add(label_4);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(73, 176, 117, 20);
		getContentPane().add(txtPrecio);
		
		label_5 = new JLabel("Precio:");
		label_5.setBounds(10, 179, 64, 14);
		getContentPane().add(label_5);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBounds(73, 207, 168, 20);
		getContentPane().add(cboCategoria);
		
		label_6 = new JLabel("Categoria:");
		label_6.setBounds(10, 210, 64, 17);
		getContentPane().add(label_6);
		
		lblConductor = new JLabel("Conductor:");
		lblConductor.setBounds(10, 241, 64, 14);
		getContentPane().add(lblConductor);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 48, 712, 415);
		getContentPane().add(scrollPane);
		
		tblDatos = new JTable();
		tblDatos.addKeyListener(this);
		tblDatos.addMouseListener(this);
		scrollPane.setViewportView(tblDatos);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setIcon(new ImageIcon(frmMantenimientoVehiculo.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setBounds(124, 311, 117, 29);
		getContentPane().add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(this);
		btnGuardar.setIcon(new ImageIcon(frmMantenimientoVehiculo.class.getResource("/imagenes/guardar.png")));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setBounds(124, 353, 117, 29);
		getContentPane().add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(frmMantenimientoVehiculo.class.getResource("/imagenes/eliminar.png")));
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setBounds(124, 395, 117, 29);
		getContentPane().add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setIcon(new ImageIcon(frmMantenimientoVehiculo.class.getResource("/imagenes/cancelar_2.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(124, 437, 117, 29);
		getContentPane().add(btnCancelar);
	
		cboConductor = new JComboBox();
		cboConductor.setBounds(73, 237, 168, 22);
		getContentPane().add(cboConductor);
		
		modelo.addColumn("Nro Placa");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Color");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Conductor");
		tblDatos.setModel(modelo);
		llenarComboBoxConductor();
		llenarComboBoxCategoria();
		cargarData();
	}

	private void llenarComboBoxCategoria() {
		ArrayList<TipoVehiculo> listar = gCatV.listarTipo();
		cboCategoria.addItem("Seleccionar Categoria");
		for(TipoVehiculo tipoVehiculo : listar){
			cboCategoria.addItem(tipoVehiculo.getDescrpicion());
		}
	}

	private void llenarComboBoxConductor() {
		ArrayList<Conductor> listar = gCod.listarConductorSinVehiculo();
		cboConductor.addItem("Seleccionar Conductor");
		for (Conductor conductor : listar){
			cboConductor.addItem(conductor.getNombre());
		}
	}
	public void actionPerformed(final ActionEvent arg0) {
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
	protected void actionPerformedBtnNuevo(final ActionEvent arg0) {
		nuevoVehiculo();
	}
	private void nuevoVehiculo() {
		txtNroPlaca.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		txtColor.setText("");
		txtPrecio.setText("");
		cboCategoria.setSelectedIndex(0);
		cboConductor.setSelectedIndex(0);
		tipo = true;
	}

	protected void actionPerformedBtnGuardar(final ActionEvent arg0) {
		if(tipo == false){
			actualizarVehiculo();
		}else{
			registrarVehiculo();
		}
		cargarData();
	}
	
	private void cargarData() {
		modelo.setRowCount(0);
		ArrayList<ListaVehiculo> lista = gV.listar();
		for (ListaVehiculo vehiculo : lista) {
			Object fila[] = { vehiculo.getNroPlaca(),
							  vehiculo.getMarca(),
							  vehiculo.getModelo(),
							  vehiculo.getColor(),
							  vehiculo.getPrecio(),
							  vehiculo.getCategoria(),
							  vehiculo.getConductor()
							};
			modelo.addRow(fila);
		}
	}

	private void actualizarVehiculo() {
		// variables
		String nroPlaca, marca, modelo, color;
		double precio;
		int idCategoria, idConductor;
		// Entradas
		nroPlaca = getNroPlaca();
		marca = getMarca();
		modelo = getModelo();
		color = getColor();
		precio = getPrecio();
		idCategoria = getIdCategoria();
		idConductor = getIdConductor();
		if(nroPlaca == null || marca == null || modelo == null || color == null || precio == -1 || idCategoria == 0 || idConductor == 0) {
			mensaje("No se actualizó vehículo!!");
		} else {
			Vehiculo v = new Vehiculo();
			v.setNroPlaca(nroPlaca);
			v.setMarca(marca);
			v.setModelo(modelo);
			v.setColor(color);
			v.setPrecio(precio);
			v.setIdCategoria(idCategoria);
			v.setIdEmpleado(idConductor);
			// Salidas
			int ok = gV.actualizar(v);
			if(ok == 0) {
				mensaje("Error al actualizar los datos!!!");
			}
			else {
				mensaje("Actualización de datos exitosa!!");
				nuevoVehiculo();
			}
		}		
	}

	private void registrarVehiculo() {
		String nroPlaca, marca, modelo, color;
		double precio;
		int idCategoria, idConductor;
		//Validaciones
		nroPlaca = getNroPlaca();
		marca = getMarca();
		modelo = getModelo();
		color = getColor();
		precio = getPrecio();
		idCategoria = getIdCategoria();
		idConductor = getIdConductor();
		
		if(nroPlaca == null || marca == null || modelo == null || color == null || precio == -1 || idCategoria == 0 || idConductor == 0) {
			mensaje("No se registró vehículo!!");
		} else {
			Vehiculo v = new Vehiculo();
			v.setNroPlaca(nroPlaca);
			v.setMarca(marca);
			v.setModelo(modelo);
			v.setColor(color);
			v.setPrecio(precio);
			v.setIdCategoria(idCategoria);
			v.setIdEmpleado(idConductor);
			
			int ok = gV.registrar(v);
			if(ok == 0) {
				mensaje("Error en el registro!!!");
				tipo = true;
			} else {
				mensaje("Registro correcto!");
				nuevoVehiculo();
			}
		}
	}

	private int getIdConductor() {
		int idConductor = 0;
		idConductor = cboConductor.getSelectedIndex();
		if (idConductor == 0) {
			mensaje("Seleccione un conductor!!");
		}
		return idConductor;
	}

	private int getIdCategoria() {
		int idCat = 0;
		idCat = cboCategoria.getSelectedIndex();
		if (idCat == 0) {
			mensaje("Seleccione una categoria!!");
		}
		return idCat;
	}

	private double getPrecio() {
		double pre = -1;
		if(txtPrecio.getText().trim().length() == 0) {
			mensaje("Ingrese precio!!");
		}
		else {
			try {
				pre = Double.parseDouble(txtPrecio.getText().trim());
			} catch (NumberFormatException e) {
				mensaje("Ingrese solo números!!");
			}
		}
		return pre;
	}

	private String getColor() {
		String color = null;
		if(txtColor.getText().trim().length() == 0) {
			mensaje("Ingrese color!!");
		} 
		else if(txtColor.getText().trim().matches("[a-zA-Z \\s]{4,25}")) {
			color = txtColor.getText().trim();
		}
		else {
			mensaje("Ingrese solo letras(4 a 25 caracteres)!!!");
		}
		return color;
	}

	private String getModelo() {
		String modelo = null;
		if(txtModelo.getText().trim().length() == 0) {
			mensaje("Ingrese modelo!!");
		} 
		else if(txtModelo.getText().trim().matches("[a-zA-Z \\s]{3,25}")) {
			modelo = txtModelo.getText().trim();
		}
		else {
			mensaje("Ingrese solo letras(3 a 25 caracteres)!!!");
		}
		return modelo;
	}

	private String getMarca() {
		String marca = null;
		if(txtMarca.getText().trim().length() == 0) {
			mensaje("Ingrese marca!!");
		} 
		else if(txtMarca.getText().trim().matches("[a-zA-Z \\s]{3,25}")) {
			marca = txtMarca.getText().trim();
		}
		else {
			mensaje("Ingrese solo letras(3 a 25 caracteres)!!!");
		}
		return marca;
	}

	private String getNroPlaca() {
		String nroPlaca = null;
		if(txtNroPlaca.getText().trim().length() == 0) {
			mensaje("Ingrese número de placa!!");
		} 
		else if(txtNroPlaca.getText().trim().matches("[a-zA-Z0-9]{3}[-][0-9]{3}")) {
			nroPlaca = txtNroPlaca.getText().trim().toUpperCase();
		}
		else {
			mensaje("Número de placa no válido!! Ej. AEF-717");
		}
		return nroPlaca;
	}

	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string, "Alerta!!", 2);
	}

	protected void actionPerformedBtnEliminar(final ActionEvent arg0) {
		String nroPlaca;
		nroPlaca = getNroPlaca();
		
		if(nroPlaca == null) {
			mensaje("No se eliminó empleado!!");
		}
		else {
			int ok = gV.eliminar(nroPlaca);
			if(ok == 0) {
				mensaje("Error al eliminar!!!");
			} else {
				mensaje("Vehículo eliminado!!");
				nuevoVehiculo();
				cargarData();
			}
		}
	}
	protected void actionPerformedBtnCancelar(final ActionEvent arg0) {
		dispose();
	}
	public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getSource() == tblDatos) {
			mouseClickedTblDatos(arg0);
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
	protected void mouseClickedTblDatos(final MouseEvent arg0) {
		int fila;
		fila = tblDatos.getSelectedRow();
		mostrarData(fila);
	}

	public void keyPressed(final KeyEvent arg0) {
	}
	public void keyReleased(final KeyEvent arg0) {
		if (arg0.getSource() == tblDatos) {
			keyReleasedTblDatos(arg0);
		}
	}
	public void keyTyped(final KeyEvent arg0) {
	}
	protected void keyReleasedTblDatos(final KeyEvent arg0) {
		int fila;
		fila = tblDatos.getSelectedRow();
		mostrarData(fila);
	}
	
	private void mostrarData(int fila) {
		if(fila != -1) {
			tipo = false;
			String nroPlaca, marca, modeloVeh, color, precio, categoria, idEmpleado, sede;
			nroPlaca = modelo.getValueAt(fila, 0).toString();
			marca = modelo.getValueAt(fila, 1).toString();
			modeloVeh = modelo.getValueAt(fila, 2).toString();
			color = modelo.getValueAt(fila, 3).toString();
			precio = modelo.getValueAt(fila, 4).toString();
			categoria = modelo.getValueAt(fila, 5).toString();
			idEmpleado = modelo.getValueAt(fila, 6).toString();
			txtNroPlaca.setText(nroPlaca);
			txtMarca.setText(marca);
			txtModelo.setText(modeloVeh);
			txtColor.setText(color);
			txtPrecio.setText(precio);
			cboCategoria.setSelectedItem(categoria);
			cboConductor.setSelectedItem(idEmpleado);
		}
	}
}
