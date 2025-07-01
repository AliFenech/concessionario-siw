package it.uniroma3.romatremotors.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.PuntoVendita;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Long>{

	
	List<Auto> findByCategoriaAndKmBetweenAndPrezzoBetween(String categoria, int kmMin, int kmMax, int prezzoMin, int prezzoMax);
    List<Auto> findByMarcaAndKmBetweenAndPrezzoBetween(String marca, int kmMin, int kmMax, int prezzoMin, int prezzoMax);
    List<Auto> findByColoreAndKmBetweenAndPrezzoBetween(String colore, int kmMin, int kmMax, int prezzoMin, int prezzoMax);
    List<Auto> findByCarburanteAndKmBetweenAndPrezzoBetween(String carburante, int kmMin, int kmMax, int prezzoMin, int prezzoMax);
	
	List<Auto> findByPuntoVendita(PuntoVendita puntoVendita);
	
}
