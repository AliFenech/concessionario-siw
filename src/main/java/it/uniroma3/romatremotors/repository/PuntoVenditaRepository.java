package it.uniroma3.romatremotors.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.romatremotors.model.PuntoVendita;
@Repository
public interface PuntoVenditaRepository extends CrudRepository<PuntoVendita, Long> {

    // Metodo per trovare un punto vendita per ID
    Optional findById(Long id);

    // Metodo per trovare un punto vendita per città
    List<PuntoVendita> findByCitta(String citta);

    // Metodo per trovare un punto vendita per indirizzo
    List<PuntoVendita> findByIndirizzo(String indirizzo);

    // Metodo per trovare un punto vendita per città e indirizzo
    List<PuntoVendita> findByCittaAndIndirizzo(String citta, String indirizzo);
}
	