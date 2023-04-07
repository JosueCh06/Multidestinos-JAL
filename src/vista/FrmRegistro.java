package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import mantenimiento.GestionBoleta;
import model.CabezeraBoleta;
import model.DetalleBoleta;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmRegistro extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JLabel lblRegistroDeServicio;
	private JPanel panel;
	private JLabel lblIdEmpleado;
	private JLabel lblNombre;
	public static JTextField txtEmpleado;
	public static JTextField txtNombEmpleado;
	private JButton btnBuscarEmpleado;
	private JPanel panel_1;
	private JLabel lblIdCliente;
	private JLabel lblNombre_1;
	public static JTextField txtCliente;
	public static JTextField txtNombCliente;
	private JButton btnBuscarCliente;
	private JPanel panel_2;
	private JLabel lblLugarPartida;
	private JTextField txtPartida;
	private JLabel lblLugarLlegada;
	private JTextField txtllegada;
	private JLabel lblTarifa;
	private JTextField txtTarifa;
	private JButton btnRegistrar;
	private JButton btnNuevo;
	private JButton btnCancelar;
	private JTable tblServicios;
	private JScrollPane scrollPane;
	private JLabel lblNum;
	private JLabel lblFecha;
	private JTextField txtNumBoleta;
	private JTextField txtFecha;
	private JButton btnAgregarServicio;
	private JTextField txtTotal;
	private JLabel lblTotal;

	DefaultTableModel modelo = new DefaultTableModel();

	double impTotal;
	
	ArrayList<DetalleBoleta> detBol = new ArrayList<DetalleBoleta>(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistro frame = new FrmRegistro();
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
	public FrmRegistro() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 784, 559);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRegistroDeServicio = new JLabel("REGISTRO DE SERVICIOS (BOLETA)");
		lblRegistroDeServicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeServicio.setOpaque(true);
		lblRegistroDeServicio.setBackground(Color.BLACK);
		lblRegistroDeServicio.setForeground(Color.WHITE);
		lblRegistroDeServicio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegistroDeServicio.setBounds(12, 13, 735, 31);
		contentPane.add(lblRegistroDeServicio);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(new TitledBorder(null, "Datos del Empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 167, 355, 97);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblIdEmpleado = new JLabel("Id Empleado:");
		lblIdEmpleado.setBounds(26, 26, 84, 16);
		panel.add(lblIdEmpleado);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(26, 55, 56, 16);
		panel.add(lblNombre);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setBounds(109, 23, 116, 22);
		panel.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		txtNombEmpleado = new JTextField();
		txtNombEmpleado.setEditable(false);
		txtNombEmpleado.setBounds(109, 55, 214, 22);
		panel.add(txtNombEmpleado);
		txtNombEmpleado.setColumns(10);
		
		btnBuscarEmpleado = new JButton("");
		btnBuscarEmpleado.addActionListener(this);
		btnBuscarEmpleado.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/buscar_empleado.png")));
		btnBuscarEmpleado.setBounds(237, 23, 45, 25);
		panel.add(btnBuscarEmpleado);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(12, 57, 368, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblIdCliente = new JLabel("Id Cliente:");
		lblIdCliente.setBounds(33, 26, 70, 16);
		panel_1.add(lblIdCliente);
		
		lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setBounds(33, 55, 56, 16);
		panel_1.add(lblNombre_1);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(100, 23, 116, 22);
		panel_1.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtNombCliente = new JTextField();
		txtNombCliente.setEditable(false);
		txtNombCliente.setBounds(101, 52, 238, 22);
		panel_1.add(txtNombCliente);
		txtNombCliente.setColumns(10);
		
		btnBuscarCliente = new JButton("");
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/buscar_empleado.png")));
		btnBuscarCliente.setBounds(221, 22, 48, 25);
		panel_1.add(btnBuscarCliente);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos del servicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(386, 132, 368, 129);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblLugarPartida = new JLabel("Lugar Partida:");
		lblLugarPartida.setBounds(12, 29, 91, 16);
		panel_2.add(lblLugarPartida);
		
		lblLugarLlegada = new JLabel("Lugar Llegada:");
		lblLugarLlegada.setBounds(12, 58, 91, 16);
		panel_2.add(lblLugarLlegada);
		
		txtPartida = new JTextField();
		txtPartida.setBounds(115, 26, 241, 22);
		panel_2.add(txtPartida);
		txtPartida.setColumns(10);
		
		txtllegada = new JTextField();
		txtllegada.setBounds(115, 55, 241, 22);
		panel_2.add(txtllegada);
		txtllegada.setColumns(10);
		
		lblTarifa = new JLabel("Tarifa:");
		lblTarifa.setBounds(12, 87, 49, 16);
		panel_2.add(lblTarifa);
		
		txtTarifa = new JTextField();
		txtTarifa.setBounds(115, 84, 116, 22);
		panel_2.add(txtTarifa);
		txtTarifa.setColumns(10);
		
		btnAgregarServicio = new JButton("");
		btnAgregarServicio.addActionListener(this);
		btnAgregarServicio.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/cartp.png")));
		btnAgregarServicio.setBounds(237, 83, 49, 25);
		panel_2.add(btnAgregarServicio);
		
		btnRegistrar = new JButton("Registrar servicio");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/registrar.png")));
		btnRegistrar.setBounds(153, 458, 177, 41);
		contentPane.add(btnRegistrar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/nuevo.png")));
		btnNuevo.setBounds(12, 458, 129, 41);
		contentPane.add(btnNuevo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setIcon(new ImageIcon(FrmRegistro.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBounds(342, 458, 132, 41);
		contentPane.add(btnCancelar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 283, 742, 150);
		contentPane.add(scrollPane);
		
		tblServicios = new JTable();
		tblServicios.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblServicios);
		
		lblNum = new JLabel("Num:");
		lblNum.setBounds(406, 73, 46, 16);
		contentPane.add(lblNum);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(406, 103, 56, 16);
		contentPane.add(lblFecha);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setEditable(false);
		txtNumBoleta.setBounds(464, 70, 97, 22);
		contentPane.add(txtNumBoleta);
		txtNumBoleta.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(464, 100, 139, 22);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
				
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(631, 467, 116, 22);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(584, 470, 46, 16);
		contentPane.add(lblTotal);
		
		modelo.addColumn("Lugar de llegada");
		modelo.addColumn("Lugar de Llegada");
		modelo.addColumn("Tarifa");
		tblServicios.setModel(modelo);
		
		txtNumBoleta.setText(""+ObtenerNumBoleta());
		txtFecha.setText(""+ObtenerFecha());
		
	}
	public void actionPerformed(final ActionEvent arg0) {
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
		if (arg0.getSource() == btnAgregarServicio) {
			actionPerformedBtnAgregarServicio(arg0);
		}
		if (arg0.getSource() == btnBuscarCliente) {
			actionPerformedBtnBuscarCliente(arg0);
		}
		if (arg0.getSource() == btnBuscarEmpleado) {
			actionPerformedBtnBuscarEmpleado(arg0);
		}
	}
	protected void actionPerformedBtnBuscarEmpleado(final ActionEvent arg0) {
		DlgBuscarConductor emp = new DlgBuscarConductor();
		emp.setVisible(true);
	}
	
	protected void actionPerformedBtnBuscarCliente(final ActionEvent arg0) {
		DlgBuscarCliente cli = new DlgBuscarCliente();
		cli.setVisible(true);
	}
	protected void actionPerformedBtnAgregarServicio(final ActionEvent arg0) {
		agregarServicio();
	}

	private void agregarServicio() {
		double tarifa;
		String lugarPartida, lugarLlegada;
		
		lugarPartida = leerLugarPartida();
		lugarLlegada = leerLugarLlegada();
		tarifa = leerTarifa();
		
		if(lugarPartida == null || lugarLlegada == null || tarifa == -1){
			mensaje("No se registro el servicio!!!");
		}else{
		Object fila[] = {lugarPartida, lugarLlegada, tarifa};
		impTotal += tarifa;
		txtTotal.setText("S/. " + impTotal );
		modelo.addRow(fila);
		
		//Agregar los datos a la clase DetalleBoleta
		DetalleBoleta d = new DetalleBoleta(null, lugarPartida, lugarLlegada, tarifa);
		detBol.add(d);
		}
	}

	private String leerLugarLlegada() {
		String lleg = null;
		if(txtllegada.getText().trim().length() == 0) {
			mensaje("Ingrese lugar de llegada!!");
		} 
		else if(txtllegada.getText().trim().matches("[A-Z a-z. 0-9 áéíóú]{5,35}")) {
			lleg = txtllegada.getText().trim();
		}
		else {
			mensaje("Ingrese formato pedido Por ejm: Av. Grau 196!!!");
		}
		return lleg;
	}

	private double leerTarifa() {
		double tarifa = -1;
		if(txtTarifa.getText().trim().length() == 0) {
			mensaje("Ingrese Tarifa!!");
		}
		else {
			try {
				tarifa = Double.parseDouble(txtTarifa.getText().trim());
			} catch (NumberFormatException e) {
				mensaje("Ingrese solo números!!");
			}
		}
		return tarifa;
	}

	private String leerLugarPartida() {
		String part = null;
		if(txtPartida.getText().trim().length() == 0) {
			mensaje("Ingrese lugar de partida!!");
		} 
		else if(txtPartida.getText().trim().matches("[A-Z a-z. 0-9 áéíóú]{5,35}")) {
			part = txtPartida.getText().trim();
		}
		else {
			mensaje("Ingrese formato pedido Por ejm: Av. Grau 196!!!");
		}
		return part;
	}
	
	private String ObtenerFecha(){
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}
	
	private String ObtenerNumBoleta(){
		return new GestionBoleta().generarNumBoleta();
	}
	protected void actionPerformedBtnNuevo(final ActionEvent arg0) {
		nuevaBoleta();
	}

	private void nuevaBoleta() {
		impTotal = 0;
		detBol = new ArrayList<DetalleBoleta>();
		modelo.setRowCount(0);
		
		txtPartida.setText("");
		txtllegada.setText("");
		txtTarifa.setText("");
	}	
	protected void actionPerformedBtnRegistrar(final ActionEvent arg0) {
		finalizarServicio();
	}

	private void finalizarServicio() {
		String numBol = ObtenerNumBoleta();
		String fecha = ObtenerFecha();
		int codConductor = leerCodConductor() ;
		int codCliente = leerCodCliente();
		double total = impTotal;
		
		CabezeraBoleta boleta = new CabezeraBoleta(numBol, fecha, codConductor, codCliente, total);
		
		int rs = new GestionBoleta().realizarServicio(boleta, detBol);
		
		if(rs == 0){
			mensaje("Error en la transacción");
		}else{
			mensaje("Transacción existosa");
			imprimirBoleta();
			nuevaBoleta();
		}
	}

	private void imprimirBoleta() {
		//1. dar un nombre al pdf a generar
		String boleta=txtNumBoleta.getText();
			String nomArchivo = "boletas/Boleta-"+boleta+".pdf";
			try {
				//crear el archivo
				FileOutputStream fos = new FileOutputStream(nomArchivo);
				//crear objeto para agregar componentes al reporte 
				Document doc = new Document();
				//asociar
				PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
				//crear el documento para escritura
				doc.open();
				//agregar componentes
				//agregar imagenes
				Image img = Image.getInstance("src/imagenes/logo.jpeg");
				img.scaleToFit(184,65);
				img.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(img);
				//
				Paragraph p = new Paragraph("Boleta de servicio", FontFactory.getFont("arial",30,Font.BOLD,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_CENTER);
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				//agregar fecha 
				String fecha = ObtenerFecha();
				p = new Paragraph("Fecha : "+fecha, FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_RIGHT);
				doc.add(p);
				
				p = new Paragraph("\n");
				doc.add(p);
				//mostrar datos del cliente
				p = new Paragraph("Datos del cliente:", FontFactory.getFont("arial",18,Font.BOLD,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				
				p = new Paragraph("		Nombre: "+txtNombCliente.getText(), FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				p = new Paragraph("-------------------------------------------------------------------------------------");
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				//mostrar datos del conductor
				p = new Paragraph("Datos del conductor:", FontFactory.getFont("arial",18,Font.BOLD,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				
				p = new Paragraph("		Nro de conductor: "+txtEmpleado.getText(), FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				p = new Paragraph("		Nombre: "+txtNombEmpleado.getText(), FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				p = new Paragraph("-------------------------------------------------------------------------------------");
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				//servicio
				p = new Paragraph("Datos del servicio:", FontFactory.getFont("arial",20,Font.BOLD,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_LEFT);
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				
				//pasar el listado
				if(detBol==null ||detBol.size()==0){
					p = new Paragraph("Lista vacia");
					p.setAlignment(Chunk.ALIGN_CENTER);
					doc.add(p);
				}else{
					//Crear tabla
					PdfPTable tabla = new PdfPTable(3);
					tabla.addCell("Punto de partida");
					tabla.addCell("Punto de llegada");
					tabla.addCell("Importe");
					
					for(DetalleBoleta d : detBol){
						//agregar 
						tabla.addCell(d.getLugarPartida()+" ");
						tabla.addCell(d.getLugarLlegada()+" ");
						tabla.addCell(d.getImporte()+" ");
					}
					doc.add(tabla);
				}
				//al pie de la boleta
				p = new Paragraph("\n");
				doc.add(p);
				p = new Paragraph("Total a pagar: "+txtTotal.getText(), FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_RIGHT);
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				p = new Paragraph("\n");
				doc.add(p);
				p = new Paragraph("Que tenga un buen viaje!!", FontFactory.getFont("arial",16,BaseColor.BLACK));
				p.setAlignment(Chunk.ALIGN_CENTER);
				doc.add(p);
				//al finalizar, cerrar 
				doc.close();
				//abrir el archivo
				Desktop.getDesktop().open(new File(nomArchivo));
			} catch (Exception e) {
				System.out.println("Error al crear el archivo "+e.getMessage());
			}
	}

	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string, "Alerta!!!", 2);
	}

	private int leerCodCliente() {
		return Integer.parseInt(txtCliente.getText());
	}

	private int leerCodConductor() {
		return Integer.parseInt(txtEmpleado.getText());
	}
	protected void actionPerformedBtnCancelar(final ActionEvent arg0) {
		dispose();
	}
}
