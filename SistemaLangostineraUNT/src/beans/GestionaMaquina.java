package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Maquina;

public class GestionaMaquina implements Serializable {
    //Integer ucorrel;
    String idMaquina,nombre,descripcion,cantidad,salida,tipoOper;
    ModelDaoImpl dao;
    String modelo = "Maquina";
    Maquina Maquina;    
    
    ResultSet rs;
    
    
    List<Maquina> maquinas = new ArrayList<Maquina>();

	public GestionaMaquina() {
	
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
                case "T":getTableMaquinas();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectMaquina();break;//Retorna los registros de grupo en un select
                case "C":CreateMaquina();break;//Registra un nueva especie
                case "U":UpdateMaquina();break;//Actualiza 
                case "D":DeleteMaquina();break;//Elimina 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las especies y los llena en una tabla
    void getTableMaquinas()  throws Exception{
    	
    	  
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:10%\">id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:27%\">Nombre</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Descripción</th>"
                            + "<th class=\"text-center\" style=\"width:8%\">Cantidad</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idMaquina,nombre,descripcion, cantidad FROM maquina "
                     + " WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                    String nom= rs.getString(2);
                    String descr= rs.getString(3);
                    String cant= rs.getString(4);
// Creo el arraylist de Especies
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(4) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+nom+"','"+descr+"','"+cant+"')\" >"
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
    void getSelectMaquina() throws Exception{
    	String[] campos = {idMaquina,nombre};
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nuevo grupo
    void CreateMaquina() throws Exception{
        	
           Object[] campos = {getNombre(),getDescripcion(),getCantidad()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateMaquina() throws Exception{
    	
    	 Object[] campos = {getIdMaquina(), getNombre(),getDescripcion(),getCantidad()};
         salida = dao.update(modelo,campos);
    }
    //Elimina 
    void DeleteMaquina() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteEspecie. El id es "+getIdMaquina());
        GestionaBaseDeDatos.ejecutar("call delete_especie('"+getIdMaquina()+"')");
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
    
    
	public String getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(String idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    

}
