/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import beans.GestionaParametro;
import dao.LoginDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Parametro;
import modelos.Usuario;

@WebServlet(name = "ControlaLogin", urlPatterns = {"/ControlaLogin"})
public class ControlaLogin extends HttpServlet { //la clase controlaLogin hereda los métodos de HttpServlet

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("operacion");
        HttpSession sesion = request.getSession();
        String nick,password;
        Usuario usuario;
        if(operacion.equals("Ingresar"))
        {
        	
            try {
                nick = request.getParameter("nick").trim();//metodo trim() elimina espacios en blanco a la izq. y a la derecha
                password = request.getParameter("password").trim();
               
                usuario = new Usuario(nick,password); // creo un objeto de la clase usuario
                LoginDaoImpl servicio = new LoginDaoImpl();
                Usuario usa =(Usuario)servicio.loguearse(usuario);//metodo loguearse q permite loguear
                if(usa!=null)   {

                    sesion.setAttribute("authUser", usa);
                //Cargamos recursos en la session
                    GestionaParametro gestP = new GestionaParametro();
                    sesion.setAttribute("parametros",gestP.getParametros());
                }
            } catch (Exception e) {
            	
                sesion.setAttribute("error", e.getMessage());
                sesion.setAttribute("estadoError",1);
            }
            //RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            //rd.forward(request, response);
            response.sendRedirect(request.getContextPath()+"/jsp/home.jsp"); //getContextPath() url raiz de la aplicación osea el nombre de la aplicación
        }
        else if(operacion.equals("Cerrar Sesion"))
        {
           sesion.invalidate();
           response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        }
        else if(operacion.equals("Perfil")){
            response.sendRedirect(request.getContextPath() + "/jsp/VistaPerfil.jsp");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
