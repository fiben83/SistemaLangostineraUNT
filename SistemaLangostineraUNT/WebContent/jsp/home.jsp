<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp" %>
<% if(request.getSession().getAttribute("authUser") != null){ %>
<!DOCTYPE html>

  <%@include file="../jspf/header.jsp" %>  
  <%@include file="../jspf/menu.jsp" %>  
  <%@include file="../jspf/navbar.jsp" %>     
<%System.out.println("Home.jsp. Terminé de cargar los JSP");
System.out.println("El context path es "+request.getContextPath());
%>     
   
   
   
   
      <!-- contenido -->
      <div class="right_col" role="main">
          <!-- Desde Aqui va el contenido de la pagina -->
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="panel panel-default" style="margin-top: 2%; height:600px;)">

                    <div class="panel-body" >
                    <p align="center">
                          <img src="../dist/imagenes/fondo.jpg" heigth=550px width=800>
                   </p>
                         <h1 style="text-align: center;">Gestión de langostinos-UNT</h1>
                        <h5 style="text-align: center;">Bienvenido</h5>
                    </div>
                </div>
            </div>
          
      </div>
      <!-- contenido -->
   
   
   
   
      <!-- contenido -->
      <div class="right_col" role="main">
          <!-- Desde Aqui va el contenido de la pagina -->
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="panel panel-default" style="margin-top: 2%; height:500px;)">
                    <div class="panel-body" >
                       <p align="center">   <img src="../dist/imagenes/fondo.jpg">  </p>                    
                   
                         <h1 style="text-align: center;"><i class="fa fa-landmark"></i>Langostinos-FIP</h1>
                        <h5 style="text-align: center;">Bienvenido al sistema de Gestión</h5>
                    </div>
                </div>
            </div>
          
      </div>
      <!-- contenido -->

    <%@include file="../jspf/footer.jsp" %> 
<% } %>