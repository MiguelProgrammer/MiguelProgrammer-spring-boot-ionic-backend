package com.estudandoemcasa.cursomg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.Cliente;
import com.estudandoemcasa.cursomg.repositories.ClienteRepository;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;
/*
 * Camada de Servico
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID = " + id +" "
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
}
