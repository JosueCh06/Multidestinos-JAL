package interfaces;

import java.util.ArrayList;

import model.BuscarVehiculo;
import model.TipoVehiculo;

public interface InterfaceCategoriaVehiculo {

	public ArrayList<TipoVehiculo> listarTipo();
	
	public ArrayList<BuscarVehiculo> listarVehiculoXCategoria(int tipo);

}
