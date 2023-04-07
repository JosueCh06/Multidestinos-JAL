package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceCargo;
import model.BuscarEmpleado;
import model.BuscarVehiculo;
import model.Cargo;
import model.TipoVehiculo;
import utils.MySQLConexion;

public class GestionCargo implements InterfaceCargo {

	@Override
	public ArrayList<Cargo> listarCargo() {
		ArrayList<Cargo> listar = new ArrayList<Cargo>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_CARGO()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			Cargo c;
			while (res.next()) {
				c = new Cargo();
				c.setTipo(res.getInt(1));
				c.setDescripcion(res.getString(2));
				listar.add(c);
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
	public ArrayList<BuscarEmpleado> listarEmpleadoXCargo(int tipo) {
		ArrayList<BuscarEmpleado> lista = new ArrayList<BuscarEmpleado>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_EMPLEADOXCARGO(?)";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tipo);
			res = pstm.executeQuery();
			BuscarEmpleado be;
			while (res.next()) {
				be = new BuscarEmpleado();
				be.setIdEmpleado(res.getInt(1));
				be.setNombrecompleto(res.getString(2));
				be.setDni(res.getString(3));
				be.setCorreo(res.getString(4));
				be.setCelular(res.getString(5));
				be.setFechaInc(res.getString(6));
				be.setSueldo(res.getDouble(7));
				be.setSede(res.getString(8));
				be.setCargo(res.getString(9));
				be.setTurno(res.getString(10));
				lista.add(be);
			}
		} catch (Exception e) {
			System.out.println("Error en la instruccion SQL" + e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close(); 
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos" + e2.getMessage());
			}
		}
		return lista;
	}

}
