package com.remedios.pedro.curso.remedio;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(
		Long id,
		
		String nome,
		
		Via via,
		
		String lote,
		
		int quantidade,
		
		LocalDate validade,
		
		Laboratorio laboratorio,
		
		boolean ativo) {
	
	public DadosDetalhamentoRemedio(Remedio remedio) {
		this(
			remedio.getId(), 
			remedio.getNome(), 
			remedio.getVia(), 
			remedio.getLote(), 
			remedio.getQuantidade(), 
			remedio.getValidade(), 
			remedio.getLaboratorio(), 
			remedio.getAtivo()
			);
	}

}