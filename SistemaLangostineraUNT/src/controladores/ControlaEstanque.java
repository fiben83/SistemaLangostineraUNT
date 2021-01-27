package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaEstanque;

/**
 * Servlet implementation class ControlaGranja
 */
@WebServlet(name = "ControlaEstanque", urlPatterns = {"/ControlaEstanque"})
public class ControlaEstanque extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        HttpSession sesion = request.getSession();//variable sesion
        GestionaEstanque gestiona = new GestionaEstanque();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdEstanque(request.getParameter("id").trim());//Almaceno los parametros                	
                    gestiona.setDescEstanque(request.getParameter("descEstanque").trim());//Almaceno los parametros
                    gestiona.setArea(request.getParameter("tamEstanque").trim());
                    gestiona.setIdGranja(request.getParameter("idGranja").trim());//Almaceno los parametros
                   
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setIdEstanque(request.getParameter("idG").trim());//Almaceno los parametros                	
                    gestiona.setDescEstanque(request.getParameter("descEstanqueG").trim());//Almaceno los parametros
                    gestiona.setArea(request.getParameter("tamEstanqueG").trim());
                    gestiona.setIdGranja(request.getParameter("idGranjaG").trim());//Almaceno los parametros
                   
                    gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaEstanque. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	gestiona.setIdEstanque(request.getParameter("cod").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Granja

                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormEstanque" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormEstanque.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
