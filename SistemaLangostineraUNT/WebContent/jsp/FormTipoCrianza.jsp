<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>

<jsp:useBean id="beanGrupo"  class="beans.GestionaTipoCrianza" />
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
                        <h2>Tipos de Cultivo <small>Mantenimiento de registros</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li>
                                <button class="btn btn-success"  onclick="add()" >
                                <small class="glyphicon glyphicon-plus"></small> Agregar Tipo Crianza</button>
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
        <div class="modal-dialog" style="width:50%; align:right; ">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Añadir Tipo de Crianza</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                    <form action="../ControlaTipoCrianza" method="POST" id="mod_form">
                    <div class="row">
						<div class="form-group col-md-6">
							<label>id Tipo Crianza</label>
							<input id="mod_idGrupo" type="text" class="form-control  form-control-success" name="id" readonly>
						</div>
						<div class="form-group col-md-6">
							
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Nombre</label>
                            <input id="mod_nombre" type="text" class="form-control" name="nomTipoCrianza" required>
						</div>
						<div class="form-group col-md-6">
							<label>Densidad de siembra (ind/m2)</label>
                            <input id="mod_densidad" type="text" class="form-control" name="densidad" required>
						</div>
					</div>
                    <div class="row">
						<div class="form-group col-md-6">
							<label>Peso Cosecha (g)</label>
                            <input id="mod_peso" type="text" class="form-control" name="peso" required>
						</div>
						<div class="form-group col-md-6">
							<label>Ciclo cultivo (anual)</label>
                            <input id="mod_ciclo" type="text" class="form-control" name="ciclo" required>
						</div>
					</div>
                    <div class="row">
						<div class="form-group col-md-6">
							<label>Tiempo de cultivo (dias)</label>
                            <input id="mod_tiempo" type="text" class="form-control" name="tiempo" required>
						</div>
						<div class="form-group col-md-6">
							<label>Recambio de agua (%)</label>
                            <input id="mod_recambio" type="text" class="form-control" name="recambio" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Supervivencia (%)</label>
                            <input id="mod_supervivencia" type="text" class="form-control" name="supervivencia" required>
						</div>
						<div class="form-group col-md-6">
							<label>Rendimiento (t/ha/año)</label>
                            <input id="mod_rendimiento" type="text" class="form-control" name="rendimiento" required>
						</div>
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
        <div class="modal-dialog"  style="width:50%; align:right;" >
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title text-center">Actualizar Tipo de Crianza</h4>
                </div>
                <div class="modal-body" style="background-color: #FFFFFF;">
                    <form action="../ControlaTipoCrianza" method="POST">
                    <div class="row">
						<div class="form-group col-md-6">
							<label>id Tipo Crianza</label>
							<input id="mod_idG" type="text" class="form-control  form-control-success" name="idG" readonly>
						</div>
						<div class="form-group col-md-6"></div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Nombre</label>
                            <input id="mod_nombreG" type="text" class="form-control" name="nomTipoCrianzaG" required>
						</div>
						<div class="form-group col-md-6">
							<label>Densidad de siembra (ind/m2)</label>
                            <input id="mod_densidadG" type="text" class="form-control" name="densidadG" required>
						</div>
					</div>
                    <div class="row">
						<div class="form-group col-md-6">
							<label>Peso Cosecha (g)</label>
                            <input id="mod_pesoG" type="text" class="form-control" name="pesoG" required>
						</div>
						<div class="form-group col-md-6">
							<label>Ciclo cultivo (anual)</label>
                            <input id="mod_cicloG" type="text" class="form-control" name="cicloG" required>
						</div>
					</div>
                    <div class="row">
						<div class="form-group col-md-6">
							<label>Tiempo de cultivo (dias)</label>
                            <input id="mod_tiempoG" type="text" class="form-control" name="tiempoG" required>
						</div>
						<div class="form-group col-md-6">
							<label>Recambio de agua (%)</label>
                            <input id="mod_recambioG" type="text" class="form-control" name="recambioG" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Supervivencia (%)</label>
                            <input id="mod_supervivenciaG" type="text" class="form-control" name="supervivenciaG" required>
						</div>
						<div class="form-group col-md-6">
							<label>Rendimiento (t/ha/año)</label>
                            <input id="mod_rendimientoG" type="text" class="form-control" name="rendimientoG" required>
						</div>
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
                    <h4 class="modal-title text-center">¿Está Seguro que deseas eliminar este tipo de crianza?</h4>
                    <form action="../ControlaTipoCrianza" method="POST">
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

        function edit(idGrupo,descrGr,den,pes,ci,tie,rec,sup,ren){
            $('#modal_edit').modal('show');
            $("#mod_idG").prop('readonly', true);
            document.getElementById("mod_idG").value = idGrupo;
            document.getElementById("mod_nombreG").value = descrGr;
            document.getElementById("mod_densidadG").value = den;
            document.getElementById("mod_pesoG").value = pes;
            document.getElementById("mod_cicloG").value = ci;
            document.getElementById("mod_tiempoG").value = tie;
            document.getElementById("mod_recambioG").value = rec;
            document.getElementById("mod_supervivenciaG").value = sup;
            document.getElementById("mod_rendimientoG").value = ren;
            document.getElementById("mod_input").value = "Actualizar";
            
        }
        
        function remove(codigo){
            $('#modal_delete').modal('show');
            document.getElementById("mod_codDelete").value = codigo;
        }        
        
    </script>

    <%@include file="../jspf/footer.jsp" %>
<% } %>
    
    
   
