package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import beans.GestionaUsuario;

@WebServlet(name = "ControlaUsuario", urlPatterns = {"/ControlaUsuario"})
public class ControlaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
    
        HttpSession sesion = request.getSession();//variable sesion
        GestionaUsuario gestiona = new GestionaUsuario();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdUsuario(request.getParameter("idUser").trim());//Almaceno los parametros                	
                    gestiona.setIdPersona(Integer.valueOf(request.getParameter("id").trim()));//Almaceno los parametros
                	gestiona.setNick(request.getParameter("nick").trim());//Almaceno los parametros        
                	gestiona.setPassword(DigestUtils.md5Hex(request.getParameter("pass").trim()));//Almaceno los parametros 
                	gestiona.setTelefono(request.getParameter("telefono").trim());//Almaceno los parametros        
                	gestiona.setEmail(request.getParameter("email").trim());//Almaceno los parametros       
                	gestiona.setIdMenu(Integer.valueOf(request.getParameter("rol").trim()));//Almaceno los parametros        
                	gestiona.setEstado(request.getParameter("estado").trim());//Almaceno los parametros        
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                    gestiona.setIdUsuario(request.getParameter("idU").trim());//Almaceno los parametros  
                    gestiona.setNick(request.getParameter("nickG").trim());//Almaceno los parametros        
                	gestiona.setPassword(DigestUtils.md5Hex(request.getParameter("passG").trim()));//Almaceno los parametros 
                	gestiona.setTelefono(request.getParameter("telefonoG").trim());//Almaceno los parametros        
                	gestiona.setEmail(request.getParameter("emailG").trim());//Almaceno los parametros       
                	gestiona.setEstado(request.getParameter("estadoG").trim());//Almaceno los parametros
                	gestiona.setTipoOper("U");//actualizo un grupo
                    break;
                case 3 :
                    System.out.println("ControlaEspecie. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	gestiona.setEstado(request.getParameter("estado").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Especie

                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
          
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormUsuario" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormUsuario.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
