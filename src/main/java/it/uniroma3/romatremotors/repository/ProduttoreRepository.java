package it.uniroma3.romatremotors.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Modello;
import it.uniroma3.romatremotors.model.Produttore;

@Repository
public interface ProduttoreRepository extends CrudRepository<Produttore, Long> {

    // Cerca per ID (CrudRepository ha già findById, ma puoi ridichiararlo)
    Optional<Produttore> findById(Long idProduttore);

    // Cerca tutti i produttori con un certo nome
    List<Produttore> findByNome(String nome);

    // Cerca tutti i produttori con una certa nazionalità
    List<Produttore> findByNazionalita(String nazionalita);

    // Cerca un produttore per nome e nazionalità
    Optional<Produttore> findByNomeAndNazionalita(String nome, String nazionalita);

    // Cerca produttori il cui nome contiene una parola ignorando maiuscole/minuscole
    List<Produttore> findByNomeContainingIgnoreCase(String keyword);
    
    // Cerca produttori per il modello (assumendo che il Modello sia una relazione OneToOne)
    Optional<Produttore> findByModello(Modello modello);

    // Cerca produttori che hanno un modello con un nome specifico
    List<Produttore> findByModelloNome(String nomeModello);
}