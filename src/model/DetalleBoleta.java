package model;

public class DetalleBoleta {
	
	private String numBoleta;
	private String lugarPartida;
	private String lugarLlegada;
	private double Importe;
	
	public DetalleBoleta(String numBoleta, String lugarPartida, String lugarLlegada, double importe) {
	
		this.numBoleta = numBoleta;
		this.lugarPartida = lugarPartida;
		this.lugarLlegada = lugarLlegada;
		Importe = importe;
	}

	public String getNumBoleta() {
		return numBoleta;
	}

	public void setNumBoleta(String numBoleta) {
		this.numBoleta = numBoleta;
	}

	public String getLugarPartida() {
		return lugarPartida;
	}

	public void setLugarPartida(String lugarPartida) {
		this.lugarPartida = lugarPartida;
	}

	public String getLugarLlegada() {
		return lugarLlegada;
	}

	public void setLugarLlegada(String lugarLlegada) {
		this.lugarLlegada = lugarLlegada;
	}

	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}
}
