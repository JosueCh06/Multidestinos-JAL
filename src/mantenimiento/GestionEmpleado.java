package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceEmpleado;
import model.BuscarEmpleado;
import model.Empleado;
import model.ListaEmpleado;
import utils.MySQLConexion;

public class GestionEmpleado implements InterfaceEmpleado{

	@Override
	public int registrar(Empleado e) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm=null;
		try {
			//Establecer conexion con BD
			con=MySQLConexion.getConexion();
			//Establecer tu instrucción
			String sql="insert into empleado values(null,?,?,?,?,?,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			//parametros
			pstm.setString(1, e.getNombre());
			pstm.setString(2, e.getApellido());
			pstm.setString(3, e.getDNI());
			pstm.setString(4, e.getCorreo());
			pstm.setString(5, e.getCelular());
			pstm.setString(6, e.getFechaInicio());
			pstm.setDouble(7, e.getSueldo());
			pstm.setInt(8, e.getIdSede());
			pstm.setInt(9, e.getIdCargo());
			pstm.setInt(10, e.getIdTurno());
			
			estado=pstm.executeUpdate();
		} catch (Exception e1) {
			System.out.println("Error en la instrucción SQL - Registrar " + e1);
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos " + e2);
			}
		}
		return estado;
	}

	@Override
	public int actualizar(Empleado e) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update empleado set nombre = ?, apellido = ?, DNI = ?, correo = ?," +
						 "celular = ?, fecha_inicio = ?, sueldo = ?, Sede_idSede = ?, Cargo_idCargo = ?," + 
					     "Turno_idTurno = ? where idEmpleado = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, e.getNombre());
			pstm.setString(2, e.getApellido());
			pstm.setString(3, e.getDNI());
			pstm.setString(4, e.getCorreo());
			pstm.setString(5, e.getCelular());
			pstm.setString(6, e.getFechaInicio());
			pstm.setDouble(7, e.getSueldo());
			pstm.setInt(8, e.getIdSede());
			pstm.setInt(9, e.getIdCargo());
			pstm.setInt(10, e.getIdTurno());
			pstm.setInt(11, e.getIdEmpleado());
			
			estado = pstm.executeUpdate();
		} catch (Exception e1) {
			System.out.println("Error en la instrucción SQL - Actualizar " + e1.getMessage());
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		return estado;
	}

	@Override
	public int eliminar(int idEmpleado) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from empleado where idEmpleado = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idEmpleado);
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - Eliminar " + e.getMessage());
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		return estado;
	}

	@Override
	public ArrayList<ListaEmpleado> listar() {
		ArrayList<ListaEmpleado> listar = new ArrayList<ListaEmpleado>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_EMPLEADO()";
			pstm = con.prepareStatement(sql);
			res = pstm.executeQuery();
			ListaEmpleado l;
			while (res.next()) {
				l = new ListaEmpleado();
				l.setIdEmpleado(res.getInt(1));
				l.setNombre(res.getString(2));
				l.setApellido(res.getString(3));
				l.setDNI(res.getString(4));
				l.setCorreo(res.getString(5));
				l.setCelular(res.getString(6));
				l.setFechaInicio(res.getString(7));
				l.setSueldo(res.getDouble(8));
				l.setSede(res.getString(9));
				l.setCargo(res.getString(10));
				l.setTurno(res.getString(11));
				listar.add(l);
			}
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - Listar " + e.getMessage());
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null) res.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		return listar;
	}
}
