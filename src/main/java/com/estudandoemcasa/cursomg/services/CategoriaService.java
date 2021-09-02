package com.estudandoemcasa.cursomg.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.dto.CategoriaDTO;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;
import com.estudandoemcasa.cursomg.services.exceptions.DataIntegretyException;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;
/*
 * Camada de Servico
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findById(id).orElse(null);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. ID = " + id + " " + ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

	/*
	 * o método SAVE serve tanto para update quanto para insert desde que ambos
	 * estejam diferentes de branco ou nulo
	 */

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegretyException("Exclusão não permitida, a Categoria possui produtos: " + e.getMessage());
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequeste = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequeste);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
}
