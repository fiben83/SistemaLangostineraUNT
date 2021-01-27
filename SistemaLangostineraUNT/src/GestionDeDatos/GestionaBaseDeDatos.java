package GestionDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GestionaBaseDeDatos {
	ResultSet rs;
	public static Connection conexion;
      
    public static Connection conectar() {
    	  String url="jdbc:mysql://localhost:3306/bdlangostineraunt";//cadena de conexion
    	  String usr="root";
    	  String pwd="fiben";//tu contraseña es vacía
    	 try  { 
    		 Class.forName("com.mysql.jdbc.Driver");
    		 conexion=DriverManager.getConnection(url,usr,pwd);//hace la conexion al Mysql
    		 return conexion;  
    	  }
    	 catch(SQLException ex)  {
    		 System.out.println(ex.getMessage());
             //throw new Exception("Imposible realizar conexion con la BD"+ex.getMessage());
             
    	 }
    	 
    	 catch(ClassNotFoundException ex)  {
    		 System.out.println(ex.getMessage());
             //throw new Exception("Imposible realizar conexion con la BD"+ex.getMessage());

    	 }
    	 return null;
    }
    
    public static ResultSet consultar(String sql) throws Exception{
        try{        
        	conectar();
            Statement st = conexion.createStatement();
            ResultSet resultado = st.executeQuery(sql);
            return resultado;
        }
        catch (Exception e) {
   		  System.out.println(e.getMessage());
            throw new Exception(e.getMessage());            
        }
    }
    
    public static int ejecutar(String sql) throws Exception{
        try{
            Statement st = conexion.createStatement();
            int resultado = st.executeUpdate(sql);
            return resultado;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());            
        }
    }
    
    public static void desconectar() throws Exception{
        try{
            conexion.close();
        }
        catch(Exception e){
            throw new Exception("Error al desconectar con la BD"+e.getMessage());
        }
    }
    
    public static String consultaNumNuevoIngresoAlmacen() throws  Exception{
    	ResultSet rs=consultar("SELECT RIGHT(CONCAT('00000',LTRIM(CAST(valor+1 AS CHAR))),6) FROM Parametro WHERE codigo=20001");  
        if(rs.next()){
           	return rs.getString(1);
         }
        else
        	return null;
    }
    
        
   	    
    public static Connection conn()
    {
        return conexion;
    }
	
}
