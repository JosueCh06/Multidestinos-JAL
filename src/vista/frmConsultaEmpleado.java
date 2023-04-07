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
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimiento.GestionCargo;
import model.BuscarEmpleado;
import model.BuscarVehiculo;
import model.Cargo;
import model.TipoVehiculo;

import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class frmConsultaEmpleado extends JDialog implements ActionListener {
	private JLabel label;
	private JScrollPane scrollPane;
	private JTable tblDatos;
	private JLabel lblCargo;
	private JComboBox cboCargo;
	private JButton btnBuscar;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	GestionCargo gCarg = new GestionCargo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaEmpleado dialog = new frmConsultaEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaEmpleado() {
		setTitle("Consulta de empleados");
		setModal(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 1009, 456);
		getContentPane().setLayout(null);
		
		label = new JLabel("BUSCAR EMPLEADO");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBackground(Color.BLACK);
		label.setBounds(10, 13, 969, 28);
		getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 116, 969, 286);
		getContentPane().add(scrollPane);
		
		tblDatos = new JTable();
		scrollPane.setViewportView(tblDatos);
		
		lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(134, 62, 56, 16);
		getContentPane().add(lblCargo);
		
		cboCargo = new JComboBox();
		cboCargo.setBounds(199, 59, 224, 22);
		getContentPane().add(cboCargo);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setIcon(new ImageIcon(frmConsultaEmpleado.class.getResource("/imagenes/buscar_empleado.png")));
		btnBuscar.setBounds(703, 54, 113, 32);
		getContentPane().add(btnBuscar);
		
		modelo.addColumn("Codigo");
		modelo.addColumn("Empleado(a)");
		modelo.addColumn("DNI");
		modelo.addColumn("Correo");
		modelo.addColumn("Celular");
		modelo.addColumn("Fec Ingreso");
		modelo.addColumn("Sueldo");
		modelo.addColumn("Sede");
		modelo.addColumn("Cargo");
		modelo.addColumn("Turno");
		tblDatos.setModel(modelo);
		llenarComboBox();
	}

	private void llenarComboBox() {
		ArrayList<Cargo> listar = gCarg.listarCargo();
		cboCargo.addItem("Seleccionar cargo");
		for(Cargo cargo : listar){
			cboCargo.addItem(cargo.getDescripcion());
		}
	}


	private void listarEmpleadoXCargo() {
		int tipo;
		tipo = getTipo();
		ArrayList<BuscarEmpleado> lista = gCarg.listarEmpleadoXCargo(tipo);
		modelo.setRowCount(0);
		for(BuscarEmpleado buscarEmpleado : lista){
			Object fila[] = {buscarEmpleado.getIdEmpleado(),
						     buscarEmpleado.getNombrecompleto(),
						     buscarEmpleado.getDni(),
						     buscarEmpleado.getCorreo(),
						     buscarEmpleado.getCelular(),
						     buscarEmpleado.getFechaInc(),
						     buscarEmpleado.getSueldo(),
						     buscarEmpleado.getSede(),
						     buscarEmpleado.getCargo(),
						     buscarEmpleado.getTurno()
			};
			modelo.addRow(fila);
		}
	}
	
	private int getTipo() {
		int tipo = cboCargo.getSelectedIndex();
		if(tipo == 0){
			mensaje("Seleccionar una cargo");
		}
		return tipo;
	}
	
	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string, "Alert!!", 2);
	}
	
	public void actionPerformed(final ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(final ActionEvent arg0) {
		listarEmpleadoXCargo();
	}

}
