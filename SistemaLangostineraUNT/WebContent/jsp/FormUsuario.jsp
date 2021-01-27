<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>

<jsp:useBean id="beanUser" class="beans.GestionaUsuario" />
<%
	if (request.getSession().getAttribute("authUser") != null) {
%>
<!DOCTYPE html>
<%@include file="../jspf/header.jsp"%>

<%@include file="../jspf/menu.jsp"%>

<%@include file="../jspf/navbar.jsp"%>

<!-- contenido -->
<div class="right_col" role="main">
	<!-- Desde Aqui va el contenido de la pagina -->
	<div class="col-md-12 col-sm-12 col-xs-12">
		<%@include file="../jspf/mensaje.jsp"%>
		<div class="x_panel">
			<div class="x_title">
				<h2>
					Usuarios <small>Mantenimiento de registros</small>
				</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>

						<button class="btn btn-success" onclick="add()">
							<small class="glyphicon glyphicon-plus"></small> Agregar Usuario
						</button>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<!-- Tabla de Mantenimiento -->
				<div class="table-responsive">
					<jsp:setProperty name="beanUser" property="tipoOper" value="T" /><p>
					<jsp:getProperty name="beanUser" property="salida" />
				</div>
			</div>
		</div>
	</div>

</div>


<!-- Modal Agregar -->

<div id="modal_add" class="modal fade" role="dialog">
	<div class="modal-dialog" style="width:50%; align: right;">
		<div class="modal-content" ; style="background-color: #FFBF00;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h2 class="modal-title text-center">Nuevo Usuario</h2>
			</div>
			<div class="modal-body" ; style="background-color: #FFFFFF;">
				<form name="add_form" action="../ControlaUsuario" method="POST" id="mod_formUser">
				<div class="row">
					<div class="form-group col-md-6">
						<label>Consultar DNI:</label> <input id="mod_DNI" type="text" onfocus="obtieneFoco()" onblur="consultaDni()" class="form-control  form-control-success" name="DNI" required>
						<spam id="mensajeDni"></spam>
					</div>
					<div class="form-group col-md-6">
						<input id="mod_idPersona" type="hidden" name="id">
						 <input id="mod_idUser" type="hidden"  name="idUser">
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-6">
						<label>Nombres</label> <input id="mod_nombres" type="text" class="form-control" name="nombres" required>
					</div>
					<div class="form-group col-md-6">
						<label>Apellidos</label> <input id="mod_apellidos" type="text" class="form-control" name="apellidos" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Nick</label> <input id="mod_nick" type="text" class="form-control" name="nick" required>
					</div>
					<div class="form-group col-md-6">
						<label>Password</label> <input id="mod_pass" type="text" class="form-control" name="pass" required>
					</div>
					
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Correo</label> <input id="mod_email" type="text" class="form-control" name="email" required>
					</div>
				
					<div class="form-group col-md-6">
						<label>Telefono</label> <input id="mod_telefono" type="text" class="form-control" name="telefono" required>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-6">
						<label>Rol</label> 
						<select class="form-control" id="rol" name="rol" required>
							<option value=" ">Seleccione Rol...</option>
							<option value="1">Administrador</option>
      						<option value="2">Operador</option>
      						<option value="3">Gerencia</option>
						</select>
						
					</div>
					
					<div class="form-group col-md-6">
						<label>Estado</label> 
						<select class="form-control" id="estado" name="estado" required>
							<option value=" ">Seleccione Estado...</option>
							<option value="1">ACTIVO</option>
      						<option value="0">INACTIVO</option>
     						
						</select>
						
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
	<div class="modal-dialog" style="width:50%; align: right;">
		<div class="modal-content" ; style="background-color: #FFBF00;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h2 class="modal-title text-center">Actualizar Usuario</h2>
			</div>
			<div class="modal-body" ; style="background-color: #FFFFFF;">
				<form action="../ControlaUsuario" method="POST">
				<div class="row">
					<div class="form-group col-md-6">
						<label>id Usuario</label> 
						<input id="mod_idU" type="text" class="form-control  form-control-success" name="idU" readonly>
					</div>
					
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Usuario</label> <input id="mod_nickG" type="text" class="form-control" name="nickG" required>
					</div>
					<div class="form-group col-md-6">
						<label>Contraseña</label> <input id="mod_passG" type="text" class="form-control" name="passG" required>
					</div>
					
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Correo</label> <input id="mod_emailG" type="text" class="form-control" name="emailG" required>
					</div>
				
					<div class="form-group col-md-6">
						<label>Teléfono</label> <input id="mod_telefonoG" type="text" class="form-control" name="telefonoG" required>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-6">
						<label>Rol</label> 
						<select class="form-control" id="rolG" name="rolG" disabled>
							<option value=" ">Seleccione Rol...</option>
							<option value="1">Administrador</option>
      						<option value="2">Operador</option>
      						<option value="3">Gerencia</option>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label>Estado</label> 
						<select class="form-control" id="estadoG" name="estadoG" required>
							<option value=" ">Seleccione Estado...</option>
							<option value="1">ACTIVO</option>
      						<option value="0">INACTIVO</option>
     					</select>
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
				<h4 class="modal-title text-center">¿Está Seguro que deseas
					eliminar esta especie?</h4>
				<form action="../ControlaEspecie" method="POST">
					<div class="form-group">
						<input id="mod_codDelete" type="text" class="form-control"
							name="cod" readonly="readonly">
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


<script type="text/javascript">
function obtieneFoco(){
	elementosEnForm = document.forms['add_form'].elements;
	for (var i=0; i< elementosEnForm.length; i++) {
            if (elementosEnForm[i].type != 'button') {elementosEnForm[i].disabled = false;}
            
	}
	<!--$("#mod_DNI").prop('disabled', false);-->
	$('#mensajeDni').text('');
	document.getElementById("mod_formUser").reset();
}

function consultaDni() {
	
				$.getJSON('../ConsultaDni',
						"dni="+$('#mod_DNI').val(),
						function(data){
					var data1=data[0],data2=data[1];
					if(data1!=null){
						
						document.getElementById("mod_idPersona").value = data1["id"];
						document.getElementById("mod_nombres").value = data1["nombres"];
						document.getElementById("mod_apellidos").value = data1["apellido_paterno"]+" "+data1["apellido_materno"];
					
						if(data2!=null){
							elementosEnForm = document.forms['add_form'].elements;
							for (var i=0; i< elementosEnForm.length; i++) {
				                if (elementosEnForm[i].type != 'button') {
				                	elementosEnForm[i].disabled = true;}
				                
							}
							document.getElementById("mod_nick").value = data2["nick"];
							document.getElementById("mod_pass").value = data2["password"];
							document.getElementById("mod_email").value = data2["email"];
							document.getElementById("mod_telefono").value = data2["telefono"];
							document.getElementById("rol").value = data2["id_menu"];
							document.getElementById("estado").value = data2["estado"];
						}else{
							
							document.getElementById("mod_idPersona").value = data1["id"];
							document.getElementById("mod_nombres").value = data1["nombres"];
							document.getElementById("mod_apellidos").value = data1["apellido_paterno"]+" "+data1["apellido_materno"];
						
							$("#mod_nombres").prop('disabled', true);
							$("#mod_apellidos").prop('disabled', true);
						}	
						$("#mod_DNI").prop('disabled', false);
					}else{
						
						$('#mensajeDni').css({'color':'red'});
						$('#mensajeDni').text('Dni No Existe. Registrar Persona Primero');
					}				
						});
			
}

	$(function() {
		$("#date").datepicker({
			dateFormat : "dd-mm-yy"
		});
	});

	function add_user() {
		alert("entro user");
		document.getElementById("mod_idPersona").value = $('#mod_idG').val();
		document.getElementById("btn_CreateUser").value = "Registrar";
		document.getElementById("mod_telfG").value = $('#mod_telefonoG').val();

		$('#modal_add_user').modal('show');
	}

	function add() {
		
		elementosEnForm = document.forms['add_form'].elements;
		for (var i=0; i< elementosEnForm.length; i++) {
                if (elementosEnForm[i].type != 'button') {elementosEnForm[i].disabled = false;}
                
		}
		
		$('#mensajeDni').text('');
		$('#modal_add').modal('show');
		document.getElementById("mod_formUser").reset();
		$("#mod_codigo").removeAttr("readonly");
		document.getElementById("mod_input").value = "Registrar";
	}

	function edit(idGrupo, nick, pass, email, telf, rol, es) {
		$('#modal_edit').modal('show');
		$("#mod_idU").prop('readonly', true);
		
		document.getElementById("mod_idU").value = idGrupo;
		document.getElementById("mod_nickG").value = nick;
		
		document.getElementById("mod_emailG").value = email;
		document.getElementById("mod_telefonoG").value = telf;
		document.getElementById("rolG").value = rol;
		document.getElementById("estadoG").value = es;
		document.getElementById("mod_input").value = "Actualizar";

	}

	function remove(codigo) {
		$('#modal_delete').modal('show');
		document.getElementById("mod_codDelete").value = codigo;
	}
</script>

<%@include file="../jspf/footer.jsp"%>
<%
	}
%>



