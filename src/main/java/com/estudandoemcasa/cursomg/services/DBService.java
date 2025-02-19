package com.estudandoemcasa.cursomg.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.Cidade;
import com.estudandoemcasa.cursomg.domain.Cliente;
import com.estudandoemcasa.cursomg.domain.Endereco;
import com.estudandoemcasa.cursomg.domain.Estado;
import com.estudandoemcasa.cursomg.domain.ItemPedido;
import com.estudandoemcasa.cursomg.domain.Pagamento;
import com.estudandoemcasa.cursomg.domain.PagamentoComBoleto;
import com.estudandoemcasa.cursomg.domain.PagamentoComCartao;
import com.estudandoemcasa.cursomg.domain.Pedido;
import com.estudandoemcasa.cursomg.domain.Produto;
import com.estudandoemcasa.cursomg.domain.enums.EstadoPagamento;
import com.estudandoemcasa.cursomg.domain.enums.TipoCliente;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;
import com.estudandoemcasa.cursomg.repositories.CidadeRepository;
import com.estudandoemcasa.cursomg.repositories.ClienteRepository;
import com.estudandoemcasa.cursomg.repositories.EnderecoRepository;
import com.estudandoemcasa.cursomg.repositories.EstadoRepository;
import com.estudandoemcasa.cursomg.repositories.ItemPedidoRepository;
import com.estudandoemcasa.cursomg.repositories.PagamentoRepository;
import com.estudandoemcasa.cursomg.repositories.PedidoRepository;
import com.estudandoemcasa.cursomg.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantieateDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama Mesa & Banho");
		Categoria cat4 = new Categoria(null,"Eletrônicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");

		Produto p1 = new Produto(null,"Computador",2030.00);
		Produto p2 = new Produto(null,"Impressora",1300.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		Produto p4 = new Produto(null,"Mesa de Escritório",1200.00);
		Produto p5 = new Produto(null,"Toalha",19.00);
		Produto p6 = new Produto(null,"Colcha",189.00);
		Produto p7 = new Produto(null,"Tv Smart",2289.00);
		Produto p8 = new Produto(null,"Roçadeira",289.00);
		Produto p9 = new Produto(null,"Abajour",120.00);
		Produto p10 = new Produto(null,"Pendente",245.00);
		Produto p11 = new Produto(null,"Shampoo",29.90);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null,"Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		Cliente cli1 = new Cliente(null, "Miguel Silva", "miguel.p7.silva@gmail.com", "123.456.789-10", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11958020000","11958021111"));
		
		Endereco e1 = new Endereco(null, "Rua floresta", "777", "AP: 317", "Jardim dos Vales", "010203", cli1, c1);		
		Endereco e2 = new Endereco(null, "Rua Satelite", "123", "Casa 7", "Madri Juana", "077859", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1	 = new Pedido(null, sdf.parse("30/07/2021 10:31"), cli1, e1);
		Pedido ped2	 = new Pedido(null, sdf.parse("10/08/2021 19:33"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("29/07/2021 00:00"), null);
		ped2.setPagamento(pgto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));	
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,p1.getPreco());
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,p3.getPreco());
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,p2.getPreco());
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
