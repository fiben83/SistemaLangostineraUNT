
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- footer content -->
      <footer>
        <div class="pull-right">
          Almacén Central - UNP
        </div>
        <div class="clearfix"></div>
      </footer>
      <!-- /footer content -->
    </div>
  </div>

  <script src="../dist/js/jQuery-2.1.4.min.js"></script>
  <script src="../dist/js/jquery.dataTables.js"></script>
  <script src="../dist/js/bootstrap.min.js"></script>
  <script src="../dist/js/custom.js"></script>
  <script>
 
  $('#mi_tabla').DataTable( {
		"language": {
			"lengthMenu": "Ver _MENU_ registros por pagina",
			"iDisplayLength":	5,
			"zeroRecords": "No se encontraron coincidencias",
			"info": "Mostrando pagina _PAGE_ de _PAGES_",
			"infoEmpty": "No se encontraron registros",
			"infoFiltered": "(filtered from _MAX_ total records)",
                        "sSearch":         "Buscar:",
                        "oPaginate": {
                            "sNext":     "Siguiente",
                            "sPrevious": "Anterior"
                        }
		}
	} );
  
  $('#mi_tabla1').DataTable( {
		"language": {
			"lengthMenu": "Ver _MENU_ registros por pagina",
			"zeroRecords": "No se encontraron coincidencias",
			"info": "Mostrando pagina _PAGE_ de _PAGES_",
			"infoEmpty": "No se encontraron registros",
			"infoFiltered": "(filtered from _MAX_ total records)",
                      "sSearch":         "Buscar:",
                      "oPaginate": {
                          "sNext":     "Siguiente",
                          "sPrevious": "Anterior"
                      }
		}
	} );
  
  $('#mi_tabla2').DataTable( {
		"language": {
			"lengthMenu": "Ver _MENU_ registros por pagina",
			"zeroRecords": "No se encontraron coincidencias",
			"info": "Mostrando pagina _PAGE_ de _PAGES_",
			"infoEmpty": "No se encontraron registros",
			"infoFiltered": "(filtered from _MAX_ total records)",
                    "sSearch":         "Buscar:",
                    "oPaginate": {
                        "sNext":     "Siguiente",
                        "sPrevious": "Anterior"
                    }
		}
	} );
  
    $('#table_historia').DataTable( {
		"language": {
			"lengthMenu": "Ver _MENU_ registros por pagina",
			"zeroRecords": "No se encontraron coincidencias",
			"info": "Mostrando pagina _PAGE_ de _PAGES_",
			"infoEmpty": "No se encontraron registros",
			"infoFiltered": "(filtered from _MAX_ total records)",
                        "sSearch":         "Buscar:",
                        "oPaginate": {
                            "sNext":     "Siguiente",
                            "sPrevious": "Anterior"
                        }
		}
	} );
    
    
  </script>
  <script>
      $('.alphaonly').bind('keyup blur',function(){ 
            var node = $(this);
            node.val(node.val().replace(/[^a-z ]/g,'') ); }
        );
  </script>



  </body>

</html>
