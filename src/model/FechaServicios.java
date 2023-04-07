package model;

public class FechaServicios {
	private String idBoleta;
	private String fecha;
	private String lugarPartida;
	private String lugarLlegada;
	private double tarifa;
	private String empleado;
	private String cliente;
	
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
	public String getEmpleado() {
		return empleado;
	}
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
}
