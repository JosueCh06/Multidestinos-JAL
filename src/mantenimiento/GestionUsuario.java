package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import interfaces.InterfaceUsuario;
import model.Usuario;
import utils.MySQLConexion;

public class GestionUsuario implements InterfaceUsuario{

	@Override
	public Usuario validarIngreso(int user, String pass) {
		Usuario usu=null;
		Connection con = null;
		PreparedStatement pstm= null;
		ResultSet res = null;
		try {
			//1 establecer conexion con la base de datos
			con = MySQLConexion.getConexion();
			//2 instruccion sql
			String sql= "call SP_VALIDAR_INGRESO(?,?)";
			//3 preparamos la instrucion
			pstm = con.prepareStatement(sql);
			//4 parametros
			pstm.setInt(1, user);
			pstm.setString(2, pass);
			//5ejecutamos la instruccion
			res =pstm.executeQuery();
			//6 bucle
			while (res.next()) {
				//7 crear el objeto de tipo usuario
				usu=new Usuario();
				usu.setIdEmpleado(res.getInt(1));
				usu.setNombre(res.getString(2));
				usu.setApellido(res.getString(3));
				usu.setIdUsuario(res.getInt(4));
				usu.setContraseña(res.getString(5));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en la sentencia sql  -  Validar ingreso"+e.getMessage());
		}finally{
			try {
				if(pstm!=null)pstm.close();
				if(res!=null)res.close();
				if(con!=null)con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("Error al cerrar la base de datos -"+e2.getMessage());
			}
		}
		
		return usu;
	}

}
