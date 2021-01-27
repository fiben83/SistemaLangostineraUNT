
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%System.out.println("Estoy en FormParametro AL INICIO ******");%>
<%@include file="../jspf/session.jsp"%>
<%System.out.println("Estoy en FormParametro ******Pase Include session.jsp ");%>

<jsp:useBean id="beanParametro"  class="beans.GestionaParametro" />
<% if(request.getSession().getAttribute("authUser") != null){ %>
<!DOCTYPE html>
  <%@include file="../jspf/header.jsp"%>   
  
  <%@include file="../jspf/menu.jsp"  %>   
  
  <%@include file="../jspf/navbar.jsp"  %>  
    
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
 <!-- Modal Agregar  -->
    <div id="modal_add" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Parametro</h4>
                </div>
                <div class="modal-body">
                    <form action="../ControlaParametro" method="POST" id="mod_form">
                        <div class="form-group">
                            <label>CODIGO</label>
                            <input maxlength="5" id="mod_codigo" type="text" class="form-control" name="codigo" placeholder="Ingrese un codigo" required>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input maxlength="50" id="mod_nombre" type="text" class="form-control" name="nombre" placeholder="Ingrese un nombre" required>
                        </div>
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input maxlength="180" id="mod_decripcion" type="text" class="form-control" name="descripcion" placeholder="Ingrese una descripcion" required>
                        </div>
                        <div class="form-group">
                            <label>Unidad de medida</label>
                            <input maxlength="50" id="mod_unidad" type="text" class="form-control" name="unidad" placeholder="Ingrese unidad de medida" required>
                        </div>
                        <div class="form-group">
							<label>Minimo valor</label> <input id="mod_min" type="text" class="form-control" name="min" placeholder="Ingrese una valor mínimo" required>
							<label>Maximo valor</label> <input id="mod_max" type="text" class="form-control" name="max" placeholder="Ingrese una valor maximo" required>
						</div>
						
                        <hr>
                        <div class="text-center">
                        	<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
                            <input id="mod_input" type="submit" class="btn btn-primary" name="crud">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
 
<!-- Modal Editar -->
    <div id="modal_edit" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Parametro</h4>
                </div>
                <div class="modal-body">
                    <form action="../ControlaParametro" method="POST" >
                        <div class="form-group">
                            <label>CODIGO</label>
                            <input maxlength="5" id="mod_codigoG" type="text" class="form-control" name="codigoG" placeholder="Ingrese un codigo" required>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input maxlength="50" id="mod_nombreG" type="text" class="form-control" name="nombreG" placeholder="Ingrese un nombre" required>
                        </div>
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input maxlength="180" id="mod_decripcionG" type="text" class="form-control" name="descripcionG" placeholder="Ingrese una descripcion" required>
                        </div>
                        <div class="form-group">
                            <label>Unidad de medida</label>
                            <input maxlength="50" id="mod_unidadG" type="text" class="form-control" name="unidadG" placeholder="Ingrese unidad de medida" required>
                        </div>
                        <div class="form-group">
							<label>Minimo valor</label> <input id="mod_minG" type="text" class="form-control" name="minG" placeholder="Ingrese una valor mínimo" required>
							<label>Maximo valor</label> <input id="mod_maxG" type="text" class="form-control" name="maxG" placeholder="Ingrese una valor maximo" required>
						</div>
						
                        <hr>
                        <div class="text-center">
                        	<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
                            <input id="mod_input" type="submit" class="btn btn-primary" name="crud" value="Actualizar">
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
                    <h4 class="modal-title text-center">¿Estas Seguro que deseas eliminar LOGICAMENTE este Parametro?</h4>
                    <form action="../ControlaParametro" method="POST">
                        <div class="hide"><input id="mod_codDelete" type="number" name="cod"></div>
                        <div class="text-center">
                            <input type="submit" class="btn btn-danger" name="crud" value="Eliminar">
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
        function edit(cod,nom,des,min,max,unid){
        	
            $('#modal_edit').modal('show');
            $("#mod_codigoG").prop('readonly', true);
            document.getElementById("mod_codigoG").value = cod;
            document.getElementById("mod_nombreG").value = nom;
            document.getElementById("mod_decripcionG").value = des;
            document.getElementById("mod_minG").value = min;
            document.getElementById("mod_maxG").value = max;
            document.getElementById("mod_unidadG").value = unid;
            document.getElementById("mod_input").value = "Actualizar";
        }
        function remove(codigo){
            $('#modal_delete').modal('show');
            document.getElementById("mod_codDelete").value = codigo;
        }
    </script>

    <%@include file="../jspf/footer.jsp" %>
<% } %>
    
    
   
