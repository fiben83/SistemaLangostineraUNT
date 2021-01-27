package modelos;

public class Especie {
	
    private String idEspecie;
    private String nomEspecie;
    private String descEspecie;
    
	public String getIdEspecie() {
		return idEspecie;
	}
	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}
	
	public String getNomEspecie() {
		return nomEspecie;
	}
	public void setNomEspecie(String nomEspecie) {
		this.nomEspecie = nomEspecie;
	}
	public String getDescEspecie() {
		return descEspecie;
	}
	public void setDescEspecie(String descEspecie) {
		this.descEspecie = descEspecie;
	}
	public Especie(String idEspecie, String nomEspecie, String descEspecie) {//metodo constuctor de laclase especie
		super();
		this.idEspecie = idEspecie;
		this.descEspecie = descEspecie;
		this.nomEspecie= nomEspecie;
	}
	


}
