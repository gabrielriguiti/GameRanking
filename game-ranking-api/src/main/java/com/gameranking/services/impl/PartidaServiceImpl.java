package com.gameranking.services.impl;

import com.gameranking.exception.RegraNegocioException;
import com.gameranking.model.entities.JogadorPartida;
import com.gameranking.model.entities.Partida;
import com.gameranking.model.repository.JogadorPartidaRepository;
import com.gameranking.model.repository.PartidaRepository;
import com.gameranking.services.PartidaService;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PartidaServiceImpl implements PartidaService {

    private final PartidaRepository partidaRepository;
    private final JogadorPartidaRepository jogadorPartidaRepository;

    final JogadorServiceImpl jogadorService;

    public PartidaServiceImpl(PartidaRepository partidaRepository, JogadorPartidaRepository jogadorPartidaRepository, JogadorServiceImpl jogadorService) {
        this.partidaRepository = partidaRepository;
        this.jogadorPartidaRepository = jogadorPartidaRepository;
        this.jogadorService = jogadorService;
    }

    @Override
    @Transient
    public Partida adicionarPartida(Partida partida) {
        List<JogadorPartida> jogadores = partida.getJogadores();
        if (jogadores == null || jogadores.size() == 0) {
            throw new RegraNegocioException("Os jogadores devem ser informados.");
        }

        partida.setJogadores(null);

        partida.setDtInicio(new Timestamp(System.currentTimeMillis()));

        partidaRepository.save(partida);

        jogadores.forEach(j -> {
            j.setIdPartida(partida.getIdPartida());
            jogadorPartidaRepository.save(j);
            jogadorService.adicionarPartidaParaJogador(j.getIdJogador());
        });

        return partidaRepository.findById(partida.getIdPartida()).get();
    }

    @Override
    public Partida finalizarPartida(Integer idPartida, Integer idJogadorVencedor) {
        Optional<Partida> partida = partidaRepository.findById(idPartida);

        if (!partida.isPresent()) {
            throw new RegraNegocioException("Partida não encontrada.");
        }

        Partida partidaFinalizada = partida.get();
        partidaFinalizada.setDtFim(new Timestamp(System.currentTimeMillis()));
        partidaFinalizada.setVencedor(jogadorService.adicionarVitoria(idJogadorVencedor));

        return partidaRepository.save(partidaFinalizada);
    }
}
