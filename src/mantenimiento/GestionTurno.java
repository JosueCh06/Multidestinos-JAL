package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import interfaces.InterfaceTurno;
import model.Turno;
import utils.MySQLConexion;

public class GestionTurno implements InterfaceTurno{

	@Override
	public ArrayList<Turno> listarTurno() {
		ArrayList<Turno> listar = new ArrayList<Turno>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			con = MySQLConexion.getConexion();
			String sql = "call SP_TURNO()";
			pstm = con.prepareStatement(sql);
			res = pstm.executeQuery();
			Turno t;
			while (res.next()) {
				t = new Turno();
				t.setIdTurno(res.getInt(1));
				t.setDescripcion(res.getString(2));
				t.setHoraIngreso(res.getString(3));
				t.setHoraSalida(res.getString(4));
				listar.add(t);
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
		return listar ;
	}

}
