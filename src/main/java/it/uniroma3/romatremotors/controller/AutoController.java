package it.uniroma3.romatremotors.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private AutoService autoService;

    @Autowired
    private PuntoVenditaRepository puntoVenditaRepository;

    // Mostra le auto di un punto vendita
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

    
    
    // Mostra form nuova auto
    @GetMapping("/admin/nuova/{id}/")
    public String mostraFormNuovaAuto(@PathVariable Long id, Model model) {
        PuntoVendita puntoVendita = puntoVenditaRepository.findById(id).orElse(null);

        if (puntoVendita == null) {
            model.addAttribute("errore", "Punto vendita non trovato.");
            return "errore";
        }

        Auto auto = new Auto();
        auto.setPuntoVendita(puntoVendita);

        model.addAttribute("auto", auto);
        model.addAttribute("puntoVendita", puntoVendita);
        return "/admin/formNewAuto";
    }

    
    
    // Salva nuova auto
    @PostMapping("/admin/nuova")
    public String salvaNuovaAuto(@ModelAttribute("auto") Auto auto,
                                 BindingResult bindingResult,
                                 @RequestParam("immagine") MultipartFile file,
                                 Model model) {

        if (auto.getPuntoVendita() == null || auto.getPuntoVendita().getId() == null) {
            model.addAttribute("errore", "ID punto vendita mancante");
            return "/admin/formNewAuto";
        }

        PuntoVendita puntoVendita = puntoVenditaRepository.findById(auto.getPuntoVendita().getId()).orElse(null);
        if (puntoVendita == null) {
            model.addAttribute("errore", "Punto vendita non valido");
            return "/admin/formNewAuto";
        }
        auto.setPuntoVendita(puntoVendita);

        try {
            if (file != null && !file.isEmpty()) {
                auto.setImmagine(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errore", "Errore nel caricamento dell'immagine");
            return "/admin/formNewAuto";
        }

        autoRepository.save(auto);
        return "redirect:/punti-vendita/" + puntoVendita.getId();
    }

    
    
    
    // Restituisce immagine dell'auto
    @GetMapping("/auto/{id}/immagine")
    public ResponseEntity<byte[]> getImmagine(@PathVariable Long id) {
        Auto auto = autoRepository.findById(id).orElse(null);
        if (auto == null || auto.getImmagine() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(auto.getImmagine());
    }
    
    
    
    

    // Mostra tutti i dettagli di un'auto
    @GetMapping("/auto/{id}")
    public String dettagliAuto(@PathVariable Long id, Model model) {
        Auto auto = autoService.findById(id);
        model.addAttribute("auto", auto);
        PuntoVendita puntoVendita = auto.getPuntoVendita();
        model.addAttribute("puntoVendita", puntoVendita);
        return "dettagliAuto";
    }

    
    
    
    
    // Catalogo pubblico
    @GetMapping("/catalogo")
    public String getCatalogo(Model model) {
        model.addAttribute("autoList", autoRepository.findAll());
        model.addAttribute("categoria", null);
        model.addAttribute("marca", null);
        model.addAttribute("colore", null);
        model.addAttribute("carburante", null);
        model.addAttribute("kmMin", 0);
        model.addAttribute("kmMax", 500000);
        model.addAttribute("prezzoMin", 0);
        model.addAttribute("prezzoMax", 300000);
        return "catalogo";
    }

    
    
    
    
    // Catalogo filtrato
    @PostMapping("/catalogo/filtra")
    public String getAutoFiltrate(
            @RequestParam(value = "categoria", required = false) String categoria,
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "colore", required = false) String colore,
            @RequestParam(value = "carburante", required = false) String carburante,
            @RequestParam(value = "kmMin", defaultValue = "0") int kmMin,
            @RequestParam(value = "kmMax", defaultValue = "500000") int kmMax,
            @RequestParam(value = "prezzoMin", defaultValue = "0") int prezzoMin,
            @RequestParam(value = "prezzoMax", defaultValue = "300000") int prezzoMax,
            Model model) {

    	
    	List<Auto> autoList = autoService.filtraAuto(
                categoria, marca,  colore, carburante, kmMin, kmMax, prezzoMin, prezzoMax);

      
        model.addAttribute("autoList", autoList);
        model.addAttribute("categoria", categoria);
        model.addAttribute("marca", marca);
        model.addAttribute("colore", colore);
        model.addAttribute("carburante", carburante);
        model.addAttribute("kmMin", kmMin);
        model.addAttribute("kmMax", kmMax);
        model.addAttribute("prezzoMin", prezzoMin);
        model.addAttribute("prezzoMax", prezzoMax);

        return "catalogo";
    }
    
    
    
    @GetMapping("/filtroCategoria/{cerca}")
    public String filterHome(@PathVariable String cerca, Model model) {
    	
    	

    	List<Auto> autoList = autoService.filtraAuto(
                cerca, null,  null, null, 0, 500000, 0, 300000);
    	
    	model.addAttribute("autoList", autoList);
        model.addAttribute("categoria", cerca);
        model.addAttribute("marca", null);
        model.addAttribute("colore", null);
        model.addAttribute("carburante", null);
        model.addAttribute("kmMin", 0);
        model.addAttribute("kmMax", 500000);
        model.addAttribute("prezzoMin", 0);
        model.addAttribute("prezzoMax", 300000);

        return "catalogo";
    	
    }
    
    
    @GetMapping("/filtroCarburante/{cerca}")
    public String filterFuelHome(@PathVariable String cerca, Model model) {
    	
    	

    	List<Auto> autoList = autoService.filtraAuto(
                null, null,  null, cerca, 0, 500000, 0, 300000);
    	
    	model.addAttribute("autoList", autoList);
        model.addAttribute("categoria", null);
        model.addAttribute("marca", null);
        model.addAttribute("colore", null);
        model.addAttribute("carburante", cerca);
        model.addAttribute("kmMin", 0);
        model.addAttribute("kmMax", 500000);
        model.addAttribute("prezzoMin", 0);
        model.addAttribute("prezzoMax", 300000);

        return "catalogo";
    	
    }
    
    
    //eliminazione auto
    @GetMapping("/admin/eliminaAuto/{id}")
    public String deleteAuto(@PathVariable Long id) {
        Optional<Auto> optAuto = autoRepository.findById(id);
        if (optAuto.isPresent()) {
            Long puntoVenditaId = optAuto.get().getPuntoVendita().getId();
            autoRepository.deleteById(id);
            return "redirect:/punti-vendita/" + puntoVenditaId;
        } else {
            throw new IllegalArgumentException("ID auto non valido: " + id);
        }
    }

    
    
    //modifica Auto GET
    @GetMapping("/admin/modificaAuto/{id}")
    public String modificaAutoQueryParam(@PathVariable Long id, Model model) {
        Auto auto = autoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID auto non valido: " + id));
        model.addAttribute("auto", auto);
        return "/admin/modificaAuto";
    }


    
    
    //modifica auto POST
    @PostMapping("/admin/modificaAuto")
    public String modificaAuto(@ModelAttribute("auto") Auto auto,
                              @RequestParam("file") MultipartFile file) {
        Optional<Auto> optAuto = autoRepository.findById(auto.getId());
        if (optAuto.isPresent()) {
            Auto existingAuto = optAuto.get();

            // Aggiorna campi manualmente
            existingAuto.setTarga(auto.getTarga());
            existingAuto.setMarca(auto.getMarca());
            existingAuto.setColore(auto.getColore());
            existingAuto.setKm(auto.getKm());
            existingAuto.setPrezzo(auto.getPrezzo());
            existingAuto.setCarburante(auto.getCarburante());
            existingAuto.setCategoria(auto.getCategoria());

            // Aggiorna immagine solo se file caricato
            if (!file.isEmpty()) {
                try {
                    existingAuto.setImmagine(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            autoRepository.save(existingAuto);
            return "redirect:/punto-vendita/" + existingAuto.getPuntoVendita().getId();
        } else {
            throw new IllegalArgumentException("ID auto non valido: " + auto.getId());
        }
    }


}
