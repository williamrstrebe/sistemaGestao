package br.com.gestor.williamrs.desafio.dto;

import javax.validation.constraints.NotBlank;

import br.com.gestor.williamrs.desafio.modelo.SalaAula;

public class RequisicaoNovaSalaAula {

	@NotBlank
	private String nome;
	@NotBlank
	private Long lotacao;

	public SalaAula toSalaAula() {
		SalaAula sala = new SalaAula();
		sala.setNome(this.nome);
		sala.setLotacao(this.lotacao);

		return sala;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getLotacao() {
		return lotacao;
	}

	public void setLotacao(Long lotacao) {
		this.lotacao = lotacao;
	}

}
