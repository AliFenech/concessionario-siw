package it.uniroma3.romatremotors.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

	// Ricerca base
	List<Cliente> findByCognome(String cognome);

	List<Cliente> findByNome(String nome);

	List<Cliente> findByCognomeAndNome(String cognome, String nome);

	// Ricerca per data di nascita
	List<Cliente> findByDataNascita(Date dataNascita);

	List<Cliente> findByDataNascitaBefore(Date data);  // clienti nati prima di una certa data

	List<Cliente> findByDataNascitaAfter(Date data);   // clienti nati dopo una certa data

	// Ricerca per codice fiscale (che è anche l'ID)
	Cliente findByCodiceFiscale(String codiceFiscale);
	
	//Ricerca per auto
	List<Cliente> findByAuto(Auto auto);

	boolean existsByCodiceFiscale(String codiceFiscale);
}

