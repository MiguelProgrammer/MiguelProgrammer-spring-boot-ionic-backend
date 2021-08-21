package com.estudandoemcasa.cursomg.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudandoemcasa.cursomg.domain.Cliente;
import com.estudandoemcasa.cursomg.services.ClienteService;
/*
 * Controladore REST
 */
@RestController
@RequestMapping(value = "/clientes")// <- endpoint rest
public class ClienteResource {
	
	@Autowired
	private ClienteService servico;

	@RequestMapping(value="/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = servico.find(id);
		return ResponseEntity.ok().body(obj);
	}	
}
