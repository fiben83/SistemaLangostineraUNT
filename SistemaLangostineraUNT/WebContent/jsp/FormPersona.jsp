<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>
<jsp:useBean id="beanTipo"  class="beans.GestionaTipoPersonal" />
<jsp:useBean id="beanGrupo" class="beans.GestionaPersona" />
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
					Personas <small>Mantenimiento de registros</small>
				</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>						
						   	<select class="form-control" id="tipoPersonal" name="tipoPersonal" onchange="tipo_personal()">
  								<option value=" ">Seleccione tipo</option>
  								<option value="todos">Todos</option>
  				                <jsp:setProperty name="beanTipo" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanTipo" property="salida"/>
                            </select>
					</li><li>&nbsp&nbsp</li>
					<li>
						<button class="btn btn-success" onclick="add()">
							<small class="glyphicon glyphicon-plus"></small> Agregar Persona
						</button>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<!-- Tabla de Mantenimiento -->
				<div id="tablaSelect" class="table-responsive">
					<jsp:setProperty name="beanGrupo" property="tipoOper" value="T" /><p>
					<jsp:getProperty name="beanGrupo" property="salida" />
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
				<h2 class="modal-title text-center">Añadir Persona</h2>
			</div>
			<div class="modal-body" ; style="background-color: #FFFFFF;">
				<form action="../ControlaPersona" method="POST" id="mod_form">
				<div class="row">
					<div class="form-group col-md-6">
						<label>id Persona</label> <input id="mod_idGrupo" type="text"
							class="form-control  form-control-success" name="id" readonly>
					</div>

					<div class="form-group col-md-6">
						<label>Nombres</label> <input id="mod_nombres" type="text"
							class="form-control" name="nombres" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Apellido Paterno</label> <input id="mod_paterno"
							type="text" class="form-control" name="aPaterno" required>
					</div>
					<div class="form-group col-md-6">
						<label>Apellido Materno</label> <input id="mod_materno"
							type="text" class="form-control" name="aMaterno" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<label>DNI</label> <input id="mod_dni"
							type="text" class="form-control" name="dni" placeholder="Ingrese 10 digitos"  required>
					</div>
					<div class="form-group col-md-5">
						<label>Estado Civil</label> 
						<select class="form-control" name="estadoCivil" required>
							<option value=" ">Seleccione Estado</option>
							<option value="SOLTERO(A)">SOLTERO(A)</option>
      						<option value="CASADO(A)">CASADO(A)</option>
      						<option value="DIVORCIADO(A)">DIVORCIADO(A)</option>
      						<option value="VIUDO(A)">VIUDO(A)</option>
     						
						</select>
					</div>
					<div class="form-group col-md-4">
						<label>Sexo</label> 
						<select class="form-control" name="sexo" required>
							<option value=" ">Seleccione Sexo</option>
							<option value="M">MASCULINO</option>
      						<option value="F">FEMENINO</option>
      					</select>
					</div>
				</div>
								
				<div class="row">
					<div class="form-group col-md-6">
						<label>N° de Seguro</label> <input id="mod_seguro" type="text"
							class="form-control" name="seguro" placeholder="Ingrese 12 digitos" required>
					</div>
				
					<div class="form-group col-md-6">
						<label>Telefono</label> <input id="mod_telefono" type="text"
							class="form-control" name="telefono" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label>Dirección</label> <input id="mod_direccion" type="text"
							class="form-control" name="direccion" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
				    	<label>Fecha de Nacimiento</label> 
						<input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="date"/>
						
					</div>
					
					<div class="form-group col-md-6">
						<label>Tipo de Personal</label> 
						   	<select class="form-control" name="tipoPersonal">
  								<option value=" ">Seleccione tipo</option>
  				                <jsp:setProperty name="beanTipo" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanTipo" property="salida"/>
                            
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
				<h2 class="modal-title text-center">Actualizar Persona</h2>
			</div>
			<div class="modal-body" ; style="background-color: #FFFFFF;">
				<form action="../ControlaPersona" method="POST">
				<div class="row">
					<div class="form-group col-md-6">
						<label>id Persona</label> <input id="mod_idG" type="text" class="form-control  form-control-success" name="idG" readonly>
					</div>

					<div class="form-group col-md-6">
						<label>Nombres</label> <input id="mod_nombresG" type="text" class="form-control" name="nombresG" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label>Apellido Paterno</label> <input id="mod_paternoG" type="text" class="form-control" name="aPaternoG" required>
					</div>
					<div class="form-group col-md-6">
						<label>Apellido Materno</label> <input id="mod_maternoG" type="text" class="form-control" name="aMaternoG" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<label>DNI</label> <input id="mod_dniG" type="text" class="form-control" name="dniG" placeholder="Ingrese 10 digitos" required>
					</div>
					<div class="form-group col-md-5">
						<label>Estado Civil</label> 
						<select id="mod_estadoCivilG" class="form-control" name="estadoCivilG" required>
							<option value=" ">Seleccione Estado</option>
							<option value="SOLTERO(A)">SOLTERO(A)</option>
      						<option value="CASADO(A)">CASADO(A)</option>
      						<option value="DIVORCIADO(A)">DIVORCIADO(A)</option>
      						<option value="VIUDO(A)">VIUDO(A)</option>
     						
						</select>
					</div>
					<div class="form-group col-md-4">
						<label>Sexo</label> 
						<select id="mod_sexoG" class="form-control" name="sexoG" required>
							<option value=" ">Seleccione Sexo</option>
							<option value="M">MASCULINO</option>
      						<option value="F">FEMENINO</option>
      					</select>
					</div>
				</div>
								
				<div class="row">
					<div class="form-group col-md-6">
						<label>N° de Seguro</label> <input id="mod_seguroG" type="text" class="form-control" name="seguroG" placeholder="Ingrese 12 digitos" required>
					</div>
				
					<div class="form-group col-md-6">
						<label>Telefono</label> <input id="mod_telefonoG" type="text" class="form-control" name="telefonoG" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-12">
						<label>Dirección</label> <input id="mod_direccionG" type="text" class="form-control" name="direccionG" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
				    	<label>Fecha de Nacimiento</label> 
						<input class="form-control" id="mod_dateG" name="dateG" placeholder="MM/DD/YYY" type="date"/>
						
					</div>
					<div class="form-group col-md-6">
						<label>Tipo de Personal</label> 
						   	<select class="form-control" id="mod_tipoG" name="tipoG" disabled>
  								<option value=" ">Seleccione tipo</option>
  				                <jsp:setProperty name="beanTipo" property="tipoOper" value="S" /> <p>  
                                <jsp:getProperty name="beanTipo" property="salida"/>
                            
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

<script>
function tipo_personal(){
	var id=$('#tipoPersonal').val();
		
	$.ajax({
		url:'../ConsultaTipoPersonal',
		method:'POST',
		data:{idTipo:id},
		dataType:'html',
		success:function(data){
			
			$('#tablaSelect').html(data);
								
		},
		error: function() {
	        alert("No se ha podido obtener la información");
	    }
	});
}


$(function(){
	$("#date").datepicker({
		dateFormat:"dd-mm-yy"
	});
});

	function add_user() {
		alert("entro user");
		document.getElementById("mod_idPersona").value= $('#mod_idG').val();
		document.getElementById("btn_CreateUser").value = "Registrar";
		
		document.getElementById("mod_telfG").value = $('#mod_telefonoG').val();
		
		$('#modal_add_user').modal('show');
	}

	function add() {
		$('#modal_add').modal('show');
		document.getElementById("mod_form").reset();
		$("#mod_codigo").removeAttr("readonly");
		document.getElementById("mod_input").value = "Registrar";
	}

	function edit(idGrupo,names,apePaterno,apeMaterno,dni,dir,estCivil,fech,sex,seg,tel,idTipo) {
		$('#modal_edit').modal('show');
		$("#mod_idG").prop('readonly', true);
		document.getElementById("mod_idG").value = idGrupo;
		document.getElementById("mod_nombresG").value = names;
		document.getElementById("mod_paternoG").value = apePaterno;
		document.getElementById("mod_maternoG").value = apeMaterno;
		document.getElementById("mod_dniG").value = dni;
		document.getElementById("mod_estadoCivilG").value = estCivil;
		document.getElementById("mod_sexoG").value = sex;
		document.getElementById("mod_seguroG").value = seg;
		document.getElementById("mod_telefonoG").value = tel;
		document.getElementById("mod_direccionG").value = dir;
		document.getElementById("mod_dateG").value = fech;
		document.getElementById("mod_tipoG").value = idTipo;
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



