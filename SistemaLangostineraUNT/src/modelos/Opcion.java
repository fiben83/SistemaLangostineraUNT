/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author Eca
 */
public class Opcion {
    private Integer id;
    private String icono;
    private String nombre;
    private int id_menu;
    private ArrayList<SubOpcion> subopciones = new ArrayList<SubOpcion>();
    
    public Opcion(String icono, String nombre,ArrayList<SubOpcion> subopciones) {
        this.icono = icono;
        this.nombre = nombre;
        this.subopciones = subopciones;
    }

    public Opcion(Integer id,String icono, String nombre) {
        this.id = id;
        this.icono = icono;
        this.nombre = nombre;
    }

    public ArrayList<SubOpcion> getSubopciones() {
        return subopciones;
    }

    public void setSubopciones(ArrayList<SubOpcion> subopciones) {
        this.subopciones = subopciones;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    
    
    
}
