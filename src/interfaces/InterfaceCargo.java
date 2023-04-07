package interfaces;

import java.util.ArrayList;

import model.BuscarEmpleado;
import model.Cargo;

public interface InterfaceCargo {
	
	public ArrayList<Cargo> listarCargo();
	
	public ArrayList<BuscarEmpleado> listarEmpleadoXCargo(int tipo);
	
}
