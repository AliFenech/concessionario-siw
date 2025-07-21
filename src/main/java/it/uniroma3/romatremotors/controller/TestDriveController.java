package it.uniroma3.romatremotors.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    
    // Mostra pagina con form per selezionare la data
    @GetMapping("/cliente/prenotatestdrive/{autoId}")
    public String mostraFormData(@PathVariable Long autoId, Model model) {
        Auto auto = autoService.findById(autoId);
        model.addAttribute("auto", auto);
        return "/cliente/prenotazioneTestDrive";
    }
    
    /**
     * Trova gli orari e mostra la pagina con gli orari disponibili
     * @param autoId
     * @param dataSelezionata
     * @param model
     * @return
     */
    @PostMapping("/cliente/orariDisponibili")
    public String mostraOrariDisponibili(@RequestParam("autoId") Long autoId, @RequestParam("dataSelezionata") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSelezionata, Model model) {
    	Auto auto = this.autoService.findById(autoId);
    	model.addAttribute("auto", auto);
    	model.addAttribute("dataSelezionata", dataSelezionata);
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

        return "/cliente/prenotazioneTestDrive";
    }
    
    
 

   

    /**
     * Prende la data l'ora e salva la prenotazione
     * @param autoId
     * @param data
     * @param ora
     * @param principal
     * @param model
     * @return
     */
    @PostMapping("/cliente/salvataggioPrenotazione")
    public String prenotaTestDrive(@RequestParam("autoId") Long autoId,
                                   @RequestParam("dataSelezionata") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                   @RequestParam("orario") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime ora,
                                   Principal principal,
                                   Model model) {

        Auto auto = autoService.findById(autoId);
        Credentials credentials = credentialsService.getCredentials(principal.getName());

        LocalDateTime dataEOra = LocalDateTime.of(data, ora);

        if (testDriveService.existsByAutoAndDataEOra(autoId, dataEOra)) {
		            // Ricarica la pagina con messaggio di errore
		            model.addAttribute("auto", auto);
		            model.addAttribute("dataSelezionata", data);
		            model.addAttribute("successMessage", "⚠️ Orario non disponibile.");
		            
		            // Calcolo orari disponibili per la stessa data per mostrare i bottoni di scelta
		            List<LocalDateTime> appuntamentiPrenotati = testDriveService.findAppuntamentiPerDataEAuto(data, autoId);
		
		            List<LocalTime> orariFissi = List.of(
		                    LocalTime.of(10, 0),
		                    LocalTime.of(14, 0),
		                    LocalTime.of(16, 0),
		                    LocalTime.of(18, 0)
		            );
		
		            List<LocalTime> appuntamentiDisponibili = new ArrayList<>();
		            for (LocalTime orarioFisso : orariFissi) {
		                LocalDateTime slot = LocalDateTime.of(data, orarioFisso);
		                if (!appuntamentiPrenotati.contains(slot)) {
		                    appuntamentiDisponibili.add(orarioFisso);
		                }
		            }
		
		            model.addAttribute("appuntamenti", appuntamentiDisponibili);
		
		            return "/cliente/prenotazioneTestDrive";
		        }

        testDriveService.creaPrenotazione(auto, credentials, dataEOra);

        return "redirect:/cliente/prenotazioni";
    }

    // Mostra tutte le prenotazioni dell'utente
    @GetMapping("/cliente/prenotazioni")
    public String mostraPrenotazioni(Model model, Principal principal) {
        Credentials credentials = credentialsService.getCredentials(principal.getName());
        List<TestDrive> prenotazioni = this.testDriveService.findByCredentials(credentials);
        model.addAttribute("prenotazioni", prenotazioni);
        return "/cliente/listaPrenotazioni";
    }
    
    
    @GetMapping("/cliente/annullaPrenotazione/{id}")
    public String annullaPrenotazione(@PathVariable Long id, Principal principal) {
        
    	Optional<TestDrive> prenotazioneOpt = this.testDriveService.findById(id);
    	
    	if(prenotazioneOpt.isPresent()) {
    		
    		TestDrive prenotazione = prenotazioneOpt.get();
    		
    		if (prenotazione == null) {
                
                return "redirect:/cliente/prenotazioni";
            }
    		
    		this.testDriveService.removeTestDriveById(prenotazione);
    		return "redirect:/cliente/prenotazioni";
    	}
    	
    	return "/cliente/prenotazioni";
    	
    }
    
    
}
