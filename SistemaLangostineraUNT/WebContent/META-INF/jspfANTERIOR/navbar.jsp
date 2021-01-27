<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%
        HttpSession auth_nav = request.getSession();
        Usuario user_nav = (Usuario) auth_nav.getAttribute("authUser");
        String nick = user_nav.getNick();
        System.out.println("NavBar. obtuve nick");
    %> 
      <!-- top navigation -->
          <div class="top_nav">
            <div class="nav_menu">
              <nav>
                <div class="nav toggle">
                  <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                </div>

                <ul class="nav navbar-nav navbar-right">
                  <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <img src="../dist/imagenes/avatar.png" alt=""><% out.print(nick); %>
                      <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                      <li>
                            <form action="ControlaLogin" method="POST">
                                <div class="input-group">
                                    <input type="submit" value="Perfil" name="operacion" class="form-control" aria-describedby="basic-addon1">
                                    <span class="input-group-addon"><span class="fa fa-check-square-o"></span></span>
                                </div>
                            </form>  
                      <li>

                        <li>
                            <form action="ControlaLogin" method="POST">
                                <div class="input-group">
                                    <input type="submit" value="Cerrar Sesion" name="operacion" class="form-control" aria-describedby="basic-addon1">
                                    <span class="input-group-addon"><span class="fa fa-check-square-o"></span></span>
                                </div>
                            </form>
                        </li>
                    </ul>
                  </li>

                </ul>
              </nav>
            </div>
          </div>
          <!-- /top navigation -->
            
