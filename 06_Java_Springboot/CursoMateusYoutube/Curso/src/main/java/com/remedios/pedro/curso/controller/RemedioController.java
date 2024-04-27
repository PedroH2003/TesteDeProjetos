package com.remedios.pedro.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedios.pedro.curso.remedio.DadosAtualizarRemedio;
import com.remedios.pedro.curso.remedio.DadosCadastroRemedio;
import com.remedios.pedro.curso.remedio.DadosDetalhamentoRemedio;
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
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);
		
		var uri = uriBuilder.path("/remedio/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
	}

	@GetMapping
	public ResponseEntity<List<DadosListagemRemedio>> listar() {
		var lista =  repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id()); //pega um registro da tabela remedio como referência
		remedio.atualizarInformacoes(dados); //atualizar esse registro aqui atualiza ele também na tabela do banco
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("reativar/{id}")
	@Transactional
	public ResponseEntity<Void> reativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.reativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoRemedio> detalhar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}	
}
