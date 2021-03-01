package br.com.gestor.williamrs.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gestor.williamrs.desafio.modelo.SalaAula;

public interface SalaAulaRepository extends JpaRepository<SalaAula, Long>{
	
	@Query("select j from Jogo j join j.user u where u.username = :username")
	List<SalaAula> findAllByUsername(@Param("username") String username);

}
