package com.estudandoemcasa.cursomg;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.domain.Produto;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;
import com.estudandoemcasa.cursomg.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomgApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository cr;
	@Autowired
	ProdutoRepository pr;
	

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

		cr.saveAll(Arrays.asList(cat1,cat2));
		pr.saveAll(Arrays.asList(p1,p2,p3));

	}

}
