package com.remedios.pedro.curso.remedio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Remedio")
@Entity(name = "remedios")
@Getter
@Setter
@AllArgsConstructor
// @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Remedio {
	public Remedio() {
		
	}
	public Remedio(DadosCadastroRemedio dados) {
		this.nome = dados.nome();
		this.via = dados.via();
		this.lote = dados.lote();
		this.quantidade = dados.quantidade();
		this.validade = dados.validade();
		this.laboratorio = dados.laboratorio();
		this.ativo = true;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Via via;
	private String lote;
	private int quantidade;
	private LocalDate validade;
	
	@Enumerated(EnumType.STRING)
	private Laboratorio laboratorio;
	
	
	private Boolean ativo;
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getLote() {
		return lote;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public LocalDate getValidade() {
		return validade;
	}
	
	public Via getVia() {
		return via;
	}
	
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public void atualizarInformacoes(@Valid DadosAtualizarRemedio dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		
		if(dados.via() != null) {
			this.via = dados.via();
		}
		
		if(dados.laboratorio() != null) {
			this.laboratorio = dados.laboratorio();
		}
		
	}
	public void inativar() {
		this.ativo = false;
		
	}
	public void reativar() {
		this.ativo = true;
		
	}
						
}
