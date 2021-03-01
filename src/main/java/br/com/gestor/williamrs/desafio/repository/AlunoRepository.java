package br.com.gestor.williamrs.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gestor.williamrs.desafio.modelo.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query("select j from Jogo j join j.user u where u.username = :username")
	List<Aluno> findAllByUsername(@Param("username") String username);

}
