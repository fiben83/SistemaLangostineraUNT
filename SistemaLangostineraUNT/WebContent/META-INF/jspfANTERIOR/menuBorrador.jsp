<%@page import="helpers.HelperUrl"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelos.Persona"%>
<%@page import="modelos.SubOpcion"%>
<%@page import="modelos.Opcion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.GestionaUsuario"%>
<%@page import="modelos.Menu"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession session_menu = request.getSession();
    Usuario auth_menu = (Usuario) session_menu.getAttribute("authUser");
    GestionaUsuario gestion = new GestionaUsuario();
    Menu menu = null;Persona persona = null;
    ArrayList<Opcion> opciones = null;
    //Obtengo direccion url actual
    String url = request.getRequestURI();
    url = url.substring(request.getContextPath().length()+1,url.length() -4);//url actual
    
    System.out.println("Estoy en menu.jsp. La url actual es"+url);
    
    if(auth_menu != null){
        gestion.setIdMenu(auth_menu.getId_menu());
        gestion.setIdPersona(auth_menu.getId_persona());
        persona = (Persona)gestion.getPersona();
        menu = (Menu)gestion.getMenu();
        if(menu != null){
            opciones = menu.getOpciones();

            //Validamos url
            Boolean flag = HelperUrl.validUrl(opciones, url);
            if(!flag){//si es falso
                out.print("url no te pertenece");
                response.sendRedirect("../jsp/home.jsp");
            }
            
            System.out.println("Menu.jsp. Menu no es nulo");
            //Fin validacion url
        }
    }
%>
<% if(menu != null){ 

    System.out.println("Menu.jsp. Comienzo a mostrar Menu en Home.jsp ");
%>
               
          <% } %>

Hola
