/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelos.Usuario;


public interface LoginDao {
    
    public Usuario loguearse(Usuario u)throws Exception;
    //public void crear(Entidad e)throws Exception;
}
