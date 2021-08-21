package com.estudandoemcasa.cursomg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudandoemcasa.cursomg.domain.Produto;
/*
 * Camada de Acesso a Dados
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
