package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.RegistroSemanal;
import modelos.Usuario;

public class GestionaRegistroSemanal implements Serializable {
    //Integer ucorrel;
	
    String idRegistroSemanal,idCrianza,id,fecha,tamano_promedio, peso_promedio,poblacion,salida,tipoOper;
    String crianza;
    ModelDaoImpl dao;
    String modelo = "registro_semanal";
    RegistroSemanal registro_semanal;    
    
    ResultSet rs;
    
    
    List<RegistroSemanal> registro_diarios= new ArrayList<RegistroSemanal>();

	public GestionaRegistroSemanal() {
	
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
                case "T":getTableRegistros();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectRegistro();break;//Retorna los registros de grupo en un select
                case "C":{
                	System.out.println("Ingresó a la opcion Create");
                	CreateRegistro();
                	getTableRegistros();
                	break;}//Registra un nueva especie
                case "U":{
	                System.out.println("Ingresó a la opcion Update");
	            	UpdateRegistro();
	            	getTableRegistros();
	            	break;}//Actualiza 
                case "D":DeleteRegistro();break;//Elimina
                
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las especies y los llena en una tabla
    void getTableRegistros()  throws Exception{
    	
    	      	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla2\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:8%\">Id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Fecha</th>"
                            + "<th class=\"text-center\" style=\"width:6%\">Semana</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Tamaño Promedio (cm)</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Peso Promedio (gr.)</th>"
                            + "<th class=\"text-center\" style=\"width:13%\">Biomasa (Kg)</th>"
                            + "<th class=\"text-center\" style=\"width:13%\">Supervivencia(%)</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
         
          String sql = "SELECT idRegistroSemanal,idCrianza,idPersona,fecha,tamano_promedio,peso_promedio,poblacionActual,biomasa,supervivencia,semana FROM registro_semanal "
          + " WHERE idCrianza= '"+idCrianza+"' AND estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	 
                	String id = rs.getString(1);
                	String idCrianza = rs.getString(2);
                	String persona = rs.getString(3);
                    Date fecha= rs.getDate(4);
                    String tam_prom= rs.getString(5);
                    String peso_prom= rs.getString(6);
                    String poblacion= rs.getString(7);
                    String biomasa= rs.getString(8);
                    String sup= rs.getString(9);
                    
                    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
// Creo el arraylist de Crianzas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + fechaFormato.format(fecha) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(10) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(8) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(9) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit_semanal('"+id+"','"+persona+"','"+fecha+"','"+tam_prom+"','"+peso_prom+"','"+poblacion+"')\" >"
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
    void getSelectRegistro() throws Exception{
    	String[] campos = {idRegistroSemanal};
        salida = dao.getSelectBasic(modelo,campos);
    }
    
    //Registra un nuevo grupo
    void CreateRegistro() throws Exception{
    	
    	//Para obtener el numero de la semana de forma consecutiva
    	Date fechInicio=ConsultaFechaInicio();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaPrueba= getFecha();
    	Date fechaActual = sdf.parse(fechaPrueba);
    	
        Calendar calendario1 = Calendar.getInstance();
        Calendar calendario2 = Calendar.getInstance();
        calendario1.setTime(fechInicio);
        int diaSem=calendario1.get(Calendar.DAY_OF_WEEK)-1;//como usa el calendario inglés el domingo es el primer dia de semana x eso se resta 1
        if(diaSem==1){
        	 
        	 calendario1.setFirstDayOfWeek(Calendar.MONDAY);
             calendario1.setMinimalDaysInFirstWeek(7);
             calendario2.setFirstDayOfWeek( Calendar.MONDAY);
             calendario2.setMinimalDaysInFirstWeek(7);
        }else if(diaSem==2){
        	
        	 calendario1.setFirstDayOfWeek(Calendar.TUESDAY);
             calendario1.setMinimalDaysInFirstWeek(7);
             calendario2.setFirstDayOfWeek( Calendar.TUESDAY);
             calendario2.setMinimalDaysInFirstWeek(7);
        	}else if(diaSem==3){
        		
        		 calendario1.setFirstDayOfWeek(Calendar.WEDNESDAY);
        	        calendario1.setMinimalDaysInFirstWeek(7);
        	        calendario2.setFirstDayOfWeek( Calendar.WEDNESDAY);
        	        calendario2.setMinimalDaysInFirstWeek(7);
        		}else if(diaSem==4){
        			
        			 	calendario1.setFirstDayOfWeek(Calendar.THURSDAY);
        		        calendario1.setMinimalDaysInFirstWeek(7);
        		        calendario2.setFirstDayOfWeek( Calendar.THURSDAY);
        		        calendario2.setMinimalDaysInFirstWeek(7);
        	      	}else if(diaSem==5){
        	      		
        	      		 calendario1.setFirstDayOfWeek(Calendar.FRIDAY);
        	             calendario1.setMinimalDaysInFirstWeek(7);
        	             calendario2.setFirstDayOfWeek( Calendar.FRIDAY);
        	             calendario2.setMinimalDaysInFirstWeek(7);
        				}else if(diaSem==6){
        					
        					 	calendario1.setFirstDayOfWeek(Calendar.SATURDAY);
        				        calendario1.setMinimalDaysInFirstWeek(7);
        				        calendario2.setFirstDayOfWeek( Calendar.SATURDAY);
        				        calendario2.setMinimalDaysInFirstWeek(7);
        				}else{
        					
        						calendario1.setFirstDayOfWeek(Calendar.SUNDAY);
        				        calendario1.setMinimalDaysInFirstWeek(7);
        				        calendario2.setFirstDayOfWeek( Calendar.SUNDAY);
        				        calendario2.setMinimalDaysInFirstWeek(7);
        				}
                    
        calendario2.setTime(fechaActual);
        calendario1.setTime(fechInicio);
        int f1=calendario1.get(Calendar.WEEK_OF_YEAR);
        int f2=calendario2.get(Calendar.WEEK_OF_YEAR);
        int numSemana=f2-f1+1;
            	    
    	float pob=Float.valueOf(getPoblacion());
    	float biomasa= pob*Float.valueOf(getPeso_promedio())/1000;
    	float larvas=(float)ConsultaPoblacion();
    	float supervivencia=(float)(pob/larvas)*100;
    	
    	 
        GestionaBaseDeDatos.ejecutar("call create_registro_semanal('"+getIdCrianza()+"','"+getId()+"','"+getFecha()+"','"+getTamano_promedio()+"','"+getPeso_promedio()+"','"+getPoblacion()+"','"+biomasa+"','"+supervivencia+"','"+numSemana+"')");
        GestionaBaseDeDatos.desconectar();
        System.out.println("registro semanal registrado correctamente");
        salida = "true, Registro semanal registrado correctamente";
    }
    
    public int ConsultaPoblacion() throws Exception{
		 GestionaBaseDeDatos.conectar();
		 int semb=0;
       String sql = "SELECT larvas_sembradas FROM crianza WHERE idCrianza ="+getIdCrianza();
             rs = GestionaBaseDeDatos.consultar(sql);
             
             while(rs.next()){
          	   semb= rs.getInt(1);
          	 }
             return semb;
    }
    
    public Date ConsultaFechaInicio() throws Exception{
		 GestionaBaseDeDatos.conectar();
		 Date fech=null;
      String sql = "SELECT fecha_inicio FROM crianza WHERE idCrianza ="+getIdCrianza();
            rs = GestionaBaseDeDatos.consultar(sql);
            
            while(rs.next()){
         	   fech= rs.getDate(1);
         	 }
            return fech;
   }
    
    //Actualiza 
    void UpdateRegistro() throws Exception{
    	//Para obtener el numero de la semana de forma consecutiva
    	Date fechInicio=ConsultaFechaInicio();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaPrueba= getFecha();
    	Date fechaActual = sdf.parse(fechaPrueba);
    	
        Calendar calendario1 = Calendar.getInstance();
        Calendar calendario2 = Calendar.getInstance();
        calendario1.setTime(fechInicio);
        int diaSem=calendario1.get(Calendar.DAY_OF_WEEK)-1;//como usa el calendario inglés el domingo es el primer dia de semana x eso se resta 1
        if(diaSem==1){
        	 
        	 calendario1.setFirstDayOfWeek(Calendar.MONDAY);
             calendario1.setMinimalDaysInFirstWeek(7);
             calendario2.setFirstDayOfWeek( Calendar.MONDAY);
             calendario2.setMinimalDaysInFirstWeek(7);
        }else if(diaSem==2){
        	
        	 calendario1.setFirstDayOfWeek(Calendar.TUESDAY);
             calendario1.setMinimalDaysInFirstWeek(7);
             calendario2.setFirstDayOfWeek( Calendar.TUESDAY);
             calendario2.setMinimalDaysInFirstWeek(7);
        	}else if(diaSem==3){
        		
        		 calendario1.setFirstDayOfWeek(Calendar.WEDNESDAY);
        	        calendario1.setMinimalDaysInFirstWeek(7);
        	        calendario2.setFirstDayOfWeek( Calendar.WEDNESDAY);
        	        calendario2.setMinimalDaysInFirstWeek(7);
        		}else if(diaSem==4){
        			
        			 	calendario1.setFirstDayOfWeek(Calendar.THURSDAY);
        		        calendario1.setMinimalDaysInFirstWeek(7);
        		        calendario2.setFirstDayOfWeek( Calendar.THURSDAY);
        		        calendario2.setMinimalDaysInFirstWeek(7);
        	      	}else if(diaSem==5){
        	      		
        	      		 calendario1.setFirstDayOfWeek(Calendar.FRIDAY);
        	             calendario1.setMinimalDaysInFirstWeek(7);
        	             calendario2.setFirstDayOfWeek( Calendar.FRIDAY);
        	             calendario2.setMinimalDaysInFirstWeek(7);
        				}else if(diaSem==6){
        					
        					 	calendario1.setFirstDayOfWeek(Calendar.SATURDAY);
        				        calendario1.setMinimalDaysInFirstWeek(7);
        				        calendario2.setFirstDayOfWeek( Calendar.SATURDAY);
        				        calendario2.setMinimalDaysInFirstWeek(7);
        				}else{
        					
        						calendario1.setFirstDayOfWeek(Calendar.SUNDAY);
        				        calendario1.setMinimalDaysInFirstWeek(7);
        				        calendario2.setFirstDayOfWeek( Calendar.SUNDAY);
        				        calendario2.setMinimalDaysInFirstWeek(7);
        				}
                    
        calendario2.setTime(fechaActual);
        calendario1.setTime(fechInicio);
        int f1=calendario1.get(Calendar.WEEK_OF_YEAR);
        int f2=calendario2.get(Calendar.WEEK_OF_YEAR);
        int numSemana=f2-f1+1;
    	
       //calculando la biomasa y supervivencia
    	float pob=Float.valueOf(getPoblacion());
    	float biomasa= pob*Float.valueOf(getPeso_promedio())/1000;
    	float larvas=(float)ConsultaPoblacion();
    	float supervivencia=(float)(pob/larvas)*100;
    	
    	
    	GestionaBaseDeDatos.ejecutar("call update_registro_semanal('"+getIdRegistroSemanal()+"','"+getFecha()+"','"+getId()+"','"+getTamano_promedio()+"','"+getPeso_promedio()+"','"+getPoblacion()+"','"+biomasa+"','"+supervivencia+"','"+numSemana+"')");
        GestionaBaseDeDatos.desconectar();
        System.out.println("registro semanal actualizado correctamente");
        salida = "true, Registro semanal actualizado correctamente";
    }
    //Elimina 
    void DeleteRegistro() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteGranja. El id es "+getIdCrianza());
        GestionaBaseDeDatos.ejecutar("call delete_granja('"+getIdCrianza()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true, Crianza Eliminada Correctamente";
    }
    
	public String getIdCrianza() {
		return idCrianza;
	}

	public String getIdRegistroSemanal() {
		return idRegistroSemanal;
	}

	public void setIdRegistroSemanal(String idRegistroSemanal) {
		this.idRegistroSemanal = idRegistroSemanal;
	}

	public void setIdCrianza(String idCrianza) {
		this.idCrianza = idCrianza;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getTamano_promedio() {
		return tamano_promedio;
	}

	public void setTamano_promedio(String tamano_promedio) {
		this.tamano_promedio = tamano_promedio;
	}

	public String getPeso_promedio() {
		return peso_promedio;
	}

	public void setPeso_promedio(String peso_promedio) {
		this.peso_promedio = peso_promedio;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
	
}
