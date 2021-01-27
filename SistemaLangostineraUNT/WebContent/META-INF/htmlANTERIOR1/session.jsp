<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if(request.getSession().getAttribute("authUser") ==null){
        response.sendRedirect("index.jsp");
    }
%>
