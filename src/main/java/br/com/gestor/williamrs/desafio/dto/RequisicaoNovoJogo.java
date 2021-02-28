package br.com.gestor.williamrs.desafio.dto;

import javax.validation.constraints.NotBlank;

import br.com.gestor.williamrs.desafio.modelo.Jogo;

public class RequisicaoNovoJogo {

	@NotBlank
	private String placar;

	public Jogo toJogo() {
		Jogo jogo = new Jogo();
		jogo.setPlacar(Integer.valueOf(placar));

		return jogo;
	}

	public String getPlacar() {
		return placar;
	}

	public void setPlacar(String placar) {
		this.placar = placar;
	}

}
