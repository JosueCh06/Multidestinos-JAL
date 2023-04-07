package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionCategoriaVehiculo;
import model.BuscarVehiculo;
import model.TipoVehiculo;
import model.Vehiculo;

import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmConsultaVehiculo extends JDialog implements ActionListener {
	private JTable tblDatos;
	private JComboBox cboCategoria;
	private JButton btnBuscar;
	private JLabel lblCategoria;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	GestionCategoriaVehiculo gtipV = new GestionCategoriaVehiculo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaVehiculo dialog = new frmConsultaVehiculo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaVehiculo() {
		setTitle("Consulta de vehiculos");
		setModal(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 832, 421);
		getContentPane().setLayout(null);
		{
			JLabel label = new JLabel("BUSCAR VEHICULO ");
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBackground(Color.BLACK);
			label.setBounds(10, 11, 796, 27);
			getContentPane().add(label);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 107, 794, 254);
			getContentPane().add(scrollPane);
			{
				tblDatos = new JTable();
				scrollPane.setViewportView(tblDatos);
				tblDatos.setFillsViewportHeight(true);
			}
		}
		{
			cboCategoria = new JComboBox();
			cboCategoria.setBounds(134, 56, 164, 20);
			getContentPane().add(cboCategoria);
		}
		{
			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(592, 51, 113, 33);
			getContentPane().add(btnBuscar);
			btnBuscar.addActionListener(this);
			btnBuscar.setIcon(new ImageIcon(frmConsultaVehiculo.class.getResource("/imagenes/estado_vehicular.png")));
			btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		
		lblCategoria = new JLabel("Categoria: ");
		lblCategoria.setBounds(55, 58, 75, 16);
		getContentPane().add(lblCategoria);
		modelo.addColumn("Nro Placa");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Color");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Empleado");
		tblDatos.setModel(modelo);
		llenarComboBox();	
	}

	private void llenarComboBox() {
		ArrayList<TipoVehiculo> listar = gtipV.listarTipo();
		cboCategoria.addItem("Seleccionar categoria");
		for(TipoVehiculo tipoVehiculo : listar){
			cboCategoria.addItem(tipoVehiculo.getDescrpicion());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		listarVehiculoXCategoria();		
	}

	private void listarVehiculoXCategoria() {
		int tipo;
		tipo = getTipo();
		ArrayList<BuscarVehiculo> lista = gtipV.listarVehiculoXCategoria(tipo);
		modelo.setRowCount(0);
		for(BuscarVehiculo buscarVehiculo : lista){
			Object fila[] = {buscarVehiculo.getPlaca(),
							 buscarVehiculo.getMarca(),
							 buscarVehiculo.getModelo(),
							 buscarVehiculo.getColor(),
							 buscarVehiculo.getPrecio(),
							 buscarVehiculo.getCategoria(),
							 buscarVehiculo.getEmpleado()};
			modelo.addRow(fila);
		}
	}
		
	private int getTipo() {
		int tipo = cboCategoria.getSelectedIndex();
		if(tipo == 0){
			mensaje("Seleccionar una categoria");
		}
		return tipo;
	}

	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string, "Alert!!", 2);
	}
}
