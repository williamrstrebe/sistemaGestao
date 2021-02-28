package br.com.gestor.williamrs.desafio.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gestor.williamrs.desafio.dto.RequisicaoNovoJogo;
import br.com.gestor.williamrs.desafio.modelo.Jogo;
import br.com.gestor.williamrs.desafio.modelo.StatusJogo;
import br.com.gestor.williamrs.desafio.modelo.User;
import br.com.gestor.williamrs.desafio.repository.JogoRepository;
import br.com.gestor.williamrs.desafio.repository.UserRepository;

/**
 * 
 * @author William
 *
 *         Essa classe controller está fazendo basicamente todo o processo
 *         delógica bruta na aplicação atual No início foram criadas outras
 *         classes para fazer o mesmo, mas com a unificação dos templates html e
 *         até antes estão todos embaixo do RequestMapping de usuario, as
 *         funcionalidades básicas foram agrupadas nesta classe
 *
 */
@Controller
@RequestMapping("usuario")
public class JogoController {

	// atributo auto injetado pelo Spring
	@Autowired
	private JogoRepository jogoRepository;

	// atributo auto injetado pelo Spring
	@Autowired
	private UserRepository userRepository;

	// o método precisa dessa requisição porque é chamado pelo me´tod novoJogo e
	// precisa repopular os campos
	@GetMapping("cadastrarJogo")
	public String cadastrarJogo(RequisicaoNovoJogo requisicao) {
		return "usuario/novoJogo";
	}

	// aqui mapeamos a requisição que traz os dados preenchidos ao tentar criar um
	// nogo Jogo
	@PostMapping("salvarJogo")
	public String novoJogo(
			// com a tag valid indicamos para o spring fazer as validações requisitadas na
			// classe DTO
			// atulmente isso significa apenas verificar se o mesmo não está blank, ou seja,
			// nem null, nem com espaço em branco
			@Valid RequisicaoNovoJogo requisicao,
			// o resultado da verificação vem como Result
			BindingResult result, Principal principal) {

		// apesar de fazer a validação, não é parado a requisição
		// devemos pegar o erro e voltar ao cadastro
		// como aqui o tratamento é único, não faz-se necessário maior verificação,
		// apenas retornar para a página
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "usuario/novoJogo";
		}

		// Aqui utilizamos o Spring security para pegar o nome do usuário atualmente
		// autenticado
		// Idealmente deveríamos utilizar seu ID, mas como a sessão ainda está sendo
		// gerenciada pela dependência
		// resolvi simplificar o processo
		// Quando for implementado mais usuários essa verificação tem que mudar
		// provavelmente concatenando user e password (criptografado) e utilizando csv
		// para verificação
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// instancia de usuario a partir do username resgatado
		User usuario = userRepository.findByUsername(username);

		// fazemos as referências e verfiiações necessárias,caso se apliquem
		Jogo jogo = requisicao.toJogo();

		// atribuímos o usuário ao jogo
		jogo.setUser(usuario);

		// agora vamos verificar se esse jogo cadastrado quebrou algum recorde

		// instanciamos as variáveis zeradas
		Integer min = 0;
		Integer max = 0;

		// e tentamos fazer a consulta sql (min e max) que pode retonar null, por isso o
		// handler de exception
		try {
			max = Integer.valueOf(jogoRepository.findRecordeMax().toString());
			min = Integer.valueOf(jogoRepository.findRecordeMin().toString());
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		// se o placar cadastrado for maior que o maior registro atual
		if (jogo.getPlacar() > max) {
			// temos um novo recorde!
			jogo.setStatus(StatusJogo.RECORDE_MAX);
			// se o placar cadastrado for menor que o menor registro atual
		} else if (jogo.getPlacar() < min) {
			// tb temos um novo recorde!
			jogo.setStatus(StatusJogo.RECORDE_MIN);
		} else {
			// do contrário temos um jogo "normal"
			jogo.setStatus(StatusJogo.NORMAL);
		}

		// utilizamos o atributado injetado pelo spring para salvar o jogo no db
		jogoRepository.save(jogo);
		// e retornamos a página com todos os jogos
		return "redirect:/usuario/meusJogos";
	}

	@GetMapping("meusJogos")
	public String meusJogos(Model model, Principal principal) {

		// buscar todos jogos e listar
		List<Jogo> listaJogos = jogoRepository.findAll();

		// passa para o model (que levará para a view) a lista de jogos com a chave
		// jogos
		model.addAttribute("jogos", listaJogos);

		// buscar quantos jogos quebraram recorde máximo
		List<Jogo> listaJogosMax = jogoRepository.findByStatusEUsuario(StatusJogo.RECORDE_MAX, "root");

		// verificação para caso o retorno da lista de recordes seja null
		Integer jogosMax = 0;
		if (listaJogosMax != null) {
			jogosMax = listaJogosMax.size();

		}
		// passa para o model (que levará para a view) a qtd de jogos que quebraram
		// recorde máximo com a chave jogosMax
		model.addAttribute("jogosMax", jogosMax);

		// buscar quantos jogos quebraram recorde mínimo
		List<Jogo> listaJogosMin = jogoRepository.findByStatus(StatusJogo.RECORDE_MIN);

		// verificação para caso o retorno da lista de recordes seja null
		Integer jogosMin = 0;
		if (listaJogosMin != null) {
			jogosMin = listaJogosMin.size();

		}

		// recorde mínimo com a chave jogosMin
		model.addAttribute("jogosMin", jogosMin);

		// instanciando variáveis para ocuparem maior escopo
		Integer total = 0;
		// os número usados são para evitar um divisão por 0
		Integer avg = 1;

		// percorremos os jogos cadastrados e verificamos seu valor total
		// posteriormente podemos verificar se não será mais performático fazer o mesmo
		// através do sum do sql
		for (Jogo jogo : listaJogos) {
			total += jogo.getPlacar();
		}
		// tentamos calcular a média em um catch caso a qtd de jogos seja zero
		try {
			avg = total / listaJogos.size();
			System.out.println(avg);

		} catch (Exception e) {
			// TODO: handle exception
		}

		// Vamos buscar Recordes Mínimo e máximo

		// ambos métodos realizam queryes, min e max respectivamente, no valor de
		// placares
		Object minString = jogoRepository.findRecordeMin();
		Object maxString = jogoRepository.findRecordeMax();

		Integer min = 0;
		Integer max = 0;

		// verificamos se o valor não for null para não tomar null pointer
		if (minString != null) {
			min = Integer.valueOf(minString.toString());
			max = Integer.valueOf(maxString.toString());

		}

		// se a média aqui for um, mas os valores mínimos e máximos forem zero significa
		// que estamos zerados
		// portanto a média deve ser gerada novamente
		if (avg == 1 && min == 0 && max == 0) {
			avg = 0;
		}
		// atribuímos os valores que verificamos para a view
		model.addAttribute("avg", avg);
		model.addAttribute("min", min);
		model.addAttribute("max", max);

		// retornamos para o template meusJogos
		return "usuario/meusJogos";
	}

	// caso aconteça algum erro inesperado, voltamos à página inicial
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}

}
