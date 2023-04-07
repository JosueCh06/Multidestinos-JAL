package model;

public class Boleta {
	//atributos
	private String idBoleta;
	private String fecha;
	private String lugarPartida;
	private String lugarLlegada;
	private double tarifa;
	private int idEmpleado;
	private int idCliente;
	
	//metodos de acceso
	public String getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(String idBoleta) {
		this.idBoleta = idBoleta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
