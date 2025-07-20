package it.uniroma3.romatremotors.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.TestDrive;

public interface TestDriveRepository extends CrudRepository<TestDrive, Long> {

    List<TestDrive> findByAutoIdAndDataEOraBetween(Long autoId, LocalDateTime start, LocalDateTime end);

    boolean existsByAutoIdAndDataEOra(Long autoId, LocalDateTime dataEOra);

    List<TestDrive> findByCredentials(Credentials credentials);
}
