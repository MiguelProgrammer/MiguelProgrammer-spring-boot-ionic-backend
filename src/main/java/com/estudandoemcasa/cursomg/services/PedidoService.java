package com.estudandoemcasa.cursomg.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.ItemPedido;
import com.estudandoemcasa.cursomg.domain.PagamentoComBoleto;
import com.estudandoemcasa.cursomg.domain.Pedido;
import com.estudandoemcasa.cursomg.domain.enums.EstadoPagamento;
import com.estudandoemcasa.cursomg.repositories.ItemPedidoRepository;
import com.estudandoemcasa.cursomg.repositories.PagamentoRepository;
import com.estudandoemcasa.cursomg.repositories.PedidoRepository;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;
/*
 * Camada de Servico
 */
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoSevice;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido find(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. ID = " + id +" "
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoSevice.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDescontoDouble(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}







