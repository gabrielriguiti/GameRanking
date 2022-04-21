package com.gameranking.model.repository;

import com.gameranking.model.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

    boolean existsByNomeJogador(String nome);

    List<Jogador> findAllByIdJogador(Integer idJogador);
}
