    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import GestionDeDatos.GestionaBaseDeDatos;
import dao.ModelDaoImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Menu;
import modelos.Opcion;
import modelos.Persona;
import modelos.SubOpcion;
import modelos.Usuario;


public class GestionaUsuario implements Serializable{
  private Integer idMenu;
  private Integer idPersona;
  private String idUsuario,salida,tipoOper,nick,password,telefono, email,estado;
  private Menu menu ;
  private Persona persona;
  private Usuario usuario=null;
  String modelo = "usuario";
  ModelDaoImpl dao; 
  
  ResultSet rs;
  List<Usuario> usuarios = new ArrayList<Usuario>();
  
    public GestionaUsuario(){
    }

    public String getSalida() {
    	System.out.println("entró getsalida");
    	Controlador();
        return salida;
    }
    
    void Controlador(){
    	
        try {
            dao = new ModelDaoImpl();
            switch(getTipoOper()){
                case "T":getTableUsuarios();break;//Retorna los registros de grupo en una tabla
                case "S":getSelectUsuario();break;//Retorna los registros de grupo en un select
                case "C":CreateUsuario();break;//Registra un nueva usuario
                case "U":UpdateUsuario();break;//Actualiza 
                case "D":DeleteUsuario();break;//Elimina 
                case "R":getSelectRol();break;//retorna los tipo de rol
               
            }
        } catch (Exception ex) {
            setSalida("false,"+ex.getMessage());
        }
    }
  /*C.Un método que retorne una lista de objetos DatosAgencia y 
    recibe como parámetros un objeto del tipo RequestDatoConsulta*/
 /*   public DatosEmpresa ConsultaDatos(String cci){
    	String codEmpresa="";
    	String codAgencia=""; 
        String	numCuenta=""; 
        String codVerificacion=""; 
    	
    	if(cci!= null){
    		DatosEmpresa datos= null;
    		//CCI: 80510100000000012312
    		
    		codEmpresa= cci.substring(0, 3);
    		codAgencia=cci.substring(3,6);
    		numCuenta=cci.substring(6,18);
    		codVerificacion=cci.substring(18,20);
    		datos= new DatosEmpresa(codEmpresa,codAgencia,numCuenta,codVerificacion);

    	}
           return datos;
     }*/
     
//    Crea un método en java que recorra una lista de CCI, y que permita filtrar los CCI 
//de la agencia 101. Tener en cuenta la estructura del CCI indicada en el ejercicio 1.
    
  /*  public ArrayList<String> listRecord(ArrayList<String> lista){
    	String codAgencia="";
    	 ArrayList<String> listCCI = new ArrayList();
            for (String cci : lista)
            {
            	codAgencia=cci.substring(3,6);
            	if(codAgencia.equals("101")){
            		listCCI.add(cci);
            	}
            }
      return listCCI;
    }
    */
    public Usuario ConsultaUsuario() throws Exception{
		 GestionaBaseDeDatos.conectar();
        String sql = "SELECT nick,email,telefono,password,id_menu,estado FROM usuario WHERE id_persona ="+getIdPersona();
              rs = GestionaBaseDeDatos.consultar(sql);
              
              while(rs.next()){
           	   usuario= new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
              }
        return usuario;
               
	}
    
  //Obtiene todos los Roles de usuario y los llena en un select
    void getSelectRol() throws Exception{
    	String[] campos = {"id","nombre"};
        salida = dao.getSelectBasic(modelo,campos);
    }
    
   //Obtiene todos los usuarios y los llena en una tabla
    void getTableUsuarios()  throws Exception{
    	
    	String salidaTabla = "<table class=\"table table-bordered dataTable jambo_table\" id=\"mi_tabla\">"
                            + "<thead>"
                            + "<th class=\"text-center\" style=\"width:5%\">Id</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Apellidos y Nombres</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Usuario</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Email</th>"
                            + "<th class=\"text-center\" style=\"width:5%\">Teléfono</th>"
                            + "<th class=\"text-center\" style=\"width:15%\">Rol</th>"
                            + "<th class=\"text-center\" style=\"width:10%\">Estado</th>"
                            + "<th class=\"text-center\" style=\"width:20%\">Operaciones</th>"
                            + "</thead><tbody>";

          GestionaBaseDeDatos.conectar();
          String sql = "SELECT persona.id,nombres,apellido_paterno,apellido_materno,numero_documento,"
          		+ "usuario.id,usuario.nick,email,usuario.telefono,password,usuario.estado,menu.nombre,id_menu FROM persona INNER JOIN usuario ON persona.id=usuario.id_persona "
                + " INNER JOIN menu ON usuario.id_menu=menu.id";
          rs = GestionaBaseDeDatos.consultar(sql);
                while(rs.next()){
                	String idPer = rs.getString(1);
                    String names= rs.getString(2);
                    String ape_pa= rs.getString(3);
                    String ape_ma= rs.getString(4);
                    String dni= rs.getString(5);
                    String idU= rs.getString(6);
                    String nick= rs.getString(7);
                    String email= rs.getString(8);
                    String telf= rs.getString(9);
                    String pass= rs.getString(10);
                    String est= rs.getString(11);
                    String rol=rs.getString(13);
                    String estNom="";
                    if(est.equals("1")){
                    	estNom="ACTIVO";
                    }else{
                    	estNom="INACTIVO";
                    }
                    
// Creo el arraylist de personas
//                    		
                    salidaTabla += "<tr>"
                            + "<td class=\"text-center\" >" + rs.getString(6) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(3) +" "+rs.getString(4)+" "+rs.getString(2)+"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(7) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(8) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(9) +"</td>"
                            + "<td class=\"text-center\" >" + rs.getString(12) +"</td>"
                            + "<td class=\"text-center\" >" + estNom +"</td>"
                            + "<td class=\"text-center\" >\n" 
                            +   "<button class=\"btn btn-xs btn-warning\" onclick=\"edit('"+idU+"','"+nick+"','"+pass+"','"+email+"','"+telf+"','"+rol+"','"+est+"')\" >"
                            +   "<small class=\"glyphicon glyphicon-edit\"></small> Editar"
                            +   "</button>\n" 
                            +    "<button class=\"btn btn-xs btn-danger\" onclick=\"remove('"+idU+"')\" >" 
                            +   "<small class=\"glyphicon glyphicon-remove\"></small> Eliminar"
                            +   "</button>\n" 
                            +  "</td>"
                            + "</tr>";
                }

                salidaTabla += "</tbody></table>";
                setSalida(salidaTabla);
                GestionaBaseDeDatos.desconectar();
    }
    
    void getSelectUsuario() throws Exception{
    	String[] campos = {"id","nombres","apellido_paterno", "apellido_materno"};
        salida = dao.getSelectBasic(modelo,campos);
    }
    
   
    void CreateUsuario() throws Exception{
        	
           Object[] campos = {getNick(),getPassword(),getEmail(),getTelefono(),getIdMenu(),getIdPersona(),getEstado()};
           salida = dao.create(modelo,campos);

    }
    //Actualiza 
    void UpdateUsuario() throws Exception{
    	
    	System.out.println("entro UpdateUsuario");
//        Object[] campos = {getDescGrupo(),getDetalleGr()};
//        salida = dao.update1(modelo,getIdGrupo(),campos);
        GestionaBaseDeDatos.conectar();
        GestionaBaseDeDatos.ejecutar("call update_usuario('"+getIdUsuario()+"','"+ getNick()+"','"+getPassword()+"','"+getEmail()+"','"+getTelefono()+"','"+getEstado()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true, Usuario actualizado correctamente";
    }
    //Elimina 
    void DeleteUsuario() throws Exception{
        GestionaBaseDeDatos.conectar();
        //Validar que no este siendo utilizado
        //
       /* System.out.println("DeletePersona. El id es "+getId());
        GestionaBaseDeDatos.ejecutar("call delete_especie('"+getId()+"')");
        GestionaBaseDeDatos.desconectar();
        salida = "true,Persona Eliminado Correctamente";*/
    }
    
    
    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdMenu() {
        return idMenu;
    }
    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }
    public Integer getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public void setSalida(String salida) {
            this.salida = salida;
    }
    public Menu getMenu() {
        cargarMenu();
        return menu;
    }
    public Persona getPersona() {
        CargarPersona();
        return persona;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
            
    public String getTipoOper() {
		return tipoOper;
	}

	public void setTipoOper(String tipoOper) {
		this.tipoOper = tipoOper;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	void cargarMenu()  {
        try
        {
            ResultSet rs,rs1,rs2;
            String sql,sql1,sql2;
            GestionaBaseDeDatos.conectar();
            //Obtengo las Opciones
            sql = "Select id,nombre,icono from opcion where id_menu='"+getIdMenu()+"'";
            rs = GestionaBaseDeDatos.consultar(sql);
            ArrayList<Opcion> opciones = new ArrayList<Opcion>();
            while(rs.next()){
                ArrayList<SubOpcion> subopciones = new ArrayList<SubOpcion>();
                Opcion op = new Opcion(Integer.parseInt(rs.getString("id")),rs.getString("icono"),rs.getString("nombre"));
//                sql1 = "Select id,nombre,url from subopcion where id_opcion='"+op.getId()+"'";
                sql1 = "Select id,nombre,url,tooltip from subopcion where id_opcion='"+op.getId()+"'";                
                rs1 = GestionaBaseDeDatos.consultar(sql1);
                while(rs1.next()){
//                    SubOpcion sub = new SubOpcion(Integer.parseInt(rs1.getString("id")),rs1.getString("url"),rs1.getString("nombre"));
                    SubOpcion sub = new SubOpcion(Integer.parseInt(rs1.getString("id")),rs1.getString("url"),rs1.getString("nombre"),rs1.getString("tooltip"));
                    
                    subopciones.add(sub);
                }
                if(subopciones.size() > 0){
                    op.setSubopciones(subopciones);
                }
                opciones.add(op);
            }
            sql2 = "Select id,nick,nombre from menu where id='"+getIdMenu()+"'";
            rs2 = GestionaBaseDeDatos.consultar(sql2);
            if(rs2.next()){
                Menu mi_menu = new Menu(Integer.parseInt(rs2.getString("id")),rs2.getString("nick"),rs2.getString("nombre"),opciones);
                setMenu(mi_menu);
            }
            GestionaBaseDeDatos.desconectar();
        }
        catch(SQLException ex) {
                salida = "error en la bd";
        } catch (Exception ex) { 
          Logger.getLogger(GestionaUsuario.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }
    
    void CargarPersona(){
        try
        {
            ResultSet rs;
            String sql;
            GestionaBaseDeDatos.conectar();
            //Obtengo la persona ligada al usuario
            sql = "SELECT nombres,apellido_paterno,apellido_materno,numero_documento,"
                        + "direccion,estado_civil,sexo,idTipoPersonal,telefono"
                        + " from persona where id='"+getIdPersona()+"'";
            rs = GestionaBaseDeDatos.consultar(sql);
            if(rs.next()){
                Persona persona = new Persona(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                                              rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                                              rs.getString(9));
                setPersona(persona);
            }
            GestionaBaseDeDatos.desconectar();
        }
        catch(SQLException ex) {
                salida = "error en la bd";
        } catch (Exception ex) { 
          Logger.getLogger(GestionaUsuario.class.getName()).log(Level.SEVERE, null, ex);
      } 
        
    }
  
}
