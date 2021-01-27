<%@page import="beans.GestionaPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Usuario"%>
<%@page import="modelos.Persona"%>
<%@page import="beans.GestionaUsuario"%>
<%@include file="../jspf/session.jsp"%>

<jsp:useBean id="beanGrupo" scope="application" class="beans.GestionaCrianza" />
<jsp:useBean id="beanGranja"  class="beans.GestionaGranja" />
<jsp:useBean id="beanEstanque"  class="beans.GestionaEstanque" />
<jsp:useBean id="beanEspecie"  class="beans.GestionaEspecie" />
<jsp:useBean id="beanRegistroDiario"  class="beans.GestionaRegistroDiario" />
<jsp:useBean id="beanGestionaPersona"  class="beans.GestionaPersona" />
<jsp:useBean id="beanTipoCrianza"  class="beans.GestionaTipoCrianza" />

<% 
Usuario user=(Usuario)request.getSession().getAttribute("authUser");
if(user != null){ 
	int idPer=user.getId_persona();
	
%>
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
                        <h2>Crianza Langostinos<small>Mantenimiento de registros</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li>

                                <button class="btn btn-success"  onclick="add()" >
                                <small class="glyphicon glyphicon-plus"></small>Nueva Crianza</button>
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
                    <h2 class="modal-title text-center">Crear Crianza</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                    <form action="../ControlaCrianza" method="POST" id="mod_form">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label>id Crianza</label>
                            <input id="mod_idGrupo" type="text" class="form-control  form-control-success" name="id" readonly>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Tipo Crianza</label>
                            <select class="form-control" id="idTipoCrianza" name="tipoCrianza" onchange="tipo_crianza()">
  								<option value=" ">Seleccione Tipo</option>
  				                <jsp:setProperty name="beanTipoCrianza" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanTipoCrianza" property="salida"/>
                            </select>
                        </div>
                     </div>
                     <div class="row">
                    	<div class="form-group col-md-6">
							<label>Densidad de siembra (ind/m2)</label>
                            <input id="mod_densidad" type="text" class="form-control" name="densidad" required>
						</div>
						<div class="form-group col-md-6">
							<label>Peso Cosecha (g)</label>
                            <input id="mod_peso" type="text" class="form-control" name="peso" required>
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
					<div class="row">
						<div class="form-group col-md-6">
							<label>Granja</label>
                        	<select class="form-control" id="idGranja" name="idGranja" onchange="combo()">
  								<option value=" ">Seleccione Granja</option>
  				                <jsp:setProperty name="beanGranja" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanGranja" property="salida"/>
                            </select>
						</div>       
						<div class="form-group col-md-6">
							<label>Estanque</label> 
							<select class="form-control" id="idEstanque" name="idEstanque" required>
							<option value=" ">Seleccione Estanque</option>
							
							</select>
						</div>             
                    </div>
                    <div class="row">
						<div class="form-group col-md-6">
							<label>Fecha de Inicio</label> <input class="form-control"
								id="dateSiembra" name="dateSiembra" placeholder="MM/DD/YYY" type="date" required/>
						</div>
						<div class="form-group col-md-6">
							<label>Fecha de Cosecha Planificada</label> <input class="form-control"
								id="dateCosechaPlan" name="dateCosechaPLan" placeholder="MM/DD/YYY" type="date" required/>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
                        	<label>Especie</label>
                        	<select class="form-control" name="idEspecie" required>
  								<option value=" ">Seleccione Especie</option>
  				                <jsp:setProperty name="beanEspecie" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanEspecie" property="salida"/>
                            </select>
                        </div>
						<div class="form-group col-md-6">
							<label>Larvas sembradas</label> <input id="mod_sembradas" type="text"
								class="form-control" name="sembradas" required>
						</div>
					</div>
					
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
                    <h4 class="modal-title text-center">Actualizar Crianza</h4>
                </div>
                <div class="modal-body" style="background-color: #FFFFFF;">
                    <form action="../ControlaCrianza" method="POST">
                     <div class="row">
                        <div class="form-group col-md-6">
                            <label>id Crianza</label>
                            <input id="mod_idG" type="text" class="form-control" name="id" required>
                        </div>
                        <div class="form-group col-md-6"></div>
                     </div>
                     <div class="row">
						<div class="form-group col-md-6">
							<label>Granja</label>
                        	<select class="form-control" id="idGranjaG" name="idGranjaG" disabled>
  								<option value=" ">Seleccione Granja</option>
  				                <jsp:setProperty name="beanGranja" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanGranja" property="salida"/>
                            </select>
						</div>       
						<div class="form-group col-md-6">
							<label>Estanque</label> 
							<select class="form-control" id="idEstanqueG" name="idEstanqueG" disabled>
								<option value=" ">Seleccione Estanque</option>
								<jsp:setProperty name="beanEstanque" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanEstanque" property="salida"/>
							</select>
						</div>             
                    </div>
                     <div class="row">
                        <div class="form-group col-md-6">
                            <label>Tipo Crianza</label>
                            <select class="form-control" id="idTipoCrianzaG" name="tipoCrianza" disabled>
  								<option value=" ">Seleccione Tipo</option>
  				                <jsp:setProperty name="beanTipoCrianza" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanTipoCrianza" property="salida"/>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
							<label>Fecha de Inicio</label> <input class="form-control"
								id="dateInicio" name="dateInicio" placeholder="MM/DD/YYY"
								type="date" readonly>
						</div>
                     </div>
                     <div class="row">
                     	<div class="form-group col-md-6">
							<label>Fecha de Cosecha Planif.</label> <input class="form-control"
								id="dateCosPlan" name="dateCosPlan" placeholder="MM/DD/YYY"
								type="date" readonly>
						</div>
						<div class="form-group col-md-6">
							<label>Fecha de Cosecha</label> <input class="form-control"
								id="dateCos" name="dateCos" placeholder="MM/DD/YYY"
								type="date" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Ejemplares Sembrados (unid)</label> <input id="mod_semb"
								type="text" class="form-control" name="semb" required>
						</div>
					 	
						<div class="form-group col-md-6">
							<label>Ejemplares cosechados (unid)</label> <input id="mod_culti"
								type="text" class="form-control" name="culti" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Supervivencia(%)</label> <input id="mod_super"
								type="text" class="form-control" name="mod_super" onfocus="calculaDatos()" required>
						</div>
					 	
						<div class="form-group col-md-6">
							<label>Peso Promedio (gr)</label> <input id="mod_prom"
								type="text" class="form-control" name="mod_prom" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-6">
							<label>Biomasa (Kg)</label> <input id="mod_pesoCos" type="text"
								class="form-control" name="pesoCos" onfocus="calculaBiomasa()" required>
						</div>
						<div class="form-group col-md-6">
							<label>Estado de Crianza</label> 
							<select class="form-control" id="estadoC" name="estadoC" required>
							<option value=" ">Seleccione Estado...</option>
							<option value="INICIADA">INICIADA</option>
      						<option value="FINALIZADA">FINALIZADA</option>
      						
							</select>
						</div>
					</div>
					
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

    <!-- Modal registro_diario -->
     <div id="modal_reg_diario" class="modal fade" role="dialog" >
        <div class="modal-dialog" style="width:75%; align:right; ">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Mediciones Diarias</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                	<form id="mod_form_diario">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label>id Registro  Diario</label>
                            <input id="mod_idGrupo_diario" type="text" class="form-control  form-control-success" name="id" readonly>
                        </div>
                    
						<div class="form-group col-md-4">
						<label>N° de Crianza</label>
                            <input id="mod_idCrianza" type="text" class="form-control  form-control-success" name="idCrianza" readonly>
						</div> 
						<div class="form-group col-md-4">
							<label>Fecha de registro</label> <input class="form-control" id="date" name="date" type="date" required/>
						</div>            
                    </div>
					<div class="row">
						<div class="form-group col-md-4">
						<label>Registrado por:</label> 
						<select class="form-control" id="idPersona" name="idPersona" disabled >
							<%beanGestionaPersona.setId(String.valueOf(idPer)); %>
							<jsp:setProperty name="beanGestionaPersona" property="tipoOper" value="OP"/><p>
							<jsp:getProperty name="beanGestionaPersona" property="salida" />
						</select>
						</div> 
						<div class="form-group col-md-4">
							<label>Nivel de agua (cm)</label> <input id="mod_agua" type="text"
								class="form-control" name="agua" required>
						</div>
						<div class="form-group col-md-4">
							<label>Temperatura (°C)</label> <input id="mod_temperatura" type="text"
								class="form-control" name="temperatura" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label>Oxigeno (mg/L)</label> <input id="mod_oxigeno" type="text"
								class="form-control" name="oxigeno" required>
						</div>
						<div class="form-group col-md-4">
							<label>PH</label> <input id="mod_ph" type="text"
								class="form-control" name="ph" required>
						</div>
						<div class="form-group col-md-4">
							<label>Salinidad (ppt)</label> <input id="mod_salinidad" type="text"
								class="form-control" name="salinidad" required>
						</div>
					</div>
					<div class="text-center">
                            <input id="mod_input_guardar" type="button" class="btn btn-primary" name="crud" value="Registrar" onclick="med_diaria()">
                            <input id="mod_cancelar" type="button" style="display:none" class="btn btn-primary" name="crud" value="Cancelar" onclick="limpia_diarias()">
                            <input id="mod_input_actualizar" type="button" style="display:none" class="btn btn-primary" name="crud" value="Actualizar" onclick="med_diaria_actualizar()">
                    		
                    </div>
                    </form>
                </div>

				<div class="x_panel">
					<div class="x_title">
						<h2>
							Listado de Mediciones Diarias
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li>
								
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<!-- Tabla de Mantenimiento -->
						<div id="tabla" class="table-responsive">
                                
                    	</div>
					</div>
				</div>
			                
            </div>
        </div>
               
    </div>
   
   <!-- Modal registro_semanal -->
     <div id="modal_reg_semanal" class="modal fade" role="dialog" >
        <div class="modal-dialog" style="width:75%; align:right; ">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Mediciones Semanal</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                	<form id="mod_form_semanal">
                    <div class="row">
                        <div class="form-group col-md-4">
                            <label>id Registro Semanal</label>
                            <input id="mod_idGrupo1" type="text" class="form-control  form-control-success" name="id" readonly>
                        </div>
                    
						<div class="form-group col-md-4">
						<label>N° de Crianza</label>
                            <input id="mod_idCrianza1" type="text" class="form-control  form-control-success" name="idCrianza" readonly>
						</div> 
						<div class="form-group col-md-4">
							<label>Fecha de registro</label> <input class="form-control" id="date1" name="date1" type="date" />
						</div> 
					</div>
				   <div class="row">
				   		<div class="form-group col-md-4">
							<label>Registrado por:</label> 
							<select class="form-control" id="idPersonaG" name="idPersonaG" disabled >
							<%beanGestionaPersona.setId(String.valueOf(idPer)); %>
							<jsp:setProperty name="beanGestionaPersona" property="tipoOper" value="OP"/><p>
							<jsp:getProperty name="beanGestionaPersona" property="salida" />
						</select>
						</div> 
						<div class="form-group col-md-4">
							<label>Tamaño Promedio</label> <input id="mod_tamano" type="text"
								class="form-control" name="tamano" required>
						</div>
						<div class="form-group col-md-4">
							<label>Peso Promedio</label> <input id="mod_peso_prom" type="text"
								class="form-control" name="peso" required>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-4">
							<label>Poblacion Actual</label> <input id="mod_poblacion" type="text" 
							class="form-control" name="poblacion" required>
						</div>
						<div class="form-group col-md-4"></div>
						<div class="form-group col-md-4"></div>
					</div>
					<div class="text-center">
							<input id="mod_input_guardar1" type="button" class="btn btn-primary" name="crud" value="Registrar" onclick="med_semanal()">
							<input id="mod_cancelarSem" type="button" style="display:none" class="btn btn-primary" name="crud" value="Cancelar" onclick="limpia_semanal()">
						    <input id="mod_input_actualizar_sem" type="button" style="display:none" class="btn btn-primary" name="crud" value="Actualizar" onclick="med_semanal_actualizar()">                      
                    </div>
                    </form>
                </div>

				<div class="x_panel">
					<div class="x_title">
						<h2>
							Listado de Mediciones Semanal
						</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li>
								
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<!-- Tabla de Mantenimiento -->
						<div id="tabla1" class="table-responsive">
                                
                    	</div>
					</div>
				</div>
           </div>
        </div>
       
        
    </div>
	<script type="text/javascript"> 
	
	function calculaDatos() {
		
		var sembradas=$('#mod_semb').val();
		var cultivadas= $('#mod_culti').val();
		
		if(cultivadas!="" && sembradas!=""){
		var supervivencia= ((cultivadas/sembradas).toFixed(4))*100;
		document.getElementById("mod_super").value = supervivencia;}
	}
	
	function calculaBiomasa(){
		
		var promedio=$('#mod_prom').val();
		if(promedio!=""){
		var cultivadas= $('#mod_culti').val();
		var bio= (promedio*cultivadas).toFixed(4)/1000;
		document.getElementById("mod_pesoCos").value = bio;}
	}
	
	function tipo_crianza(){
	var id=$('#idTipoCrianza').val();
	
	$.ajax({
		url:'../ConsultaTipoCrianza',
		method:'POST',
		data:{idTipo:id},
		dataType:'JSON',
		success:function(data){
			
			if(data!=null){
			
			document.getElementById("mod_densidad").value = data["densidad_siembra"];
			document.getElementById("mod_peso").value = data["peso_cosecha"];
			document.getElementById("mod_tiempo").value = data["tiempo_cultivo"];
			document.getElementById("mod_recambio").value = data["recambio_agua"];
			document.getElementById("mod_supervivencia").value = data["supervivencia"];
			document.getElementById("mod_rendimiento").value = data["rendimiento"];
			$("#mod_densidad").prop('disabled', true);
			$("#mod_peso").prop('disabled', true);
			$("#mod_tiempo").prop('disabled', true);
			$("#mod_recambio").prop('disabled', true);
			$("#mod_supervivencia").prop('disabled', true);
			$("#mod_rendimiento").prop('disabled', true);
			}
								
		},
		error: function() {
	        alert("No se ha podido obtener la información");
	    }
	});
}
	
	function combo(){
		var id=$('#idGranja').val();
		
		$.ajax({
			url:'../ConsultaEstanque',
			method:'POST',
			data:{idGranja:id},
			dataType:'JSON',
			success:function(data){
				$('#idEstanque').empty();	
				$.each(data, function (index, item) {
                    $("<option/>")
                            .attr("value", item.id)
                            .text(item.nombre)
                            .appendTo("#idEstanque")

                });
				
			},
			error: function() {
		        alert("No se ha podido obtener la información");
		    }
		});
	}
	
function med_semanal_actualizar(){
		
			var idRegVar = $('#mod_idGrupo1').val();
	       var idCrianzaVar = $('#mod_idCrianza1').val();
			var dateVar = $('#date1').val();
			var idOperarioVar = $('#idPersonaG').val();
			var tamVar = $('#mod_tamano').val();
			var pesoVar = $('#mod_peso_prom').val();
			var pobla = $('#mod_poblacion').val();
			var botonVar= $('#mod_input_actualizar_sem').val();
						
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('../ControlaRegistroSemanal', {
				
				idReg: idRegVar,
				idCrianza: idCrianzaVar,
				date: dateVar,
				persona: idOperarioVar,
				tam: tamVar,
				peso: pesoVar,
				poblacion:pobla,
				crud: botonVar
			}, function(responseText) {
				
				document.getElementById("mod_idGrupo1").value = " ";
				document.getElementById("mod_tamano").value = " ";
				document.getElementById("mod_peso_prom").value = " ";
				document.getElementById("mod_poblacion").value = " ";
				
				$('#mod_cancelarSem').hide(1000);
				$('#mod_input_actualizar_sem').hide(1000);
				$('#mod_input_guardar1').show(1000);
				$('#tabla1').html(responseText);
			}); 
		
	}
	
	function med_diaria_actualizar(){
		
			var idRegVar = $('#mod_idGrupo_diario').val();
	       var idCrianzaVar = $('#mod_idCrianza').val();
			var dateVar = $('#date').val();
			var idPersonaVar = $('#idPersona').val();
			var aguaVar = $('#mod_agua').val();
			var tempVar = $('#mod_temperatura').val();
			var oxigenoVar = $('#mod_oxigeno').val();
			var phVar = $('#mod_ph').val();
			var salinidadVar = $('#mod_salinidad').val();
			var botonVar= $('#mod_input_actualizar').val();
						
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('../ControlaRegistroDiario', {
				
				idReg: idRegVar,
				idCrianza: idCrianzaVar,
				date: dateVar,
				persona: idPersonaVar,
				agua: aguaVar,
				temp: tempVar,
				oxigeno: oxigenoVar,
				ph: phVar,
				salinidad: salinidadVar,
				crud: botonVar
			}, function(responseText) {
				document.getElementById("mod_idGrupo_diario").value = " ";
				document.getElementById("mod_agua").value = " ";
				document.getElementById("mod_temperatura").value = " ";
				document.getElementById("mod_oxigeno").value = " ";
				document.getElementById("mod_ph").value = " ";
				document.getElementById("mod_salinidad").value = " ";
				
				$('#mod_cancelar').hide(1000);
				$('#mod_input_actualizar').hide(1000);
				$('#mod_input_guardar').show(1000);
				$('#tabla').html(responseText);
			}); 
		
	}
	
	function limpia_diarias(){
		var fecha = new Date(); //Fecha actual
		  var mes = fecha.getMonth()+1; //obteniendo mes
		  var dia = fecha.getDate(); //obteniendo dia
		  var ano = fecha.getFullYear(); //obteniendo año
		  if(dia<10)
		    dia='0'+dia; //agrega cero si el menor de 10
		  if(mes<10)
		    mes='0'+mes //agrega cero si el menor de 10
		
		document.getElementById('date').value=ano+"-"+mes+"-"+dia;
		document.getElementById("mod_idGrupo_diario").value = " ";
		
		document.getElementById("mod_agua").value = " ";
		document.getElementById("mod_temperatura").value = " ";
		document.getElementById("mod_agua").value = " ";
		document.getElementById("mod_oxigeno").value = " ";
		document.getElementById("mod_ph").value = " ";
		document.getElementById("mod_salinidad").value = " ";
		
		$('#mod_input_guardar').show(1000);
		$('#mod_cancelar').hide(1000);
		$('#mod_input_actualizar').hide(1000);
	}
	
	function edit_diaria(id,persona,fecha,med_prom_agua,med_prom_temp,med_prom_oxigeno,med_prom_ph,med_prom_salinidad){
						
		document.getElementById("mod_idGrupo_diario").value = id;
		document.getElementById("date").value = fecha;
		document.getElementById("mod_agua").value = med_prom_agua;
		document.getElementById("mod_temperatura").value = med_prom_temp;
		document.getElementById("mod_oxigeno").value = med_prom_oxigeno;
		document.getElementById("mod_ph").value = med_prom_ph;
		document.getElementById("mod_salinidad").value = med_prom_salinidad;
		$('#mod_input_guardar').hide(1000);
		$('#mod_cancelar').show(1000);
		$('#mod_input_actualizar').show(1000);
	}
	
	function edit_semanal(id,person,fecha,med_tam,med_peso,pobla){
		
		document.getElementById("mod_idGrupo1").value = id;
		document.getElementById("date1").value = fecha;
		document.getElementById("mod_tamano").value = med_tam;
		document.getElementById("mod_peso_prom").value = med_peso;
		document.getElementById("mod_poblacion").value = pobla;
		$('#mod_input_guardar1').hide(1000);
		$('#mod_cancelarSem').show(1000);
		$('#mod_input_actualizar_sem').show(1000);
	}
	
	function limpia_semanal(){
		var fecha = new Date(); //Fecha actual
		  var mes = fecha.getMonth()+1; //obteniendo mes
		  var dia = fecha.getDate(); //obteniendo dia
		  var ano = fecha.getFullYear(); //obteniendo año
		  if(dia<10)
		    dia='0'+dia; //agrega cero si el menor de 10
		  if(mes<10)
		    mes='0'+mes //agrega cero si el menor de 10
		
		document.getElementById('date1').value=ano+"-"+mes+"-"+dia;
		document.getElementById("mod_idGrupo1").value = " ";
		
		document.getElementById("mod_tamano").value = " ";
		document.getElementById("mod_peso_prom").value = " ";
		document.getElementById("mod_poblacion").value = " ";
		
		
		$('#mod_input_guardar1').show(1000);
		$('#mod_cancelarSem').hide(1000);
		$('#mod_input_actualizar_sem').hide(1000);
	}
	
	function med_semanal() {
     		var idRegVar = $('#mod_idGrupo1').val();
	        var idCrianzaVar = $('#mod_idCrianza1').val();
			var dateVar = $('#date1').val();
			var idOperarioVar = $('#idPersonaG').val();
			var tamVar = $('#mod_tamano').val();
			var pesoVar = $('#mod_peso_prom').val();
			var poblaVar = $('#mod_poblacion').val();
			var botonVar= $('#mod_input_guardar1').val();
						
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('../ControlaRegistroSemanal', {
				idReg: idRegVar,
				idCrianza: idCrianzaVar,
				date: dateVar,
				persona: idOperarioVar,
				tam: tamVar,
				peso: pesoVar,
				poblacion:poblaVar,
				crud: botonVar
			}, function(responseText) {
				document.getElementById("mod_tamano").value = " ";
				document.getElementById("mod_poblacion").value = " ";
				document.getElementById("mod_peso_prom").value = " ";
								
				$('#tabla1').html(responseText);
			}); 
}
		
	function med_diaria() {
	    	    var idRegVar = $('#mod_idGrupo_diario').val();
		       	var idCrianzaVar = $('#mod_idCrianza').val();
				var dateVar = $('#date').val();
				var idPersonaVar = $('#idPersona').val();
				var aguaVar = $('#mod_agua').val();
				var tempVar = $('#mod_temperatura').val();
				var oxigenoVar = $('#mod_oxigeno').val();
				var phVar = $('#mod_ph').val();
				var salinidadVar = $('#mod_salinidad').val();
				var botonVar= $('#mod_input_guardar').val();
								
				// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
				$.post('../ControlaRegistroDiario', {
					idReg: idRegVar,
					idCrianza: idCrianzaVar,
					date: dateVar,
					persona: idPersonaVar,
					agua: aguaVar,
					temp: tempVar,
					oxigeno: oxigenoVar,
					ph: phVar,
					salinidad: salinidadVar,
					crud: botonVar
				}, function(responseText) {
					document.getElementById("date").value = " ";
					document.getElementById("mod_agua").value = " ";
					document.getElementById("mod_temperatura").value = " ";
					document.getElementById("mod_agua").value = " ";
					document.getElementById("mod_oxigeno").value = " ";
					document.getElementById("mod_ph").value = " ";
					document.getElementById("mod_salinidad").value = " ";
					$('#tabla').html(responseText);
					
				}); 
		}
	
	    function diarias(id,fecha) {
			var idVar = id;
			var hoy=fecha;

			$.post('../ControlaRegistroDiario', {
				idC : idVar,
				crud : 'diario'
			}, function(responseText) {
				document.getElementById("mod_idCrianza").value = idVar;
				document.getElementById("date").value = hoy;
				$('#tabla').html(responseText);
				$('#modal_reg_diario').modal('show');
			});
		}

		function semanal(id,fecha) {
			var idVar = id;
			var hoy=fecha;
			$.post('../ControlaRegistroSemanal', {
				idC : idVar,
				crud : 'semanal'
			}, function(responseText) {
				document.getElementById("mod_idCrianza1").value = idVar;
				document.getElementById("date1").value = hoy;
				$('#tabla1').html(responseText);
				$('#modal_reg_semanal').modal('show');
				
			});

			
			
		}
		function add() {
			$('#modal_add').modal('show');
			document.getElementById("mod_form").reset();
			$("#mod_codigo").removeAttr("readonly");
			document.getElementById("mod_input").value = "Registrar";
		}

		function edit(idGrupo,idGran,idEst,dateIni,dateCos,dateCosPlan,semb,culti,pes,tipo,es,sup,prom) {
			
			$('#modal_edit').modal('show');
			$("#mod_idG").prop('readonly', true);
			$("#dateCosPlan").prop('readonly', true);
			$("#dateInicio").prop('readonly', true);
			document.getElementById("mod_idG").value = idGrupo;
			document.getElementById("idGranjaG").value = idGran;
			document.getElementById("idEstanqueG").value = idEst;
			document.getElementById("idTipoCrianzaG").value = tipo;
			document.getElementById("dateInicio").value = dateIni;
			document.getElementById("dateCos").value = dateCos;
			document.getElementById("dateCosPlan").value = dateCosPlan;
			document.getElementById("mod_semb").value = semb;
			document.getElementById("mod_culti").value = culti;
			document.getElementById("mod_prom").value = prom;
			document.getElementById("estadoC").value = es;
			
			if(culti!=null){
				document.getElementById("mod_super").value = sup;
				
			}else{
				document.getElementById("mod_culti").value = "";
				document.getElementById("mod_super").value = "";
				
			}
			if(prom!=null){
				
				document.getElementById("mod_pesoCos").value = pes;
			}else{
				document.getElementById("mod_prom").value = "";
				document.getElementById("mod_pesoCos").value = "";
				
			}
			
			document.getElementById("mod_input").value = "Actualizar";
		}

		function remove(codigo) {
			$('#modal_delete').modal('show');
			document.getElementById("mod_codDelete").value = codigo;
		}
		
		
	</script>

    <%@include file="../jspf/footer.jsp" %>   
<% } %>
 
				   
