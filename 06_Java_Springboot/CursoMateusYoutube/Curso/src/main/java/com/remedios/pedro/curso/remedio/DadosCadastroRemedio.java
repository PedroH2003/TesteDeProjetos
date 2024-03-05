package com.remedios.pedro.curso.remedio;

public record DadosCadastroRemedio(
		String nome,
		Via via,
		String lote,
		String quantidade,
		String validade,
		Laboratorio laboratorio
		) {
}
