package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceConductor;
import model.Cargo;
import model.Conductor;
import utils.MySQLConexion;

public class GestionConductor implements InterfaceConductor{

	@Override
	public ArrayList<Conductor> listarConductor() {
		ArrayList<Conductor> listar = new ArrayList<Conductor>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_CONDUCTOR()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			Conductor cd;
			while (res.next()) {
				cd = new Conductor();
				cd.setIdConductor(res.getInt(1));
				cd.setNombre(res.getString(2));
				listar.add(cd);
			}
		} catch (Exception e) {
			System.out.println("Error en la instruccion SQL"+ e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close(); 
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos" + e2.getMessage());
			}
		}
		return listar;
	}

	@Override
	public ArrayList<Conductor> listarConductorSinVehiculo() {
		ArrayList<Conductor> listar = new ArrayList<Conductor>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_CONDUCTORES_SIN_VEHICULO()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			Conductor cd;
			while (res.next()) {
				cd = new Conductor();
				cd.setIdConductor(res.getInt(1));
				cd.setNombre(res.getString(2));
				listar.add(cd);
			}
		} catch (Exception e) {
			System.out.println("Error en la instruccion SQL"+ e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close(); 
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos" + e2.getMessage());
			}
		}
		return listar;
	}

}
