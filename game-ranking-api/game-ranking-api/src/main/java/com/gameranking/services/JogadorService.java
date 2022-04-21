package com.gameranking.services;

import com.gameranking.model.entities.Jogador;

import java.util.List;

public interface JogadorService {

    Jogador adicionarJogador(Jogador jogador) throws Exception;

    Jogador adicionarVitoria(Integer idJogador);

    Jogador adicionarPartidaParaJogador(Integer idJogador);

    List<Jogador> visualizarRanking(Integer idJogador);

    void validarNome(String nome) throws Exception;

    Jogador getJogador(Integer idJogador);
}
