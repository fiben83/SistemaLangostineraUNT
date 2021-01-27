package controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.GestionaEstanque;
import modelos.Estanque;


/**
 * Servlet implementation class ConsultaEstanque
 */
@WebServlet(name = "ConsultaEstanque", urlPatterns = {"/ConsultaEstanque"})
public class ConsultaEstanque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
       
        String id= request.getParameter("idGranja");
      
        GestionaEstanque gestiona = new GestionaEstanque();//clase beans
        ArrayList<Estanque>listaEstanques= new ArrayList<Estanque>();
                       
        	gestiona.setIdGranja(id);
        	
        	 try{
        		 
             	listaEstanques=gestiona.ConsultaEstanques();
             	System.out.println("Estanque: "+listaEstanques.get(0).getDescEstanque()); 
             	 //de la misma manera, crearemos un arreglo json
             	JsonArrayBuilder array=Json.createArrayBuilder();
             	//esta vez recorremos la lista como lambda
             	listaEstanques.stream().map((estanque)->Json.createObjectBuilder()
             			.add("id",estanque.getIdEstanque())
             			.add("nombre", estanque.getDescEstanque()).build()).forEach((item)->{
             				array.add(item);
             			});
            	      	
             	
                  response.setContentType("application/json");
                  //response.setCharacterEncoding("utf-8");
                  try (JsonWriter jsonWriter= Json.createWriter(response.getOutputStream())){
                	  jsonWriter.writeArray(array.build());
                  }
                	  
                                   
             }
             catch (Exception ex) {
                 ex.getMessage();
                 System.out.print(ex);
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