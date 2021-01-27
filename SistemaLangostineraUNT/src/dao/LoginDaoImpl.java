/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelos.Entidad;
import modelos.Usuario;
import GestionDeDatos.GestionaBaseDeDatos;
import java.sql.ResultSet;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ECA
 */
public class LoginDaoImpl implements LoginDao{
    
    public Usuario loguearse(Usuario e)throws Exception
    {
        Usuario usuario = (Usuario)e;
        
        usuario.setPassword(DigestUtils.md5Hex(usuario.getPassword()));//Envio Contraseña Encriptada
        
        System.out.println("Pass: "+usuario.getPassword());
        String sql = "call login_usuario('"+usuario.getNick()+"','"+usuario.getPassword()+"')";//llama al procediiento almacenado
        ResultSet rs = GestionaBaseDeDatos.consultar(sql);
        if(rs.next())   {
            System.out.println("Usuario si existe");
            usuario = new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
        }
        else  {
        	System.out.println("Usuario NO existe"+usuario.getNick()+" * "+usuario.getPassword());
        	usuario = null;
            throw new Exception("Usuario y/o Contrasena incorrectos.");
        }
        GestionaBaseDeDatos.desconectar();
       return usuario;
    }


}
