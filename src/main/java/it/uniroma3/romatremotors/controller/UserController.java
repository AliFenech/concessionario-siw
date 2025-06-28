package it.uniroma3.romatremotors.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.service.CredentialsService;

@Controller
public class UserController {
	@Autowired private CredentialsService credentialsService;

	@GetMapping("/cliente/index")
	public String clienteIndex(Model model, Principal principal) {
	Credentials credentials = credentialsService.getCredentials(principal.getName());
	model.addAttribute("cliente", credentials.getUtente());
	return "cliente/index";
	}

	@GetMapping("/admin/index")
	public String adminIndex(Model model, Principal principal) {
	Credentials credentials = credentialsService.getCredentials(principal.getName());
	model.addAttribute("admin", credentials.getUtente());
	return "admin/index";
	}
}
