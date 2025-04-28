package it.uniroma3.romatremotors.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.uniroma3.romatremotors.model.AutoOptional;

@Repository
public interface AutoOptionalRepository extends CrudRepository<AutoOptional, Long> {

    // Trova un AutoOptional per ID
	Optional<AutoOptional> findById(Long id);

    // Trova un AutoOptional per nome
	Optional<AutoOptional> findByNome(String nome);
}