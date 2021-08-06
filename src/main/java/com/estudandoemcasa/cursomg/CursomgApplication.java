package com.estudandoemcasa.cursomg;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudandoemcasa.cursomg.domain.Categoria;
import com.estudandoemcasa.cursomg.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomgApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(CursomgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		cr.saveAll(Arrays.asList(cat1,cat2));
	}

}
