package it.uniroma3.romatremotors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.repository.AutoRepository;

@Controller
public class AutoController {

	@Autowired
	private AutoRepository autoRepository;
	
	@GetMapping("/catalogo")
	public String getCatalogo(Model model) {
		model.addAttribute("autoList", autoRepository.findAll());
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
	
}
