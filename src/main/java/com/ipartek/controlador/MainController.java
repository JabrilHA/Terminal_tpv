package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ipartek.modelo.Ticket;
import com.ipartek.repositorio.TicketRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private TicketRepository ticketRepo;

	@RequestMapping("/")
	public String cargarInicio(Model model, HttpSession session) {
		List<Ticket> listaTickets = ticketRepo.findAll();
		model.addAttribute("art_lista_ticket", listaTickets);

		return "ver_tickets";
	}
}
