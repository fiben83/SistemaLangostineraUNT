package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.TipoCrianza;
import modelos.TipoPersonal;

public class GestionaTipoPersonal implements Serializable {
    //Integer ucorrel;
    String id,nombre,salida,tipoOper;
    ModelDaoImpl dao;//para implementar los metodos de GestionaEspecie
    String modelo = "tipo_personal";
   TipoPersonal TipoPersonal;    
    
    ResultSet rs;
    
    List<TipoPersonal> especies = new ArrayList<TipoPersonal>();

	public GestionaTipoPersonal() {
	
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
                case "T":getTableTipoPersonal();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectTipoPersonal();break;//Retorna los registros de grupo en un select
                case "C":CreateTipoPersonal();break;//Registra un nueva especie
                case "U":UpdateTipoPersonal();break;//Actualiza 
                case "D":DeleteTipoPersonal();break;//Elimina 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	
	/* public TipoPersonal ConsultarTipo() throws Exception{
		 GestionaBaseDeDatos.conectar();
        String sql = "SELECT idTipoCrianza,nombre,densidadSiembra,pesoCosecha,cicloCultivoAnual,tiempoCultivo,recambioAgua,supervivencia,rendimiento FROM tipo_crianza WHERE idTipoCrianza ="+getIdTipoCrianza();
              rs = GestionaBaseDeDatos.consultar(sql);
              
              while(rs.next()){
           	   tipocrianza= new TipoCrianza(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDouble(8),rs.getDouble(9));
              }
        return tipocrianza;
               
	}*/
	
    //Obtiene todos las especies y los llena en una tabla
    void getTableTipoPersonal()  throws Exception{
    	
    	  
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:8%\">id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Nombre</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT id,nombre FROM tipo_personl WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1); //guarda el id
                    String nom= rs.getString(2);//guarda el nombre
                    
// Creo el arraylist de Especies
                   		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                           
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+nom+"')\" >"
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
    void getSelectTipoPersonal() throws Exception{
    	String[] campos = {"id","nombre"};
    	
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nuevo grupo
    void CreateTipoPersonal() throws Exception{
        	
           Object[] campos = {getNombre()};//arreglo que guarda el nombre de la especie
           salida = dao.create(modelo,campos);
    }
    //Actualiza 
    void UpdateTipoPersonal() throws Exception{

    	Object[] campos = {getId(),getNombre()};//arreglo que guarda campos de la especie
        salida = dao.update(modelo,campos);
            
    }
    //Elimina 
    void DeleteTipoPersonal() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        //System.out.println("DeleteEspecie. El id es "+getIdEspecie());
        GestionaBaseDeDatos.ejecutar("call delete_especie('"+getId()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Especie Eliminado Correctamente";
    }
    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    

}
