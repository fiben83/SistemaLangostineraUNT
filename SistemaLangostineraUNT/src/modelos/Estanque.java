package modelos;

public class Estanque {
	private String idEstanque;
	private String descEstanque;
	private Double area;
	private String idGranja;
	
	public String getIdEstanque() {
		return idEstanque;
	}
	
	public void setIdEstanque(String idEstanque) {
		this.idEstanque = idEstanque;
	}
	
	public String getDescEstanque() {
		return descEstanque;
	}
	
	public void setDescEstanque(String descEstanque) {
		this.descEstanque = descEstanque;
	}
			
	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getIdGranja() {
		return idGranja;
	}

	public void setIdGranja(String idGranja) {
		this.idGranja = idGranja;
	}

	public Estanque(String idEstanque, String descEstanque) {
		super();
		this.idEstanque = idEstanque;
		this.descEstanque = descEstanque;
	}

	public Estanque(String idEstanque, String descEstanque, Double area, String idGranja) {
		super();
		this.idEstanque = idEstanque;
		this.descEstanque = descEstanque;
		this.area= area;
		this.idGranja = idGranja;
	}

	
}
