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
 *         tentado acessar o caminho /loginForm
 * 
 *
 */

@Controller
public class LoginFormController {

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
	@RequestMapping("/login")
	public String loginForm (Model model, Principal principal) {
		return "loginForm";
	}

}
