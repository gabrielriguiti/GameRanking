package com.gameranking.model.repository;

import com.gameranking.model.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Integer> {
}
