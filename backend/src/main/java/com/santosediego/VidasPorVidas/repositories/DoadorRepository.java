package com.santosediego.VidasPorVidas.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.santosediego.VidasPorVidas.entities.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {

	@Query("SELECT obj FROM Doador obj INNER JOIN obj.endereco ende  WHERE (\n"
			+ "LOWER(TRANSLATE(obj.nome,'ÀÁáàÉÈéèÍíÓóÒòÚú','AAaaEEeeIiOoOoUu')) LIKE LOWER(CONCAT('%',:conditions,'%')) OR\n"
			+ "LOWER(obj.cpf) LIKE LOWER(CONCAT('%',:conditions,'%')) OR \n"
			+ "LOWER(obj.rg) LIKE LOWER(CONCAT('%',:conditions,'%')))")
	Page<Doador> findDoadores(String conditions, Pageable pageable);

	Optional<Doador> findByCpf(String string);
}
