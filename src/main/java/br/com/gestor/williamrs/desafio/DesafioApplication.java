package br.com.gestor.williamrs.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author William:
 * 
 *         Classe principal que gerencia a aplicação.
 * 
 *         Necessário definir com anotação ({@link SpringBootApplication}}) que
 *         é uma classe para que o Spring Boot gerencie a aplicação.
 *
 */

@RestController
@EnableCaching
@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {

		SpringApplication.run(DesafioApplication.class, args);
	}

	/**
	 * 
	 * @return caso o usuário não adicione/home no endereço, retornamos uma mensagem
	 *         simples indincando a forma correta de utilizar a aplicação
	 */
	@RequestMapping("/")
	public String index() {
		return "Acesse o endereço com localhost:8080/home";
	}

}
