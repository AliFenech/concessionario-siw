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
            System.out.println("Password non corrette");
            credentialsBindingResult.rejectValue("passwordConfirm", "error.credentials", "Le password non coincidono");
        }

        if (!utenteBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            credentials.setCliente(utente);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("cliente", utente); // Come nel branch `edit`
            return "cliente/index";
        }

        if (credentialsBindingResult.hasFieldErrors("passwordConfirm")) {
            model.addAttribute("errorsPasswordConfirm", credentialsBindingResult.getFieldErrors("passwordConfirm")
                .stream()
                .map(err -> err.getDefaultMessage())
                .toList());
        }

        return "formRegistrazione";
    }

    @GetMapping(value= "/success")
    public String nextLogin(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());

        if (credentials.getRuolo().equals(Credentials.ADMIN_ROLE)) {
            return "admin/index.html";
        } else if (credentials.getRuolo().equals(Credentials.CLIENT_ROLE)) {
            return "cliente/index.html";
        } else {
            return "/";
        }
    }

    @GetMapping(value= "/login")
    public String login(Model model) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("credentials", new Credentials());
        return "login";
    }
}
