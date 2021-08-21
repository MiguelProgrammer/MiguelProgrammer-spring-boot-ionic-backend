package com.estudandoemcasa.cursomg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudandoemcasa.cursomg.domain.Pedido;
/*
 * Camada de Acesso a Dados
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

}
