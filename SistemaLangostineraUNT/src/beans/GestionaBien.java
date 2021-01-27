package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

public class GestionaBien  implements Serializable {
    String idBien,descBien,unidades,ubicac,detalleBien,idFam,salida,tipoOper,estado;
    double precio,stock,stockMinimo;
    ModelDaoImpl dao;
    String modelo = "bien";
	    
	public GestionaBien() {
		
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
                case "T":getTableBienes();break;//Retorna los registros de Bn en una tabla
                case "S":getSelectBien();break;//Retorna los registros de Bn en un select
                case "C":CreateBien();break;//Registra un nuevo Bn
                case "U":UpdateBien();break;//Actualiza un Bn
                case "D":DeleteBien();break;//Elimina un Bn
            }
        } catch (Exception ex) {
	            setSalida("false,"+ex.getMessage());
        }
    }
		

	    //Obtiene todos las Familias y las llena en una tabla
	    void getTableBienes()  throws Exception{
	         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                            + "<thead>"
	                            + "<th class=\"text-center\" style=\"width:10%\">id Bien</th>"
	                            + "<th class=\"text-center\" style=\"width:35%\">Descripción</th>"
	                            + "<th class=\"text-center\" style=\"width:35%\">Unidades</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
	                            + "</thead><tbody>";
	          
	          ResultSet rs;
	          GestionaBaseDeDatos.conectar();
	          String sql = "SELECT idBien,descBien,unidades,ubicac,detalleBien FROM Bien "
	                     + " WHERE estado=1";
	                rs = GestionaBaseDeDatos.consultar(sql);
	                while(rs.next()){
	                	String id = rs.getString(1);
	                    String descr= rs.getString(2);
	                    String unidades= rs.getString(3);
	                    String ubicac= rs.getString(4);
	                    String detalleBien= rs.getString(5);
	                    salidaTabla += "<tr>"
	                            + "<td class=\"text-center\" >" + id +"</td>"
	                            + "<td class=\"text-center\" >" + descr +"</td>"
	                            + "<td class=\"text-center\" >" + unidades +"</td>"
	                            + "<td class=\"text-center\" >\n" 

	                            + "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+rs.getString(2)+"','"+rs.getString(3)+"','" +rs.getString(4)+"','"  +rs.getString(5)+"','"  +rs.getString(1)+"')\" >"
	                            
	                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
	                            +   "</button>\n"
	                            +   "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
	                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
	                            +   "</button>\n" 
	                            +  "</td>"
	                            + "</tr>";

//	                    System.out.println("El id"+id+" descr"+descr+ "detalle "+detalleFam);
	                    
	                }

	                System.out.println("GestionaBien La salida es "+salida);
	                
	                salidaTabla += "</tbody></table>";
	                setSalida(salidaTabla);
	                GestionaBaseDeDatos.desconectar();
	    }
	    //Obtiene todas los Bienes y los llena en una select
	    void getSelectBien() throws Exception{
	    	String[] campos = {idBien,descBien};
	        salida = dao.getSelectBasic(modelo,campos);
	    }
	    //Registra un nueva Familia
	    void CreateBien() throws Exception{
	        Object[] campos = {getIdBien(),getDescBien(),getUnidades(), getUbicac(),getDetalleBien()};
	        salida = dao.create(modelo,campos);
	        System.out.println("Ingrese a CREATEBien  ------. id enviado:"+getIdBien()+"descr enviado "+getDescBien()+"unidades enviado "+getUnidades());	        
	    }
	    //Actualiza 
	    void UpdateBien() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        GestionaBaseDeDatos.ejecutar("call update_Bien('"+getIdBien()+"','"+ getDescBien()+"','" + getUnidades()+  "','"+ getUbicac()+ "','"+ getDetalleBien()+ "')");
	        GestionaBaseDeDatos.desconectar();
	        
	        salida = "true,Bien actualizado correctamente";
	    }
	    //Elimina 
	    void DeleteBien() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        //Validar que no este siendo utilizado
	        //
	        GestionaBaseDeDatos.ejecutar("UPDATE Bien SET estado=0 WHERE idBien='"+getIdBien()+"'");
	        GestionaBaseDeDatos.desconectar();
	        salida = "true,Bien eliminado Correctamente";
	    }
	    
		public String getIdBien() {
			return idBien;
		}

		public void setIdBien(String idBien) {
			this.idBien = idBien;
		}

		public String getDescBien() {
			return descBien;
		}

		public void setDescBien(String descBien) {
			this.descBien = descBien;
		}

		public String getUnidades() {
			return unidades;
		}

		public void setUnidades(String unidades) {
			this.unidades = unidades;
		}

		public String getUbicac() {
			return ubicac;
		}

		public void setUbicac(String ubicac) {
			this.ubicac = ubicac;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public String getDetalleBien() {
			return detalleBien;
		}

		public void setDetalleBien(String detalleBien) {
			this.detalleBien = detalleBien;
		}

		public double getStock() {
			return stock;
		}

		public void setStock(double stock) {
			this.stock = stock;
		}

		public double getStockMinimo() {
			return stockMinimo;
		}

		public void setStockMinimo(double stockMinimo) {
			this.stockMinimo = stockMinimo;
		}

		public String getIdFam() {
			return idFam;
		}

		public void setIdFam(String idFam) {
			this.idFam = idFam;
		}

		
		
		public String getTipoOper() {
			return tipoOper;
		}

		public void setTipoOper(String tipoOper) {
			this.tipoOper = tipoOper;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
	    
	    
	
	
	
}
