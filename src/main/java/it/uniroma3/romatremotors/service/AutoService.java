package it.uniroma3.romatremotors.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
				
				cars.addAll(autoRepository.findByKmBetweenAndPrezzoBetween(kmMin,  kmMax,  prezzoMin,  prezzoMax));
				
				
				

				if (categoria != null ) { 
					Iterator<Auto> iterator = cars.iterator();
					if ( !categoria.equals("All")) {
						while(iterator.hasNext()) {
							Auto car = iterator.next();
							if(!categoria.equals(car.getCategoria())) {
								iterator.remove();
							}
						}
					}
				}
				
				
				if (marca != null ) {
					Iterator<Auto> iterator = cars.iterator();
				
					if ( marca.length()>0) {
						while(iterator.hasNext()) {
							Auto car = iterator.next();
							if(!marca.equals(car.getMarca())) {
								iterator.remove();
							}
						}
					}
				}
				
				
				if (colore != null) {
					
					Iterator<Auto> iterator = cars.iterator();
					if ( !colore.equals("All")) {
						while(iterator.hasNext()) {
							Auto car = iterator.next();
							if(!colore.equals(car.getColore())) {
								iterator.remove();
							}
						}
					}
				}
				
				
				
				if (carburante != null ) {
					
					Iterator<Auto> iterator = cars.iterator();
					if ( !carburante.equals("All")) {
						while(iterator.hasNext()) {
							Auto car = iterator.next();
							if(!carburante.equals(car.getCarburante())) {
								iterator.remove();
							}
						}
					}
				}
				
				
				return new ArrayList<>(cars);
				
			}

}
