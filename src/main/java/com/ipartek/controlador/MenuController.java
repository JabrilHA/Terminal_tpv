package com.ipartek.controlador;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.modelo.Producto;
import com.ipartek.modelo.Ticket;
import com.ipartek.repositorio.ProductoRepository;
import com.ipartek.repositorio.TicketRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {

	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private TicketRepository ticketRepo;

	@RequestMapping("/administracion_productos")
	public String cargarAdministracion(Model model) {
		List<Producto> listaProductos = productoRepo.findAll();
		model.addAttribute("atr_lista_productos", listaProductos);
		model.addAttribute("objeto_entidad", new Producto());
		
		return "admin";
	}

	@RequestMapping("/realizar_ticket")
	public String cargarRealizarTicket(Model model, HttpSession session) {
		Enumeration<String> enumerado = session.getAttributeNames();
		List<String> listaDeAtributos = Collections.list(enumerado);
		
		if (!listaDeAtributos.isEmpty()) {
			List<Map.Entry<Producto, Integer>> listaReal = new ArrayList<>();
			double total = 0;
			
			for (String elem : listaDeAtributos) {
				Producto p = productoRepo.getById(Integer.parseInt(elem));
				int cantidad = (int) session.getAttribute(elem);
				total = total + p.getPrecio();
				listaReal.add(new SimpleEntry<>(p, cantidad));
			}

			model.addAttribute("atr_datos_en_sesion", listaReal);
			model.addAttribute("total", total);
		}
		
		List<Producto> listaProductos = productoRepo.findAll();
		model.addAttribute("atr_lista_productos", listaProductos);

		return "realizar_ticket";
	}

	@RequestMapping("/tickets")
	public String cargarVerTickets(Model model) {
		List<Ticket> listaTickets = ticketRepo.findAll();
		model.addAttribute("art_lista_ticket", listaTickets);

		return "ver_tickets";
	}
}
