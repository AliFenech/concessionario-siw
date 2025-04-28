package it.uniroma3.romatremotors.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.romatremotors.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

    // Metodo per trovare un dipendente per matricola
    Dipendente findByMatricola(Long matricola);

    // Metodo per trovare dipendenti per nome
    List<Dipendente> findByNome(String nome);

    // Metodo per trovare dipendenti per cognome
    List<Dipendente> findByCognome(String cognome);

    // Metodo per trovare dipendenti con nome e cognome specificati
    List<Dipendente> findByNomeAndCognome(String nome, String cognome);
}