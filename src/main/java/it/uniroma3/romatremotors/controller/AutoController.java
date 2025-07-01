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

    // Salva nuova auto
    @PostMapping("/nuova")
    public String salvaNuovaAuto(@ModelAttribute("auto") Auto auto,
                                 BindingResult bindingResult,
                                 @RequestParam("immagine") MultipartFile file,
                                 Model model) {

        if (auto.getPuntoVendita() == null || auto.getPuntoVendita().getId() == null) {
            model.addAttribute("errore", "ID punto vendita mancante");
            return "formNewAuto";
        }

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
        model.addAttribute("kmMin", null);
        model.addAttribute("kmMax", null);
        model.addAttribute("prezzoMin", null);
        model.addAttribute("prezzoMax", null);
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
            @RequestParam(value = "kmMax", defaultValue = "300000") int kmMax,
            @RequestParam(value = "prezzoMin", defaultValue = "0") int prezzoMin,
            @RequestParam(value = "prezzoMax", defaultValue = "100000") int prezzoMax,
            Model model) {

        List<Auto> autoList = autoService.filtraAuto(
                categoria, marca,  colore, carburante, kmMin, kmMax, prezzoMin, prezzoMax);

        System.out.println("Filtri applicati: "+ categoria + marca + colore + carburante + kmMin + kmMax + prezzoMin + prezzoMax);
        
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
    //eliminazione auto
    @PostMapping("/eliminaAuto/{id}")
    public String deleteAuto(@PathVariable Long id) {
        Optional<Auto> optAuto = autoRepository.findById(id);
        if (optAuto.isPresent()) {
            Long puntoVenditaId = optAuto.get().getPuntoVendita().getId();
            autoRepository.deleteById(id);
            return "redirect:/lista?puntoVenditaId=" + puntoVenditaId;
        } else {
            throw new IllegalArgumentException("ID auto non valido: " + id);
        }
    }

    //modifica Auto GET
    @GetMapping("/modificaAuto")
    public String modificaAutoQueryParam(@RequestParam("id") Long id, Model model) {
        Auto auto = autoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID auto non valido: " + id));
        model.addAttribute("auto", auto);
        return "modificaAuto";
    }


    
    
    //modifica auto POST
    @PostMapping("/modificaAuto")
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
            return "redirect:/lista?puntoVenditaId=" + existingAuto.getPuntoVendita().getId();
        } else {
            throw new IllegalArgumentException("ID auto non valido: " + auto.getId());
        }
    }


}
