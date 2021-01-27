package beans;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.TipoCrianza;



public class GestionaTipoCrianza implements Serializable {
	 String idTipoCrianza,nomTipoCrianza,densidad,peso,ciclo,tiempo,recambio,supervivencia,rendimiento,salida,tipoOper;
	    ModelDaoImpl dao;
	    String modelo = "tipo_crianza";   
	    TipoCrianza tipocrianza=null;    
	    
	    ResultSet rs;
	    
	    
	    List<TipoCrianza> tipos = new ArrayList<TipoCrianza>();
	    
	    public GestionaTipoCrianza() {
	    	
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
	                case "T":getTableTipoCrianza();break;//Retorna los registros de grupo en una tabla
	                case "S":getSelectTipoCrianza();break;//Retorna los registros de grupo en un select
	                case "C":CreateTipoCrianza();break;//Registra un nuevo estanque 
	                case "U":UpdateTipoCrianza();break;//Actualiza 
	                case "D":DeleteTipoCrianza();break;//Elimina 
	            }
	        } catch (Exception ex) {
	            setSalida("false,"+ex.getMessage());
	        }
	    }
		

	    //Obtiene todos los tipos de estanque y los llena en una tabla
	    void getTableTipoCrianza()  throws Exception{
	    	
	    	  
	    	
	         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                            + "<thead>"
	                            + "<th class=\"text-center\" style=\"width:10%\">id Grupo</th>"
	                            + "<th class=\"text-center\" style=\"width:15%\">Nombre</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Densidad(ind/m2)</th>"
	                            + "<th class=\"text-center\" style=\"width:15%\">Peso Cosec.(g)</th>"
	                            + "<th class=\"text-center\" style=\"width:15%\">Ciclo Cultiv.</th>"
	                            + "<th class=\"text-center\" style=\"width:15%\">Supervivencia(%)</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
	                            + "</thead><tbody>";

	          GestionaBaseDeDatos.conectar();
	          String sql = "SELECT idTipoCrianza,nombre,densidadSiembra,pesoCosecha,cicloCultivoAnual,tiempoCultivo,"
	          		+ "recambioAgua,supervivencia,rendimiento FROM tipo_crianza WHERE estado=1";
	          
	                rs = GestionaBaseDeDatos.consultar(sql);
	                while(rs.next()){
	                	String id = rs.getString(1);
	                	String nom = rs.getString(2);
	                	int den = rs.getInt(3);
	                	int peso = rs.getInt(4);
	                	int ciclo = rs.getInt(5);
	                	int tiempo = rs.getInt(6);
	                	int recambio = rs.getInt(7);
	                	int supervivencia = rs.getInt(8);
	                	int rendimiento = rs.getInt(9);
	                   
	// Creo el arraylist de TipoEstanque
//	                    		
	                    salidaTabla += "<tr>"
	                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(4) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
	                            + "<td class=\"text-center\" >" + rs.getString(8) +"</td>"
	                            + "<td class=\"text-center\" >\n" 
	                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+nom+"','"+den+"','"+peso+"','"+ciclo+"','"+tiempo+"','"+recambio+"','"+supervivencia+"','"+rendimiento+"')\" >"
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
	    
	    public TipoCrianza ConsultarTipo() throws Exception{
			 GestionaBaseDeDatos.conectar();
	        String sql = "SELECT idTipoCrianza,nombre,densidadSiembra,pesoCosecha,cicloCultivoAnual,tiempoCultivo,recambioAgua,supervivencia,rendimiento FROM tipo_crianza WHERE idTipoCrianza ="+getIdTipoCrianza();
	              rs = GestionaBaseDeDatos.consultar(sql);
	              
	              while(rs.next()){
	           	   tipocrianza= new TipoCrianza(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDouble(8),rs.getDouble(9));
	              }
	        return tipocrianza;
	               
		}
	    
	    //Obtiene todas los Especies y los llena en una select
	    void getSelectTipoCrianza() throws Exception{
	    	String[] campos = {"idTipoCrianza","nombre"};
	        salida = dao.getSelectBasic(modelo,campos);
	    }
	    //Registra un nuevo grupo
	    void CreateTipoCrianza() throws Exception{
	           Object[] campos = {getNomTipoCrianza(),getDensidad(),getPeso(),getCiclo(),getTiempo(),getRecambio(),getSupervivencia(),getRendimiento()};
	           salida = dao.create(modelo,campos);

	    }
	    //Actualiza 
	    void UpdateTipoCrianza() throws Exception{
	    	Object[] campos = {getIdTipoCrianza(),getNomTipoCrianza(),getDensidad(),getPeso(),getCiclo(),getTiempo(),getRecambio(),getSupervivencia(),getRendimiento()};
	        salida = dao.update(modelo,campos);
	    }
	    //Elimina 
	    void DeleteTipoCrianza() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        //Validar que no este siendo utilizado
	        //
	       
	        GestionaBaseDeDatos.ejecutar("call delete_tipo_estanque('"+getIdTipoCrianza()+"')");
	        GestionaBaseDeDatos.desconectar();
	        salida = "true, tipo_estanque Eliminado Correctamente";
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

		public void setNomTipoCrianza(String nomTipoCrianza) {
			this.nomTipoCrianza = nomTipoCrianza;
		}

		public String getDensidad() {
			return densidad;
		}

		public void setDensidad(String densidad) {
			this.densidad = densidad;
		}

		public String getPeso() {
			return peso;
		}

		public void setPeso(String peso) {
			this.peso = peso;
		}

		public String getCiclo() {
			return ciclo;
		}

		public void setCiclo(String ciclo) {
			this.ciclo = ciclo;
		}

		public String getTiempo() {
			return tiempo;
		}

		public void setTiempo(String tiempo) {
			this.tiempo = tiempo;
		}

		public String getRecambio() {
			return recambio;
		}

		public void setRecambio(String recambio) {
			this.recambio = recambio;
		}

		public String getSupervivencia() {
			return supervivencia;
		}

		public void setSupervivencia(String supervivencia) {
			this.supervivencia = supervivencia;
		}

		public String getRendimiento() {
			return rendimiento;
		}

		public void setRendimiento(String rendimiento) {
			this.rendimiento = rendimiento;
		}

		public String getTipoOper() {
			return tipoOper;
		}

		public void setTipoOper(String tipoOper) {
			this.tipoOper = tipoOper;
		}
	    

}
