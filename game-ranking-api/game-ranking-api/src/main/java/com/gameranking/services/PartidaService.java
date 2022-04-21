package com.gameranking.services;

import com.gameranking.model.entities.Partida;

public interface PartidaService {

    Partida adicionarPartida(Partida partida);

    Partida finalizarPartida(Integer idPartida, Integer idJogadorVencedor);
}
