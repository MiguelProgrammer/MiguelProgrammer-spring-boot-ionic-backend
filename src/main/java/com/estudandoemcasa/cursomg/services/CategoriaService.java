package com.estudandoemcasa.cursomg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
