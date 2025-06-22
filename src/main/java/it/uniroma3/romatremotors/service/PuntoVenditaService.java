package it.uniroma3.romatremotors.service;

import java.util.List;

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
}
