package br.com.gestor.williamrs.desafio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author William
 * 
 *         Definimos a classe modelo como Entity para que o spring gerencie
 *         novas instâncias com o banco de dados
 * 
 *         Definimos seus relacionamentos com outras classes gerenciados, como a
 *         lista de Jogos, onde uma vez que o usuário pode ter mais jogos
 *         cadastrados, chamamos esta relaçaõ de OneToMany O fetch type definido
 *         como lazy aqui tem a intenção de quando buscar um usuário não buscar
 *         todos os jogos cadastrados pelo mesmo em toda pesquisa feita, apenas
 *         caso for expressamente solicitado
 * 
 *         A chave id com as anotações Id e generated value é indicada para o
 *         Spring como sendo classe primária que deve ser auto incrementada no
 *         banco criado pelo mesmo
 *
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	private String username;
	private String password;
	private Boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Jogo> jogosCadastrados;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Jogo> getJogosCadastrados() {
		return jogosCadastrados;
	}

	public void setJogosCadastrados(List<Jogo> jogosCadastrados) {
		this.jogosCadastrados = jogosCadastrados;
	}

}