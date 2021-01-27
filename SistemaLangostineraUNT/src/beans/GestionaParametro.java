/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Parametro;

public class GestionaParametro implements Serializable{
    
    String salida,tipoOper,nombre,codigo,descripcion, valorMin, valorMax,unidadMed;
    ModelDaoImpl dao;
    
    ArrayList<Parametro> parametros;
    
    public GestionaParametro(){
        parametros = new ArrayList<Parametro>();
    }

    public String getSalida() {
        Controller();
        return this.salida;
    }
    void Controller(){
        try {
            dao = new ModelDaoImpl();
            switch(getTipoOper()){
                case "T":getTableParametros();break;
                case "C":CreateParametro();break;
                case "U":UpdateParametro();break;
                case "D":DeleteParametro();break;
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
    
    public ArrayList<Parametro> getParametros() throws Exception {
        cargaParametros();
        return parametros;
    }

    public void setParametros(ArrayList<Parametro> parametros) {
        this.parametros = parametros;
    }
    

    ArrayList<Parametro> cargaParametros() throws Exception{
            ResultSet rs;
            GestionaBaseDeDatos.conectar();
            String sql = "Select codigo,nombre,descripcion, valorMin, valorMax, unidad_medida from parametro WHERE estado=1";
            rs = GestionaBaseDeDatos.consultar(sql);
            while(rs.next()){
                Parametro p = new Parametro(rs.getString("codigo"),rs.getString("nombre"),rs.getString("descripcion"),rs.getDouble("valorMin"),rs.getDouble("valorMax"),rs.getString("unidad_medida"));
                parametros.add(p);
               
            }
            GestionaBaseDeDatos.desconectar();
            return parametros;
    }
    
    public Parametro findParametro(String codigo){
        Iterator it = parametros.iterator();
        while(it.hasNext()){
            Parametro p = (Parametro)it.next();
            if(codigo.equals(p.getCodigo())){
                return p;
            }
        }
        return null;
    }
    public ArrayList<Parametro> getSexos(){
        ArrayList<Parametro> sexos = getList("10");
        return sexos;
    }
    public ArrayList<Parametro> getTiposDocumento(){
        ArrayList<Parametro> docs = getList("11");
        return docs;
    }
    public ArrayList<Parametro> getGruposSanguineo(){
        ArrayList<Parametro> grup = getList("12");
        return grup;
    }
    public ArrayList<Parametro> getEstadosCivil(){
        ArrayList<Parametro> est = getList("13");
        return est;
    }
    public ArrayList<Parametro> getTurnos(){
        ArrayList<Parametro> tur = getList("14");
        return tur;
    }
    
    public ArrayList<Parametro> getList(String cod){
        ArrayList<Parametro> lista = new ArrayList<Parametro>();
        Iterator it = parametros.iterator();
        while(it.hasNext()){
            Parametro p = (Parametro)it.next();
            if(p.getCodigo().substring(0,2).equals(cod)){
                lista.add(p);
            }
        }
        return lista;
    }
    void CreateParametro() throws Exception{
        Object[] campos = {getNombre(),getCodigo(),getDescripcion(),getValorMin(),getValorMax(),getUnidadMed()};
        salida = dao.create("parametro",campos);
    }
    void UpdateParametro() throws Exception{
    	Object[] campos = {getNombre(),getCodigo(),getDescripcion(),getValorMin(),getValorMax(),getUnidadMed()};
        salida = dao.update("parametro",campos);
    }
    void DeleteParametro() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        GestionaBaseDeDatos.ejecutar("UPDATE parametro SET estado=0 WHERE codigo='"+getCodigo()+"'");
        GestionaBaseDeDatos.desconectar();
        salida = "Parametro Eliminado Correctamente";
        
    }

    void getTableParametros(){
        try
        {
            String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                        + "<thead>"
                        + "<th class=\"text-center\" style=\"width:15%\">Codigo</th>"
                        + "<th class=\"text-center\" style=\"width:20%\">Nombre</th>"
                        + "<th class=\"text-center\" style=\"width:10%\">Unid. Medida</th>"
                        + "<th class=\"text-center\" style=\"width:15%\">Valor Minimo</th>"
                        + "<th class=\"text-center\" style=\"width:15%\">Valor Maximo</th>"
                        + "<th class=\"text-center\" style=\"width:25%\">Operaciones</th>"
                        + "</thead><tbody>";
            ResultSet rs;
            GestionaBaseDeDatos.conectar();
            rs = GestionaBaseDeDatos.consultar("SELECT codigo,nombre,descripcion, valorMin, valorMax,unidad_medida FROM parametro WHERE estado=1");
            while(rs.next()){
         
               String codigo = rs.getString(1);
                String valor = rs.getString(2);
               String des = rs.getString(3);
                String min = rs.getString(4);
               String max = rs.getString(5);
               String unid = rs.getString(6);
                salidaTabla += "<tr>"
                        + "<td class=\"text-center\" >" + codigo +"</td>"
                        + "<td class=\"text-center\" >" + valor +"</td>"
                        + "<td class=\"text-center\" >" + unid+"</td>"
                        + "<td class=\"text-center\" >" + min+"</td>"
                         + "<td class=\"text-center\" >" + max +"</td>"
                        + "<td class=\"text-center\" >\n" 
                        +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+codigo+"','"+valor+"','"+des+"','"+min+"','"+max+"','"+unid+"')\" >"
                        +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                        +   "</button>\n" 
                        +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+codigo+"')\" >"
                        +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                        +   "</button>\n" 
                        +  "</td>"
                        + "</tr>";
            }
            salidaTabla += "</tbody></table>";
            setSalida(salidaTabla);
            GestionaBaseDeDatos.desconectar();
        } catch (Exception ex) {
            salida = ex.getMessage();
        }
    }
    

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValorMin() {
		return valorMin;
	}

	public void setValorMin(String valorMin) {
		this.valorMin = valorMin;
	}

	public String getValorMax() {
		return valorMax;
	}

	public void setValorMax(String valorMax) {
		this.valorMax = valorMax;
	}

	public String getTipoOper() {
        return tipoOper;
    }

    public void setTipoOper(String tipoOper) {
        this.tipoOper = tipoOper;
    }

	public String getUnidadMed() {
		return unidadMed;
	}

	public void setUnidadMed(String unidadMed) {
		this.unidadMed = unidadMed;
	}
    
    
    
}
