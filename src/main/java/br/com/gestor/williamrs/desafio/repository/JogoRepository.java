package br.com.gestor.williamrs.desafio.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gestor.williamrs.desafio.modelo.Jogo;
import br.com.gestor.williamrs.desafio.modelo.StatusJogo;

/**
 * 
 * @author William
 * 
 *         Interface repositório de {@link Jogo} que indica ao Spring que
 *         métodos criar para fazer as consultas no banco
 *
 */

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

	/**
	 * 
	 * Diferente do fizemos na classe Users, aqui queremos fazer pesquisas mais
	 * específicas Por isso fazemos uso da tag query para especificar o que queremos
	 * E indicamos na assinatura qual parâmetro que o spring deve relacionar com o
	 * sql
	 * 
	 */

	List<Jogo> findByStatus(StatusJogo status);

	@Query("select j from Jogo j join j.user u where u.username = :username")
	List<Jogo> findAllByUsername(@Param("username") String username);

	@Query("select j from Jogo j join j.user u where u.username = :username and j.status = :status")
	List<Jogo> findByStatusEUsuario(@Param("status") StatusJogo status, @Param("username") String username);

	@Query("select j from Jogo j join j.user u where u.username = :username and j.status = :status")
	List<Jogo> findByStatusEUsuario(@Param("status") StatusJogo status, @Param("username") String username,
			Pageable sort);

	@Query("SELECT MAX(placar) FROM Jogo")
	Object findRecordeMax();

	@Query("SELECT MIN(placar) FROM Jogo")
	Object findRecordeMin();

}
