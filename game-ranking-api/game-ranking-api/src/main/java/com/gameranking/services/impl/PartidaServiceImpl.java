package com.gameranking.services.impl;

import com.gameranking.exception.RegraNegocioException;
import com.gameranking.model.entities.JogadorPartida;
import com.gameranking.model.entities.Partida;
import com.gameranking.model.repository.PartidaRepository;
import com.gameranking.services.PartidaService;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaServiceImpl implements PartidaService {

    private final PartidaRepository partidaRepository;

    final JogadorServiceImpl jogadorService;

    public PartidaServiceImpl(PartidaRepository partidaRepository, JogadorServiceImpl jogadorService) {
        this.partidaRepository = partidaRepository;
        this.jogadorService = jogadorService;
    }

    @Override
    @Transient
    public Partida adicionarPartida(Partida partida) {
        List<JogadorPartida> jogadores = partida.getJogadores();
        List<JogadorPartida> jogadoresEntity = new ArrayList<>();

        if (jogadores == null || jogadores.size() == 0) {
            throw new RegraNegocioException("Os jogadores devem ser informados.");
        }

        partida.setDtInicio(new Timestamp(System.currentTimeMillis()));
        Partida partidaSalva = partidaRepository.save(partida);

        jogadores.forEach(j -> {
           j.setIdPartida(partidaSalva.getIdPartida());
            jogadoresEntity.add(j);
        });

        partidaSalva.setJogadores(jogadoresEntity);

        return partidaRepository.save(partidaSalva);
    }

    @Override
    public Partida finalizarPartida(Integer idPartida, Integer idJogadorVencedor) {
        Optional<Partida> partida = partidaRepository.findById(idPartida);

        if (!partida.isPresent()){
            throw new RegraNegocioException("Partida não encontrada.");
        }

        Partida partidaFinalizada = partida.get();
        partidaFinalizada.setDtFim(new Timestamp(System.currentTimeMillis()));
        partidaFinalizada.setVencedor(jogadorService.adicionarVitoria(idJogadorVencedor));

        return partidaRepository.save(partidaFinalizada);
    }
}
