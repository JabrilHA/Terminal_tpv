package com.ipartek.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Ticket;
import com.ipartek.modelo.TicketProducto;
import com.ipartek.repositorio.ProductoRepository;
import com.ipartek.repositorio.TicketProductoRepository;
import com.ipartek.repositorio.TicketRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class TicketController {
	
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private TicketProductoRepository ticketProdRepo;
	
	@RequestMapping("/guardarEnTicket/{id}")
	public String CrearTicket(@PathVariable int id,HttpSession session) {
		String id_producto = ""+id;

		if (session.getAttribute(id_producto)!=null) {
			int cantidad = (int)session.getAttribute(id_producto);
			session.setAttribute(id_producto, cantidad+1);
		}else {
			session.setAttribute(id_producto,1);
		}

		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeProductos = Collections.list(enumerado);
		
		for (String elemento : listaDeProductos) {
			System.out.println("Cantidad: "+session.getAttribute(elemento) +"del elemento: " +elemento);
		}

		return "redirect:/realizar_ticket";
	}
	
	@RequestMapping("/añadirProducto/{id}")
	public String añadirProductoMas(@PathVariable int id,HttpSession session) {
		String id_producto = ""+id;
		int cantidad = (int)session.getAttribute(id_producto);
		session.setAttribute(id_producto, cantidad+1);
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeProductos = Collections.list(enumerado);
		
		for (String elemento : listaDeProductos) {
			System.out.println("Cantidad: "+session.getAttribute(elemento) +"del elemento: " +elemento);
		}
		
		return "redirect:/realizar_ticket";
	}
	
	@RequestMapping("/restarProducto/{id}")
	public String restarProductoMenos(@PathVariable int id,HttpSession session) {
		String id_producto = ""+id;
		int cantidad = (int)session.getAttribute(id_producto);
		
		if(cantidad == 1) {
			session.removeAttribute(id_producto);
		}else {
			session.setAttribute(id_producto, cantidad-1);
		}
		
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeProductos = Collections.list(enumerado);
		for (String elemento : listaDeProductos) {
			System.out.println("Cantidad: "+session.getAttribute(elemento) +"del elemento: " +elemento);
		}
		
		return "redirect:/realizar_ticket";
	}
	
	
	@RequestMapping("/borrarTicket")
	public String BorrarTicket(HttpSession session) {
		session.invalidate();
		return "redirect:/realizar_ticket";
	}
	
	
	@RequestMapping("/guardarTicket")
	public String GuardarTicket(HttpSession session) {
		Ticket ti = new Ticket();
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String fechaFormateada = formato.format(fecha);
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
		String horaFormateada = formatoHora.format(fecha);
		ti.setFecha(fechaFormateada);
		ti.setHora(horaFormateada);
		Ticket ticketinsertado = ticketRepo.save(ti);
		
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeAtributos = Collections.list(enumerado);
		
		if (!listaDeAtributos.isEmpty()) {
			List<TicketProducto> listaTicketProducto = new ArrayList<TicketProducto>();
			
			for (String elem : listaDeAtributos) {
				Producto p = productoRepo.getById(Integer.parseInt(elem));
				int cantidad = (int) session.getAttribute(elem);
				TicketProducto prodTick = new TicketProducto(ticketinsertado,p,cantidad);
				listaTicketProducto.add(prodTick);												
			}
			
			for(TicketProducto ticketProducto: listaTicketProducto) {
				
				ticketProdRepo.save(ticketProducto);
			}
			
			session.invalidate();
		}
		
		return "redirect:/realizar_ticket";
	}
	
	
	@RequestMapping("/detallesTicket/{id}")
	public String verTicket(Model model, @PathVariable int id) {
		List<TicketProducto> listaTicketProd = ticketProdRepo.obtenerProductosPorTicketId(id);
		List<Ticket> listaTickets = ticketRepo.findAll();
		double total = 0;
		
		for (TicketProducto ticket : listaTicketProd) {
			total = total + ticket.getProducto().getPrecio() * ticket.getCantidad();
			}
		
		//Optional<Ticket> ticket = ticketRepo.findById(id);
		Ticket ticket = ticketRepo.getById(id);
		String fecha = ticket.getFecha();
		String hora = ticket.getHora();

		model.addAttribute("art_lista_ticketProd", listaTicketProd);
		model.addAttribute("art_lista_ticket", listaTickets);
		model.addAttribute("art_total", total);
		model.addAttribute("fecha", fecha);
		model.addAttribute("hora", hora);
		
		return "ver_tickets";
	}

}
