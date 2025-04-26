package it.uniroma3.romatremotors.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.Cliente;

@Repository
public interface AutoRepository extends CrudRepository<Auto, String> {  // 'String' è il tipo di telaio

    // Ricerca base per targa
    List<Auto> findByTarga(String targa);
    
    // Ricerca per chilometraggio
    List<Auto> findByKm(int km);  
    
    List<Auto> findByKmGreaterThan(int km);  
    
    List<Auto> findByKmLessThan(int km);  

    // Ricerca per colore
    List<Auto> findByColore(String colore);
    
    // Ricerca per prezzo
    List<Auto> findByPrezzo(double prezzo); 
    
    List<Auto> findByPrezzoGreaterThan(double prezzo);  
    
    List<Auto> findByPrezzoLessThan(double prezzo);  

    // Ricerca combinata (esempio per targa e colore)
    List<Auto> findByTargaAndColore(String targa, String colore);

    // Ricerca per chilometri e prezzo
    List<Auto> findByKmLessThanAndPrezzoGreaterThan(int km, double prezzo);  
    
    //Ricerca per proprietario
    List<Auto> findByCliente(Cliente proprietario);
}
