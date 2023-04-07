package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.InterfaceCliente;
import model.Cliente;
import model.ClienteBoleta;
import model.Conductor;
import utils.MySQLConexion;

public class GestionCliente implements InterfaceCliente{

	@Override
	public int registrar(Cliente c) {
		int estado=0;
		Connection con=null;
		PreparedStatement pstm=null;
		try {
			con=MySQLConexion.getConexion();							      
			String sql="insert into cliente values (null,?,?,?,?,?)";
			pstm=con.prepareStatement(sql);
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getApellido());
			pstm.setString(3, c.getDNI());
			pstm.setString(4, c.getCelular());
			pstm.setString(5, c.getCorreo());
			
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
	public int actualizar(Cliente c) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "update cliente set nombre = ?, apellido = ?, DNI= ?, celular = ?, correo=? where idCliente = ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, c.getNombre());
			pstm.setString(2, c.getApellido());
			pstm.setString(3, c.getDNI());
			pstm.setString(4, c.getCelular());
			pstm.setString(5, c.getCorreo());
			pstm.setInt(6, c.getIdCliente());
			
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
	public int eliminar(int idCliente) {
		int estado = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "delete from cliente where idCliente = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idCliente);
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
	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null; 
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_LISTAR_CLIENTE";
			pstm = con.prepareStatement(sql);
			res=pstm.executeQuery();
			Cliente c;
			while (res.next()) {
				c = new Cliente();
				c.setIdCliente(res.getInt(1));
				c.setNombre(res.getString(2));
				c.setApellido(res.getString(3));
				c.setDNI(res.getString(4));
				c.setCelular(res.getString(5));
				c.setCorreo(res.getString(6));
			
				lista.add(c);
			}	
		} catch (Exception e) {
			System.out.println("Error >>> en la sentencia SQL - Listar" + e.getMessage());
		}finally {
			try {
				if( pstm != null )pstm.close();
				if( res != null )res.close();
				if (con != null) con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error >>> al cerrar base de datos" + e2.getMessage());
			}
		}
		return lista;
	}

	@Override
	public ArrayList<ClienteBoleta> listarCliente() {
		ArrayList<ClienteBoleta> listar = new ArrayList<ClienteBoleta>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_CLIENTE()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			ClienteBoleta cb;
			while (res.next()) {
				cb = new ClienteBoleta(); 
				cb.setIdCliente(res.getInt(1));
				cb.setNombreCompleto(res.getString(2));
				listar.add(cb);
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
