/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import beans.GestionaParametro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ControlaParametro", urlPatterns = {"/ControlaParametro"})
public class ControlaParametro extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Declaro e Instancio variables
        HttpSession sesion = request.getSession();//variable sesion
        GestionaParametro gestiona = new GestionaParametro();//clase beans
        try{
            switch(request.getParameter("crud")){
                case "Registrar" :
                     gestiona.setCodigo(request.getParameter("codigo").trim());
                    gestiona.setNombre(request.getParameter("nombre").trim());//Almaceno los parametros
                    gestiona.setDescripcion(request.getParameter("descripcion").trim());//Almaceno los parametros
                    gestiona.setUnidadMed(request.getParameter("unidad").trim());//Almaceno los parametros
                    gestiona.setValorMin(request.getParameter("min").trim());//Almaceno los parametros
                    gestiona.setValorMax(request.getParameter("max").trim());//Almaceno los parametros
                    gestiona.setTipoOper("C");//Tipo operación CREATE
                    break;
                case "Actualizar":
                    //Obtengo los parametros
                	gestiona.setCodigo(request.getParameter("codigoG").trim());
                    gestiona.setNombre(request.getParameter("nombreG").trim());//Almaceno los parametros
                    gestiona.setDescripcion(request.getParameter("descripcionG").trim());//Almaceno los parametros
                    gestiona.setUnidadMed(request.getParameter("unidadG").trim());//Almaceno los parametros
                    gestiona.setValorMin(request.getParameter("minG").trim());//Almaceno los parametros
                    gestiona.setValorMax(request.getParameter("maxG").trim());//Almaceno los parametros
                    gestiona.setTipoOper("U");//Tipo operación UPDATE
                    break;
                case "Eliminar":
                    gestiona.setCodigo(request.getParameter("cod"));//Almaceno el id
                    gestiona.setTipoOper("D");//Tipo operación DELETE
                    break;
            }
            sesion.setAttribute("flash",gestiona.getSalida());//Almaceno respuesta Success y Ejecuto el metodo Controller
        }catch(Exception e){
            sesion.setAttribute("flash",e.getMessage());//Almaceno respuesta Error
        }
        sesion.setAttribute("statusFlash",1);//Indico bandera de mensaje
        response.sendRedirect(request.getContextPath()+"/jsp/FormParametro.jsp");//Retorno ala pagina
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
