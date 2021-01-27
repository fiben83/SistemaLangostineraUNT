package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Crianza;

public class GestionaProduccion implements Serializable {
    //Integer ucorrel;
	
    private String idCrianza,idEstanque,idEspecie,larvas_sembradas, ejemplares_cultivados, peso_cultivado,salida,tipoOper;
    private String fecha_inicio, fecha_cosecha, fecha_cosecha_plan, estadoCrianza;
    ModelDaoImpl dao;
   String modelo = "crianza";
    Crianza crianza;   
    ResultSet rs;
       
   // List<Crianza> crianzas = new ArrayList<Crianza>();

	public GestionaProduccion() {
	
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
                case "T":getTableCrianzas();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectCrianza();break;//Retorna los registros de grupo en un select
                case "C":CreateCrianza();break;//Registra un nueva especie
                case "U":UpdateCrianza();break;//Actualiza 
                case "D":DeleteCrianza();break;//Elimina 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las especies y los llena en una tabla
    void getTableCrianzas()  throws Exception{
    	    	  
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:5%\">id Crianza</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Estanque</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Fecha de Inicio</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Fecha de Cosecha</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Estado</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Larvas Sembradas</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Reporte Crianza</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Reporte Mediciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idCrianza,estanque.nombre,fecha_inicio, fecha_cosecha, fecha_cosecha_plan,estadoCrianza,larvas_sembradas,ejemplares_cultivados, peso_cultivado FROM crianza "
        		  + " INNER JOIN estanque ON crianza.idEstanque=estanque.idEstanque WHERE crianza.estado=1";
        	
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                	String estanque= rs.getString(2);
                    String fech_ini= rs.getString(3);
                    String fech_cos= rs.getString(4);
                    String fech_cos_plan= rs.getString(5);
                    String estadoCrianza= rs.getString(6);
                    String larvas_sem= rs.getString(7);
                    String larvas_culti= rs.getString(8);
                    String peso_culti= rs.getString(9);
// Creo el arraylist de Crianzas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(4) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(7) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+fech_cos_plan+"','"+fech_cos+"','"+larvas_sem+"','"+larvas_culti+"','"+peso_culti+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Pdf"
                            +   "</button>\n" 
                            +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Excel"
                            +   "</button>\n" 
                            +  "</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"diarias('"+id+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Pdf"
                            +   "</button>\n"
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"semanal('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Excel"
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
    
    //Registra un nuevo grupo
    void CreateCrianza() throws Exception{
        	
           Object[] campos = {getIdEstanque(), getIdEspecie(), getFecha_inicio(),getFecha_cosecha_plan(),getLarvas_sembradas()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateCrianza() throws Exception{
        System.out.println("ingresó");
//        Object[] campos = {getDescGrupo(),getDetalleGr()};
//        salida = dao.update1(modelo,getIdGrupo(),campos);
        GestionaBaseDeDatos.conectar();  

        System.out.println("Ingrese a GestionaCrianza ------. id enviado:"+getIdCrianza()+"Fecha Inicio: "+getFecha_cosecha()+"larvas sembradas: "+getLarvas_sembradas()+"ejemplares cultivados: "+getEjemplares_cultivados()+"peso cultivado: "+getPeso_cultivado());

        GestionaBaseDeDatos.ejecutar("call update_crianza('"+getIdCrianza()+"','"+getFecha_cosecha_plan()+"','"+getFecha_cosecha()+"','"+getLarvas_sembradas()+"','"+getEjemplares_cultivados()+"','"+getPeso_cultivado()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true, Crianza actualizado correctamente";
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
	  
   
    public String getIdCrianza() {
		return idCrianza;
	}

	public void setIdCrianza(String idCrianza) {
		this.idCrianza = idCrianza;
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

	public String getEjemplares_cultivados() {
		return ejemplares_cultivados;
	}

	public void setEjemplares_cultivados(String ejemplares_cultivados) {
		this.ejemplares_cultivados = ejemplares_cultivados;
	}

	public String getPeso_cultivado() {
		return peso_cultivado;
	}

	public void setPeso_cultivado(String peso_cultivado) {
		this.peso_cultivado = peso_cultivado;
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

