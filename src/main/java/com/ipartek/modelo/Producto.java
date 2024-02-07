package com.ipartek.modelo;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

	@Id//primary key and not null
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrement
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="iva")
	private int iva;
	
	

	public Producto(int id, String nombre, double precio, String foto, int iva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.foto = foto;
		this.iva = iva;
		
	}

	public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0.0;
		this.foto = "";
		this.iva = 0;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	

	

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", foto=" + foto + ", iva=" + iva
				+ "]";
	}
	
	
}
