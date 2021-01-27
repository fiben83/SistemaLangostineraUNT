
<%@page import="modelos.Usuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelos.Parametro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.GestionaParametro"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesionp = request.getSession();
    Usuario user_p = (Usuario) sesionp.getAttribute("authUser");
    GestionaParametro gestP = new GestionaParametro();
    ArrayList<Parametro> tiposDocumento=null,estadosCivil=null,sexos=null;
    if(user_p!=null){
        gestP.setParametros((ArrayList<Parametro>)sesionp.getAttribute("parametros"));
        tiposDocumento = gestP.getTiposDocumento();
        estadosCivil = gestP.getEstadosCivil();
        sexos = gestP.getSexos();
    }
    
    %>
<!DOCTYPE html>
<% if(user_p!=null){ %>
<div class="hidden"> <input name="id" value="0"></div>
<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Nombres</label>
  <input type="text" class="form-control has-feedback-left" id="inputSuccess2" name="nombres" pattern="^[a-zA-Z0-9 ]+$"  required>
  <small class="fa fa-user form-control-feedback left" aria-hidden="true"></small>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Apellido Paterno</label>
  <input type="text" class="form-control has-feedback-left" id="inputSuccess2" name="ape_pater" required>
  <small class="fa fa-user form-control-feedback left" aria-hidden="true"></small>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
	  <label>Apellido Materno</label>
  <input type="text" class="form-control has-feedback-left" id="inputSuccess2" name="ape_mater" required>
  <small class="fa fa-user form-control-feedback left" aria-hidden="true"></small>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Direcciòn</label>
  <input type="text" class="form-control has-feedback-left" id="inputSuccess6" name="dir" required>
  <small class="fa fa-location-arrow form-control-feedback left" aria-hidden="true"></small>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Tipo de Documento</label>
  <select class="form-control" name="tipo_doc" required>
          <option value="">Seleccione</option>
          <% Iterator tp = tiposDocumento.iterator(); %>
          <% while(tp.hasNext()){ %>
                 <% Parametro p = (Parametro)tp.next(); %>
                 <option value="<% out.print(p.getCodigo()); %>"><% out.print(p.getNombre()); %></option>	
          <% } %>
  </select>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Numero de Documento</label>
  <input type="text" class="form-control has-feedback-left" id="inputSuccess6" name="doc" required>
  <small class="fa fa-file form-control-feedback left" aria-hidden="true"></small>
</div>

<div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
    <label>Estado Civil</label>
  <select class="form-control" name="est_civil" required>
          <option value="">Seleccione</option>
          <% Iterator ec = estadosCivil.iterator(); %>
          <% while(ec.hasNext()){ %>
                 <% Parametro p = (Parametro)ec.next(); %>
                 <option value="<% out.print(p.getCodigo()); %>"><% out.print(p.getNombre()); %></option>	
          <% } %>
  </select>
</div>

 <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
     <label>Sexo</label>
  <select id="mod_sexo" class="form-control" name="sexo" required>
          <option value="">Seleccione</option>
          <% Iterator se = sexos.iterator(); %>
          <% while(se.hasNext()){ %>
                 <% Parametro p = (Parametro)se.next(); %>
                 <option value="<% out.print(p.getCodigo()); %>"><% out.print(p.getNombre()); %></option>	
          <% } %>
  </select>
</div>

  <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
      <label>Fecha de Nacimiento</label>
      <input type="date" class="form-control has-feedback-left" id="inputSuccess6" name="fecha_nac" required>
      <small class="fa fa-calendar form-control-feedback left" aria-hidden="true"></small>
</div>
  <% }%>