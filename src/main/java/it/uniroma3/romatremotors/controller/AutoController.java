package it.uniroma3.romatremotors.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.PuntoVendita;
import it.uniroma3.romatremotors.repository.AutoRepository;
import it.uniroma3.romatremotors.repository.PuntoVenditaRepository;
import it.uniroma3.romatremotors.service.AutoService;

@Controller
public class AutoController {

	@Autowired
	private AutoRepository autoRepository;
	
	@Autowired AutoService autoService;
	
	@Autowired
    private PuntoVenditaRepository puntoVenditaRepository;
	
	
	//Per visualizzare tutte le auto in un punto vendita specifico
    @GetMapping("/lista")
    public String mostraAutoPerPuntoVendita(@RequestParam("puntoVenditaId") Long puntoVenditaId, Model model) {
        PuntoVendita puntoVendita = puntoVenditaRepository.findById(puntoVenditaId).orElse(null);
        
        if (puntoVendita == null) {
            model.addAttribute("errore", "Punto vendita non trovato.");
            return "errore";
        }

        List<Auto> autoNelPuntoVendita = autoRepository.findByPuntoVendita(puntoVendita);
        model.addAttribute("autoList", autoNelPuntoVendita);
        model.addAttribute("puntoVendita", puntoVendita);
        return "listaAuto";
    }
    
    
 // FORM GET: Mostra form nuova auto
    @GetMapping("/nuova")
    public String mostraFormNuovaAuto(@RequestParam("puntoVenditaId") Long puntoVenditaId, Model model) {
        PuntoVendita puntoVendita = puntoVenditaRepository.findById(puntoVenditaId).orElse(null);

        if (puntoVendita == null) {
            model.addAttribute("errore", "Punto vendita non trovato.");
            return "errore";
        }

        Auto auto = new Auto();
        auto.setPuntoVendita(puntoVendita);

        model.addAttribute("auto", auto);
        model.addAttribute("puntoVendita", puntoVendita);
        return "formNewAuto";
    }

    // FORM POST: Salva nuova auto
    @PostMapping("/nuova")
    public String salvaNuovaAuto(@ModelAttribute("auto") Auto auto,
                                 BindingResult bindingResult,
                                 @RequestParam("immagine") MultipartFile file,
                                 Model model) {

        System.out.println(">>> Ricevuta AUTO:");
        System.out.println("Telaio: " + auto.getTelaio());
        System.out.println("Punto vendita: " + (auto.getPuntoVendita() != null ? auto.getPuntoVendita().getId() : "null"));

        if (auto.getPuntoVendita() == null || auto.getPuntoVendita().getId() == null) {
            model.addAttribute("errore", "ID punto vendita mancante");
            return "formNewAuto";
        }

        // Ricarica il punto vendita dal database
        PuntoVendita puntoVendita = puntoVenditaRepository.findById(auto.getPuntoVendita().getId()).orElse(null);
        if (puntoVendita == null) {
            model.addAttribute("errore", "Punto vendita non valido");
            return "formNewAuto";
        }
        auto.setPuntoVendita(puntoVendita);

        try {
            if (file != null && !file.isEmpty()) {
                auto.setImmagine(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errore", "Errore nel caricamento dell'immagine");
            return "formNewAuto";
        }

        autoRepository.save(auto);
        return "redirect:/lista?puntoVenditaId=" + puntoVendita.getId();
    }


    // Endpoint per servire l'immagine
    @GetMapping("/immagine")
    @ResponseBody
    public ResponseEntity<byte[]> getImmagine(@RequestParam String telaio) {
        Auto auto = autoRepository.findById(telaio).orElse(null);
        if (auto == null || auto.getImmagine() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
            .header("Content-Type", "image/jpeg")
            .body(auto.getImmagine());
    }


    
    
	
	@GetMapping("/catalogo")
	public String getCatalogo(Model model) {
		model.addAttribute("autoList", autoRepository.findAll());
        model.addAttribute("categorie", null);
        model.addAttribute("marche", null);
        model.addAttribute("optional", null);
        model.addAttribute("colori", null);
        model.addAttribute("carburante", null);
        model.addAttribute("kmMin", null);
        model.addAttribute("kmMax", null);
        model.addAttribute("prezzoMin", null);
        model.addAttribute("prezzoMax", null);
		
		return "catalogo";
	}
	
	//@GetMapping("/auto/immagine/{id}")
    //public ResponseEntity<byte[]> getImmagine(@PathVariable String id) {
      //  Auto auto = autoRepository.findById(id).get();
        //if (auto == null || auto.getImmagine() == null) {
          //  return ResponseEntity.notFound().build();
        //}
        //return ResponseEntity.ok()
          //                   .header("Content-Type", "image/jpeg") 
            //                 .body(auto.getImmagine());
    //}
    
    
    @PostMapping("/catalogo/filtra")
    public String getAutoFiltrate( @RequestParam(value = "categoria", required = false) List<String> categorie,
            @RequestParam(value = "marca", required = false) List<String> marche,
            @RequestParam(value = "optional", required = false) List<String> optional,
            @RequestParam(value = "colore", required = false) List<String> colori,
            @RequestParam(value = "carburante", required = false) List<String> carburante,
            @RequestParam(value = "kmMin", defaultValue = "0") int kmMin,
            @RequestParam(value = "kmMax", defaultValue = "300000") int kmMax,
            @RequestParam(value = "prezzoMin", defaultValue = "0") int prezzoMin,
            @RequestParam(value = "prezzoMax", defaultValue = "100000") int prezzoMax,
            Model model) {
    	
    		
    	
	    	List<Auto> autoList = autoService.findByFilter(categorie, marche, optional, colori, carburante, kmMin, kmMax, prezzoMin, prezzoMax);
	    	
	    	model.addAttribute("autoList", autoList);
	        model.addAttribute("categorie", categorie);
	        model.addAttribute("marche", marche);
	        model.addAttribute("optional", optional);
	        model.addAttribute("colori", colori);
	        model.addAttribute("carburante", carburante);
	        model.addAttribute("kmMin", kmMin);
	        model.addAttribute("kmMax", kmMax);
	        model.addAttribute("prezzoMin", prezzoMin);
	        model.addAttribute("prezzoMax", prezzoMax);

        return "catalogo";
    }
    
    @GetMapping("/catalogo-admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCatalogoAdmin(Model model) {
        model.addAttribute("autoList", this.autoService.findAll());
        return "admin/catalogoAdmin";
    }
    	
  }
    
	
    
    

