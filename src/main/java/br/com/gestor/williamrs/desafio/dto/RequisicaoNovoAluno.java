package br.com.gestor.williamrs.desafio.dto;

import javax.validation.constraints.NotBlank;

import br.com.gestor.williamrs.desafio.modelo.Aluno;

public class RequisicaoNovoAluno {

	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;

	public Aluno toAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setSobreNome(sobrenome);

		return aluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
