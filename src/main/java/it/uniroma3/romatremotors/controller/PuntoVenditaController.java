package it.uniroma3.romatremotors.controller;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.PuntoVendita;
import it.uniroma3.romatremotors.repository.AutoRepository;
import it.uniroma3.romatremotors.repository.PuntoVenditaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PuntoVenditaController {

    @Autowired
    private PuntoVenditaRepository puntoVenditaRepository;
    
    @Autowired
    private AutoRepository autoRepository;

    // Mostra la lista dei punti vendita
    @GetMapping("/punti-vendita")
    public String getPuntiVendita(Model model) {
        model.addAttribute("puntiVendita", puntoVenditaRepository.findAll());
        return "punti-vendita";
    }

    // Mostra il form per creare un nuovo punto vendita
    @GetMapping("/admin/punti-vendita-form/new")
    public String showForm(Model model) {
        model.addAttribute("puntoVendita", new PuntoVendita());
        return "/admin/punti-vendita-form";
    }

    // Salva il nuovo punto vendita nel database (con controllo ID duplicato evitato)
    @PostMapping("/admin/punti-vendita")
    public String savePuntoVendita(@ModelAttribute PuntoVendita puntoVendita, Model model) {
        // Forza ID a null per creare un nuovo oggetto
        puntoVendita.setId(null);
        puntoVenditaRepository.save(puntoVendita);
        return "redirect:/punti-vendita";
    }

    // Mostra i dettagli di un punto vendita
    @GetMapping("/punti-vendita/{id}")
    public String mostraDettagli(@PathVariable("id") Long id, Model model) {
        PuntoVendita puntoVendita = puntoVenditaRepository.findById(id).orElse(null);
        model.addAttribute("puntoVendita", puntoVendita);
        return "dettagli-punto-vendita";
    }
    
    //elimina punto vendita
    @GetMapping("/admin/punti-vendita/{id}/delete")
    public String deletePuntoVendita(@PathVariable("id") Long id) {
        PuntoVendita pv = puntoVenditaRepository.findById(id).orElse(null);
        
        if (pv != null) {
            
			// Elimina tutte le auto associate a quel punto vendita
            List<Auto> autoAssociate = autoRepository.findByPuntoVendita(pv);
            autoRepository.deleteAll(autoAssociate);

            // Ora si può eliminare il punto vendita
            puntoVenditaRepository.delete(pv);
        }

        return "redirect:/punti-vendita";
    }

}
