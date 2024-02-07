package com.ipartek.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets_listaproductos")
public class TicketProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "cantidad")
	private int cantidad;

	@ManyToOne
	private Ticket ticket;

	@ManyToOne
	private Producto producto;

	public TicketProducto() {
	}

	public TicketProducto(Ticket ticket, Producto producto, int cantidad) {
		this.ticket = ticket;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "TicketProducto [id=" + id + ", ticket=" + ticket + ", producto=" + producto + ", cantidad=" + cantidad
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
