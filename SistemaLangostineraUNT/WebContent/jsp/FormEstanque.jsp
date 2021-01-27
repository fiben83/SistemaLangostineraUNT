<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>
<%@page import="beans.GestionaGranja"%>

<jsp:useBean id="beanEstanque"  class="beans.GestionaEstanque" />
<jsp:useBean id="beanGranja"  class="beans.GestionaGranja" />

<%
GestionaGranja granja = new GestionaGranja(); 
%>

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
                        <h2>Estanques <small>Mantenimiento de registros</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li>

                                <button class="btn btn-success"  onclick="add()" >
                                <small class="glyphicon glyphicon-plus"></small> Agregar Estanque</button>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                      </div>
                    <div class="x_content">
                            <!-- Tabla de Mantenimiento -->
                            <div class="table-responsive">
                                <jsp:setProperty name="beanEstanque" property="tipoOper" value="T" /> <p>  
                             <%out.println(beanEstanque.getSalida()); %>
                            </div>
                    </div>
                </div>
            </div>

        </div>



<!-- Modal Agregar -->

    <div id="modal_add" class="modal fade" role="dialog" >
        <div class="modal-dialog" style="width:50%; align:right; ">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Añadir Estanque</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                    <form action="../ControlaEstanque" method="POST" id="mod_form">
                        <div class="form-group" style="width:20%;">
                            <label>id Estanque</label>
                            <input id="mod_idGrupo" type="text" class="form-control  form-control-success" name="id" readonly>
                        </div>
                                              
                        <div class="form-group"  style="width:60%;">
                            <label>Descripción</label>
                            <input id="mod_descr" type="text" class="form-control" name="descEstanque" placeholder="Ejm: Estanque N° 01" required>
                        </div>
                        <div class="form-group"  style="width:60%;">
                            <label>Area del Estanque (Ha.)</label>
                            <input id="mod_tam" type="text" class="form-control" name="tamEstanque" placeholder="Ejm: 2" required>
                        </div>
                        <div class="form-grup" style="width:60%;">
                        	<label>Granja</label>
                        	<select class="form-control" name="idGranja">
  								<option value=" ">Seleccione Granja</option>
  				                <jsp:setProperty name="beanGranja" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanGranja" property="salida"/>
                            
  							</select>
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
        <div class="modal-dialog"  style="width:60%; align:right;" >
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Actualizar Estanque</h4>
                </div>
                <div class="modal-body" style="background-color: #FFFFFF;">
                    <form action="../ControlaEstanque" method="POST">
                        <div class="form-group" style="width:20%;">
                            <label>id Estanque</label>
                            <input id="mod_idG" type="text" class="form-control" name="idG" required>
                        </div>
                       
                        <div class="form-group"  style="width:60%;">
                            <label>Descripción</label>
                            <input id="mod_descrG" type="text" class="form-control" name="descEstanqueG" placeholder="Ejm: Estanque N° 01" required>
                        </div>
                        <div class="form-group"  style="width:60%;">
                            <label>Area del Estanque (Ha.)</label>
                            <input id="mod_tamG" type="text" class="form-control" name="tamEstanqueG" placeholder="Ejm: 2" required>
                        </div>
                        <div class="form-grup" style="width:60%;">
                        	<label>Granja</label>
                        	<select class="form-control" name="idGranjaG" id="idGranjaG">
  								<option value=" ">Seleccione Granja</option>
  				                <jsp:setProperty name="beanGranja" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanGranja" property="salida"/>
                            
  							</select>
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
                    <h4 class="modal-title text-center">¿Está Seguro que deseas eliminar Estanque?</h4>
                    <form action="../ControlaEstanque" method="POST">
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

        function edit(idGrupo,des,idGranja,area){
        	
            $('#modal_edit').modal('show');
            $("#mod_idG").prop('readonly', true);
            document.getElementById("mod_idG").value = idGrupo;
            document.getElementById("mod_descrG").value = des;
            
            document.getElementById("idGranjaG").value = idGranja;
            document.getElementById("mod_tamG").value = area;
            document.getElementById("mod_input").value = "Actualizar";
            
        }
        
        function remove(codigo){
            $('#modal_delete').modal('show');
            document.getElementById("mod_codDelete").value = codigo;
        }        
        
    </script>

    <%@include file="../jspf/footer.jsp" %>   
<% } %>
    
    
   
