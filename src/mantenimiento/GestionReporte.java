package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceServicio;
import model.Boleta;
import model.FechaServicios;
import utils.MySQLConexion;

public class GestionReporte implements InterfaceServicio{

	@Override
	public ArrayList<FechaServicios> ReporteXFechas(String fecha1, String fecha2) {
		ArrayList<FechaServicios> lista = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_BOLETA(?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, fecha1);
			pstm.setString(2, fecha2);
			res = pstm.executeQuery();
			lista = new ArrayList<FechaServicios>();
			while(res.next()){
				FechaServicios fs = new FechaServicios();
				fs.setIdBoleta(res.getString(1));
				fs.setFecha(res.getString(2));
				fs.setLugarPartida(res.getString(3));
				fs.setLugarLlegada(res.getString(4));
				fs.setTarifa(res.getDouble(5));
				fs.setEmpleado(res.getString(6));
				fs.setCliente(res.getString(7));
				lista.add(fs);
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
