package it.uniroma3.romatremotors.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.Cliente;
import it.uniroma3.romatremotors.model.TestDrive;

@Repository
public interface TestDriveRepository extends CrudRepository<TestDrive, Long> {

    // Ricerca per auto
    List<TestDrive> findByAuto(Auto auto);

    // Ricerca per cliente
    List<TestDrive> findByCliente(Cliente cliente);

    // Ricerca per data (esempio per test drive in un range di date)
    List<TestDrive> findByDataOraBetween(LocalDateTime startDate, LocalDateTime endDate); // Trova test drive in un intervallo di date
}
