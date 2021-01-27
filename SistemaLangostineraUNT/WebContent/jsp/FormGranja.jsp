<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>

<jsp:useBean id="beanGrupo"  class="beans.GestionaGranja" />
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
                        <h2>Granjas <small>Mantenimiento de registros</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li>

                                <button class="btn btn-success"  onclick="add()" >
                                <small class="glyphicon glyphicon-plus"></small> Agregar Granja</button>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                      </div>
                    <div class="x_content">
                            <!-- Tabla de Mantenimiento -->
                            <div class="table-responsive">
                                <jsp:setProperty name="beanGrupo" property="tipoOper" value="T" /> <p>  
                                <jsp:getProperty name="beanGrupo" property="salida"/>
                            </div>
                    </div>
                </div>
            </div>

        </div>



<!-- Modal Agregar -->

    <div id="modal_add" class="modal fade" role="dialog" >
        <div class="modal-dialog" style="width:60%; align:right; ">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Añadir Granja</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                    <form action="../ControlaGranja" method="POST" id="mod_form">
                        <div class="form-group" style="width:20%;">
                            <label>id Granja</label>
                            <input id="mod_idGrupo" type="text" class="form-control  form-control-success" name="id" readonly>
                        </div>
                        <div class="form-group"  style="width:50%;">
                            <label>Nombre</label>
                            <input id="mod_nom" type="text" class="form-control" name="nomGranja" required>
                        </div>
                         <div class="form-group"  style="width:70%;">
                            <label>Direccion</label>
                            <input id="mod_dir" type="text" class="form-control" name="dirGranja" required>
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
        <div class="modal-dialog"  style="width:70%; align:right;" >
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Actualizar Granja</h4>
                </div>
                <div class="modal-body" style="background-color: #FFFFFF;">
                    <form action="../ControlaGranja" method="POST">
                        <div class="form-group">
                            <label>id Granja</label>
                            <input id="mod_idG" type="text" class="form-control" name="id" required>
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input id="mod_nomGr" type="text" class="form-control" name="nomGrupo" required>
                        </div>
                        <div class="form-group">
                            <label>Direccion</label>
                            <input id="mod_dirGr" type="text" class="form-control" name="dirGrupo" required>
                        </div>
                        <hr>
                        <div class="text-center">
                        	<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
                            <input type="submit" class="btn btn-primary" name="crud" value="Actualizar">
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
                    <h4 class="modal-title text-center">¿Está Seguro que deseas eliminar Granja?</h4>
                    <form action="../ControlaGranja" method="POST">
                        <div class="form-group">
                                <input id="mod_codDelete" type="text"  class="form-control"  name="cod"  readonly="readonly">
                        </div>
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

        function edit(idGrupo,nomGr,dirGr){
            $('#modal_edit').modal('show');
            $("#mod_idG").prop('readonly', true);
            document.getElementById("mod_idG").value = idGrupo;
            document.getElementById("mod_nomGr").value = nomGr;
            document.getElementById("mod_dirGr").value = dirGr;
            document.getElementById("mod_input").value = "Actualizar";
            
        }
        
        function remove(codigo){
            $('#modal_delete').modal('show');
            document.getElementById("mod_codDelete").value = codigo;
        }        
        
    </script>

    <%@include file="../jspf/footer.jsp" %>   
<% } %>
    
    
   
