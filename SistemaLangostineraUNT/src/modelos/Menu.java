/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;


public class Menu implements Entidad{
    private Integer id;
    private String nick;
    private String nombre;
    private ArrayList<Opcion> opciones;

    public Menu(Integer id, String nick, String nombre) {
        this.id = id;
        this.nick = nick;
        this.nombre = nombre;
    }

    public Menu(Integer id, String nick, String nombre, ArrayList<Opcion> opciones) {
        this.id = id;
        this.nick = nick;
        this.nombre = nombre;
        this.opciones = opciones;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }
    
    
}
