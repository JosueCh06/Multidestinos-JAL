package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceSede;
import model.Sede;
import utils.MySQLConexion;

public class GestionSede implements InterfaceSede{

	@Override
	public ArrayList<Sede> listarSede() {
		ArrayList<Sede> listar = new ArrayList<Sede>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try{
			con = MySQLConexion.getConexion();
			String sql = "call SP_SEDE";
			pstm = con.prepareStatement(sql);
			res = pstm.executeQuery();
			Sede s;
			while (res.next()) {
				s = new Sede();
				s.setIdSede(res.getInt(1));
				s.setNombre(res.getString(2));
				listar.add(s);
			}
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL " + e.getMessage());
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null) res.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la  base de datos " + e2.getMessage());
			}
		}
		return listar;
	}

}
