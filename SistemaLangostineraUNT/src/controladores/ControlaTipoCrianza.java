package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GestionaTipoCrianza;

@WebServlet(name = "ControlaTipoCrianza", urlPatterns = {"/ControlaTipoCrianza"})
public class ControlaTipoCrianza extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        HttpSession sesion = request.getSession();//variable sesion
        GestionaTipoCrianza gestiona = new GestionaTipoCrianza();//clase beans
        
        System.out.println(" request.getParameter:"+ request.getParameter("crud"));
        int opcion=0;
        if(request.getParameter("crud").compareTo("Registrar")==0) opcion=1;
        if(request.getParameter("crud").compareTo("Actualizar")==0) opcion=2;
        if(request.getParameter("crud").compareTo("Eliminar")==0) opcion=3;
        System.out.println("opcion" + opcion);
        try{
            switch(opcion){
                case 1 :
                	gestiona.setIdTipoCrianza(request.getParameter("id").trim());//Almaceno los parametros                	
                    gestiona.setNomTipoCrianza(request.getParameter("nomTipoCrianza").trim());//Almaceno los parametros
                    gestiona.setDensidad(request.getParameter("densidad").trim());//Almaceno los parametros
                    gestiona.setPeso(request.getParameter("peso").trim());//Almaceno los parametros
                    gestiona.setCiclo(request.getParameter("ciclo").trim());//Almaceno los parametros
                    gestiona.setTiempo(request.getParameter("tiempo").trim());//Almaceno los parametros
                    gestiona.setRecambio(request.getParameter("recambio").trim());//Almaceno los parametros
                    gestiona.setSupervivencia(request.getParameter("supervivencia").trim());//Almaceno los parametros
                    gestiona.setRendimiento(request.getParameter("rendimiento").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Registra un grupo
                    break;
                case 2:
                	gestiona.setIdTipoCrianza(request.getParameter("idG").trim());//Almaceno los parametros                	
                    gestiona.setNomTipoCrianza(request.getParameter("nomTipoCrianzaG").trim());//Almaceno los parametros
                    gestiona.setDensidad(request.getParameter("densidadG").trim());//Almaceno los parametros
                    gestiona.setPeso(request.getParameter("pesoG").trim());//Almaceno los parametros
                    gestiona.setCiclo(request.getParameter("cicloG").trim());//Almaceno los parametros
                    gestiona.setTiempo(request.getParameter("tiempoG").trim());//Almaceno los parametros
                    gestiona.setRecambio(request.getParameter("recambioG").trim());//Almaceno los parametros
                    gestiona.setSupervivencia(request.getParameter("supervivenciaG").trim());//Almaceno los parametros
                    gestiona.setRendimiento(request.getParameter("rendimientoG").trim());//Almaceno los parametros
                    gestiona.setTipoOper("U");//actualizo un grupo

                    break;
                case 3 :
                    System.out.println("ControlaTipoEstanque. Estoy em Eliminar. El id es "+request.getParameter("cod"));
                	gestiona.setIdTipoCrianza(request.getParameter("cod").trim());//Almaceno el id
                    gestiona.setTipoOper("D");//Elimino un tipo Estanque

                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        System.out.println("Regresando a FormTipoCrianza" );
        response.sendRedirect(request.getContextPath()+"/jsp/FormTipoCrianza.jsp");//Retorno ala pagina
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
}
