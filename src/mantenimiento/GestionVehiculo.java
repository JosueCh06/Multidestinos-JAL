package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceVehiculo;
import model.ListaVehiculo;
import model.Vehiculo;
import utils.MySQLConexion;

public class GestionVehiculo implements InterfaceVehiculo{

	@Override
	public int registrar(Vehiculo v) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm=null;
		try {
			//Establecer conexion con BD
			con=MySQLConexion.getConexion();
			//Establecer tu instrucción
			String sql="insert into vehiculo values(?,?,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			//parametros
			pstm.setString(1, v.getNroPlaca());
			pstm.setString(2, v.getMarca());
			pstm.setString(3, v.getModelo());
			pstm.setString(4, v.getColor());
			pstm.setDouble(5, v.getPrecio());
			pstm.setInt(6, v.getIdCategoria());
			pstm.setInt(7, v.getIdEmpleado());
			
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
	public int actualizar(Vehiculo v) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update vehiculo set marca = ?, modelo = ?, color = ?, precio = ?," +
						 "Categoria_Vehiculo_idCategoria_Vehiculo = ?" + 
					     "Empleado_idEmpleado = ? where nro_placa = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, v.getMarca());
			pstm.setString(2, v.getModelo());
			pstm.setString(3, v.getColor());
			pstm.setDouble(4, v.getPrecio());
			pstm.setInt(5, v.getIdCategoria());
			pstm.setInt(6, v.getIdEmpleado());
			pstm.setString(7, v.getNroPlaca());
			
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
	public int eliminar(String nroPlaca) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from vehiculo where nro_placa = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nroPlaca);
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
	public ArrayList<ListaVehiculo> listar() {
		ArrayList<ListaVehiculo> listar = new ArrayList<ListaVehiculo>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_VEHICULO()";
			pstm = con.prepareStatement(sql);
			res = pstm.executeQuery();
			ListaVehiculo l;
			while (res.next()) {
				l = new ListaVehiculo();
				l.setNroPlaca(res.getString(1));
				l.setMarca(res.getString(2));
				l.setModelo(res.getString(3));
				l.setColor(res.getString(4));
				l.setPrecio(res.getDouble(5));
				l.setCategoria(res.getString(6));
				l.setConductor(res.getString(7));
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
		return listar ;
	}
}
