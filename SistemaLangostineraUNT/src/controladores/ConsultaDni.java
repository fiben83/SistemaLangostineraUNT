package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.GestionaPersona;
import beans.GestionaUsuario;
import modelos.Persona;
import modelos.Usuario;

/**
 * Servlet implementation class ConsultaDni
 */
@WebServlet(name = "ConsultaDni", urlPatterns = {"/ConsultaDni"})
public class ConsultaDni extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        System.out.println("servlet consultaDni");
        String dni= request.getParameter("dni");
        System.out.println("dni:"+dni);
        GestionaPersona gestiona = new GestionaPersona();//clase beans
        GestionaUsuario gestionaU = new GestionaUsuario();//clase beans
        Integer idPersona=-1;
        Persona p=null;
        Usuario u=null;
        if(dni!=null){
        	  System.out.println("dni no es nulo");
        	gestiona.setNumero_documento(dni);
        	
        	 try{
        		 System.out.println("entro al try");
             	p=gestiona.ConsultaPersona();
             	if(p!=null)
             	{
             		System.out.println("persona: "+p.getApellido_paterno());
             		idPersona=p.getId();
                 	gestionaU.setIdPersona(idPersona);
                 	u=gestionaU.ConsultaUsuario();
                 	if(u!=null)
                 	 System.out.println("usuario: "+u.getNick());
                 	else
                 		System.out.println("usuario: nulo");
             	}
             	else{
             		System.out.println("persona: is null");
             	}
             	            	
             	String json1 = new Gson().toJson(p);
             	String json2 = new Gson().toJson(u);

                  response.setContentType("application/json");
                  response.setCharacterEncoding("utf-8");
                  
                  String bothJson = "["+json1+","+json2+"]"; //Put both objects in an array of 2 elements
                  response.getWriter().write(bothJson);
                                   
             }
             catch (Exception ex) {
                 ex.getMessage();
             }
                	
        }
      
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}