package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaGranja;

/**
 * Servlet implementation class ControlaGranja
 */
@WebServlet(name = "ControlaGranja", urlPatterns = {"/ControlaGranja"})
public class ControlaGranja extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        HttpSession sesion = request.getSession();//variable sesion
        GestionaGranja gestiona = new GestionaGranja();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdGranja(request.getParameter("id").trim());//Almaceno los parametros                	
                    gestiona.setNomGranja(request.getParameter("nomGranja").trim());//Almaceno los parametros
                    gestiona.setDirGranja(request.getParameter("dirGranja").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setIdGranja(request.getParameter("id").trim());//Almaceno los parametros
                    gestiona.setNomGranja(request.getParameter("nomGrupo").trim());//Almaceno los parametros
                    gestiona.setDirGranja(request.getParameter("dirGrupo").trim());//Almaceno los parametros
                    gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaGranja. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	gestiona.setIdGranja(request.getParameter("cod").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Granja

                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormGranja" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormGranja.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
