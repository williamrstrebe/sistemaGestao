package br.com.gestor.williamrs.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestor.williamrs.desafio.modelo.Jogo;
import br.com.gestor.williamrs.desafio.modelo.User;

/**
 * 
 * @author William
 * 
 *         Interface repositório de {@link User} que indica ao Spring que
 *         métodos criar para fazer as consultas no banco
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * a partir da anotação de repositório e da nomenclatura Find By padrão com
	 * Indicativos (username, IdUsuario) que tem relação com o nome declarado na
	 * classe User o spring faz as ligações necessárias
	 * 
	 */

	User findByUsername(String username);

	User findByIdUsuario(String idUsuario);

}
