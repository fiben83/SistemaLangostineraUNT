package beans;
import java.io.Serializable;
import java.sql.ResultSet;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

public class GestionaClase implements Serializable {
    Integer ucorrelFam;
    String idClase,descClase,detalleCl,idGrupo,salida,tipoOper;
    ModelDaoImpl dao;
    String modelo = "clase";
    
	public GestionaClase() {
	
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
                case "T":getTableClases();break;//Retorna los registros de Clase en una tabla
                case "S":getSelectClase();break;//Retorna los registros de Clase en un select
                case "C":CreateClase();break;//Registra un nuevo Clase
                case "U":UpdateClase();break;//Actualiza un Clase
                case "D":DeleteClase();break;//Elimina un Clase
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las clases y las llena en una tabla
    void getTableClases()  throws Exception{
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:10%\">id Clase</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Descripción</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Detalle</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";
          ResultSet rs;
          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idClase,descClase,detalleCl FROM Clase "
                     + " WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                    String descr= rs.getString(2);
                    String detalleCl= rs.getString(3);
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+descr+"','"+detalleCl+"','"+id+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                            +   "</button>\n" 
                            +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                            +   "</button>\n" 
                            +  "</td>"
                            + "</tr>";

                    System.out.println("El id"+id+" descr"+descr+ "detalle "+detalleCl);
                }

                
                salidaTabla += "</tbody></table>";
                setSalida(salidaTabla);
                GestionaBaseDeDatos.desconectar();
    }
    //Obtiene todas las clases y los llena en una select
    void getSelectClase() throws Exception{
    	String[] campos = {idClase,descClase};
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nueva clase
    void CreateClase() throws Exception{
        Object[] campos = {getIdClase(),getDescClase(),getDetalleCl()};
        salida = dao.create(modelo,campos);
        
    }
    //Actualiza 
    void UpdateClase() throws Exception{
        GestionaBaseDeDatos.conectar();
        System.out.println("Ingrese a GestionaClase  ------. id enviado:"+getIdClase()+"descr enviado "+getDescClase()+"detalle enviado "+getDetalleCl());
        GestionaBaseDeDatos.ejecutar("call update_clase('"+getIdClase()+"','"+ getDescClase()+"','" + getDetalleCl()+"')");
        GestionaBaseDeDatos.desconectar();
        
        salida = "true,Clase actualizada correctamente";
    }
    //Elimina 
    void DeleteClase() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeleteClase. El id es "+getIdClase());
        GestionaBaseDeDatos.ejecutar("UPDATE clase SET estado=0 WHERE idClase='"+getIdClase()+"'");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Clase eliminada Correctamente";
    }


	public String getIdClase() {
		return idClase;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}

	public String getDescClase() {
		return descClase;
	}

	public void setDescClase(String descClase) {
		this.descClase = descClase;
	}

	public String getDetalleCl() {
		return detalleCl;
	}

	public void setDetalleCl(String detalleCl) {
		this.detalleCl = detalleCl;
	}

	public Integer getucorrelFam() {
		return ucorrelFam;
	}

	public void setucorrelFam(Integer ucorrelFam) {
		this.ucorrelFam = ucorrelFam;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    
}
