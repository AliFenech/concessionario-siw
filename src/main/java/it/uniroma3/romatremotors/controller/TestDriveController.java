package it.uniroma3.romatremotors.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.TestDrive;
import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.service.AutoService;
import it.uniroma3.romatremotors.service.TestDriveService;
import it.uniroma3.romatremotors.service.CredentialsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestDriveController {

    @Autowired private AutoService autoService;
    @Autowired private TestDriveService testDriveService;
    @Autowired private CredentialsService credentialsService;
    
    @GetMapping("/cliente/prenotatestdrive/{id}")
    public String showOrari(@PathVariable Long id,  Model model) {
    	
    	
    	model.addAttribute("auto", this.autoService.findById(id));
    	return "/cliente/prenotazioneTestDrive";
    }

    @GetMapping("/cliente/prenotazioneTestDrive/{autoId}")
    public String showOrari(@PathVariable Long autoId,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSelezionata,
                            Model model) {

        Auto auto = autoService.findById(autoId);
        model.addAttribute("auto", auto);
        model.addAttribute("dataSelezionata", dataSelezionata);

        if (dataSelezionata != null) {
            List<LocalDateTime> appuntamentiPrenotati = testDriveService.findAppuntamentiPerDataEAuto(dataSelezionata, autoId);

            List<LocalTime> orariFissi = List.of(
                    LocalTime.of(10, 0),
                    LocalTime.of(14, 0),
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0)
            );

            List<LocalTime> appuntamentiDisponibili = new ArrayList<>();
            for (LocalTime orario : orariFissi) {
                LocalDateTime slot = LocalDateTime.of(dataSelezionata, orario);
                if (!appuntamentiPrenotati.contains(slot)) {
                    appuntamentiDisponibili.add(orario);
                }
            }

            model.addAttribute("appuntamenti", appuntamentiDisponibili);
        }

        return "/cliente/prenotazioneTestDrive";
    }

    @PostMapping("/cliente/prenotatestdrive/{autoId}")
    public String prenotaTestDrive(@PathVariable Long autoId,
                                   @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                   @RequestParam("ora") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime ora,
                                   Principal principal,
                                   Model model) {

        Auto auto = autoService.findById(autoId);
        Credentials credentials = credentialsService.getCredentials(principal.getName());
       
        LocalDateTime dataEOra = LocalDateTime.of(data, ora);

        if (testDriveService.existsByAutoAndDataEOra(autoId, dataEOra)) {
            model.addAttribute("auto", auto);
            model.addAttribute("dataSelezionata", data);
            model.addAttribute("successMessage", "⚠️ Orario non disponibile.");
            return "/cliente/prenotazioneTestDrive";
        }

        testDriveService.creaPrenotazione(auto, credentials, dataEOra);

        
        return "redirect:/cliente/prenotazioni";
    }
    
    @GetMapping("/cliente/prenotazioni")
    public String showPrenotazioni(Model model, Principal principal) {
    	
    	Credentials credentials = credentialsService.getCredentials(principal.getName());
    	
    	List<TestDrive> prenotazioni = this.testDriveService.findByCredentials(credentials);
    	
    	model.addAttribute("prenotazioni", prenotazioni);
    	
    	return "/cliente/listaPrenotazioni";
    	
    }
    
    
}
