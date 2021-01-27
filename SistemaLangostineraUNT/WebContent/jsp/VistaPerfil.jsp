
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp" %>
<% if(request.getSession().getAttribute("authUser") != null){ %>
<!DOCTYPE html>
<%@include file="../jspf/header.jsp" %>  
    <%@include file="../jspf/menu.jsp" %>  
    <%@include file="../jspf/navbar.jsp" %>  
      <!-- contenido -->
      <div class="right_col" role="main">
          <!-- Desde Aqui va el contenido de la pagina -->
            <div class="row">
		<h3 class="text-center">Configuración general de la cuenta</h3>
		<hr>
		<div class="panel">
			<div class="panel-body">
				<div class="col-md-8">
					<div class="panel borde-primary">
						<div class="panel-heading">
							<div class="panel-title">
								<div class="text-center">Actualización de Datos</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-hover table-bordered">
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="text-center">Imagen de Perfil</div>
						</div>
						<div class="panel-body" ng-show="mostrar_imagen">
							<img src="../dist/imagenes/avatar.png" width="100%" height="300px">
						</div>
						<div class="panel-footer">
							<div class="text-center">
								<button class="btn btn-success" ng-click="seleccionar()">Seleccionar Imagen</button>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
            </div>
      </div>
      <!-- contenido -->

    <%@include file="../jspf/footer.jsp" %> 
<% } %>
