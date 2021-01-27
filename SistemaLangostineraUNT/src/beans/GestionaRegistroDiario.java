package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.RegistroDiario;

public class GestionaRegistroDiario implements Serializable {
    //Integer ucorrel;
	
    String idRegistroDiario,idCrianza,id,fecha,medicion_promedio_agua,salida,tipoOper;
    String medicion_promedio_temp,medicion_promedio_oxigeno,medicion_promedio_ph,medicion_promedio_salinidad;
    String crianza;
    ModelDaoImpl dao;
    String modelo = "registro_diario";
    RegistroDiario registro_diario;    
    
    ResultSet rs;
    
    
    List<RegistroDiario> registro_diarios= new ArrayList<RegistroDiario>();

	public GestionaRegistroDiario() {
	
	}

	public String getSalida() {
		System.out.println("Ingresó a getSalida");
        Controlador();
        return salida;
	}
	
	public void setSalida(String salida) {
		this.salida = salida;
	}

	void Controlador(){
		System.out.println("Ingresó al controlador");
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
    	
    	      	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla1\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:8%\">Id</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Fecha</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Nivel Agua (cm)</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Temperatura (°C)</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Oxigeno (mg/L)</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">PH</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Salinidad (ppt)</th>"
                            + "<th class=\"text-center\" style=\"width:27%\">Operaciones</th>"
                            
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
         
          String sql = "SELECT idRegistroDiario,idCrianza,idPersona,fecha,medicionPromedioAgua,medicionPromedioTemp,medicionPromedioOxigeno,medicionPromedioPH,medicionPromedioSalinidad FROM registro_diario "
          + " WHERE idCrianza= '"+idCrianza+"' AND estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	 
                	String id = rs.getString(1);
                	String idCrianza = rs.getString(2);
                	String persona= rs.getString(3);
                    Date fecha= rs.getDate(4);
                    String med_prom_agua= rs.getString(5);
                    String med_prom_temp= rs.getString(6);
                    String med_prom_oxigeno= rs.getString(7);
                    String med_prom_ph= rs.getString(8);
                    String med_prom_salinidad= rs.getString(9);
                    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
// Creo el arraylist de Crianzas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" +  fechaFormato.format(fecha) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(7) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(8) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(9) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit_diaria('"+id+"','"+persona+"','"+fecha+"','"+med_prom_agua+"','"+med_prom_temp+"','"+med_prom_oxigeno+"','"+med_prom_ph+"','"+med_prom_salinidad+"')\" >"
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
    	String[] campos = {idRegistroDiario};
        salida = dao.getSelectBasic(modelo,campos);
    }
    
    //Registra un nuevo grupo
    void CreateRegistro() throws Exception{
    	        	
           Object[] campos = {getIdCrianza(), getId(), getFecha(),getMedicion_promedio_agua(), getMedicion_promedio_temp(),getMedicion_promedio_oxigeno(),getMedicion_promedio_ph(),getMedicion_promedio_salinidad()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateRegistro() throws Exception{
//        Object[] campos = {getDescGrupo(),getDetalleGr()};
//        salida = dao.update1(modelo,getIdGrupo(),campos);
        GestionaBaseDeDatos.conectar(); 
        GestionaBaseDeDatos.ejecutar("call update_registro_diario('"+getIdRegistroDiario()+"','"+getFecha()+"','"+getId()+"','"+getMedicion_promedio_agua()+"','"+getMedicion_promedio_temp()+"','"+getMedicion_promedio_oxigeno()+"','"+getMedicion_promedio_ph()+"','"+getMedicion_promedio_salinidad()+"')");
        GestionaBaseDeDatos.desconectar();
        System.out.println("actualizado correctamente");
        salida = "true, Crianza actualizado correctamente";
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

	  
    /*	
	public Integer getUcorrelClase() {
		return ucorrelClase;
	}

	public void setUcorrelClase(Integer ucorrelClase) {
		this.ucorrelClase = ucorrelClase;
	}
*/
  
	
	public String getIdRegistroDiario() {
		return idRegistroDiario;
	}

	public void setIdRegistroDiario(String idRegistroDiario) {
		this.idRegistroDiario = idRegistroDiario;
	}

	public String getIdCrianza() {
		return idCrianza;
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

	public String getMedicion_promedio_agua() {
		return medicion_promedio_agua;
	}

	public void setMedicion_promedio_agua(String medicion_promedio_agua) {
		this.medicion_promedio_agua = medicion_promedio_agua;
	}

	public String getMedicion_promedio_temp() {
		return medicion_promedio_temp;
	}

	public void setMedicion_promedio_temp(String medicion_promedio_temp) {
		this.medicion_promedio_temp = medicion_promedio_temp;
	}

	public String getMedicion_promedio_oxigeno() {
		return medicion_promedio_oxigeno;
	}

	public void setMedicion_promedio_oxigeno(String medicion_promedio_oxigeno) {
		this.medicion_promedio_oxigeno = medicion_promedio_oxigeno;
	}

	public String getMedicion_promedio_ph() {
		return medicion_promedio_ph;
	}

	public void setMedicion_promedio_ph(String medicion_promedio_ph) {
		this.medicion_promedio_ph = medicion_promedio_ph;
	}

	public String getMedicion_promedio_salinidad() {
		return medicion_promedio_salinidad;
	}

	public void setMedicion_promedio_salinidad(String medicion_promedio_salinidad) {
		this.medicion_promedio_salinidad = medicion_promedio_salinidad;
	}
	
	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
	
}
