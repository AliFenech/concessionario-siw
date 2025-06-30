package it.uniroma3.romatremotors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssistenzaController {

    @GetMapping("/assistenza-clienti")
    public String mostraPaginaAssistenza() {
        return "assistenza-clienti";
    }
}

