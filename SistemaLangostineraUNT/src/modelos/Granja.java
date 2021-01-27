package modelos;

public class Granja {
	 private String idGranja;
	 private String nomGranja;
	 private String dirGranja;
	 
	 	
	public String getIdGranja() {
		return idGranja;
	}

	public void setIdGranja(String idGranja) {
		this.idGranja = idGranja;
	}

	public String getNomGranja() {
		return nomGranja;
	}

	public void setNomGranja(String nomGranja) {
		this.nomGranja = nomGranja;
	}

	public String getDirGranja() {
		return dirGranja;
	}

	public void setDirGranja(String dirGranja) {
		this.dirGranja = dirGranja;
	}

		public Granja(String idGranja, String nomGranja,String dirGranja) {
			super();
			this.idGranja = idGranja;
			this.nomGranja =nomGranja;
			this.dirGranja=dirGranja;
		}
		
		
		

}
