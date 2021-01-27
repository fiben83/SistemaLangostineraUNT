package modelos;

import java.util.Date;

public class RegistroDiario {

	 private String idRegistroDiario;
	 private String idCrianza;
	 private String id;// id operador
	 private Date fecha;
	 private double medicion_promedio_agua;
	 private double medicion_promedio_temp;
	 private double medicion_promedio_oxigeno;
	 private double medicion_promedio_ph;
	 private double medicion_promedio_salinidad;
	 
	public String getIdRegistroDiario() {
		return idRegistroDiario;
	}
	public void setIdRegistroDiario(String idRegistroDiario) {
		this.idRegistroDiario = idRegistroDiario;
	}
	public String getIdCrianza() {
		return idCrianza;
	}
	public void setIdCrianza(String idCrianza) {
		this.idCrianza = idCrianza;
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getMedicion_promedio_agua() {
		return medicion_promedio_agua;
	}
	public void setMedicion_promedio_agua(double medicion_promedio_agua) {
		this.medicion_promedio_agua = medicion_promedio_agua;
	}
	public double getMedicion_promedio_temp() {
		return medicion_promedio_temp;
	}
	public void setMedicion_promedio_temp(double medicion_promedio_temp) {
		this.medicion_promedio_temp = medicion_promedio_temp;
	}
	public double getMedicion_promedio_oxigeno() {
		return medicion_promedio_oxigeno;
	}
	public void setMedicion_promedio_oxigeno(double medicion_promedio_oxigeno) {
		this.medicion_promedio_oxigeno = medicion_promedio_oxigeno;
	}
	public double getMedicion_promedio_ph() {
		return medicion_promedio_ph;
	}
	public void setMedicion_promedio_ph(double medicion_promedio_ph) {
		this.medicion_promedio_ph = medicion_promedio_ph;
	}
	public double getMedicion_promedio_salinidad() {
		return medicion_promedio_salinidad;
	}
	public void setMedicion_promedio_salinidad(double medicion_promedio_salinidad) {
		this.medicion_promedio_salinidad = medicion_promedio_salinidad;
	}
	
	public RegistroDiario(String idRegistroDiario, String idCrianza, String id, Date fecha,
			double medicion_promedio_agua, double medicion_promedio_temp, double medicion_promedio_oxigeno,
	        double medicion_promedio_ph, double medicion_promedio_salinidad) {
		super();
		
		this.idRegistroDiario= idRegistroDiario;
		this.idCrianza= idCrianza;
		this.id= id;
		this.fecha=fecha;
		this.medicion_promedio_agua= medicion_promedio_agua;
		this.medicion_promedio_temp=medicion_promedio_temp;
		this.medicion_promedio_oxigeno=medicion_promedio_oxigeno;
		this.medicion_promedio_ph=medicion_promedio_ph;
		this.medicion_promedio_salinidad=medicion_promedio_salinidad;
		
	}
	 
}
