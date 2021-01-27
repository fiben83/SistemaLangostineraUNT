package modelos;

import java.util.Date;

public class Crianza {
	 private String idCrianza;
	 private Date fecha_inicio;
	 private Date fecha_cosecha;
	 private Date fecha_cosecha_plan;
	 private String estadoCrianza;
	 private int larvas_sembradas;
	 private int ejemplares_cultivados;
	 private double supervivencia;
	 private double peso_promedio;
	 private double peso_cultivado;
	 private int idEstanque;
	 private int idEspecie;
	 	  
	public int getIdEstanque() {
		return idEstanque;
	}

	public void setIdEstanque(int idEstanque) {
		this.idEstanque = idEstanque;
	}

	public int getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(int idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getIdCrianza() {
		return idCrianza;
	}

	public void setIdCrianza(String idCrianza) {
		this.idCrianza = idCrianza;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_cosecha() {
		return fecha_cosecha;
	}

	public void setFecha_cosecha(Date fecha_cosecha) {
		this.fecha_cosecha = fecha_cosecha;
	}

	public Date getFecha_cosecha_plan() {
		return fecha_cosecha_plan;
	}

	public void setFecha_cosecha_plan(Date fecha_cosecha_plan) {
		this.fecha_cosecha_plan = fecha_cosecha_plan;
	}
	
	public String getEstadoCrianza() {
		return estadoCrianza;
	}

	public void setEstadoCrianza(String estadoCrianza) {
		this.estadoCrianza = estadoCrianza;
	}

	public int getLarvas_sembradas() {
		return larvas_sembradas;
	}

	public void setLarvas_sembradas(int larvas_sembradas) {
		this.larvas_sembradas = larvas_sembradas;
	}

	public int getEjemplares_cultivados() {
		return ejemplares_cultivados;
	}

	public void setEjemplares_cultivados(int ejemplares_cultivados) {
		this.ejemplares_cultivados = ejemplares_cultivados;
	}

	public double getPeso_cultivado() {
		return peso_cultivado;
	}

	public void setPeso_cultivado(double peso_cultivado) {
		this.peso_cultivado = peso_cultivado;
	}
	  
	public double getSupervivencia() {
		return supervivencia;
	}

	public void setSupervivencia(double supervivencia) {
		this.supervivencia = supervivencia;
	}

	public double getPeso_promedio() {
		return peso_promedio;
	}

	public void setPeso_promedio(double peso_promedio) {
		this.peso_promedio = peso_promedio;
	}

	public Crianza(String idCrianza, Date fecha_inicio, Date fecha_cosecha, Date fecha_cosecha_plan, String estadoCrianza,
			int larvas_sembradas, int ejemplares_cultivados, double peso_cultivado, int idEstanque, int idEspecie,double supervivencia,double peso_promedio) {
			super();
			this.idCrianza = idCrianza;
			this.fecha_inicio = fecha_inicio;
			this.fecha_cosecha= fecha_cosecha;
			this.fecha_cosecha_plan= fecha_cosecha_plan;
			this.estadoCrianza= estadoCrianza;
			this.larvas_sembradas= larvas_sembradas;
			this.ejemplares_cultivados= ejemplares_cultivados;
			this.peso_cultivado= peso_cultivado;
			this.idEstanque= idEstanque;
			this.idEspecie= idEspecie;
			this.supervivencia= supervivencia;
			this.peso_promedio= peso_promedio;
	}
	
			
		
		

}
