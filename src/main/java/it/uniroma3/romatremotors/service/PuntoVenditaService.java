package it.uniroma3.romatremotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.PuntoVendita;
import it.uniroma3.romatremotors.repository.PuntoVenditaRepository;

@Service
public class PuntoVenditaService {

    private final PuntoVenditaRepository puntoVenditaRepository;

    public PuntoVenditaService(PuntoVenditaRepository puntoVenditaRepository) {
        this.puntoVenditaRepository = puntoVenditaRepository;
    }

    public List<PuntoVendita> findAll() {
        return (List<PuntoVendita>) puntoVenditaRepository.findAll();
    }

    public PuntoVendita save(PuntoVendita puntoVendita) {
        puntoVendita.setId(null); // per evitare override
        return puntoVenditaRepository.save(puntoVendita);
    }

    public Optional<PuntoVendita> findById(Long id) {
        return puntoVenditaRepository.findById(id);
    }
}
