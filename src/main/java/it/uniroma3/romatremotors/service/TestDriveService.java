package it.uniroma3.romatremotors.service;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.model.TestDrive;
import it.uniroma3.romatremotors.model.Utente;
import it.uniroma3.romatremotors.repository.TestDriveRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDriveService {

    @Autowired
    private TestDriveRepository testDriveRepository;

    public List<LocalDateTime> findAppuntamentiPerDataEAuto(LocalDate data, Long autoId) {
        return testDriveRepository.findByAutoIdAndDataEOraBetween(
                autoId,
                data.atTime(23, 59),
                data.atStartOfDay()
        ).stream().map(TestDrive::getDataEOra).toList();
    }

    public boolean existsByAutoAndDataEOra(Long autoId, LocalDateTime dataEOra) {
        return testDriveRepository.existsByAutoIdAndDataEOra(autoId, dataEOra);
    }

    public void creaPrenotazione(Auto auto, Utente cliente, LocalDateTime dataEOra) {
        TestDrive td = new TestDrive();
        td.setAuto(auto);
        td.setCliente(cliente);
        td.setDataEOra(dataEOra);
        testDriveRepository.save(td);
    }

    public List<TestDrive> findByCliente(Long clienteId) {
        return testDriveRepository.findByClienteId(clienteId);
    }
    

}
