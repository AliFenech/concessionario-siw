package it.uniroma3.romatremotors.repository;

import it.uniroma3.romatremotors.model.PuntoVendita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVenditaRepository extends CrudRepository<PuntoVendita, Long> {
    // CrudRepository già fornisce metodi come findAll, findById, save, delete
}
