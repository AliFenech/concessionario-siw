package it.uniroma3.romatremotors.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.repository.AutoRepository;

@Service
public class AutoService {
	private final AutoRepository autoRepository;
	
	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
	public List<Auto> findAll(){
		return (List<Auto>) autoRepository.findAll();
	}
	
	public Auto findById(String id) {
		return autoRepository.findById(id).get();
	}
	
	public List<Auto> findByFilter( List<String> categorie, List<String> marche, List<String> optionalAuto, List<String> colori, List<String> carburante, int kmMin, int kmMax, int prezzoMin, int prezzoMax) {
		return autoRepository.findByCategoriaInAndMarcaInAndOptionalAutoInAndColoreInAndCarburanteInAndKmBetweenAndPrezzoBetween(categorie, marche, optionalAuto, colori, carburante, kmMin, kmMax, prezzoMin, prezzoMax);
	}
}
