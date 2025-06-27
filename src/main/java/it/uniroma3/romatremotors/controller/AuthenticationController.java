package it.uniroma3.romatremotors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.romatremotors.controller.validator.CredentialsValidation;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.service.CredentialsService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired 
    private CredentialsService credentialsService;

    @Autowired 
    private CredentialsValidation credentialsValidation;
    
    
    @GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			  model.addAttribute("username", userDetails.getUsername());
			  System.out.println("Username in controller: " + userDetails.getUsername());

			if (credentials.getRuolo().equals(Credentials.ADMIN_ROLE)) {
				return "admin/indexAdmin";
			}
		}
        return "index"; 
        }
    
    
    

    @GetMapping("/registrazione")    
    public String showRegisterForm(Model model) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("credentials", new Credentials());
        return "formRegistrazione";
    }
    
    @PostMapping("/registrazione")
    public String registerClient(@Valid @ModelAttribute("utente") Utente utente,
                                 BindingResult utenteBindingResult,
                                 @Valid @ModelAttribute("credentials") Credentials credentials,
                                 BindingResult credentialsBindingResult,
                                 Model model) {

        this.credentialsValidation.validate(credentials, credentialsBindingResult);

        if (!credentials.getPassword().equals(credentials.getPasswordConfirm())) {
            credentialsBindingResult.rejectValue("passwordConfirm", "error.credentials", "Le password non coincidono");
        }

        if (!utenteBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentials.setCliente(utente);
            credentials.setRuolo(Credentials.CLIENTE_ROLE); // assegna il ruolo CLIENT
            credentialsService.saveCredentials(credentials);
            model.addAttribute("cliente", utente);
            return "redirect:/login";
        }

        if (credentialsBindingResult.hasFieldErrors("passwordConfirm")) {
            model.addAttribute("errorsPasswordConfirm", credentialsBindingResult.getFieldErrors("passwordConfirm")
                .stream()
                .map(err -> err.getDefaultMessage())
                .toList());
        }

        return "formRegistrazione";
    }


    @GetMapping(value = "/success")
    public String nextLogin(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

        if (credentials.getRuolo().equals(Credentials.ADMIN_ROLE)) {
            return "redirect:/admin/index";
        } else if (credentials.getRuolo().equals(Credentials.CLIENTE_ROLE)) {
            return "redirect:/cliente/index";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping(value= "/login")
    public String login(Model model) {
      
        return "login";
    }
}
