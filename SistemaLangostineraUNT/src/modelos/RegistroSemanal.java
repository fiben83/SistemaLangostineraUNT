package modelos;

import java.util.Date;

public class RegistroSemanal {

	 private String idTallaPeso;
	 private String idCrianza;
	 private String id;// id operador
	 private Date fecha;
	 private double tamano_promedio;
	 private double peso_promedio;
	 
	
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
		
	public String getIdTallaPeso() {
		return idTallaPeso;
	}
	public void setIdTallaPeso(String idTallaPeso) {
		this.idTallaPeso = idTallaPeso;
	}
	public double getTamano_promedio() {
		return tamano_promedio;
	}
	public void setTamano_promedio(double tamano_promedio) {
		this.tamano_promedio = tamano_promedio;
	}
	public double getPeso_promedio() {
		return peso_promedio;
	}
	public void setPeso_promedio(double peso_promedio) {
		this.peso_promedio = peso_promedio;
	}
	public RegistroSemanal(String idTallaPeso, String idCrianza, String id, Date fecha,
			double tamano_promedio, double peso_promedio) {
		super();
		
		this.idTallaPeso= idTallaPeso;
		this.idCrianza= idCrianza;
		this.id= id;
		this.fecha=fecha;
		this.tamano_promedio= tamano_promedio;
		this.peso_promedio= peso_promedio;
				
	}
	 
}

