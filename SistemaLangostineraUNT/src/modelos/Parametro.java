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
public class Parametro {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMed;
    private double valorMin;
    private double valorMax;

    public Parametro(String codigo, String nombre,String descripcion, double valorMin, double valorMax,String unidadMed) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion=descripcion;
        this.valorMin=valorMin;
        this.valorMax=valorMax;
        this.unidadMed=unidadMed;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValorMin() {
		return valorMin;
	}

	public void setValorMin(double valorMin) {
		this.valorMin = valorMin;
	}

	public double getValorMax() {
		return valorMax;
	}

	public void setValorMax(double valorMax) {
		this.valorMax = valorMax;
	}

	public String getUnidadMed() {
		return unidadMed;
	}

	public void setUnidadMed(String unidadMed) {
		this.unidadMed = unidadMed;
	}
    
	
    
}
