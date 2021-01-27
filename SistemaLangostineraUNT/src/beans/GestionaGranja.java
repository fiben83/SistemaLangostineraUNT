package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Granja;

public class GestionaGranja implements Serializable {
    //Integer ucorrel;
    String idGranja,nomGranja,dirGranja,salida,tipoOper;
    ModelDaoImpl dao;
    String modelo = "granja";
    Granja granja;    
    
    ResultSet rs;
    
    
    List<Granja> granjas = new ArrayList<Granja>();

	public GestionaGranja() {
	
	}

	public String getSalida() {
		
        Controlador();
        return salida;
	}
	
	public void setSalida(String salida) {
		this.salida = salida;
	}

	void Controlador(){
        try {
            dao = new ModelDaoImpl();
            switch(getTipoOper()){
                case "T":getTableGranjas();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectGranja();break;//Retorna los registros de grupo en un select
                case "C":CreateGranja();break;//Registra un nueva especie
                case "U":UpdateGranja();break;//Actualiza 
                case "D":DeleteGranja();break;//Elimina 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las especies y los llena en una tabla
    void getTableGranjas()  throws Exception{
    	
    	  
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:10%\">id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Nombre</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Direccion</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idGranja,nombre,direccion FROM granja "
          + " WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                    String nom= rs.getString(2);
                    String dir= rs.getString(3);
// Creo el arraylist de Especies
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                           + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+nom+"','"+dir+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                            +   "</button>\n" 
                            +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                            +   "</button>\n" 
                            +  "</td>"
                            + "</tr>";

                }

                
                salidaTabla += "</tbody></table>";
                setSalida(salidaTabla);
                GestionaBaseDeDatos.desconectar();
    }
    //Obtiene todas los Especies y los llena en una select
    void getSelectGranja() throws Exception{
    	String[] campos = {"idGranja","nombre"};
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nuevo grupo
    void CreateGranja() throws Exception{
        	
           Object[] campos = {getNomGranja(),getDirGranja()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateGranja() throws Exception{
    	Object[] campos = {getIdGranja(), getNomGranja(),getDirGranja()};
        salida = dao.update(modelo,campos);
    }
    //Elimina 
    void DeleteGranja() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteGranja. El id es "+getIdGranja());
        GestionaBaseDeDatos.ejecutar("call delete_granja('"+getIdGranja()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true, Granja Eliminada Correctamente";
    }
    
    
    
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

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    

}

