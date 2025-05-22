package it.uniroma3.romatremotors.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.romatremotors.model.Cliente;
import it.uniroma3.romatremotors.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired private ClienteService clienteService;

	@GetMapping("/cliente/")
	public String getHome(@PathVariable("id") Long id,  Model model, Principal principal) {
		if(principal == null) {
			return "redirect:/login";
		}
		
		Cliente cliente = clienteService.getClienteById(id);
		model.addAttribute("cliente", cliente);
		return "cliente/index";
	}
}
