package br.com.gestor.williamrs.desafio;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	// permite acesso a páginas com /, /home e /novoUsuario
	// barra acesso a qualqaue routra pagina se n estiver logado,
	// direciona logados para usuarios/meus jogos
	// direciona logout para /home
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home", "/novoUsuario").permitAll().anyRequest().authenticated()
				.and()
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/usuario/meusJogos", true).permitAll())
				.logout(logout -> {
					logout.logoutUrl("/logout").logoutSuccessUrl("/home");
				}).csrf().disable();
	}

	/**
	 * Método responsável pela autenticação no login
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// instância da classe de criptografia que será usada para 
		//autenticar o registro buscado no banco, de acordo com input do usuário
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		// usado para criar instância de novo usuário 
		// UserDetails user = User.builder()
		
		//passa o nome desejado (neste caso hardcoded)
		// .username("root")
		
		//indicando que a senha, aqui tbm hardcoded, deve ser criptografada
		//.password(encoder.encode("root"))
		
		// define a posição como usuário comum - ADM, por exemplo, tb é aceito
		// .roles("USER")
		
		//executa o código supracitado
		//.build();

		//aqui é feita a autenticação propriamente dita 
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(encoder)
		
		// A linha abaixo deve ser usado quando for criado um novo usuário
		//lembrando que o mesmo é instanciado com UserDetails logo acima
		// .withUser(user);
		;

	}

}
