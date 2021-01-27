package modelos;

public class DatosCuentas {

		private String numeroCuenta;
	private String nombreCuenta;
	private Double saldoCuenta;
	
			
	public DatosCuentas(String numeroCuenta, String nombreCuenta, Double saldoCuenta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.nombreCuenta = nombreCuenta;
		this.saldoCuenta = saldoCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public Double getSaldoCuenta() {
		return saldoCuenta;
	}
	public void setSaldoCuenta(Double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	
}
