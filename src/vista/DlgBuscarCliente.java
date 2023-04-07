package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mantenimiento.GestionCliente;
import model.ClienteBoleta;
import model.Conductor;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgBuscarCliente extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTable tblCliente;
	private JScrollPane scrollPane;
	private JButton okButton;
	private JButton cancelButton;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	GestionCliente gCli = new GestionCliente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgBuscarCliente dialog = new DlgBuscarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgBuscarCliente() {
		setTitle("Buscar Cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 432, 218);
		contentPanel.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCliente);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre Completo");
		tblCliente.setModel(modelo);
		listar();
	}

	private void listar() {
		ArrayList<ClienteBoleta> lista = gCli.listarCliente();
		modelo.setRowCount(0);
		for(ClienteBoleta clienteBoleta : lista){
			Object fila[] = { clienteBoleta.getIdCliente(),
							  clienteBoleta.getNombreCompleto()
			};
			modelo.addRow(fila);
		}
	}
	
	public void actionPerformed(final ActionEvent arg0) {
		if (arg0.getSource() == cancelButton) {
			actionPerformedCancelButton(arg0);
		}
		if (arg0.getSource() == okButton) {
			actionPerformedOkButton(arg0);
		}
	}
	protected void actionPerformedOkButton(final ActionEvent arg0) {
		enviarDatos();
	}
	
	private void enviarDatos() {
		int fila = tblCliente.getSelectedRow();
		if(fila != -1){
			String cod, nom;
			cod = tblCliente.getValueAt(fila, 0).toString();
			nom = tblCliente.getValueAt(fila, 1).toString();
			
			FrmRegistro.txtCliente.setText(cod);
			FrmRegistro.txtNombCliente.setText(nom);
		}
		dispose();
	}

	protected void actionPerformedCancelButton(final ActionEvent arg0) {
		dispose();
	}
}
