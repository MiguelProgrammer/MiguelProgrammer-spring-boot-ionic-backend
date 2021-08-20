package com.estudandoemcasa.cursomg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudandoemcasa.cursomg.domain.Pedido;
import com.estudandoemcasa.cursomg.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")// <- endpoint rest
public class PedidoResource {
	
	@Autowired
	private PedidoService servico;

	@RequestMapping(value="/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido obj = servico.buscar(id);
		return ResponseEntity.ok().body(obj);
	}	
}
