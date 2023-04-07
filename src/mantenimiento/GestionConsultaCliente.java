package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceConsultaCliente;
import model.BuscarCliente;
import model.BuscarEmpleado;
import model.Cargo;
import model.IdCliente;
import utils.MySQLConexion;

public class GestionConsultaCliente implements InterfaceConsultaCliente{

	@Override
	public ArrayList<IdCliente> listarCliente() {
		ArrayList<IdCliente> listar = new ArrayList<IdCliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_CLIENTE()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			IdCliente ic;
			while (res.next()) {
				ic = new IdCliente();
				ic.setId(res.getInt(1));
				listar.add(ic);
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
	public ArrayList<BuscarCliente> listarClienteXId(int id) {
		ArrayList<BuscarCliente> lista = new ArrayList<BuscarCliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_CLIENTEXID(?)";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			res = pstm.executeQuery();
			BuscarCliente bc;
			while (res.next()) {
				bc = new BuscarCliente();
				bc.setIdCliente(res.getInt(1));
				bc.setCliente(res.getString(2));
				bc.setDNI(res.getString(3));
				bc.setCelular(res.getString(4));
				bc.setCorreo(res.getString(5));
				lista.add(bc);
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
