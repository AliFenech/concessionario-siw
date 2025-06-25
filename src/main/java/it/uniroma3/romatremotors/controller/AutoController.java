package it.uniroma3.romatremotors.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.repository.AutoRepository;
import it.uniroma3.romatremotors.service.AutoService;

@Controller
public class AutoController {

	@Autowired
	private AutoRepository autoRepository;
	
	@Autowired AutoService autoService;
	
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
	
	@GetMapping("/auto/immagine/{id}")
    public ResponseEntity<byte[]> getImmagine(@PathVariable String id) {
        Auto auto = autoRepository.findById(id).get();
        if (auto == null || auto.getImmagine() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                             .header("Content-Type", "image/jpeg") 
                             .body(auto.getImmagine());
    }
    
    
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
    	
  }
    
	
    
    

