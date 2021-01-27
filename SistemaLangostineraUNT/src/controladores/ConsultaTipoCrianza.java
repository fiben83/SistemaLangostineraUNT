package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import beans.GestionaTipoCrianza;

import modelos.TipoCrianza;


/**
 * Servlet implementation class ConsultaTipoCrianza
 */
@WebServlet(name = "ConsultaTipoCrianza", urlPatterns = {"/ConsultaTipoCrianza"})
public class ConsultaTipoCrianza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        System.out.println("servlet consultaTipo");
        String tipo= request.getParameter("idTipo");
       
        GestionaTipoCrianza gestiona = new GestionaTipoCrianza();//clase beans
        
        TipoCrianza tipo_crianza=null;
       
        if(tipo!=null){
        	  
        	gestiona.setIdTipoCrianza(tipo);
        	
        	 try{
        		 System.out.println("entro al try");
             	tipo_crianza=gestiona.ConsultarTipo();
             	             	            	
             	String json1 = new Gson().toJson(tipo_crianza);
             
                  response.setContentType("application/json");
                  response.setCharacterEncoding("utf-8");
                  
                  response.getWriter().write(json1);
                                   
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