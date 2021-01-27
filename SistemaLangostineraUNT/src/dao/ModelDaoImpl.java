package dao;
import GestionDeDatos.GestionaBaseDeDatos;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelDaoImpl implements ModelDao{
	
	//@Override
    public ResultSet getDataOperario(String modelo, String[] campos) throws Exception{
        ResultSet rs;
        String op="OPERARIO";
        GestionaBaseDeDatos.conectar();
        String sql = "Select "; // se quito el campo id
        for(int i=0;i<campos.length;i++){
        	
            sql += campos[i] ;
            if (i<campos.length-1) {
            	sql += ",";
            }else {
            	
            }
        }
        System.out.println(sql);
        sql += " FROM "+modelo+" WHERE estado=1 AND tipo_trabajador= 'OPERARIO'";
        System.out.println(sql);
        rs = GestionaBaseDeDatos.consultar(sql);
        return rs;
    }

	    //@Override
	    public ResultSet getData(String modelo, String[] campos) throws Exception{
	        ResultSet rs;
	        GestionaBaseDeDatos.conectar();
	        String sql = "Select "; // se quito el campo id
	        for(int i=0;i<campos.length;i++){
	        	
	            sql += campos[i] ;
	            if (i==0) {
	            	sql += ",";
	            }else {
	            	
	            }
	        }
	        sql += " FROM "+modelo+" WHERE estado=1";
	        rs = GestionaBaseDeDatos.consultar(sql);
	        return rs;
	    }
	    
	    //@Override
	    public ResultSet getDataAnio(String modelo, String[] campos) throws Exception{
	        ResultSet rs;
	        GestionaBaseDeDatos.conectar();
	        String sql = "Select DISTINCT YEAR("; // se quito el campo id
	        for(int i=0;i<campos.length;i++){
	        	
	            sql += campos[i] ;
	            sql+=")";
	            if (i<campos.length-1) {
	            	sql += ",";
	            }else {
	            	
	            }
	        }
	        sql += " FROM "+modelo+" WHERE estado=1";
	        System.out.print("sql "+sql);
	        rs = GestionaBaseDeDatos.consultar(sql);
	        return rs;
	    }
	      
	    @Override
	    public String getDataTableBasic(String modelo){
	        try{
	            String[] campos = {"nombre"};
	            ResultSet rs = this.getData(modelo, campos);
	            String salida = "";
	            salida = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                        + "<thead><th class=\"text-center\">Nombre</th>"
	                        + "<th class=\"text-center\">Operaciones</th>"
	                        + "</thead><tbody>";
	            while(rs.next()){
	                Integer id = Integer.parseInt(rs.getString("id"));
	                String nombre = rs.getString("nombre");
	                salida += "<tr><th class=\"text-center\">" + nombre +"</th>"
	                        + "<td class=\"text-center\">\n" +
	                          "<button class=\"btn btn-xs btn-warning\" onclick=\"edit("+id +",'"+nombre+"')\"><span class=\"glyphicon glyphicon-edit\"></span> Modificar</button>\n" +
	                          "<button class=\"btn btn-xs btn-danger\" onclick=\"remove("+id+")\"><span class=\"glyphicon glyphicon-trash\"></span> Eliminar</button>\n" +
	                          "</td>"
	                        + "</tr>";
	            }
	            salida += "</tbody></table>";
	            GestionaBaseDeDatos.desconectar();
	            return salida;
	        }catch(Exception e){
	            return e.getMessage();
	        }
	    }

	    @Override
	    public String create(String modelo,Object[] campos) throws Exception  {
	            GestionaBaseDeDatos.conectar();
	            String sql = "call create_"+modelo+"(";//procedimiento almacenado call create_especie()
	            sql += this.processValues(campos);
	            
	            GestionaBaseDeDatos.ejecutar(sql);
	            GestionaBaseDeDatos.desconectar();
	            return "true,"+modelo + " registrado correctamente";
	    }

	    @Override
	    public String update(String modelo,Object[] campos) throws Exception {
	            GestionaBaseDeDatos.conectar();
	            ResultSet rs;
	            String sql = "call update_"+modelo+"(";
	            sql += this.processValues(campos);
	            GestionaBaseDeDatos.ejecutar(sql);
	            GestionaBaseDeDatos.desconectar();
	            return "true,"+modelo + " actualizado correctamente";
	    }

	    
	    public String update1(String modelo,String id,Object[] campos) throws Exception {
	            GestionaBaseDeDatos.conectar();
	            ResultSet rs;
	            String sql = "call update_"+modelo+"("+id+",";
	            sql += this.processValues(campos);
	            GestionaBaseDeDatos.ejecutar(sql);
	            GestionaBaseDeDatos.desconectar();
	            return "true,"+modelo + " actualizado correctamente";
	    }
    
	    
	    @Override
	    public String remove(String modelo, Integer id) throws Exception{
	            GestionaBaseDeDatos.conectar();
	            ResultSet rs;
	            String sql = "UPDATE "+modelo+" set estado=0 WHERE id="+id;
	            GestionaBaseDeDatos.ejecutar(sql);
	            GestionaBaseDeDatos.desconectar();
	            return "true,"+modelo + " eliminado correctamente";
	    }
	    @Override
	    public void removeRow(String modelo, Integer id) {
	        try {
	            GestionaBaseDeDatos.conectar();
	            ResultSet rs;
	            String sql = "DELETE FROM "+modelo+" WHERE id="+id;
	            GestionaBaseDeDatos.ejecutar(sql);
	            GestionaBaseDeDatos.desconectar();
	        } catch (Exception ex) {
	            Logger.getLogger(ModelDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    @Override
	    public String getSelectBasic(String modelo, String[] campos ) throws Exception {
	      try{ 
	    	  ResultSet rs =null;
	            if(modelo.equals("persona")) {
	            	rs=this.getDataOperario(modelo, campos);
	            }
	            else {
	            	 rs = this.getData(modelo, campos);
	            }
	           
	            String salida = "";
	            if(modelo.equals("persona")) {
	            	while(rs.next()){
		                Integer id= Integer.parseInt(rs.getString(1));
		                String nom= rs.getString(2);
		                String ape_paterno= rs.getString(3);
		                String ape_materno= rs.getString(4);
		                salida += "<option value=\""+id+"\">"+nom+" "+ape_paterno+" "+ape_materno+"</option>";
		            }
	            }
	            else {
	            	while(rs.next()){
		                Integer id= Integer.parseInt(rs.getString(1));
		                String nom= rs.getString(2);
		                salida += "<option value=\""+id+"\">"+nom+"</option>";
		            }
	            }
	                     
	             GestionaBaseDeDatos.desconectar();
	            return salida;
	        }catch(Exception e){
	            return e.getMessage();
	        }
	    }
	    
	    @Override
	    public String getSelectBasicAnio(String modelo, String[] campos ) throws Exception {
	      try{ 
	    	  ResultSet rs =null;
	            if(modelo.equals("crianza")) {
	            	rs=this.getDataAnio(modelo, campos);
	            }
	            else {
	            	 rs = this.getData(modelo, campos);
	            }
	           
	            String salida = "";
	            if(modelo.equals("crianza")) {
	            	while(rs.next()){
		                
		                String annio= rs.getString(1);
		                salida += "<option value=\""+annio+"\">"+annio+"</option>";
		            }
	            }
	            else {
	            	while(rs.next()){
		                Integer id= Integer.parseInt(rs.getString(1));
		                String nom= rs.getString(2);
		                salida += "<option value=\""+id+"\">"+nom+"</option>";
		            }
	            }
	                     
	             GestionaBaseDeDatos.desconectar();
	            return salida;
	        }catch(Exception e){
	            return e.getMessage();
	        }
	    }
	    
	    //Metodos reutilizados en la clase
	     public String processValues(Object[] values){
	        String salida = "";
	        for(int i=0;i<values.length;i++){
	            String value;
	            if(values[i] instanceof String || values[i] instanceof Date){
	                value = value = "'" + values[i] + "'";
	            }else{
	                value = String.valueOf(values[i]);
	            }
	            if(i<values.length -1){ value+= ",";}//Proceso un campo
	            else{ value+=")";}//proceso el ultimo campo
	            salida+=value;
	        }
	        return salida;
	    }
	
}
