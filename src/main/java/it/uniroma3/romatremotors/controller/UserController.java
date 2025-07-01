package it.uniroma3.romatremotors.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.TestDrive;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.service.CredentialsService;
import it.uniroma3.romatremotors.service.TestDriveService;
import it.uniroma3.romatremotors.service.UserService;

@Controller
public class UserController {
	@Autowired private CredentialsService credentialsService;
	
	@Autowired private TestDriveService testDriveService;

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
	
	@GetMapping("/cliente/prenotazioni")
	public String showPrenotazioni(Model model, Principal principal) {
		Credentials credentials = credentialsService.getCredentials(principal.getName());
		Utente utente = credentials.getUtente();
		List<TestDrive> listaPrenotazioni = testDriveService.findByCliente(utente.getId());
		model.addAttribute("prenotazioni", listaPrenotazioni);
		return "cliente/listaPrenotazioni";
	}
}
