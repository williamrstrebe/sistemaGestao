package br.com.gestor.williamrs.desafio.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author William
 * 
 *         Definimos a classe modelo como Entity para que o spring gerencie
 *         novas instâncias com o banco de dados
 * 
 *         Da mesma forma como em {@link User}, é necessário definir o
 *         relacionmento com outras entidades gerenciados pelo Spring. Por isso
 *         faz-se o uso da tag ManyTonOne
 * 
 *         A tag JsonIgnore n está sendo utilizada agora, mas será mantida para
 *         futura integração com Vue.js e Ajax
 * 
 *         A tag enumerated no Enum StatusJogo indica ao Spring que o valor do
 *         campo deve ser criado com o conteúdo da string e não o número de
 *         índica da mesma
 * 
 *         A chave id com as anotações Id e generated value é indicada para o
 *         Spring como sendo classe primária que deve ser auto incrementada no
 *         banco criado pelo mesmo
 *
 */

@Entity
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer placar;
	private LocalDate dataDeCadastro;
	@Enumerated(EnumType.STRING)
	private StatusJogo status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlacar() {
		return placar;
	}

	public void setPlacar(Integer placar) {
		this.placar = placar;
	}

	public LocalDate getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(LocalDate dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public StatusJogo getStatus() {
		return status;
	}

	public void setStatus(StatusJogo status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
