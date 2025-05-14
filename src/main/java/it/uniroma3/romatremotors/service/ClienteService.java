package it.uniroma3.romatremotors.service;

import org.springframework.stereotype.Service;

import it.uniroma3.romatremotors.model.Cliente;
import it.uniroma3.romatremotors.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;
	
	public Cliente getClienteById(Long id) {
		return clienteRepository.findById(id).get();
	}
	
}
