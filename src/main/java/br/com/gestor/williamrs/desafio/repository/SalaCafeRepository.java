package br.com.gestor.williamrs.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gestor.williamrs.desafio.modelo.SalaCafe;

public interface SalaCafeRepository extends JpaRepository<SalaCafe, Long>{

	@Query("select j from Jogo j join j.user u where u.username = :username")
	List<SalaCafe> findAllByUsername(@Param("username") String username);
	
}
