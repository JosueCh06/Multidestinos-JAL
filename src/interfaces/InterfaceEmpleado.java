package interfaces;

import java.util.ArrayList;

import model.BuscarEmpleado;
import model.Empleado;
import model.ListaEmpleado;


public interface InterfaceEmpleado {
	//metodos de mantenimiento
	public int registrar (Empleado e);
	
	public int actualizar(Empleado e);
	
	public int eliminar (int idEmpleado);
	
	public ArrayList<ListaEmpleado> listar();

}
