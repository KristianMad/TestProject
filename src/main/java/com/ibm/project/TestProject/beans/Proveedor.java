package com.ibm.project.TestProject.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Proveedores")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
	private int idProveedor;
	
	
	@Column(name="nombre")
    private String nombre;
	
	@Column(name="fecha_alta")
    private Date fechaAlta;
	
	@Column(name="cliente")
    private int cliente;
    
	public Proveedor(int idProveedor, String nombre, Date fechaAlta, int cliente) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.cliente = cliente;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return idProveedor+";"+nombre+";"+fechaAlta+";"+cliente;
		
	}
	

}