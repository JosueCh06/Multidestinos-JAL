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

import mantenimiento.GestionConsultaCliente;
import model.BuscarCliente;
import model.BuscarEmpleado;
import model.Cargo;
import model.IdCliente;

import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmConsultarClientes extends JDialog implements ActionListener {
	private JLabel lblBuscarCliente;
	private JButton btnBuscar;
	private JTable tblDatos;
	private JScrollPane scrollPane;
	private JLabel lblId;
	private JComboBox cboCliente;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	GestionConsultaCliente gConCli = new GestionConsultaCliente();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultarClientes dialog = new frmConsultarClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultarClientes() {
		setModal(true);
		setTitle("Consultar  de Clientes");
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 784, 306);
		getContentPane().setLayout(null);
		
		lblBuscarCliente = new JLabel("BUSCAR CLIENTE");
		lblBuscarCliente.setOpaque(true);
		lblBuscarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarCliente.setForeground(Color.WHITE);
		lblBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuscarCliente.setBackground(Color.BLACK);
		lblBuscarCliente.setBounds(9, 13, 745, 35);
		getContentPane().add(lblBuscarCliente);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(11, 146, 743, 98);
		getContentPane().add(scrollPane);
		
		tblDatos = new JTable();
		scrollPane.setViewportView(tblDatos);
		tblDatos.setFillsViewportHeight(true);
				
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(570, 78, 110, 33);
		getContentPane().add(btnBuscar);
		btnBuscar.setIcon(new ImageIcon(frmConsultarClientes.class.getResource("/imagenes/buscar_empleado.png")));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblId = new JLabel("Id_Cliente");
		lblId.setBounds(72, 85, 90, 16);
		getContentPane().add(lblId);
		
		cboCliente = new JComboBox();
		cboCliente.setBounds(174, 82, 199, 22);
		getContentPane().add(cboCliente);
		
		modelo.addColumn("ID Cliente");
		modelo.addColumn("Cliente");
		modelo.addColumn("DNI");
		modelo.addColumn("Celular");
		modelo.addColumn("Correo");	
		tblDatos.setModel(modelo);
		llenarcomboBox();
	}

	private void llenarcomboBox() {
		ArrayList<IdCliente> listar = gConCli.listarCliente();
		cboCliente.addItem("Seleccionar ID");
		for(IdCliente idCliente : listar){
			cboCliente.addItem(idCliente.getId());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		listarClienteXId();
	}

	private void listarClienteXId() {
		int id;
		id = getId();
		ArrayList<BuscarCliente> lista = gConCli.listarClienteXId(id);
		modelo.setRowCount(0);
		for(BuscarCliente buscarCliente : lista){
			Object fila[] = {buscarCliente.getIdCliente(),
						     buscarCliente.getCliente(),
						     buscarCliente.getDNI(),
						     buscarCliente.getCelular(),
						     buscarCliente.getCorreo()
			};
			modelo.addRow(fila);
		}
	}

	private int getId() {
		int id = cboCliente.getSelectedIndex();
		if(id == 0){
			mensaje("Seleccionar un ID");
		}
		return id;
	}

	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string);
	}
}
