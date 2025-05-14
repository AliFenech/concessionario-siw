package it.uniroma3.romatremotors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.romatremotors.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired private ClienteService clienteService;
	
	@GetMapping("/cliente/{id}")
	public String getCliente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", this.clienteService.getClienteById(id));
		return "cliente.html";
	}
}
