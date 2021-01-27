package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.GestionaPersona;



/**
 * Servlet implementation class ConsultaTipoPersonal
 */
@WebServlet(name = "ConsultaTipoPersonal", urlPatterns = {"/ConsultaTipoPersonal"})
public class ConsultaTipoPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        
        String tipo= request.getParameter("idTipo");
       
        GestionaPersona gestiona1 = new GestionaPersona();//clase beans
        response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
    	String salida;
       
        if(tipo.equals("todos")){
        	try{
          		 System.out.println("entro a todos");
          		 gestiona1.setTipoOper("T");
          		 salida=gestiona1.getSalida();
          		 out.println(salida);
                                     
               }
               catch (Exception ex) {
                   ex.getMessage();
               }
        	        	
        	               	
        }else{
        	try{
       		 System.out.println("entro al try");
       		 
       		 salida=gestiona1.getTableTipoPersona(tipo);
            	out.println(salida);
                                  
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