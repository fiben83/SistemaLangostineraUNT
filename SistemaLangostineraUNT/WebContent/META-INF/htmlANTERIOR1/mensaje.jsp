
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion_res = request.getSession();
    String respuesta = "";String[] mensajes = null;
    if(sesion_res.getAttribute("statusFlash")!=null){
        Integer estado = (Integer)sesion_res.getAttribute("statusFlash");
        if(estado == 1){
            respuesta = (String)sesion_res.getAttribute("flash");
            mensajes = respuesta.split(",");
            sesion_res.setAttribute("statusFlash",0);
        }
    }
    %>
<!DOCTYPE html>
<% if(!respuesta.equals("")){ %>
    <% for(String mensaje : mensajes) { %>
    <%if(mensajes[0].equals("true") && !mensaje.equals("true")){ %>
        <div id="mensaje" class="alert alert-success alert-dismissible fade in text-uppercase" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
            </button><strong class="fa fa-check"></strong>
                <strong>Exito!</strong>  <% out.print(mensaje);%>
        </div>
    <% } %>
    <%if(mensajes[0].equals("false") && !mensaje.equals("false")){ %>
    <% if(!mensaje.equals("null")){ %>
        <div id="mensaje" class="alert alert-danger alert-dismissible fade in text-uppercase" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span>
            </button><strong class="fa fa-remove"></strong>
                <strong>Atencion!</strong>  <% out.print(mensaje);%>
        </div>
        <% } %>
    <% } %>
    
    <% }%>
<% }%>
