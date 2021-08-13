package com.estudandoemcasa.cursomg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudandoemcasa.cursomg.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{

}
