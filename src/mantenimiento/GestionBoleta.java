package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import interfaces.InterfaceBoleta;
import model.Boleta;
import model.CabezeraBoleta;
import model.DetalleBoleta;
import utils.MySQLConexion;

public class GestionBoleta implements InterfaceBoleta{
	
	@Override
	public String generarNumBoleta() {
		String codigo = "B0001";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "Select substring(num_boleta,2)"
					+ "from cab_boleta order by num_boleta desc limit 1";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next()){
				DecimalFormat df = new DecimalFormat("0000");
				codigo = "B" + df.format(Integer.parseInt(rs.getString(1))+1);
			}
		} catch (Exception e) {
			System.out.println("Error en generar num Boleta: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar: " + e2.getMessage());
			}
		}
		return codigo;
	}

	@Override
	public int realizarServicio(CabezeraBoleta cBol, ArrayList<DetalleBoleta> dBol) {
		int ok = -1;
		Connection con = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			String sql1 = "insert into cab_boleta values(?,?,?,?,?)";
			pstm1 = con.prepareStatement(sql1);
			
			pstm1.setString(1, cBol.getNumboleta());
			pstm1.setString(2, cBol.getFecha());
			pstm1.setInt(3, cBol.getIdEmpleado());
			pstm1.setInt(4, cBol.getIdCiente());
			pstm1.setDouble(5, cBol.getTotalbol());
			
			ok = pstm1.executeUpdate();
			
			String sql2 = "insert into det_boleta values (?,?,?,?)";
			for(DetalleBoleta det : dBol){
				pstm2 = con.prepareStatement(sql2);
				pstm2.setString(1, cBol.getNumboleta());
				pstm2.setString(2, det.getLugarPartida());
				pstm2.setString(3, det.getLugarLlegada());
				pstm2.setDouble(4, det.getImporte());
				
				ok = pstm2.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			System.out.println("Error al realizar el servicio: " + e.getMessage());
			ok = -1;
			try {
				con.rollback();
			} catch (SQLException e2) {
				System.out.println("Error al restaurar: " + e2.getMessage());
			}
		}finally {
			try {
				con.close();
			} catch (SQLException e3) {
				System.out.println("Error al cerrar: " + e3.getMessage());
			}
		}
		return ok;
	}

	@Override
	public ArrayList<Boleta> listadoXFechas(String fecha1, String fecha2) {
		ArrayList<Boleta> lista = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_REPORTE_BOLETASXFECHAS(?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, fecha1);
			pstm.setString(2, fecha2);
			res = pstm.executeQuery();
			lista = new ArrayList<Boleta>();
			while(res.next()){
				Boleta b = new Boleta();
				b.setIdBoleta(res.getString(1));
				b.setFecha(res.getString(2));
				b.setLugarPartida(res.getString(3));
				b.setLugarLlegada(res.getString(4));
				b.setIdEmpleado(res.getInt(5));
				b.setIdCliente(res.getInt(6));
				lista.add(b);
			}
		} catch (Exception e) {
			System.out.println("Error en Listado "+ e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
				if(res != null) res.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar");
			}
		}
		return lista;
	}
}
