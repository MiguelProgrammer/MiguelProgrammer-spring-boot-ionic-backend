package com.estudandoemcasa.cursomg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudandoemcasa.cursomg.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
