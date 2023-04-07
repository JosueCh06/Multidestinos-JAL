package interfaces;

import java.util.ArrayList;

import model.FechaServicios;

public interface InterfaceServicio {
	public ArrayList<FechaServicios> ReporteXFechas(String fecha1, String fecha2);
}
