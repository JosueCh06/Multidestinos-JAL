package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class frmPrincipal extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnRegistro;
	private JMenu mnModificarInformacin;
	private JMenuItem mntmEmpleado;
	private JMenuItem mntmVehiculo;
	private JMenu mnConsultar;
	private JMenuItem mntmBuscarEmpleado;
	private JMenuItem mntmBuscarVehiculo;
	private JMenu mnReporte;
	private JMenu mnSalir;
	private JLabel lblFondo;
	private JMenuItem mntmBuscarCliente;
	private JMenuItem mntmCliente;
	private JMenuItem mntmReporteDeServicios;
	public static JLabel lblBienvenida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 374);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnRegistro = new JMenu("Registro");
		mnRegistro.addMouseListener(this);
		mnRegistro.addActionListener(this);
		mnRegistro.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/registrar.png")));
		menuBar.add(mnRegistro);
		
		mnModificarInformacin = new JMenu("Mantenimiento");
		mnModificarInformacin.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/modificar.png")));
		menuBar.add(mnModificarInformacin);
		
		mntmEmpleado = new JMenuItem("Empleado");
		mntmEmpleado.addMouseListener(this);
		mntmEmpleado.addActionListener(this);
		
		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(this);
		mntmCliente.addMouseListener(this);
		mntmCliente.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/mantenimiento_cliente.png")));
		mnModificarInformacin.add(mntmCliente);
		mntmEmpleado.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/mantenimiento_empleado.png")));
		mnModificarInformacin.add(mntmEmpleado);
		
		mntmVehiculo = new JMenuItem("Veh\u00EDculo");
		mntmVehiculo.addMouseListener(this);
		mntmVehiculo.addActionListener(this);
		mntmVehiculo.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/taxi.png")));
		mnModificarInformacin.add(mntmVehiculo);
		
		mnConsultar = new JMenu("Consultar");
		mnConsultar.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/consultar.png")));
		menuBar.add(mnConsultar);
		
		mntmBuscarEmpleado = new JMenuItem("Buscar Empleado");
		mntmBuscarEmpleado.addActionListener(this);
		
		mntmBuscarCliente = new JMenuItem("Buscar Cliente");
		mntmBuscarCliente.addActionListener(this);
		mntmBuscarCliente.addMouseListener(this);
		mntmBuscarCliente.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/consultar_cliente.png")));
		mnConsultar.add(mntmBuscarCliente);
		mntmBuscarEmpleado.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/buscar_empleado.png")));
		mnConsultar.add(mntmBuscarEmpleado);
		
		mntmBuscarVehiculo = new JMenuItem("Buscar Vehiculo");
		mntmBuscarVehiculo.addActionListener(this);
		mntmBuscarVehiculo.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/estado_vehicular.png")));
		mnConsultar.add(mntmBuscarVehiculo);
		
		mnReporte = new JMenu("Reporte");
		mnReporte.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/reporte.png")));
		menuBar.add(mnReporte);
		
		mntmReporteDeServicios = new JMenuItem("Reporte de Servicios");
		mntmReporteDeServicios.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/mantenimiento_vehiculo.png")));
		mntmReporteDeServicios.addActionListener(this);
		mnReporte.add(mntmReporteDeServicios);
		
		mnSalir = new JMenu("Salir");
		mnSalir.addMouseListener(this);
		mnSalir.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/salir.png")));
		menuBar.add(mnSalir);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBienvenida = new JLabel("");
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setOpaque(true);
		lblBienvenida.setBackground(Color.BLACK);
		lblBienvenida.setBounds(0, 265, 549, 36);
		contentPane.add(lblBienvenida);
		
		lblFondo = new JLabel("");
		lblFondo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFondo.setIcon(new ImageIcon(frmPrincipal.class.getResource("/imagenes/fondo.jpg")));
		lblFondo.setBounds(0, 0, 549, 301);
		contentPane.add(lblFondo);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmReporteDeServicios) {
			actionPerformedMntmReporteDeServicios(arg0);
		}
		if (arg0.getSource() == mntmCliente) {
			actionPerformedMntmCliente(arg0);
		}
		if (arg0.getSource() == mntmBuscarCliente) {
			actionPerformedMntmBuscarCliente(arg0);
		}
		if (arg0.getSource() == mntmBuscarVehiculo) {
			actionPerformedMntmBuscarVehiculo(arg0);
		}
		if (arg0.getSource() == mntmBuscarEmpleado) {
			actionPerformedMntmBuscarEmpleado(arg0);
		}
		if (arg0.getSource() == mntmVehiculo) {
			actionPerformedMntmVehiculo(arg0);
		}
		if (arg0.getSource() == mntmEmpleado) {
			actionPerformedMntmEmpleado(arg0);
		}
		if (arg0.getSource() == mnRegistro) {
			actionPerformedMnRegistro(arg0);
		}
	}

	protected void actionPerformedMnRegistro(ActionEvent arg0) {
		
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == mnRegistro) {
			mouseClickedMnRegistro(arg0);
		}
		if (arg0.getSource() == mnSalir) {
			mouseClickedMnSalir(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedMnSalir(MouseEvent arg0) {
		System.exit(0);
	}
	protected void mouseClickedMnRegistro(MouseEvent arg0) {
		FrmRegistro dr = new FrmRegistro();
		dr.setLocationRelativeTo(null);
		dr.setVisible(true);
	}
	protected void actionPerformedMntmCliente(ActionEvent arg0) {
		frmMantenimientoCliente mant = new frmMantenimientoCliente();
		mant.setLocationRelativeTo(null);
		mant.setVisible(true);
	}
	protected void actionPerformedMntmEmpleado(ActionEvent arg0) {
		frmMantenimientoEmpleado mant = new frmMantenimientoEmpleado();
		mant.setLocationRelativeTo(null);
		mant.setVisible(true);
	}

	protected void actionPerformedMntmVehiculo(ActionEvent arg0) {
		frmMantenimientoVehiculo frm = new frmMantenimientoVehiculo();
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}
	protected void actionPerformedMntmBuscarEmpleado(ActionEvent arg0) {
		frmConsultaEmpleado con=new frmConsultaEmpleado();
		con.setLocationRelativeTo(null);
		con.setVisible(true);
	}
	protected void actionPerformedMntmBuscarVehiculo(ActionEvent arg0) {
		frmConsultaVehiculo con= new frmConsultaVehiculo();
		con.setLocationRelativeTo(null);
		con.setVisible(true);
	}
	protected void actionPerformedMntmBuscarCliente(ActionEvent arg0) {
		frmConsultarClientes con = new frmConsultarClientes();
		con.setLocationRelativeTo(null);
		con.setVisible(true);
	}
	protected void actionPerformedMntmReporteDeServicios(ActionEvent arg0) {
		frmReporteServicios frm = new frmReporteServicios();
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}
}
