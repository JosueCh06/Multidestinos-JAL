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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import mantenimiento.GestionBoleta;
import mantenimiento.GestionReporte;
import model.FechaServicios;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class frmReporteServicios extends JDialog implements ActionListener {
	private JTable tblDatos;
	
	private JDateChooser dtcFechaFinal;
	private JDateChooser dtcFechaInicial;
	private JScrollPane scrollPane;
	private JButton btnGenerar;
	private JButton btnReportePdf;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmReporteServicios dialog = new frmReporteServicios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmReporteServicios() {
		setTitle("Reporte de servicios");
		setModal(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 823, 436);
		getContentPane().setLayout(null);
		{
			JLabel label = new JLabel("REPORTE DE SERVICIOS BRINDADOS");
			label.setOpaque(true);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			label.setBackground(Color.BLACK);
			label.setBounds(10, 11, 783, 29);
			getContentPane().add(label);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(11, 105, 782, 271);
			getContentPane().add(scrollPane);
			{
				tblDatos = new JTable();
				scrollPane.setViewportView(tblDatos);
				tblDatos.setFillsViewportHeight(true);
			}

		}
		{
			dtcFechaInicial = new JDateChooser();
			dtcFechaInicial.setBounds(107, 58, 108, 20);
			getContentPane().add(dtcFechaInicial);
		}
		{
			JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
			lblFechaInicial.setBounds(20, 62, 89, 16);
			getContentPane().add(lblFechaInicial);
		}
		{
			JLabel lblFechaFinal = new JLabel("Fecha Final:");
			lblFechaFinal.setBounds(237, 62, 82, 16);
			getContentPane().add(lblFechaFinal);
		}
		
		dtcFechaFinal = new JDateChooser();
		dtcFechaFinal.setBounds(316, 58, 105, 22);
		getContentPane().add(dtcFechaFinal);
		{
			btnGenerar = new JButton("Generar");
			btnGenerar.addActionListener(this);
			btnGenerar.setIcon(new ImageIcon(frmReporteServicios.class.getResource("/imagenes/generatetables_ok_home_out_edit_generar_1491.png")));
			btnGenerar.setBounds(482, 53, 120, 39);
			getContentPane().add(btnGenerar);
		}
		{
			btnReportePdf = new JButton("Reporte PDF");
			btnReportePdf.addActionListener(this);
			btnReportePdf.setIcon(new ImageIcon(frmReporteServicios.class.getResource("/imagenes/reporte.png")));
			btnReportePdf.setBounds(614, 53, 156, 39);
			getContentPane().add(btnReportePdf);
			
			modelo.addColumn("Nro Boleta");
			modelo.addColumn("Fecha");
			modelo.addColumn("Lugar de Partida");
			modelo.addColumn("Lugar de Llegada");
			modelo.addColumn("Tarifa");
			modelo.addColumn("Empleado");
			modelo.addColumn("Cliente");
			
			tblDatos.setModel(modelo);
		}
	}

	private void Reporte() {
		String fecha1, fecha2;
		fecha1 = leerFechaInicial();
		fecha2 = leerFechaFinal();
		
		GestionReporte grep = new GestionReporte();
		ArrayList<FechaServicios> lista = grep.ReporteXFechas(fecha1, fecha2);
		
		if(lista == null){
			JOptionPane.showMessageDialog(this, "Lista Vacia");
		}else{
			modelo.setRowCount(0);
			for(FechaServicios fv : lista){
				Object fila[] = {
									fv.getIdBoleta(),
									fv.getFecha(),
									fv.getLugarPartida(),
									fv.getLugarLlegada(),
									fv.getTarifa(),
									fv.getEmpleado(),
									fv.getCliente(),
				};
				modelo.addRow(fila);
			}
		}
	}

	private String leerFechaFinal() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaFin = new Date();
		String fecha = null;
		try {
			if(dtcFechaFinal.getDate().before(fechaFin)){
				fecha = sdf.format(dtcFechaFinal.getDate());
			}else{
				mensaje("Debe seleccionar una fecha menor a la actual");
			}
		} catch (Exception e) {
			mensaje("Debe seleccionar una fecha");
		}
		return fecha;
	}

	private String leerFechaInicial() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaInc = new Date();
		String fecha = null;
		try{
			if(dtcFechaInicial.getDate().before(fechaInc)){
				fecha = sdf.format(dtcFechaInicial.getDate());
			}else{
				mensaje("Debe seleccionar una fecha menor a la actual");
			}
		}catch(Exception e){
			mensaje("Debe seleccionar una fecha");
		}
		return fecha;
	}
	
	private void mensaje(String string) {
		JOptionPane.showMessageDialog(this, string);
	}

	public void actionPerformed(final ActionEvent arg0) {
		if (arg0.getSource() == btnReportePdf) {
			actionPerformedBtnReportePdf(arg0);
		}
		if (arg0.getSource() == btnGenerar) {
			actionPerformedBtnGenerar(arg0);
		}
	}
	protected void actionPerformedBtnGenerar(final ActionEvent arg0) {
		Reporte();
	}
	protected void actionPerformedBtnReportePdf(final ActionEvent arg0) {
		ReportePDF();
	}
	String fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	private void ReportePDF() {
		String nombArchivo = "reportes/Reporte_Servicios.pdf";
		try{
			FileOutputStream fos = new FileOutputStream(nombArchivo);
			Document doc = new Document();
			PdfWriter pdfw = PdfWriter.getInstance(doc, fos);
			doc.open();
			Image img = Image.getInstance("src/imagenes/logo.jpeg");
			img.scaleToFit(184,65);
			img.setAlignment(Chunk.ALIGN_LEFT);
			doc.add(img);
			Paragraph p = new Paragraph("REPORTE DE SERVICIOS",FontFactory.getFont("arial",25,Font.BOLD));
			p.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p);
			p = new Paragraph(fecha);
			p.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p);
			p = new Paragraph("---------------------------");
			p.setAlignment(Chunk.ALIGN_CENTER);
			doc.add(p);
			p = new Paragraph("");
			doc.add(p);
			p = new Paragraph("Entre las fechas: " + leerFechaInicial() + " al " + leerFechaFinal());
			p.setAlignment(Chunk.ALIGN_LEFT);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			PdfPTable table = new PdfPTable(5);
			ArrayList<FechaServicios> lista = new GestionReporte().ReporteXFechas(leerFechaInicial(), leerFechaFinal());
			Paragraph n = new Paragraph("N° Boleta", FontFactory.getFont("arial",13,Font.BOLD));
			table.addCell(n);
			Paragraph fec = new Paragraph("Fecha", FontFactory.getFont("arial",13,Font.BOLD));
			table.addCell(fec);
			Paragraph emp = new Paragraph("Conductor", FontFactory.getFont("arial",13,Font.BOLD));
			table.addCell(emp);
			Paragraph cli = new Paragraph("Cliente", FontFactory.getFont("arial",13,Font.BOLD));
			table.addCell(cli);
			Paragraph tf = new Paragraph("Tarifa", FontFactory.getFont("arial",13,Font.BOLD));
			table.addCell(tf);
			for(FechaServicios fs : lista){
				table.addCell(fs.getIdBoleta()+"");
				table.addCell(fs.getFecha());
				table.addCell(fs.getEmpleado());
				table.addCell(fs.getCliente());
				table.addCell(fs.getTarifa()+"");
			}
			table.setWidthPercentage(100);
			doc.add(table);
			doc.close();
			Desktop.getDesktop().open(new File(nombArchivo));
		}catch(Exception e){
			System.out.println("Error al crear el archivo: " + e.getMessage());
		}
	}
}
