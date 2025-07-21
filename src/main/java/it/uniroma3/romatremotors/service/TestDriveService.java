package it.uniroma3.romatremotors.service;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.Credentials;
import it.uniroma3.romatremotors.model.TestDrive;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.repository.TestDriveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDriveService {

    @Autowired
    private TestDriveRepository testDriveRepository;

    public List<LocalDateTime> findAppuntamentiPerDataEAuto(LocalDate data, Long autoId) {
        return testDriveRepository.findByAutoIdAndDataEOraBetween(
                autoId,
                data.atStartOfDay(),         // inizio del giorno
                data.atTime(23, 59)          // fine del giorno
        ).stream()
         .map(TestDrive::getDataEOra)
         .toList();
    }


    public boolean existsByAutoAndDataEOra(Long autoId, LocalDateTime dataEOra) {
        return testDriveRepository.existsByAutoIdAndDataEOra(autoId, dataEOra);
    }

    public void creaPrenotazione(Auto auto, Credentials credentials, LocalDateTime dataEOra) {
        TestDrive td = new TestDrive();
        td.setAuto(auto);
        td.setCredentials(credentials);
        td.setDataEOra(dataEOra);
        testDriveRepository.save(td);
    }

    public List<TestDrive> findByCredentials(Credentials credentials) {
        return testDriveRepository.findByCredentials(credentials);
    }
    
    public Optional<TestDrive> findById(Long id) {
    	return this.testDriveRepository.findById(id);
    }
    
    
    public void removeTestDriveById(TestDrive testDrive) {
    	
    	this.testDriveRepository.delete(testDrive);
    }

}
