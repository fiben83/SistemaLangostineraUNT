/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

public class Persona {
    
    private Integer id;
    private String nombres,apellido_paterno,apellido_materno,numero_documento,direccion,
                    estado_civil,sexo,urlfoto, idTipoPersonal;
    private Date fecha_nacimiento;

    public Persona(String nombres, String apellido_paterno, String apellido_materno, 
                   String numero_documento, String direccion, String estado_civil,
                   String sexo,String idTipoPersonal,String urlfoto) {
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.numero_documento = numero_documento;
        this.direccion = direccion;
        this.estado_civil = estado_civil;
        this.sexo = sexo;
        this.idTipoPersonal= idTipoPersonal;
        
        this.urlfoto = urlfoto;
    }

	public Persona(String id,String nombres, String apellido_paterno, String apellido_materno, String numero_documento,
			String telefono) {
		this.id=Integer.parseInt(id);
		this.nombres = nombres;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.numero_documento = numero_documento;
		this.direccion = telefono;
		
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    
    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdTipoPersonal() {
		return idTipoPersonal;
	}

	public void setIdTipoPersonal(String idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}

	public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

	public String getUrlfoto() {
		return urlfoto;
	}

	public void setUrlfoto(String urlfoto) {
		this.urlfoto = urlfoto;
	}
    
    
    
}
