package com.gameranking.services.impl;

import com.gameranking.exception.RegraNegocioException;
import com.gameranking.model.entities.Jogador;
import com.gameranking.model.repository.JogadorRepository;
import com.gameranking.services.JogadorService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorServiceImpl implements JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorServiceImpl(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Override
    @Transactional
    public Jogador adicionarJogador(Jogador jogador) {
        this.validarNome(jogador.getNomeJogador());

        return jogadorRepository.save(jogador);
    }

    @Override
    public Jogador adicionarVitoria(Integer idJogador) {
        Jogador jogador = this.getJogador(idJogador);
        jogador.setQtdVitorias(jogador.getQtdVitorias() + 1);

        return jogadorRepository.save(jogador);
    }

    @Override
    @Transactional
    public Jogador adicionarPartidaParaJogador(Integer idJogador) {
        Jogador jogador = this.getJogador(idJogador);
        jogador.setQtdPartidas(jogador.getQtdPartidas() + 1);

        return jogadorRepository.save(jogador);
    }

    @Override
    public List<Jogador> visualizarRanking(Integer idJogador) {
        if (idJogador != null){
            return jogadorRepository.findAllByIdJogador(idJogador);
        } else {
            return jogadorRepository.findAll();
        }
    }

    @Override
    public void validarNome(String nome) {
        if (nome == null) {
            throw new RegraNegocioException("Nome do jogador deve ser informado.");
        }

        boolean existe = jogadorRepository.existsByNomeJogador(nome);

        if (existe) {
            throw new RegraNegocioException("Já existe um jogador cadastrado com o nome informado.");
        }
    }

    @Override
    public Jogador getJogador(Integer idJogador) {
        if (idJogador == null) {
            throw new RegraNegocioException("Jogador deve ser informado.");
        }

        Optional<Jogador> jogador = jogadorRepository.findById(idJogador);

        if (!jogador.isPresent()) {
            throw new RegraNegocioException("Jogador " + idJogador + " não encontrado.");
        }

        return jogador.get();
    }
}
