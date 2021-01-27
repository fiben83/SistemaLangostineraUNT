package beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Estanque;
import modelos.Granja;
import modelos.Persona;

public class GestionaEstanque implements Serializable {
	 	String idEstanque,descEstanque,idGranja,descGranja,area,salida,tipoOper;
	    ModelDaoImpl dao;
	    String modelo = "estanque";
	    Estanque estanque;    
	    
	    ResultSet rs;
	    	    
	    List<Estanque> Estanques = new ArrayList<Estanque>();
	    
	    public GestionaEstanque() {
	    	
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
	                case "T":getTableEstanque();break;//Retorna los registros de grupo en una tabla
	                case "S":getSelectEstanque();break;//Retorna los registros de grupo en un select
	                case "C":CreateEstanque();break;//Registra un nuevo estanque 
	                case "U":UpdateEstanque();break;//Actualiza 
	                case "D":DeleteEstanque();break;//Elimina 
	                case "E":ConsultaEstanques();break;//Elimina
	            }
	        } catch (Exception ex) {
	            setSalida("false,"+ex.getMessage());
	        }
	    }
		

	    //Obtiene todos las especies y los llena en una tabla
	 void getTableEstanque()  throws Exception{
	    		    	
	    String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                            + "<thead>"
	                            + "<th class=\"text-center\" style=\"width:10%\">id Grupo</th>"
	                            + "<th class=\"text-center\" style=\"width:35%\">Granja</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Nombre</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Area(Ha.)</th>"
	                            + "<th class=\"text-center\" style=\"width:25%\">Operaciones</th>"
	                            + "</thead><tbody>";
	    	
	          GestionaBaseDeDatos.conectar();
	          String sql = "SELECT idEstanque,g.nombre,e.nombre,e.area,e.idGranja FROM granja AS g "
	                     + " INNER JOIN estanque AS e ON g.idGranja=e.idGranja WHERE e.estado=1 AND g.estado=1 ORDER BY g.nombre ASC";
	          
	                rs = GestionaBaseDeDatos.consultar(sql);
	                while(rs.next()){
	                	String id = rs.getString(1);
	                	String nomGranja = rs.getString(2);
	                    String descr= rs.getString(3);
	                   String area= rs.getString(4);
	                    String idGranja= rs.getString(5);
	// Creo el arraylist de Estanque
                  		
	                    salidaTabla += "<tr>"
	                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(4) +"</td>"
	                            + "<td class=\"text-center\" >\n" 
	                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+descr+"','"+idGranja+"','"+area+"')\" >"
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
	    void getSelectEstanque() throws Exception{
	    	String[] campos = {"idEstanque","nombre"};
	        salida = dao.getSelectBasic(modelo,campos);
	    }
	    //Consulta un estanque libre
	    public ArrayList<Estanque> ConsultaEstanques() throws Exception{
	    	//Map<String, String> parametros= new HashMap<String, Object>();
			ArrayList<Estanque>lista=new ArrayList<Estanque>();
			
			 GestionaBaseDeDatos.conectar();
	         String sql = "SELECT DISTINCT estanque.idEstanque,estanque.nombre FROM estanque "
	         		    		+ "WHERE idGranja ="+getIdGranja()+" ORDER BY estanque.nombre ASC";
	               rs = GestionaBaseDeDatos.consultar(sql);
	               
	               while(rs.next()){
	            	  Estanque e = new Estanque(rs.getString(1),rs.getString(2));
	            	    
	            	  lista.add(e);
	               }
	              
	          return lista;
	                
		}
	  
	    //Registra un nuevo grupo
	    void CreateEstanque() throws Exception{
	        	
	           Object[] campos = {getDescEstanque(),getArea(),getIdGranja()};
	           salida = dao.create(modelo,campos);

	    }
	    //Actualiza 
	    void UpdateEstanque() throws Exception{

	    	 Object[] campos = {getIdEstanque(), getDescEstanque(),getArea(),getIdGranja()};
	         salida = dao.update(modelo,campos);
	    }
	    //Elimina 
	    void DeleteEstanque() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        //Validar que no este siendo utilizado
	        //
	        System.out.println("DeleteEstanque. El id es "+getIdEstanque());
	        GestionaBaseDeDatos.ejecutar("call delete_estanque('"+getIdEstanque()+"')");
	        GestionaBaseDeDatos.desconectar();
	        salida = "true, Estanque Eliminado Correctamente";
	    }
	    
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
		
		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getIdGranja() {
			return idGranja;
		}

		public void setIdGranja(String idGranja) {
			this.idGranja = idGranja;
		}

		public String getTipoOper() {
			return tipoOper;
		}

		public void setTipoOper(String tipoOper) {
			this.tipoOper = tipoOper;
		}
	    

	


}
