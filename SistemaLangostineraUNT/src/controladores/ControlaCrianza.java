package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaCrianza;

/**
 * Servlet implementation class ControlaGranja
 */
@WebServlet(name = "ControlaCrianza", urlPatterns = {"/ControlaCrianza"})
public class ControlaCrianza extends HttpServlet {
	private static final long serialVersionUID = 1L;

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
        //Declaro e Instancio variables
       HttpSession sesion = request.getSession();//variable sesion
        GestionaCrianza gestiona = new GestionaCrianza();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        if(request.getParameter("crud").compareTo("diario")==0) opcion=4;
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdCrianza(request.getParameter("id").trim());//Almaceno los parametros       
                	gestiona.setIdTipoCrianza(request.getParameter("tipoCrianza").trim());//Almaceno los parametros 
                    gestiona.setIdEstanque(request.getParameter("idEstanque").trim());//Almaceno los parametros
                    gestiona.setIdEspecie(request.getParameter("idEspecie").trim());//Almaceno los parametros
                    gestiona.setFecha_inicio(request.getParameter("dateSiembra").trim());//Almaceno los parametros
                    gestiona.setFecha_cosecha_plan(request.getParameter("dateCosechaPLan").trim());//Almaceno los parametros
                    gestiona.setLarvas_sembradas(request.getParameter("sembradas").trim());//Almaceno los parametros
                    gestiona.setEstadoCrianza("INICIADA");//Almaceno los parametros
                   
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setIdCrianza(request.getParameter("id").trim());//Almaceno los parametros
                	gestiona.setFecha_cosecha(request.getParameter("dateCos").trim());//Almaceno los parametros
                    gestiona.setLarvas_sembradas(request.getParameter("semb").trim());//Almaceno los parametros
                    gestiona.setEjemplares_cosechados(request.getParameter("culti").trim());//Almaceno los parametros
                    gestiona.setSupervivencia(request.getParameter("mod_super").trim());//Almaceno los parametros
                    gestiona.setPeso_promedio(request.getParameter("mod_prom").trim());//Almaceno los parametros
                    gestiona.setEstadoCrianza(request.getParameter("estadoC").trim());
                	gestiona.setPeso_cosechado(request.getParameter("pesoCos").trim());//Almaceno los parametros
                    gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaCrianza. Estoy en Eliminar. El id es "+request.getParameter("id"));
                	gestiona.setIdCrianza(request.getParameter("id").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Granja

                    break;
                
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        	System.out.println("Regresando a FormCrianza" );
            response.sendRedirect(request.getContextPath()+"/jsp/FormCrianza.jsp");//Retorno ala pagina
             
    }
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
}