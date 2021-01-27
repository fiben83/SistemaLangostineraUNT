<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="modelos.Usuario"%>
<%@page import="controladores.ControlaLogin"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    Usuario user = (Usuario)sesion.getAttribute("authUser");
    String error = "";
    if(user!=null)
    {
        response.sendRedirect("home.jsp");
    }
    else
    {
        if( sesion.getAttribute("estadoError")!=null){
            if((Integer)sesion.getAttribute("estadoError") == 1){
                error = (String)sesion.getAttribute("error");
                sesion.setAttribute("estadoError",0);
            }
        }
    }
    
    
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
         <title>Universidad Nacional de Tumbes</title>
        <link rel="icon" type="image/jpg" href="../dist/imagenes/logo0.jpg" />
        <link rel="stylesheet" href="../dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../dist/css/AdminLTE.min.css"/>
        <link rel="stylesheet" href="../dist/css/login.css"/>
        <script src="../dist/js/jQuery-2.1.4.min.js"></script>
        <script src="../dist/js/bootstrap.min.js"></script>
    </head>
    <body class="hold-transition">
        <div class="login-box">
            <% if(!error.equals("")){ %>
                <div class="alert alert-danger alert-dismissable fade in text-center">
                    <small><% out.print(error);%>
                        <small>
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        </small>
                    </small>
                </div>
            <% } %>
            <div class="login-logo">
              <a style="color:blue"></a>
                    
            </div>
            <div class="login-box-body" style="background-color:#eee;border-radius:10px;opacity:0.8;">
              <p class="login-box-msg"><img src="../dist/imagenes/logo0.jpg" width="150" height="150" class="img-circle" alt=""></p>
              <div class="login-logo">
                <a style="color: #337ab7"><b>Langostinos - FIP</b></a>
              </div>
              <form action="/SistemaLangostineraUNT/ControlaLogin"> <%--clase controlaLogin (servlet)--%>
                <div class="form-group has-feedback">				<%--al presionar ingresa se dirije a controlaLogin--%>
                  <input type="text" class="form-control"  placeholder="Usuario" name="nick">
                  <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                  <input type="password" class="form-control" placeholder="Password" name="password">
                  <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                  <div class="col-xs-offset-3 col-xs-6">
                    <input type="submit" id="boton" class="btn btn-primary btn-block btn-flat" name="operacion" value="Ingresar">
                  </div>
                </div>
              </form>
              
            </div>
          </div>
    </body>
    
</html>

