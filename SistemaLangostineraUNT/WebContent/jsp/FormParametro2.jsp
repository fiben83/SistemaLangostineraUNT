
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%System.out.println("Estoy en FormParametro AL INICIO");%>
<%@include file="../jspf/session.jsp"%>
    <%System.out.println("Estoy en FormParametro Include session.jsp ");%>

<jsp:useBean id="beanParametro"  class="beans.GestionaParametro" />
<% if(request.getSession().getAttribute("authUser") != null){ %>
<!DOCTYPE html>
  <%@include file="../jspf/header.jsp"%>   
  
  <%System.out.println("Estoy en FormParametro Include header.jsp "); %>
  
    <%@include file="../jspf/menu.jsp"    %>   
    
    <%System.out.println(" Estoy en FormParametro Include menu.jsp "); %>

    <%@include file="../jspf/navbar.jsp"     %>  
    
    <%System.out.println("Estoy en FormParametro Include navbar.jsp "); %>

        <!-- contenido -->
        <div class="right_col" role="main">
            <!-- Desde Aqui va el contenido de la pagina -->
            <div class="col-md-12 col-sm-12 col-xs-12">
                <%@include file="../jspf/mensaje.jsp" %> 
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Parametro <small>Mantenimiento de registros</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li>
                                <button class="btn btn-success"  onclick="add()" >
                                <small class="glyphicon glyphicon-plus"></small> Agregar Parametro</button>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                      </div>
                    <div class="x_content">
                            <!-- Tabla de Mantenimiento -->
                            <div class="table-responsive">
                                <jsp:setProperty name="beanParametro" property="tipoOper" value="T" /> <p>  
                                <jsp:getProperty name="beanParametro" property="salida"/>
                            </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- contenido -->
<!-- Modal Agregar o Editar -->
    <div id="modal_add" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Parametro</h4>
                </div>
                <div class="modal-body">
                    <form action="ControlaParametro" method="POST" id="mod_form">
                        <div class="form-group">
                            <label>CODIGO</label>
                            <input maxlength="5" id="mod_codigo" type="text" class="form-control" name="codigo" placeholder="Ingrese un codigo" required>
                        </div>
                        <div class="form-group">
                            <label>VALOR</label>
                            <input maxlength="50" id="mod_valor" type="text" class="form-control" name="valor" placeholder="Ingrese un valor" required>
                        </div>
                        <hr>
                        <div class="text-center">
                            <input id="mod_input" type="submit" class="btn btn-primary" name="crud">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<!-- Modal Eliminar -->
    <div id="modal_delete" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">¿Estas Seguro que deseas inhabilitar este Parametro?</h4>
                    <form action="ControlaParametro" method="POST">
                        <div class="hide"><input id="mod_codDelete" type="number" name="cod"></div>
                        <div class="text-center">
                            <input type="submit" class="btn btn-danger" name="crud" value="Inhabilitar">
                            <button class="btn btn-warning" data-dismiss="modal">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
     
     <script>    
        function add(){
            $('#modal_add').modal('show');
            document.getElementById("mod_form").reset();
            $("#mod_codigo").removeAttr("readonly");
            document.getElementById("mod_input").value = "Registrar";
        }
        function edit(valor,codigo){
            $('#modal_add').modal('show');
            $("#mod_codigo").prop('readonly', true);
            document.getElementById("mod_codigo").value = codigo;
            document.getElementById("mod_valor").value = valor;
            document.getElementById("mod_input").value = "Actualizar";
        }
        function remove(codigo){
            $('#modal_delete').modal('show');
            document.getElementById("mod_codDelete").value = codigo;
        }
    </script>

    <%@include file="../jspf/footer.jsp" %>
<% } %>
    
    
   
