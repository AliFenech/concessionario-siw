package it.uniroma3.romatremotors.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.PuntoVendita;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Long>{

	List<Auto> findByKmBetweenAndPrezzoBetween(int kmMin, int kmMax, int prezzoMin, int prezzoMax);

	
	List<Auto> findByPuntoVendita(PuntoVendita puntoVendita);
	
}
