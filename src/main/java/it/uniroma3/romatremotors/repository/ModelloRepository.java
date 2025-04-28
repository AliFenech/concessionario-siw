package it.uniroma3.romatremotors.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Modello;
import it.uniroma3.romatremotors.model.Produttore;

@Repository
public interface ModelloRepository extends CrudRepository<Modello, Long> {

    // Cerca un modello per ID
    Optional<Modello> findById(Long idModello);

    // Cerca un modello per nome
    Optional<Modello> findByNome(String nome);

    // Cerca tutti i modelli che contengono una certa parola nel nome (ignore case)
    List<Modello> findByNomeContainingIgnoreCase(String keyword);

    // Cerca modelli con nome che corrisponde esattamente a una stringa
    List<Modello> findByNomeEquals(String nome);
    
    // Cerca modelli per produttore (relazione ManyToOne con Produttore)
    List<Modello> findByProduttore(Produttore produttore);

    // Cerca modelli per produttore e nome
    List<Modello> findByProduttoreAndNome(Produttore produttore, String nome);
}