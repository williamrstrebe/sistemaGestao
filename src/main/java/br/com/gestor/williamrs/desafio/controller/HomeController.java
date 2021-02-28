package br.com.gestor.williamrs.desafio.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author William:
 * 
 *         Com o uso nas das anotações Controller e RequestMapping notificamos o
 *         framework que essa classe deve tanto ser entendida como um controller
 *         para mediar a aplicação quanto criar mapeamento para caso seja
 *         tentado acessar o caminho /home
 * 
 *
 */
//anotar por controller passa a ser gerenciado pelo spring

@Controller
@RequestMapping("/home")
public class HomeController {
	// mapeia a requisição /home para chegar ao nosso método home
	// utlizar model em vez de http serlvet request por ser uma camada mais avançada
	/**
	 * Aqui mapeamos o que será necessário para o funcionamento ao requisitar o
	 * acesso a home
	 * 
	 * @param model:     interface que utilizada para trabalhar com o transporte de
	 *                   atributos na requisição
	 * @param principal: interface utilizada para trabalhar com o login,
	 *                   específicamente com uma entidade para gerenciar o login
	 * @return retorna a página html a ser acessada
	 */
	@GetMapping
	public String home(Model model, Principal principal) {

		return "home";
	}

}
