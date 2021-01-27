package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaEspecie;

@WebServlet(name = "ControlaEspecie", urlPatterns = {"/ControlaEspecie"})
public class ControlaEspecie extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
    	
        HttpSession sesion = request.getSession();//variable sesion
        GestionaEspecie gestiona = new GestionaEspecie();//clase beans
              
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
      
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdEspecie(request.getParameter("id").trim());//Almaceno los parametros         
                	gestiona.setNomEspecie(request.getParameter("nameEspecie").trim());
                    gestiona.setDescEspecie(request.getParameter("descEspecie").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setIdEspecie(request.getParameter("id").trim());//Almaceno los parametros
                	gestiona.setNomEspecie(request.getParameter("nameGr").trim());
                    gestiona.setDescEspecie(request.getParameter("descGrupo").trim());//Almaceno los parametros
                    gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaEspecie. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	gestiona.setIdEspecie(request.getParameter("cod").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino una Especie

                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
          
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormEspecie" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormEspecie.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
