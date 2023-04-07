package model;

public class CabezeraBoleta {
	private String numboleta;
	private String fecha;
	private int idEmpleado;
	private int idCiente;
	private double totalbol;
	
	public CabezeraBoleta(String numboleta, String fecha, int idEmpleado, int idCiente, double totalbol) {
		
		this.numboleta = numboleta;
		this.fecha = fecha;
		this.idEmpleado = idEmpleado;
		this.idCiente = idCiente;
		this.totalbol = totalbol;
	}

	public String getNumboleta() {
		return numboleta;
	}

	public void setNumboleta(String numboleta) {
		this.numboleta = numboleta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdCiente() {
		return idCiente;
	}

	public void setIdCiente(int idCiente) {
		this.idCiente = idCiente;
	}

	public double getTotalbol() {
		return totalbol;
	}

	public void setTotalbol(double totalbol) {
		this.totalbol = totalbol;
	}
}
