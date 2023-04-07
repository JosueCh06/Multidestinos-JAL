package interfaces;

import java.util.ArrayList;

import model.Cliente;
import model.ClienteBoleta;


public interface InterfaceCliente {
	//metodos de mantenimiento 
	public int registrar (Cliente c);
	
	public int actualizar(Cliente c);
	
	public int eliminar (int idCliente);
	
	public ArrayList<Cliente> listar();
	
	public ArrayList<ClienteBoleta> listarCliente();
	
}
