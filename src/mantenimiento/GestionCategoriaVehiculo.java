package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceCategoriaVehiculo;
import model.BuscarVehiculo;
import model.TipoVehiculo;
import utils.MySQLConexion;

public class GestionCategoriaVehiculo implements InterfaceCategoriaVehiculo{

	@Override
	public ArrayList<TipoVehiculo> listarTipo() {
		ArrayList<TipoVehiculo> listar = new ArrayList<TipoVehiculo>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_TIPO_VEHICULO()";
			pstm  = con.prepareStatement(sql);
			res = pstm.executeQuery();
			TipoVehiculo t;
			while (res.next()) {
				t = new TipoVehiculo();
				t.setTipo(res.getInt(1));
				t.setDescrpicion(res.getString(2));
				listar.add(t);
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
	public ArrayList<BuscarVehiculo> listarVehiculoXCategoria(int tipo) {
		ArrayList<BuscarVehiculo> lista = new ArrayList<BuscarVehiculo>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_VEHICULOXTIPO(?)";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tipo);
			res = pstm.executeQuery();
			BuscarVehiculo b;
			while (res.next()) {
				b = new BuscarVehiculo();
				b.setPlaca(res.getString(1));
				b.setMarca(res.getString(2));
				b.setModelo(res.getString(3));
				b.setColor(res.getString(4));
				b.setPrecio(res.getDouble(5));
				b.setCategoria(res.getString(6));
				b.setEmpleado(res.getString(7));
				
				lista.add(b);
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
