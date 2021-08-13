package com.estudandoemcasa.cursomg;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.Cidade;
import com.estudandoemcasa.cursomg.domain.Cliente;
import com.estudandoemcasa.cursomg.domain.Endereco;
import com.estudandoemcasa.cursomg.domain.Estado;
import com.estudandoemcasa.cursomg.domain.Produto;
import com.estudandoemcasa.cursomg.domain.enums.TipoCliente;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;
import com.estudandoemcasa.cursomg.repositories.CidadeRepository;
import com.estudandoemcasa.cursomg.repositories.ClienteRepository;
import com.estudandoemcasa.cursomg.repositories.EnderecoRepository;
import com.estudandoemcasa.cursomg.repositories.EstadoRepository;
import com.estudandoemcasa.cursomg.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomgApplication implements CommandLineRunner {

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
	

	public static void main(String[] args) {
		SpringApplication.run(CursomgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");

		Produto p1 = new Produto(null,"Computador",2030.00);
		Produto p2 = new Produto(null,"Impressora",1300.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null,"Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		Cliente cli1 = new Cliente(null, "Miguel Silva", "miguel@gmail.com", "123.456.789-10", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11958020000","11958021111"));
		
		Endereco e1 = new Endereco(null, "Rua floresta", "777", "AP: 317", "Jardim dos Vales", "010203", cli1, c1);		
		Endereco e2 = new Endereco(null, "Rua Satelite", "123", "Casa 7", "Madri Juana", "077859", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
