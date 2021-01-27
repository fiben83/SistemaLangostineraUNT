<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../dist/css/bootstrap.min.css">


 <link rel="stylesheet" href="../dist/css/bootstrap.min.css" />
  <link rel="stylesheet" href="../dist/css/font-awesome.min.css"/>
  <link rel="stylesheet" href="../dist/css/custom.min.css">
  <link rel="stylesheet" href="../dist/css/jquery.dataTables.css"/>
  


<title>Insert title here</title>
</head>
<body>
<h1>hola</h1>
<h1>hola</h1>
<h1>hola</h1>
<%
String urlfoto1="",etiqueta;
urlfoto1="http://localhost:8083"+request.getContextPath()+"/dist/imagenes/"+"hfb.JPG";


System.out.println(request.getContextPath());
out.println("<a href='#'><img src='"+urlfoto1+"' alt='...' > </a>");		         
		        

		        %>

</body>


</html>