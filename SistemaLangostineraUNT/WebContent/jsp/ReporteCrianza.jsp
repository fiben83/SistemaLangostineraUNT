<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/session.jsp"%>
<%@page import="modelos.Crianza" %>
<%@page import="beans.GestionaCrianza" %>
<%@page import="java.util.List" %>

<jsp:useBean id="beanCrianza" class="beans.GestionaCrianza" />
<jsp:useBean id="beanEstanque"  class="beans.GestionaEstanque" />
<jsp:useBean id="beanEspecie"  class="beans.GestionaEspecie" />
<jsp:useBean id="beanRegistroDiario"  class="beans.GestionaRegistroDiario" />
<jsp:useBean id="beanGestionaPersona"  class="beans.GestionaPersona" />


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
                        <h2>Crianza de Langostinos<small>Reportes y Consultas</small></h2>
                        <ul class="nav navbar-right panel_toolbox">
                       		<li>
                       		 <label>Año</label>
                       		</li>
                            <li>
                            	<select id="selectAnnio" class="form-control" name="annioCrianza" required>
										<option value="-1">Seleccione..</option>
										<jsp:setProperty name="beanCrianza" property="tipoOper" value="A"/><p>
										<jsp:getProperty name="beanCrianza" property="salida" />
								</select>
							</li>
							<li>
								<button id="btnPdf" class="btn btn-success"  onclick="show_pdf('pdf')">Reporte PDF</button>
							</li>
							<li>
								<button id="btnExcel" class="btn btn-success"  onclick="show_pdf('xls')">Reporte Excel</button>
                            </li>
                           
                        </ul>
                                              
                        <div class="clearfix"></div>
                      </div>
                    <div class="x_content">
                            <!-- Tabla de Mantenimiento -->
                           <div class="table-responsive">
                          		  <jsp:setProperty name="beanCrianza" property="tipoOper" value="E" /> <p>  
                               	  <jsp:getProperty name="beanCrianza" property="salida"/>
                					                                                               
                           </div>
                    </div>
                </div>
            </div>
            
            <div id="miPdf">
            
            </div>

        </div>
        
 <!-- Modal reporte PDF -->
     <div id="modal_pdf" class="modal fade" tabindex="-1" role="dialog" >
        <div class="modal-dialog" style="width:75%; align:right; " role="document">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Reporte PDF</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                	<!-- <object type="application/pdf" data="http://localhost:8080/SistemaLangostineraUNT/ReporteCrianzas?idC=2019&crud=pdf" width="100%" height="500px"></object> -->
					 <iframe id="frameCrianzas" width="100%" height="500px"></iframe>
                               
                </div>

				<div class="modal-footer justify-content-center">
					<a id="enlacePdf" type="button" class="btn btn-primary" download="Reporte_Crianza">Descargar<i class="far fa-file-pdf ml-1 text-white"></i></a>
					<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
				</div>
			                
            </div>
        </div>
     </div>

<!-- Modal reporte diario PDF -->
     <div id="modal_pdf_diario" class="modal fade" tabindex="-1" role="dialog" >
        <div class="modal-dialog" style="width:75%; align:right; " role="document">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Reporte PDF</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                	<!--<object id="obpdf" type="application/pdf" width="100%" height="500px"></object> -->
					<iframe id="framePdf" width="100%" height="500px"></iframe>
                               
                </div>

				<div class="modal-footer justify-content-center">
					
					<a id="enlaceDiario" type="button" class="btn btn-primary" download="Reporte_Crianza">Descargar<i class="far fa-file-pdf ml-1 text-white"></i></a>
					<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
				</div>
			                
            </div>
        </div>
               
    </div>
    
    <!-- Modal reporte semanal PDF -->
     <div id="modal_pdf_sem" class="modal fade" tabindex="-1" role="dialog" >
        <div class="modal-dialog" style="width:75%; align:right; " role="document">
            <div class="modal-content"; style="background-color: #FFBF00;">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2 class="modal-title text-center">Reporte PDF</h2>
                </div>
                <div class="modal-body"; style="background-color: #FFFFFF;">
                	<!-- <object type="application/pdf" data="http://localhost:8080/SistemaLangostineraUNT/ReporteCrianzas" width="100%" height="500px"></object> -->
					 <iframe id="frameSem" width="100%" height="500px"></iframe> 
                               
                </div>

				<div class="modal-footer justify-content-center">
					
					<a id="enlaceSemanal" type="button" class="btn btn-primary" download="Reporte_Crianza">Descargar<i class="far fa-file-pdf ml-1 text-white"></i></a>
					<a type="button" class="btn btn-primary" data-dismiss="modal">Cerrar </a>
				</div>
			                
            </div>
        </div>
       
        
    </div>
    
	<script type="text/javascript"> 
	
	function show_pdf(btn) {
			
		var t =btn;
		var a = $('#selectAnnio option:selected').val();
		var url= 'http://localhost:8081/SistemaLangostineraUNT/ReporteCrianzas?annio='+a+'&tipo='+t;
	
      if(a==-1){
    	  alert("Seleccione Año");
      }else{
    	  
    	  	if(t=='pdf'){
  				$('#frameCrianzas').attr('src',url);
  				$('#enlacePdf').attr('href',url);
  				$('#modal_pdf').modal('show');
  			
  			}else{
  			$('#frameCrianzas').attr('src',url);
  			}
      }
				
	}

	function show_diario(id,t) {

		var idVar = id;
		var tipo=t;
		var url= 'http://localhost:8081/SistemaLangostineraUNT/ReporteDiario?idC='+idVar+'&crud='+tipo;
		if(tipo=='pdf'){
			$('#framePdf').attr('src',url);
			$('#enlaceDiario').attr('href',url);
			$('#modal_pdf_diario').modal('show');
		}else{
			$('#framePdf').attr('src',url);
		}
			
	}

	function show_semanal(id,t) {
		
		var idVar = id;
		var tipo=t;
		var url= 'http://localhost:8081/SistemaLangostineraUNT/ReporteSemanal?idC='+idVar+'&crud='+tipo;
		
		if(tipo=='pdf'){
			$('#frameSem').attr('src',url);
			$('#enlaceSemanal').attr('href',url);
			$('#modal_pdf_sem').modal('show');
		}else{
			$('#frameSem').attr('src',url);
		}
				
	}
	
	function med_semanal() {
     	
 	   		var idRegVar = $('#mod_idGrupo1').val();
	        var idCrianzaVar = $('#mod_idCrianza1').val();
			var dateVar = $('#date1').val();
			var idOperarioVar = $('#idOperario1').val();
			var tamVar = $('#mod_tamano').val();
			var pesoVar = $('#mod_peso_prom').val();
			var botonVar= $('#mod_input_guardar1').val();
						
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('../ControlaRegistroSemanal', {
				
				idReg: idRegVar,
				idCrianza: idCrianzaVar,
				date: dateVar,
				operario: idOperarioVar,
				tam: tamVar,
				peso: pesoVar,
				crud: botonVar
			}, function(responseText) {
				
				document.getElementById("date").value = " ";
				document.getElementById("idOperario").value = " ";
				document.getElementById("mod_tamano").value = " ";
				document.getElementById("date1").value = " ";
				document.getElementById("mod_peso_prom").value = " ";
								
				$('#tabla1').html(responseText);
			}); 
}
		
	function med_diaria() {
	    	     	
	    	   var idRegVar = $('#mod_idGrupo_diario').val();
		       var idCrianzaVar = $('#mod_idCrianza').val();
				var dateVar = $('#date').val();
				var idOperarioVar = $('#idOperario').val();
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
					operario: idOperarioVar,
					agua: aguaVar,
					temp: tempVar,
					oxigeno: oxigenoVar,
					ph: phVar,
					salinidad: salinidadVar,
					crud: botonVar
				}, function(responseText) {
					
					document.getElementById("date").value = " ";
					document.getElementById("idOperario").value = " ";
					document.getElementById("mod_agua").value = " ";
					document.getElementById("mod_temperatura").value = " ";
					document.getElementById("mod_agua").value = " ";
					document.getElementById("mod_oxigeno").value = " ";
					document.getElementById("mod_ph").value = " ";
					document.getElementById("mod_salinidad").value = " ";
					
					$('#tabla').html(responseText);
				}); 
	}
	
		
		function add() {
			$('#modal_add').modal('show');
			document.getElementById("mod_form").reset();
			$("#mod_codigo").removeAttr("readonly");
			document.getElementById("mod_input").value = "Registrar";
		}

		function edit(idGrupo, dateCosPlan, dateCos, semb, culti, peso) {
			$('#modal_edit').modal('show');
			$("#mod_idG").prop('readonly', true);
			$("#dateCosPlan").prop('readonly', true);
			document.getElementById("mod_idG").value = idGrupo;
			document.getElementById("dateCosPlan").value = dateCosPlan;
			document.getElementById("dateCos").value = dateCos;
			document.getElementById("mod_semb").value = semb;
			document.getElementById("mod_culti").value = culti;
			document.getElementById("mod_peso").value = peso;
			document.getElementById("mod_input").value = "Actualizar";
		}

		function remove(codigo) {
			$('#modal_delete').modal('show');
			document.getElementById("mod_codDelete").value = codigo;
		}
		
		
	</script>

    <%@include file="../jspf/footer.jsp" %>   
<% } %>
 
				   
