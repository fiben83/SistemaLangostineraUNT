/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Eca
 */
public class SubOpcion {
    private Integer id;
    private String url;
    private String nombre;
    private String tooltip;
    private Integer id_opcion;

    public SubOpcion(Integer id, String url, String nombre, String tooltip) {
        this.id = id;
        this.url = url;
        this.nombre = nombre;
        this.tooltip=tooltip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
    public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public Integer getId_opcion() {
        return id_opcion;
    }

    public void setId_opcion(Integer id_opcion) {
        this.id_opcion = id_opcion;
    }
    
    
    
}
