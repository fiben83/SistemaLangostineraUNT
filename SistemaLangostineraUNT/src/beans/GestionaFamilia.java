package beans;
import java.io.Serializable;
import java.sql.ResultSet;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

public class GestionaFamilia implements Serializable {
    Integer ucorrelBien;
    String idFamilia,descFamilia,detalleFam,idClase,salida,tipoOper;
    ModelDaoImpl dao;
    String modelo = "familia";
    
	public GestionaFamilia() {
	
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
                case "T":getTableFamilias();break;//Retorna los registros de Familia en una tabla
                case "S":getSelectFamilia();break;//Retorna los registros de Familia en un select
                case "C":CreateFamilia();break;//Registra un nuevo Familia
                case "U":UpdateFamilia();break;//Actualiza un Familia
                case "D":DeleteFamilia();break;//Elimina un Familia
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	

    //Obtiene todos las Familias y las llena en una tabla
    void getTableFamilias()  throws Exception{
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:10%\">id Familia</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Descripción</th>"
                            + "<th class=\"text-center\" style=\"width:35%\">Detalle</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";
          
          ResultSet rs;
          GestionaBaseDeDatos.conectar();
          String sql = "SELECT idFamilia,descFamilia,detalleFam FROM Familia "
                     + " WHERE estado=1";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                    String descr= rs.getString(2);
                    String detalleFam= rs.getString(3);
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + id +"</td>"
                            + "<td class=\"text-center\" >" + descr +"</td>"
                            + "<td class=\"text-center\" >" + detalleFam +"</td>"
                            + "<td class=\"text-center\" >\n" 

                            + "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(1)+"')\" >"
                            
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                            +   "</button>\n"
                            +   "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                            +   "</button>\n" 
                            +  "</td>"
                            + "</tr>";

//                    System.out.println("El id"+id+" descr"+descr+ "detalle "+detalleFam);
                    
                }

                System.out.println("GestionaFamilia La salida es "+salida);
                
                salidaTabla += "</tbody></table>";
                setSalida(salidaTabla);
                GestionaBaseDeDatos.desconectar();
    }
    //Obtiene todas las Familias y los llena en una select
    void getSelectFamilia() throws Exception{
    	String[] campos = {idFamilia,descFamilia};
        salida = dao.getSelectBasic(modelo,campos);
    }
    //Registra un nueva Familia
    void CreateFamilia() throws Exception{
        Object[] campos = {getIdFamilia(),getDescFamilia(),getDetalleFam()};
        salida = dao.create(modelo,campos);
        
    }
    //Actualiza 
    void UpdateFamilia() throws Exception{
        GestionaBaseDeDatos.conectar();
        System.out.println("Ingrese a GestionaFamilia  ------. id enviado:"+getIdFamilia()+"descr enviado "+getDescFamilia()+"detalle enviado "+getDetalleFam());
        GestionaBaseDeDatos.ejecutar("call update_Familia('"+getIdFamilia()+"','"+ getDescFamilia()+"','" + getDetalleFam()+"')");
        GestionaBaseDeDatos.desconectar();
        
        salida = "true,Familia actualizada correctamente";
    }
    //Elimina 
    void DeleteFamilia() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        GestionaBaseDeDatos.ejecutar("UPDATE Familia SET estado=0 WHERE idFamilia='"+getIdFamilia()+"'");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Familia eliminada Correctamente";
    }


	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getDescFamilia() {
		return descFamilia;
	}

	public void setDescFamilia(String descFamilia) {
		this.descFamilia = descFamilia;
	}

	public String getDetalleFam() {
		return detalleFam;
	}

	public void setDetalleFam(String detalleFam) {
		this.detalleFam = detalleFam;
	}

	public Integer getUcorrelBien() {
		return ucorrelBien;
	}

	public void setUcorrelBien(Integer ucorrelBien) {
		this.ucorrelBien = ucorrelBien;
	}

	public String getIdClase() {
		return idClase;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}
	
	public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    
}
