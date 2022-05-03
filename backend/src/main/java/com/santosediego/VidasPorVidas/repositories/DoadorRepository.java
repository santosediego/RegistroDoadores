package com.santosediego.VidasPorVidas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosediego.VidasPorVidas.entities.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {

}