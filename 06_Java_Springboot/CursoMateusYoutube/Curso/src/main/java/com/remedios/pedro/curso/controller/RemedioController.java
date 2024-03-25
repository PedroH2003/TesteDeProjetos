package com.remedios.pedro.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.pedro.curso.remedio.DadosAtualizarRemedio;
import com.remedios.pedro.curso.remedio.DadosCadastroRemedio;
import com.remedios.pedro.curso.remedio.DadosListagemRemedio;
import com.remedios.pedro.curso.remedio.Remedio;
import com.remedios.pedro.curso.remedio.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
		repository.save(new Remedio(dados));
	}

	@GetMapping
	public List<DadosListagemRemedio> listar() {
		return repository.findAll().stream().map(DadosListagemRemedio::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id()); //pega um registro da tabela remedio como referência
		remedio.atualizarInformacoes(dados); //atualizar esse registro aqui atualiza ele também na tabela do banco
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
