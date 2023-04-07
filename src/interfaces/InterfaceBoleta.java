package interfaces;

import java.util.ArrayList;

import model.Boleta;
import model.CabezeraBoleta;
import model.DetalleBoleta;

public interface InterfaceBoleta {

	public String generarNumBoleta();
	
	public int realizarServicio(CabezeraBoleta cBol, ArrayList<DetalleBoleta> dBol);
	
	//metodos para el reporte
	public ArrayList<Boleta> listadoXFechas(String fecha1, String fecha2);
	
}
