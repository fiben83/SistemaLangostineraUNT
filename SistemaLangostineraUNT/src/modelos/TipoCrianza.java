package modelos;

public class TipoCrianza {
	
    private String idTipoCrianza;
    private String nomTipoCrianza;
    private int densidad_siembra;
    private double peso_cosecha;
    private int ciclo_cultivo;
    private int tiempo_cultivo;
    private int recambio_agua;
    private double supervivencia;
    private double rendimiento;
	
    
	public TipoCrianza(String idTipoCrianza, String nomTipoCrianza,int densidad_siembra,double peso_cosecha,
			int ciclo_cultivo, int tiempo_cultivo,int recambio_agua,double supervivencia,double rendimiento) {
		super();
		this.idTipoCrianza =idTipoCrianza;
		this.nomTipoCrianza = nomTipoCrianza;
		this.densidad_siembra=densidad_siembra;
		this.peso_cosecha=peso_cosecha;
		this.ciclo_cultivo=ciclo_cultivo;
		this.tiempo_cultivo=tiempo_cultivo;
		this.recambio_agua=recambio_agua;
		this.supervivencia=supervivencia;
		this.rendimiento=rendimiento;
	}


	public String getIdTipoCrianza() {
		return idTipoCrianza;
	}

	public void setIdTipoCrianza(String idTipoCrianza) {
		this.idTipoCrianza = idTipoCrianza;
	}

	public String getNomTipoCrianza() {
		return nomTipoCrianza;
	}

	public void setNomTipoCrianzaCrianza(String nombreCrianza) {
		this.nomTipoCrianza = nombreCrianza;
	}

	public int getDensidad_siembra() {
		return densidad_siembra;
	}

	public void setDensidad_siembra(int densidad_siembra) {
		this.densidad_siembra = densidad_siembra;
	}

	public double getPeso_cosecha() {
		return peso_cosecha;
	}

	public void setPeso_cosecha(int peso_cosecha) {
		this.peso_cosecha = peso_cosecha;
	}

	public int getCiclo_cultivo() {
		return ciclo_cultivo;
	}

	public void setCiclo_cultivo(int ciclo_cultivo) {
		this.ciclo_cultivo = ciclo_cultivo;
	}

	public int getTiempo_cultivo() {
		return tiempo_cultivo;
	}

	public void setTiempo_cultivo(int tiempo_cultivo) {
		this.tiempo_cultivo = tiempo_cultivo;
	}

	public int getRecambio_agua() {
		return recambio_agua;
	}

	public void setRecambio_agua(int recambio_agua) {
		this.recambio_agua = recambio_agua;
	}

	public double getSupervivencia() {
		return supervivencia;
	}

	public void setSupervivencia(int supervivencia) {
		this.supervivencia = supervivencia;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}
	
}

