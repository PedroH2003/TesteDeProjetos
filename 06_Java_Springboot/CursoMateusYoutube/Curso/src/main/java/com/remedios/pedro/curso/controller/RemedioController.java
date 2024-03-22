package com.remedios.pedro.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.pedro.curso.remedio.DadosCadastroRemedio;
import com.remedios.pedro.curso.remedio.DadosListagemRemedio;
import com.remedios.pedro.curso.remedio.Remedio;
import com.remedios.pedro.curso.remedio.RemedioRepository;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroRemedio dados) {
		repository.save(new Remedio(dados));
	}

	@GetMapping
	public List<DadosListagemRemedio> listar() {
		return repository.findAll().stream().map(DadosListagemRemedio::new).toList();
	}
	
}
