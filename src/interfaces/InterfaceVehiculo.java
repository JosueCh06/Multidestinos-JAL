package interfaces;

import java.util.ArrayList;

import model.ListaVehiculo;
import model.Vehiculo;


public interface InterfaceVehiculo {
	//metodos de mantenimiento
		public int registrar (Vehiculo v);
		
		public int actualizar(Vehiculo v);
		
		public int eliminar (String nroPlaca);
		
		public ArrayList<ListaVehiculo> listar();
		
}
