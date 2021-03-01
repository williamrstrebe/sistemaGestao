package br.com.gestor.williamrs.desafio.dto;

import javax.validation.constraints.NotBlank;

import br.com.gestor.williamrs.desafio.modelo.SalaCafe;

public class RequisicaoNovaSalaCafe {

	@NotBlank
	private String nome;

	public SalaCafe toSalaCafe() {
		SalaCafe sala = new SalaCafe();
		sala.setNome(this.nome);

		return sala;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
