package it.uniroma3.romatremotors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessoriController {

    @GetMapping("/vendita-accessori")
    public String mostraPaginaAccessori() {
        return "vendita-accessori";
    }
}
