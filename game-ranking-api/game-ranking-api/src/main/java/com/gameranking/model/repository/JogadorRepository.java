package com.gameranking.model.repository;

import com.gameranking.model.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}
