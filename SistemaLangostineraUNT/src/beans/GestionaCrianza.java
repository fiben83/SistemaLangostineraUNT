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
import modelos.Crianza;

public class GestionaCrianza implements Serializable {
    //Integer ucorrel;
	
    String idCrianza,idTipoCrianza,idEstanque,idEspecie,larvas_sembradas, ejemplares_cosechados, peso_cosechado,supervivencia,peso_promedio,salida,tipoOper;
    String fecha_inicio, fecha_cosecha, fecha_cosecha_plan, estadoCrianza;
    ModelDaoImpl dao;
    String modelo = "crianza";
    Crianza crianza;   
    ResultSet rs;
        
    List<Crianza> crianzas = new ArrayList<Crianza>();

	public GestionaCrianza() {
		
	}

	public String getSalida() {
		System.out.println("entró a getSalida");
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
            	
                case "T":getTableCrianzas();System.out.println("entro a T");break;//Retorna los registros de grupo en una tabla
                case "E":getListadoCrianzas();System.out.println("entro a E");break;//listado crianzas para reporte
                case "P":getListadoParametros();System.out.println("entro a P");break;//listado crianzas para reporte
                case "S":getSelectCrianza();break;//Retorna los registros de grupo en un select
                case "A":getSelectAnio();break;// Retorna el año de las crianzas
                case "C":CreateCrianza();System.out.println("entro a C");break;//Registra un nueva especie
                case "U":UpdateCrianza();break;//Actualiza 
                case "D":DeleteCrianza();break;//Elimina 
            }
        } catch (Exception ex) {
        	
            setSalida("false,"+ex.getMessage());
        }
    }
	
	//Obtiene todos las especies y los llena en una tabla
		void getListadoParametros() throws Exception{
					System.out.println("entro a getListadoParametros");
			
				String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
		                + "<thead>"
		                + "<th class=\"text-center\" style=\"width:5%\">Id</th>"
		                + "<th class=\"text-center\" style=\"width:17%\">Granja</th>"
		                + "<th class=\"text-center\" style=\"width:10%\">Estanque</th>"
		                + "<th class=\"text-center\" style=\"width:10%\">Inicio</th>"
		                + "<th class=\"text-center\" style=\"width:10%\">Estado</th>"
		                + "<th class=\"text-center\" style=\"width:25%\">Parametros</th>"
		                + "<th class=\"text-center\" style=\"width:23%\">Mediciones</th>"
		                + "</thead><tbody>";

		GestionaBaseDeDatos.conectar();
		String sql = "SELECT idCrianza,estanque.nombre,fecha_inicio, fecha_cosecha, fecha_cosecha_plan, estadoCrianza,larvas_sembradas,ejemplares_cosechados,peso_cosechado,granja.nombre FROM crianza "
			  + " INNER JOIN estanque ON crianza.idEstanque=estanque.idEstanque "
			  + " INNER JOIN granja ON estanque.idGranja=granja.idGranja WHERE crianza.estado=1";

		    rs = GestionaBaseDeDatos.consultar(sql);
		    while(rs.next()){
		    	
		    	String id = rs.getString(1);
		    	String estanque= rs.getString(2);
		        Date fech_ini= rs.getDate(3);
		        Date fech_cos= rs.getDate(4);
		        String fech_cos_plan= rs.getString(5);
		        String estadoCrianza= rs.getString(6);
		        String larvas_sem= rs.getString(7);
		        String larvas_culti= rs.getString(8);
		        String peso_culti= rs.getString(9);
		        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
		//Creo el arraylist de Crianzas
//		        		
		        salidaTabla += "<tr>"
		                + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
		                + "<td class=\"text-center\" >" + rs.getString(10) +"</td>"
		                + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
		                + "<td class=\"text-center\" >" +  fechaFormato.format(fech_ini) +"</td>"
		                + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
		                + "<td class=\"text-center\" >\n" 
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','o')\" >"
		                +   "<small class=\"glyphicon glyphicon-edit\"></small> O2"
		                +   "</button>\n" 
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','c')\" >"
		                +   "<small class=\"glyphicon glyphicon-edit\"></small> °C"
		                +   "</button>\n"
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','ph')\" >"
		                +   "<small class=\"glyphicon glyphicon-edit\"></small>   PH  "
		                +   "</button>\n"
		                +    "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','sal')\" >" 
		                +   "<small class=\"glyphicon glyphicon-remove\"></small> Salini"
		                +   "</button>\n" 
		                +  "</td>"
		                + "<td class=\"text-center\" >\n" 
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','talla')\" >"
		                +   "<small class=\"glyphicon glyphicon-edit\"></small> Talla"
		                +   "</button>\n"
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','peso')\" >"
		                +   "<small class=\"glyphicon glyphicon-edit\"></small> Peso"
		                +   "</button>\n"
		                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_parametros('"+id+"','bio')\" >" 
		                +   "<small class=\"glyphicon glyphicon-edit\"></small> Biomasa"
		                +   "</button>\n" 
		                +  "</td>"
		                + "</tr>";

		    		}
		    
		    salidaTabla += "</tbody></table>";
		    setSalida(salidaTabla);
		    GestionaBaseDeDatos.desconectar();
				
			}
	
	
	//Obtiene todos las especies y los llena en una tabla
	void getListadoCrianzas() throws Exception{
				System.out.println("entro a getListadoCrianzas");
		
			String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                + "<thead>"
	                + "<th class=\"text-center\" style=\"width:8%\">Crianza</th>"
	                + "<th class=\"text-center\" style=\"width:20%\">Granja</th>"
	                + "<th class=\"text-center\" style=\"width:12%\">Estanque</th>"
	                + "<th class=\"text-center\" style=\"width:12%\">Inicio</th>"
	                + "<th class=\"text-center\" style=\"width:13%\">Estado</th>"
	                + "<th class=\"text-center\" style=\"width:15%\">Reporte Diario</th>"
	                + "<th class=\"text-center\" style=\"width:15%\">Reporte Semanal</th>"
	                + "</thead><tbody>";
			

	GestionaBaseDeDatos.conectar();
	String sql = "SELECT idCrianza,estanque.nombre,fecha_inicio,fecha_cosecha_plan,estadoCrianza,larvas_sembradas,ejemplares_cosechados,peso_cosechado,granja.nombre FROM crianza "
			  + " INNER JOIN estanque ON crianza.idEstanque=estanque.idEstanque "
			  + " INNER JOIN granja ON estanque.idGranja=granja.idGranja WHERE crianza.estado=1 ";
	
		rs = GestionaBaseDeDatos.consultar(sql);
	    while(rs.next()){
	    
	    	String id = rs.getString(1);
	    	String estanque= rs.getString(2);
	        Date fech_ini= rs.getDate(3);
	        String fech_cos_plan= rs.getString(4);
	        String estadoCrianza= rs.getString(5);
	        String larvas_sem= rs.getString(6);
	        String larvas_culti= rs.getString(7);
	        String peso_culti= rs.getString(8);
	        String granja= rs.getString(9);
	        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
	//Creo el arraylist de Crianzas
//	        		
	        salidaTabla += "<tr>"
	                + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
	                + "<td class=\"text-center\" >" + rs.getString(9) +"</td>"
	                + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
	                + "<td class=\"text-center\" >" + fechaFormato.format(fech_ini) +"</td>"
	                + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
	                + "<td class=\"text-center\" >\n" 
	                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_diario('"+id+"','pdf')\" >"
	                +   "<small class=\"glyphicon glyphicon-edit\"></small> Pdf"
	                +   "</button>\n" 
	                +    "<button class=\"btn btn-xs btn-success\" onclick=\"show_diario('"+id+"','xls')\" >" 
	                +   "<small class=\"glyphicon glyphicon-remove\"></small> Excel"
	                +   "</button>\n" 
	                +  "</td>"
	                + "<td class=\"text-center\" >\n" 
	                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_semanal('"+id+"','pdf')\" >"
	                +   "<small class=\"glyphicon glyphicon-edit\"></small> Pdf"
	                +   "</button>\n"
	                +   "<button class=\"btn btn-xs btn-success\" onclick=\"show_semanal('"+id+"','xls')\" >" 
	                +   "<small class=\"glyphicon glyphicon-edit\"></small> Excel"
	                +   "</button>\n" 
	                +  "</td>"
	                + "</tr>";

	    		}
	    
	    salidaTabla += "</tbody></table>";
	    setSalida(salidaTabla);
	    GestionaBaseDeDatos.desconectar();
			
		}
    
    void getTableCrianzas()  throws Exception{
    	System.out.println("entró a getTableCrianzas()");
    	 String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:5%\">id Grupo</th>"
                            + "<th class=\"text-center\" style=\"width:19%\">Granja</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Estanque</th>"
                            + "<th class=\"text-center\" style=\"width:8%\">Tipo</th>"
                            + "<th class=\"text-center\" style=\"width:12%\">Fecha de Inicio</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Estado</th>"
                            + "<th class=\"text-center\" style=\"width:18%\">Operaciones</th>"
                            + "<th class=\"text-center\" style=\"width:18%\">Mediciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idCrianza,g.nombre,e.nombre,fecha_inicio,fecha_cosecha,fecha_cosecha_plan,larvas_sembradas,"
          		+ "ejemplares_cosechados,peso_cosechado,t.nombre,t.idTipoCrianza,e.idEstanque,g.idGranja,estadoCrianza,c.supervivencia,c.peso_promedio,c.annio FROM tipo_crianza AS t "
        			+ "INNER JOIN crianza AS c ON t.idTipoCrianza=c.idTipoCrianza "
        			+ "INNER JOIN estanque AS e ON c.idEstanque=e.idEstanque "
        			+ "INNER JOIN granja AS g ON e.idGranja= g.idGranja WHERE c.estado=1 ORDER BY annio DESC, g.nombre ASC";

        	
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                	String granja= rs.getString(2);
                    String estanque= rs.getString(3);
                    Date fech_ini= rs.getDate(4);
                    Date fech_cos= rs.getDate(5);
                    Date fech_cos_plan= rs.getDate(6);
                    String larvas= rs.getString(7);
                    String ejem= rs.getString(8);
                    String peso= rs.getString(9);
                    String tipo= rs.getString(10);
                    String idTipo= rs.getString(11);
                    String idEstan= rs.getString(12);
                    String idGran= rs.getString(13);
                    String est= rs.getString(14);
                    String sup= rs.getString(15);
                    String prom= rs.getString(16);
                    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
                    Date date= new Date();
                    SimpleDateFormat fechaFormatoHoy = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println("Fecha Hoy: "+fechaFormatoHoy.format(date));
// Creo el arraylist de Crianzas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(10) +"</td>"
                            + "<td class=\"text-center\" >" +  fechaFormato.format(fech_ini) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(14) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+idGran+"','"+idEstan+"','"+fech_ini+"','"+fech_cos+"','"+fech_cos_plan+"','"+larvas+"','"+ejem+"','"+peso+"','"+idTipo+"','"+est+"','"+sup+"','"+prom+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                            +   "</button>\n" 
                            +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                            +   "</button>\n" 
                            +  "</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"diarias('"+id+"','"+fechaFormatoHoy.format(date)+"')\" >"
                            +   "Diarias"
                            +   "</button>\n"
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"semanal('"+id+"','"+fechaFormatoHoy.format(date)+"')\" >" 
                            +   "Semanal"
                            +   "</button>\n" 
                            +  "</td>"
                            + "</tr>";

                }

                
                salidaTabla += "</tbody></table>";
                setSalida(salidaTabla);
                GestionaBaseDeDatos.desconectar();
    }
    //Obtiene todas los Especies y los llena en una select
    void getSelectCrianza() throws Exception{
    	String[] campos = {idCrianza};
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Obtiene los años de crianza
    void getSelectAnio() throws Exception{
    	String[] campos = {"fecha_inicio"};
        salida = dao.getSelectBasicAnio(modelo, campos);
    }
    
    //Registra un nuevo grupo
    void CreateCrianza() throws Exception{
    	 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String fecha= getFecha_cosecha_plan();
    	Date fechaCos = sdf.parse(fecha);
    	Calendar calendario1 = Calendar.getInstance();
    	 calendario1.setTime(fechaCos);
    	 
    	 int annio=calendario1.get(Calendar.YEAR);
    	
         Object[] campos = {getIdEstanque(),getIdEspecie(),getFecha_inicio(),getFecha_cosecha_plan(),getLarvas_sembradas(),getEstadoCrianza(),getIdTipoCrianza(),annio};
         salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateCrianza() throws Exception{
    	  Object[] campos = {getIdCrianza(),getFecha_cosecha(),getLarvas_sembradas(),getEjemplares_cosechados(),getPeso_cosechado(),getEstadoCrianza(),getSupervivencia(),getPeso_promedio()};
          salida = dao.update(modelo,campos);

    }
    //Elimina 
    void DeleteCrianza() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteGranja. El id es "+getIdCrianza());
        GestionaBaseDeDatos.ejecutar("call delete_granja('"+getIdCrianza()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true, Crianza Eliminada Correctamente";
    }

    public String getSupervivencia() {
		return supervivencia;
	}

	public void setSupervivencia(String supervivencia) {
		this.supervivencia = supervivencia;
	}

	public String getPeso_promedio() {
		return peso_promedio;
	}

	public void setPeso_promedio(String peso_promedio) {
		this.peso_promedio = peso_promedio;
	}

	public String getIdCrianza() {
		return idCrianza;
	}

	public void setIdCrianza(String idCrianza) {
		this.idCrianza = idCrianza;
	}
	
	public String getIdTipoCrianza() {
		return idTipoCrianza;
	}

	public void setIdTipoCrianza(String idTipoCrianza) {
		this.idTipoCrianza = idTipoCrianza;
	}

	public String getIdEstanque() {
		return idEstanque;
	}

	public void setIdEstanque(String idEstanque) {
		this.idEstanque = idEstanque;
	}

	public String getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getLarvas_sembradas() {
		return larvas_sembradas;
	}

	public void setLarvas_sembradas(String larvas_sembradas) {
		this.larvas_sembradas = larvas_sembradas;
	}
	
	public String getEjemplares_cosechados() {
		return ejemplares_cosechados;
	}

	public void setEjemplares_cosechados(String ejemplares_cosechados) {
		this.ejemplares_cosechados = ejemplares_cosechados;
	}

	public String getPeso_cosechado() {
		return peso_cosechado;
	}

	public void setPeso_cosechado(String peso_cosechado) {
		this.peso_cosechado = peso_cosechado;
	}

	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_cosecha() {
		return fecha_cosecha;
	}

	public void setFecha_cosecha(String fecha_cosecha) {
		this.fecha_cosecha = fecha_cosecha;
	}

	public String getFecha_cosecha_plan() {
		return fecha_cosecha_plan;
	}

	public void setFecha_cosecha_plan(String fecha_cosecha_plan) {
		this.fecha_cosecha_plan = fecha_cosecha_plan;
	}

	public String getEstadoCrianza() {
		return estadoCrianza;
	}

	public void setEstadoCrianza(String estadoCrianza) {
		this.estadoCrianza = estadoCrianza;
	}
		
	
}
