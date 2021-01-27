package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaRegistroSemanal;

/**
 * Servlet implementation class ControlaGranja
 */
@WebServlet(name = "ControlaRegistroSemanal", urlPatterns = {"/ControlaRegistroSemanal"})
public class ControlaRegistroSemanal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
    	response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
    	String salida="";
        HttpSession sesion = request.getSession();//variable sesion
        GestionaRegistroSemanal gestiona = new GestionaRegistroSemanal();//clase beans
        
        System.out.println(" entró a ControlaRegistroSemanal");
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        if(request.getParameter("crud").compareTo("semanal")==0) opcion=4;
        
        try{
            switch(opcion){
                case 1 :
                	//gestiona.setIdRegistroDiario(request.getParameter("id").trim());//Almaceno los parametros   
                	System.out.println("Ingresó a la opcion 1");
                	gestiona.setIdRegistroSemanal(request.getParameter("idReg").trim());
                    gestiona.setIdCrianza(request.getParameter("idCrianza").trim());//Almaceno los parametros
                	gestiona.setFecha(request.getParameter("date").trim());//Almaceno los parametros
                    gestiona.setId(request.getParameter("persona").trim());
                    gestiona.setTamano_promedio(request.getParameter("tam").trim());//Almaceno los parametros
                    gestiona.setPeso_promedio(request.getParameter("peso").trim());//Almaceno los parametros
                    gestiona.setPoblacion(request.getParameter("poblacion").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	System.out.println("Ingresó a la opcion 2");
                	gestiona.setIdRegistroSemanal(request.getParameter("idReg").trim());//Almaceno los parametros      
                	gestiona.setIdCrianza(request.getParameter("idCrianza").trim());//Almaceno los parametros      
                    gestiona.setFecha(request.getParameter("date").trim());//Almaceno los parametros
                    gestiona.setId(request.getParameter("persona").trim());//Almaceno los parametros
                    gestiona.setTamano_promedio(request.getParameter("tam").trim());//Almaceno los parametros
                    gestiona.setPeso_promedio(request.getParameter("peso").trim());//Almaceno los parametros
                    gestiona.setPoblacion(request.getParameter("poblacion").trim());//Almaceno los parametros
                    
                    gestiona.setTipoOper("U");//actualizo un grupo
                    System.out.println("hasta el break 2");
                    break;
                case 3 :
                    System.out.println("ControlaCrianza. Estoy en Eliminar. El id es "+request.getParameter("id"));
                	gestiona.setIdCrianza(request.getParameter("id").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Granja

                    break;
                case 4 :
                    System.out.println("Ingresó a la opcion 4");
                    
                	gestiona.setIdCrianza(request.getParameter("idC").trim());//Almaceno el id
                    gestiona.setTipoOper("T");//Elimino una Granja

                    break;
            }
            salida= gestiona.getSalida();
            sesion.setAttribute("flash",salida);//Almaceno respuesta Success
           
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        if(opcion!=4){
        	//System.out.println("Regresando a FormCrianza" );
           // response.sendRedirect(request.getContextPath()+"/jsp/FormCrianza.jsp");//Retorno ala pagina
        	out.println(salida);
        }else {
        	 out.println(salida);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}