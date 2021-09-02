package com.estudandoemcasa.cursomg.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudandoemcasa.cursomg.domain.Cidade;
import com.estudandoemcasa.cursomg.domain.Cliente;
import com.estudandoemcasa.cursomg.domain.Endereco;
import com.estudandoemcasa.cursomg.domain.enums.TipoCliente;
import com.estudandoemcasa.cursomg.dto.ClienteDTO;
import com.estudandoemcasa.cursomg.dto.ClienteNewDTO;
import com.estudandoemcasa.cursomg.repositories.ClienteRepository;
import com.estudandoemcasa.cursomg.repositories.EnderecoRepository;
import com.estudandoemcasa.cursomg.services.exceptions.DataIntegretyException;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;
/*
 * Camada de Servico
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID = " + id +" "
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj.orElse(null);
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		//enderecoRepository.save(obj.getEnderecos());
		return obj; 
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj,obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegretyException("Exclusão não permitida. O cliente possui pedidos!");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequeste = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequeste);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = 
				new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), 
						TipoCliente.toEnum(objDto.getTipo()));

		Cidade cidObj = new Cidade(objDto.getCidadeId(),null,null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cidObj);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().addAll(Arrays.asList(objDto.getTelefone1(),objDto.getTelefone2(),objDto.getTelefone3()));
		
		return cli;
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
