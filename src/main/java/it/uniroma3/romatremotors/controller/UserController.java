package it.uniroma3.romatremotors.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.service.CredentialsService;
import it.uniroma3.romatremotors.service.UserService;

@Controller
public class UserController {
	@Autowired private UserService userService;
	@Autowired private CredentialsService credentialsService;

	@GetMapping("/cliente/")
	public String getHome(@PathVariable("id") Long id,  Model model, Principal principal) {
		if(principal == null) {
			return "redirect:/login";
		}
		
		Utente utente = userService.getUserById(id);
		
		Credentials credentials = credentialsService.getCredentials(utente.getId());
		
		if (credentials.getRuolo() == "ADMIN") {	
		
			model.addAttribute("cliente", utente);
			return "cliente/index";
		}else {
			model.addAttribute("admin", utente);
			return "admin/index";
		}
	}
}
