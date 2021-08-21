package com.estudandoemcasa.cursomg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.Pedido;
import com.estudandoemcasa.cursomg.repositories.PedidoRepository;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID = " + id +" "
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
}
