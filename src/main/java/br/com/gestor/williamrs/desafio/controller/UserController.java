package br.com.gestor.williamrs.desafio.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author William
 * 
 *         Classe para o Spring boot gerenciador as requisições http e devolver
 *         o resultado
 *
 */

public class UserController {
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
