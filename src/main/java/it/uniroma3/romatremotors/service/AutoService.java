package it.uniroma3.romatremotors.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.Auto;
import it.uniroma3.romatremotors.repository.AutoRepository;
import jakarta.transaction.Transactional;

@Service
public class AutoService {
	private final AutoRepository autoRepository;
	

	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
	@Transactional
	public List<Auto> findAll(){
		return (List<Auto>) autoRepository.findAll();
	}
	
	@Transactional
	public Auto findById(Long id) {
		return autoRepository.findById(id).get();
	}
	
	@Transactional
	public List<Auto> filtraAuto(String categoria, String marca, String colore, String carburante,
            Integer kmMin, Integer kmMax, Integer prezzoMin, Integer prezzoMax) {

				List<Auto> cars = new ArrayList<>();
				
				
				//aggiungo le auto filtrate ad una lista (anche i duplicati)
				if (categoria != null) {
					cars.addAll(autoRepository.findByCategoriaAndKmBetweenAndPrezzoBetween( categoria,  kmMin,  kmMax,  prezzoMin,  prezzoMax));
				}
				
				if (marca != null) {
					cars.addAll(autoRepository.findByMarcaAndKmBetweenAndPrezzoBetween( marca,  kmMin,  kmMax,  prezzoMin,  prezzoMax));
				}
				
				if (colore != null) {
					cars.addAll(autoRepository.findByColoreAndKmBetweenAndPrezzoBetween( colore,  kmMin,  kmMax,  prezzoMin,  prezzoMax));
				}
				
				if (carburante != null) {
					cars.addAll(autoRepository.findByCarburanteAndKmBetweenAndPrezzoBetween( colore,  kmMin,  kmMax,  prezzoMin,  prezzoMax));
				}
				
				
				Set<Auto> listaAuto = new HashSet<>(cars);
				
				return new ArrayList<>(listaAuto);
				
			}

}
