package interfaces;

import java.util.ArrayList;

import model.BuscarCliente;
import model.BuscarEmpleado;
import model.IdCliente;

public interface InterfaceConsultaCliente {
	
	public ArrayList<IdCliente> listarCliente();
	
	public ArrayList<BuscarCliente> listarClienteXId(int id);
}
