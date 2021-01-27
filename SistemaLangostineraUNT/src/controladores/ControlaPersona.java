package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaPersona;

/**
 * Servlet implementation class ControlaPersona
 */
@WebServlet(name = "ControlaPersona", urlPatterns = {"/ControlaPersona"})
public class ControlaPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        HttpSession sesion = request.getSession();//variable sesion
        GestionaPersona gestiona = new GestionaPersona();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setId(request.getParameter("id").trim());//Almaceno los parametros 
                	gestiona.setNombres(request.getParameter("nombres").trim());//Almaceno los parametros 
                	gestiona.setApellido_paterno(request.getParameter("aPaterno").trim());//Almaceno los parametros 
                	gestiona.setApellido_materno(request.getParameter("aMaterno").trim());//Almaceno los parametros 
                	gestiona.setNumero_documento((request.getParameter("dni").trim()).substring(0, 8));//Almaceno los parametros 
                	gestiona.setEstado_civil(request.getParameter("estadoCivil").trim());//Almaceno los parametros 
                	gestiona.setSexo(request.getParameter("sexo").trim());//Almaceno los parametros 
                	gestiona.setNumero_seguro((request.getParameter("seguro").trim()).substring(0, 12));//Almaceno los parametros 
                    gestiona.setTelefono(request.getParameter("telefono").trim());//Almaceno los parametros
                    gestiona.setDireccion(request.getParameter("direccion").trim());//Almaceno los parametros
                    gestiona.setFecha_Nacimiento(request.getParameter("date"));
                    gestiona.setTipo_personal(request.getParameter("tipoPersonal").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setId(request.getParameter("idG").trim());//Almaceno los parametros 
                	gestiona.setNombres(request.getParameter("nombresG").trim());//Almaceno los parametros 
                	gestiona.setApellido_paterno(request.getParameter("aPaternoG").trim());//Almaceno los parametros 
                	gestiona.setApellido_materno(request.getParameter("aMaternoG").trim());//Almaceno los parametros 
                	gestiona.setNumero_documento((request.getParameter("dniG").trim()).substring(0, 8));//Almaceno los parametros 
                	gestiona.setEstado_civil(request.getParameter("estadoCivilG").trim());//Almaceno los parametros 
                	gestiona.setSexo(request.getParameter("sexoG").trim());//Almaceno los parametros 
                	gestiona.setNumero_seguro((request.getParameter("seguroG").trim()).substring(0, 12));//Almaceno los parametros 
                    gestiona.setTelefono(request.getParameter("telefonoG").trim());//Almaceno los parametros
                    gestiona.setDireccion(request.getParameter("direccionG").trim());//Almaceno los parametros
                    gestiona.setFecha_Nacimiento(request.getParameter("dateG"));
                                   
                   gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaEstanque. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	//gestiona.setIdEstanque(request.getParameter("cod").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Granja
                    break;
                    
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormPersona" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormPersona.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
