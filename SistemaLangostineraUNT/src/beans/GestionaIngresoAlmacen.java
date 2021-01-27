package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

public class GestionaIngresoAlmacen   implements Serializable {

    String numero,provId,tipoing,fecha,oc,referencia,tipoOper,estado,salida;
    double total;
        
    ModelDaoImpl dao;
    String modelo = "ingresosalmacen";
	    
	public GestionaIngresoAlmacen() {
		
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
                case "T":getTableIngresos();break;//Retorna los registros de Ingresos en una tabla
                case "S":getSelectIngreso();break;//Retorna los registros de  Ingresos en un select
                case "C":CreateIngreso();break;//Registra un nuevo  Ingreso
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
	                            + "<th class=\"text-center\" style=\"width:10%\">Número</th>"
	                            + "<th class=\"text-center\" style=\"width:15%\">O/C</th>"
	                            + "<th class=\"text-center\" style=\"width:35%\">Proveedor</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Fecha</th>"
	                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
	                            + "</thead><tbody>";
	          
	          ResultSet rs;
	          GestionaBaseDeDatos.conectar();
	          String sql = "SELECT i.numero,i.oc,p.nomrazsoc,i.fecha,i.referencia,i.tipoing FROM IngresosAlmacen i"
	                     + " INNER JOIN proveedor p ON i.provId=p.idProv WHERE i.estado=1";
	                rs = GestionaBaseDeDatos.consultar(sql);
	                while(rs.next()){
	                	String numero = rs.getString(1);
	                    String oc= rs.getString(2);
	                	String nomrazsoc= rs.getString(3);
	                    String fecha= rs.getString(4);
	                    String referencia= rs.getString(5);
	                    String tipoing= rs.getString(6);	                    
	                    salidaTabla += "<tr>"
	                            + "<td class=\"text-center\" >" + numero +"</td>"
	                            + "<td class=\"text-center\" >" + oc +"</td>"
	                            + "<td class=\"text-center\" >" + nomrazsoc +"</td>"
	                            + "<td class=\"text-center\" >" + fecha +"</td>"
	                            + "<td class=\"text-center\" >\n" 

	                            + "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+rs.getString(2)+"','"+rs.getString(3)+"','" +rs.getString(4)+"','"  +rs.getString(5)+"','"  +rs.getString(6)+"','"  +rs.getString(1)+"')\" >"
	                            
	                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
	                            +   "</button>\n"
	                            +   "<button class=\"btn btn-xs btn-primary\" onclick=\"remove('"+numero+"')\" >" 
	                            +   "<small class=\"glyphicon glyphicon-pencil\"></small> Ingresar Bienes"
	                            +   "</button>\n" 
	                            +  "</td>"
	                            + "</tr>";
	                    
	                }

	                System.out.println("GestionaIngresoAlmacen La salida es "+salida);
	                
	                salidaTabla += "</tbody></table>";
	                setSalida(salidaTabla);
	                GestionaBaseDeDatos.desconectar();
	    }
	    //Obtiene todos los Ingresos y los llena en una select
	    void getSelectIngreso() throws Exception{
	    	String[] campos = {numero,provId};
	        salida = dao.getSelectBasic(modelo,campos);
	    }
	    //Registra un nuevo Ingreso
	    void CreateIngreso() throws Exception{
	        Object[] campos = {getNumero(), getProvId(),getTipoing(),getFecha(),getOc(),getReferencia()};
	        salida = dao.create(modelo,campos);
	        System.out.println("Ingrese a CreateIngreso  ------. numero enviado:"+getNumero()+"Prov Id enviado "+getProvId());	        
	    }
	    //Actualiza 
	    void UpdateIngreso() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        GestionaBaseDeDatos.ejecutar("call update_Ingreso('"+getNumero()+"','"+ getProvId()+"','" + getTipoing()+  "','"+ getFecha()+ "','"+ getOc() + "','"+ getReferencia() + "')");
	        
	        GestionaBaseDeDatos.desconectar();
	        
	        salida = "true,Ingreso al almacén ha sido actualizado correctamente";
	    }
	    //Elimina 
	    void DeleteIngreso() throws Exception{
	        GestionaBaseDeDatos.conectar();
	        //Validar que no este siendo utilizado
	        //
	        GestionaBaseDeDatos.ejecutar("UPDATE IngresoAlmacen SET estado=0 WHERE numero='"+getNumero()+"'");
	        GestionaBaseDeDatos.desconectar();
	        salida = "true,Ingreso al almacén ha sido eliminado Correctamente";
	    }

	    
	    public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getProvId() {
			return provId;
		}

		public void setProvId(String provId) {
			this.provId = provId;
		}

		public String getTipoing() {
			return tipoing;
		}

		public void setTipoing(String tipoing) {
			this.tipoing = tipoing;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getOc() {
			return oc;
		}

		public void setOc(String oc) {
			this.oc = oc;
		}

		public String getReferencia() {
			return referencia;
		}

		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
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
