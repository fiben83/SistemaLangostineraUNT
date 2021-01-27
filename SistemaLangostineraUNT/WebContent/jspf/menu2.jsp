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
	String urlfoto="";  // Foto de la persona	
   

    Usuario auth_menu = (Usuario) session_menu.getAttribute("authUser");
    GestionaUsuario gestion = new GestionaUsuario();
    Menu menu = null;Persona persona = null;
    ArrayList<Opcion> opciones = null;
    //Obtengo direccion url actual
    String url = request.getRequestURI();
    url = url.substring(request.getContextPath().length()+1,url.length() -4);//url actual
     
    
    if(auth_menu != null){
        gestion.setIdMenu(auth_menu.getId_menu());
        gestion.setIdPersona(auth_menu.getId_persona());
        persona = (Persona)gestion.getPersona();
        urlfoto=persona.getUrlfoto();    //*** Foto de la persona
        menu = (Menu)gestion.getMenu();
        if(menu != null){
            opciones = menu.getOpciones();

            //Validamos url
            
            /*
            
            DESACTVADO VALIDACION DE URL  10 MARZO 2017 *****
            
            Boolean flag = HelperUrl.validUrl(opciones, url);
            if(!flag){//si es falso
                out.print("url no te pertenece");
                System.out.println("url no te pertenece. ******Redirigimos a home.jsp");
                response.sendRedirect("../jsp/home.jsp");
            }
            
            
            */
            
            //Fin validacion url
        }
    }
    
   
    
%>
<% if(menu != null){ 

    System.out.println("Menu.jsp. ******Comienzo a mostrar Menu en Home.jsp ");

%>
<div class="col-md-3 left_col">
        <div class="left_col scroll-view">
          <div class="navbar nav_title" style="border: 0;">
             <a href="../jsp/home.jsp" class="site_title"><i class="fa fa-hospital-o"></i> <span>FIP-UNP</span></a>
          </div>
          <div class="clearfix"></div>
          <div class="profile">
            <div class="profile_pic">
		        <% out.print("<img src='../dist/imagenes/usuarios/"+urlfoto+"' alt='...' class='img-circle profile_img'>");
		    	
		        System.out.println("<img src='../dist/imagenes/usuarios/"+urlfoto+"' alt='...' class='img-circle profile_img'>");  //Muestro foto
		        
		        %>
             </div>
            <div class="profile_info">
              <span>Bienvenido,</span>
              <h2><% out.print(persona.getApellido_paterno()+" "+persona.getApellido_materno()+" "+persona.getNombres()); 
              
              
              System.out.println("Menu.jsp. ****** Muestro nombre y apellido de usuario");
              
              %></h2>
            </div>
          </div>    
          <br />
          <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <h3><% out.print(menu.getNombre()); %></h3>
              <ul class="nav side-menu">
                  <% for(int i=0;i<opciones.size();i++){ %>
                  <li><a><i class="fa fa-<% out.print(opciones.get(i).getIcono()); %>"></i> <span><% out.print(opciones.get(i).getNombre()); %></span> <span class="fa fa-chevron-down"></span></a>
                      <% if(opciones.get(i).getSubopciones().size() > 0 ){ %>
                      <% ArrayList<SubOpcion> subopciones = opciones.get(i).getSubopciones(); %>
                        <ul class="nav child_menu">
                            <%  System.out.println("******Comienzo a mostrar SubOpciones");
                                for(int j=0;j<subopciones.size();j++){ %>
                            <li><a href="<% out.print(subopciones.get(j).getUrl()); %>.jsp">
                            <% out.print(subopciones.get(j).getNombre());
                            
                                 System.out.println(subopciones.get(j).getNombre());
                            
                            %>  </a></li>
                            <% } %>
                        </ul>
                      <% } %>
                  </li>
                <% } %>
              </ul>
            </div>

          </div>
          <div class="sidebar-footer hidden-small">
            <a data-toggle="tooltip" data-placement="top" title="Settings">
              <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
              <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Lock">
              <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
            </a>
            <a data-toggle="tooltip" data-placement="top" title="Logout">
              <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            </a>
          </div>
        </div>
      </div>
<% } %>

