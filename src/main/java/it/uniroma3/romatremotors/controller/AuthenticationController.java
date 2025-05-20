package it.uniroma3.romatremotors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.romatremotors.model.Cliente;
import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.service.CredentialsService;

@Controller
public class AuthenticationController {

	@Autowired CredentialsService credentialsService;
	
	@GetMapping("/registrazione")
	public String showRegisterForm(Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("credentials", new Credentials());
		return "formRegistrazione";
	}
	
	@PostMapping("/registrazione")
	public String registerClient( @ModelAttribute("cliente") Cliente cliente, BindingResult clienteBindingResult,  @ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult, Model model) {
		
		//se il cliente e le credenziali hanno contenuti che rispettano i valid allora completo la registrazione del cliente
		if(!clienteBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
			credentials.setCliente(cliente);
			credentialsService.saveCredentials(credentials);
			model.addAttribute("cliente", cliente);
			return "registrazioneCompletata";
		}
		return "registrazione";
	}
	
}
