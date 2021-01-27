package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

public class GestionaIngresoSoloBienes   implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String numero,bienId,descbien,unidades,cantidad,precio,tipoOper,estado,salida;
    double subtotal;
        
    ModelDaoImpl dao;
    String modelo = "detalleingresosalmacen";
	    
	public GestionaIngresoSoloBienes() {
		
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
                case "T":
                	     getTableIngresos();
                         break;
                 //Retorna los registros de Ingresos en una tabla
                case "S":getSelectIngreso();break;//Retorna los registros de  Ingresos en un select
                case "C":
                	    CreateIngreso();break;//Registra un nuevo  Ingreso
                case "U":UpdateIngreso();break;//Actualiza un  Ingreso
                case "D":DeleteIngreso();break;//Elimina un  Ingreso
            }
        } catch (Exception ex) {
	            setSalida("false,"+ex.getMessage());
        }
    }

	    //Obtiene todos los INGRESOS y las llena en una tabla
	    void getTableIngresos()  throws Exception{
	         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
	                            + "<thead>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Código</th>"
	                            + "<th class=\"text-center\" style=\"width:30%\">Descripción</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Unidades</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Cantidad</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Precio</th>"
	                            + "<th class=\"text-center\" style=\"width:10%\">Subtotal</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
	                            + "</thead><tbody>";
	          
	          ResultSet rs;
	          GestionaBaseDeDatos.conectar();
	          String sql = "SELECT di.numero,di.bienId,b.descBien,b.unidades,di.cantidad,di.precio,di.subtotal FROM DetalleIngresosAlmacen di"
	                     + " INNER JOIN bien b ON di.bienId=b.idBien WHERE di.numero="+numero+" AND di.estado=1";
	                rs = GestionaBaseDeDatos.consultar(sql);
	                while(rs.next()){
	                	String numero = rs.getString(1);
	                    String bienId= rs.getString(2);
	                	String descbien= rs.getString(3);
	                    String unidades= rs.getString(4);
	                    String cantidad= rs.getString(5);
	                    String precio= rs.getString(6);
	                    String subtotal= rs.getString(7);
	                    salidaTabla += "<tr>"
	                            + "<td class=\"text-center\" >" + bienId +"</td>"
	                            + "<td class=\"text-center\" >" + descbien +"</td>"
	                            + "<td class=\"text-center\" >" + unidades +"</td>"
	                            + "<td class=\"text-center\" >" + cantidad +"</td>"
	                            + "<td class=\"text-center\" >" + precio +"</td>"
	                            + "<td class=\"text-center\" >" + subtotal +"</td>"
	                            + "<td class=\"text-center\" >\n" 

	                            + "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+rs.getString(2)+"','"+rs.getString(3)+"','" +rs.getString(4)+"','"  +rs.getString(5)+"','"  +rs.getString(6)+"','"  +rs.getString(1)+"')\" >"
	                            
	                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
	                            +   "</button>\n"
	                            +   "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+bienId+"')\" >" 
	                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
	                            +   "</button>\n" 
	                            +  "</td>"
	                            + "</tr>";
	                    
	                }
	                
	                salidaTabla += "</tbody></table>";
	                setSalida(salidaTabla);
	                GestionaBaseDeDatos.desconectar();
	    }
	    //Obtiene todos los Ingresos y los llena en una select
	    void getSelectIngreso() throws Exception{
	    	String[] campos = {bienId,descbien};
	        salida = dao.getSelectBasic(modelo, campos);
	    }
	    //Registra un nueva Familia
	    void CreateIngreso() throws Exception{
	    	
	    	/*
	    	 * AQUI SE CREA EL INGRESO
	    	 */
	    	
	    	
	        Object[] campos = {getNumero(), getBienId(),getCantidad(),getPrecio()};
	        salida = dao.create(modelo,campos);
	        System.out.println("Ingrese a CreateIngreso  ------. numero enviado:"+getNumero()+"Bien Id enviado "+getBienId());	        
	    }
	    //Actualiza 
	    void UpdateIngreso() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        GestionaBaseDeDatos.ejecutar("call update_detalleingresosalmacen('"+getNumero()+"','"+ getBienId()+"','" + getCantidad()+  "','"+ getPrecio()+ "')");
	        
	        GestionaBaseDeDatos.desconectar();
	        
	        salida = "true,Ingreso de BIEN "+getDescbien() +" ha sido actualizado correctamente";
	    }
	    //Elimina 
	    void DeleteIngreso() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        //Validar que no este siendo utilizado
	        //
	        GestionaBaseDeDatos.ejecutar("UPDATE detalleIngresosAlmacen SET estado=0 WHERE numero='"+getNumero()+"' AND bienId='"+getBienId()+"'");
	        GestionaBaseDeDatos.desconectar();
	        salida = "true, Ingreso de BIEN "+getDescbien() +" se eliminò correctamente";
	    }

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getBienId() {
			return bienId;
		}

		public void setBienId(String bienId) {
			this.bienId = bienId;
		}

		public String getDescbien() {
			return descbien;
		}

		public void setDescbien(String descbien) {
			this.descbien = descbien;
		}

		public String getUnidades() {
			return unidades;
		}

		public void setUnidades(String unidades) {
			this.unidades = unidades;
		}

		public String getCantidad() {
			return cantidad;
		}

		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}

		public String getPrecio() {
			return precio;
		}

		public void setPrecio(String precio) {
			this.precio = precio;
		}

		public double getSubtotal() {
			return subtotal;
		}

		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
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
