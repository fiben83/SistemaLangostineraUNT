package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Especie;

public class GestionaEspecie implements Serializable {
    //Integer ucorrel;
    String idEspecie,nomEspecie,descEspecie,salida,tipoOper;
    ModelDaoImpl dao;//para implementar los metodos de GestionaEspecie
    String modelo = "especie";
    Especie especie;    
    
    ResultSet rs;
    
    List<Especie> especies = new ArrayList<Especie>();

	public GestionaEspecie() {
	
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
                case "T":getTableEspecies();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectEspecie();break;//Retorna los registros de grupo en un select
                case "C":CreateEspecie();break;//Registra un nueva especie
                case "U":UpdateEspecie();break;//Actualiza 
                case "D":DeleteEspecie();break;//Elimina 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las especies y los llena en una tabla
    void getTableEspecies()  throws Exception{
    	
    	  
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:8%\">id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Nombre común</th>"
                            + "<th class=\"text-center\" style=\"width:52%\">Descripción</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idEspecie,nombre, descripcion FROM especie "
                     + " WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1); //guarda el idEspecie
                    String nom= rs.getString(2);//guarda el nombre
                    String descr= rs.getString(3);//guarda la descripcion
// Creo el arraylist de Especies
                   		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+nom+"','"+descr+"')\" >"
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
                GestionaBaseDeDatos.desconectar();//este metodo desconecta la conexion al mysql
    }
    //Obtiene todas los Especies y los llena en una select
    void getSelectEspecie() throws Exception{
    	String[] campos = {"idEspecie","nombre"};
    	
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nuevo grupo
    void CreateEspecie() throws Exception{
        	
           Object[] campos = {getNomEspecie(), getDescEspecie()};//arreglo que guarda el nombre de la especie
           salida = dao.create(modelo,campos);
    }
    //Actualiza 
    void UpdateEspecie() throws Exception{

    	Object[] campos = {getIdEspecie(),getNomEspecie(), getDescEspecie()};//arreglo que guarda campos de la especie
        salida = dao.update(modelo,campos);
            
    }
    //Elimina 
    void DeleteEspecie() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteEspecie. El id es "+getIdEspecie());
        GestionaBaseDeDatos.ejecutar("call delete_especie('"+getIdEspecie()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Especie Eliminado Correctamente";
    }
    
    
/*	
	public Integer getUcorrelClase() {
		return ucorrelClase;
	}

	public void setUcorrelClase(Integer ucorrelClase) {
		this.ucorrelClase = ucorrelClase;
	}
*/    
    
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

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    

}
