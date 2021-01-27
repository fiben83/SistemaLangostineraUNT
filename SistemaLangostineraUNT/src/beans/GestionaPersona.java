package beans;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;
import modelos.Persona;

public class GestionaPersona implements Serializable {
    //Integer ucorrel;
    String id,nombres,apellido_paterno, apellido_materno,numero_documento,direccion,estado_civil;
    String sexo, numero_seguro,telefono,tipo_personal,fecha_Nacimiento, salida,tipoOper;
    ModelDaoImpl dao;
    String modelo = "persona";
    Persona persona=null;    
    
    ResultSet rs;
        
    List<Persona> personas = new ArrayList<Persona>();

	public GestionaPersona() {
	
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
                case "T":getTablePersonas();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectPersona();break;//Retorna los registros de grupo en un select
                case "OP":getSelectPersonaId();System.out.println("entro a OP");break;//Retorna los registros de operarios en un select
                case "C":CreatePersona();break;//Registra un nueva persona
                case "U":UpdatePersona();break;//Actualiza 
                case "D":DeletePersona();break;//Elimina 
                case "CP":ConsultaPersona();break;//CONSULTA EL DNI DE PERSONA 
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
	
	 void getSelectPersonaId() throws Exception{
	    	System.out.println("entro a getSelectPersonaId()");
	    	 GestionaBaseDeDatos.conectar();
	    	 System.out.println("IdPer: "+getId());
	         String sql = "SELECT id,nombres,apellido_paterno,apellido_materno FROM persona WHERE id ="+getId();
	        
	               rs = GestionaBaseDeDatos.consultar(sql);
	    	while(rs.next()){
	    		System.out.println("entro al while");
	            String id= rs.getString(1);
	            String nom= rs.getString(2);
	            String ape_paterno= rs.getString(3);
	            String ape_materno= rs.getString(4);
	            salida += "<option value=\""+id+"\">"+nom+" "+ape_paterno+" "+ape_materno+"</option>";
	        }
	    	    	
	        GestionaBaseDeDatos.desconectar();
	    }
	
	public Persona ConsultaPersona() throws Exception{
		 GestionaBaseDeDatos.conectar();
         String sql = "SELECT id,nombres,apellido_paterno,apellido_materno,numero_documento,telefono FROM persona WHERE numero_documento ="+getNumero_documento();
               rs = GestionaBaseDeDatos.consultar(sql);
               
               while(rs.next()){
            	   persona= new Persona(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
               }
               GestionaBaseDeDatos.desconectar();
          return persona;
                
	}

    //Obtiene todos las especies y los llena en una tabla
    void getTablePersonas()  throws Exception{
    	
    	
         String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:5%\">Id</th>"
                            + "<th class=\"text-center\" style=\"width:18%\">Apellidos</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Nombres</th>"
                            + "<th class=\"text-center\" style=\"width:8%\">DNI</th>"
                            + "<th class=\"text-center\" style=\"width:22%\">Direccion</th>"
                            + "<th class=\"text-center\" style=\"width:13%\">Tipo</th>"
                            + "<th class=\"text-center\" style=\"width:19%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT p.id,nombres,apellido_paterno,apellido_materno,numero_documento,direccion,estado_civil,fecha_nacimiento,"
          		+ "sexo,numero_seguro,telefono,t.nombre, t.id FROM persona AS p INNER JOIN tipo_personal AS t ON p.idTipoPersonal=t.id";
                rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String id = rs.getString(1);
                    String names= rs.getString(2);
                    String ape_pa= rs.getString(3);
                    String ape_ma= rs.getString(4);
                    String dni= rs.getString(5);
                    String direccion= rs.getString(6);
                    String estCivil= rs.getString(7);
                    String fechNac= rs.getString(8);
                    String sex= rs.getString(9);
                    String numSeg= rs.getString(10);
                    String telf= rs.getString(11);
                    String tipo= rs.getString(12);
                    String idTipo= rs.getString(13);
// Creo el arraylist de personas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3)+" "+rs.getString(4)+"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(2)+"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(12) +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+names+"','"+ape_pa+"','"+ape_ma+"','"+dni+"','"+direccion+"','"+estCivil+"','"+fechNac+"','"+sex+"','"+numSeg+"','"+telf+"','"+idTipo+"')\" >"
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
    //Obtiene todas los operarios en una tabla
    
    public String getTableTipoPersona(String tipo)  throws Exception{
    	 	  
    	
        String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                           + "<thead>"
                           + "<th class=\"text-center\" style=\"width:5%\">Id</th>"
                           + "<th class=\"text-center\" style=\"width:18%\">Apellidos</th>"
                           + "<th class=\"text-center\" style=\"width:15%\">Nombres</th>"
                           + "<th class=\"text-center\" style=\"width:8%\">DNI</th>"
                           + "<th class=\"text-center\" style=\"width:22%\">Direccion</th>"
                           + "<th class=\"text-center\" style=\"width:13%\">Tipo</th>"
                           + "<th class=\"text-center\" style=\"width:19%\">Operaciones</th>"
                           + "</thead><tbody>";

         GestionaBaseDeDatos.conectar();
         String sql = "SELECT p.id,nombres,apellido_paterno,apellido_materno,numero_documento,direccion,estado_civil,fecha_nacimiento,"
           		+ "sexo,numero_seguro,telefono,t.nombre, t.id FROM persona AS p INNER JOIN tipo_personal AS t ON p.idTipoPersonal=t.id WHERE t.id='"+tipo+"'";
                 rs = GestionaBaseDeDatos.consultar(sql);
                 while(rs.next()){
                 	String id = rs.getString(1);
                     String names= rs.getString(2);
                     String ape_pa= rs.getString(3);
                     String ape_ma= rs.getString(4);
                     String dni= rs.getString(5);
                     String direccion= rs.getString(6);
                     String estCivil= rs.getString(7);
                     String fechNac= rs.getString(8);
                     String sex= rs.getString(9);
                     String numSeg= rs.getString(10);
                     String telf= rs.getString(11);
                     String tipoPer= rs.getString(12);
                     String idTipo= rs.getString(13);
 // Creo el arraylist de personas
//                     		
                     salidaTabla += "<tr>"
                             + "<td class=\"text-center\" >" + rs.getString(1) +"</td>"
                             + "<td class=\"text-center\" >" + rs.getString(3)+" "+rs.getString(4)+"</td>"
                             + "<td class=\"text-center\" >" + rs.getString(2)+"</td>"
                             + "<td class=\"text-center\" >" + rs.getString(5) +"</td>"
                             + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                             + "<td class=\"text-center\" >" + rs.getString(12) +"</td>"
                             + "<td class=\"text-center\" >\n" 
                             +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+id+"','"+names+"','"+ape_pa+"','"+ape_ma+"','"+dni+"','"+direccion+"','"+estCivil+"','"+fechNac+"','"+sex+"','"+numSeg+"','"+telf+"','"+idTipo+"')\" >"
                             +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                             +   "</button>\n" 
                             +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+id+"')\" >" 
                             +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                           +   "</button>\n" 
                           +  "</td>"
                           + "</tr>";
               }

               salidaTabla += "</tbody></table>";
                GestionaBaseDeDatos.desconectar();
                return salidaTabla;
   }
    void getSelectPersona() throws Exception{
    	String[] campos = {"id","nombres","apellido_paterno", "apellido_materno"};
        salida = dao.getSelectBasic(modelo,campos);
    }
       
    //Registra un nuevo grupo
    void CreatePersona() throws Exception{
        	
           Object[] campos = {getNombres(),getApellido_paterno(),getApellido_materno(),getNumero_documento(), getEstado_civil(),getSexo(),getNumero_seguro(),getTelefono(),getDireccion(),getFecha_Nacimiento(),getTipo_personal()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdatePersona() throws Exception{
    	 Object[] campos = {getId(),getNombres(),getApellido_paterno(),getApellido_materno(),getNumero_documento(),getNumero_seguro(),getTelefono(),getDireccion(),getEstado_civil(),getSexo(),getFecha_Nacimiento()};
         salida = dao.update(modelo, campos);
    }
    //Elimina 
    void DeletePersona() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
        System.out.println("DeletePersona. El id es "+getId());
        GestionaBaseDeDatos.ejecutar("call delete_especie('"+getId()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Persona Eliminado Correctamente";
    }
    
   	
	public String getTipoOper() {
		return tipoOper;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	
	
	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
		
	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNumero_seguro() {
		return numero_seguro;
	}

	public void setNumero_seguro(String numero_seguro) {
		this.numero_seguro = numero_seguro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
 
		
	public String getTipo_personal() {
		return tipo_personal;
	}

	public void setTipo_personal(String tipo_personal) {
		this.tipo_personal = tipo_personal;
	}

	public String getFecha_Nacimiento() {
		return fecha_Nacimiento;
	}

	public void setFecha_Nacimiento(String fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}
    

}
