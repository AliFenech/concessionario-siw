package it.uniroma3.romatremotors.controller;

import it.uniroma3.romatremotors.model.PuntoVendita;
import it.uniroma3.romatremotors.repository.PuntoVenditaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/puntiVendita")
public class PuntoVenditaController {

    @Autowired
    private PuntoVenditaRepository repository;

    @GetMapping
    public String listaPuntiVendita(Model model) {
        model.addAttribute("puntiVendita", repository.findAll());
        return "puntiVendita/lista";  // Thymeleaf template
    }

    @GetMapping("/{id}")
    public String dettagliPuntoVendita(@PathVariable Long id, Model model) {
        PuntoVendita pv = repository.findById(id).orElse(null);
        if (pv == null) {
            return "redirect:/puntiVendita";
        }
        model.addAttribute("puntoVendita", pv);
        return "puntiVendita/dettagli";
    }

    @GetMapping("/aggiungi")
    public String mostraFormAggiungi(Model model) {
        model.addAttribute("puntoVendita", new PuntoVendita());
        return "puntiVendita/aggiungi";
    }

    @PostMapping("/aggiungi")
    public String aggiungiPuntoVendita(@ModelAttribute PuntoVendita pv) {
        repository.save(pv);
        return "redirect:/puntiVendita";
    }
}
