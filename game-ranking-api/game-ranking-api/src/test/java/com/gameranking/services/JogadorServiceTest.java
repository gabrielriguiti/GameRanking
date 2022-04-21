package com.gameranking.services;

import com.gameranking.exception.RegraNegocioException;
import com.gameranking.model.entities.Jogador;
import com.gameranking.model.repository.JogadorRepository;
import com.gameranking.services.impl.JogadorServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class JogadorServiceTest {

    @SpyBean
    JogadorServiceImpl jogadorService;

    @MockBean
    JogadorRepository jogadorRepository;

    @Test
    public void deveSalvarUmJogador() {
        Mockito.doNothing().when(jogadorService).validarNome(Mockito.anyString());
        Jogador jogador = Jogador.builder().nomeJogador("Jogador Teste").build();
        Mockito.when(jogadorRepository.save(Mockito.any(Jogador.class))).thenReturn(jogador);

        Jogador jogadorSalvo = jogadorService.adicionarJogador(jogador);

        Assertions.assertNotNull(jogadorSalvo);
    }

    @Test
    public void naoDeveSalvarUmUsuarioSemNome() {
        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () -> jogadorService.adicionarJogador(new Jogador()));

        Assertions.assertEquals("Nome do jogador deve ser informado.", ex.getMessage());
    }

    @Test(expected = RegraNegocioException.class)
    public void naoDeveSalvarUmUsuarioComNomeJaCadastrado() {
        String nome = "Jogador";
        Jogador jogador = Jogador.builder().nomeJogador(nome).build();
        Mockito.doThrow(RegraNegocioException.class).when(jogadorService).validarNome(nome);

        jogadorService.adicionarJogador(jogador);
    }

    @Test
    public void deveAdicionarUmaVitoriaAoJogador() {
        Jogador jogador = Jogador.builder().idJogador(1).nomeJogador("Jogador").build();
        Mockito.when(jogadorRepository.findById(1)).thenReturn(Optional.of(jogador));
        Mockito.when(jogadorRepository.save(Mockito.any(Jogador.class))).thenReturn(jogador);

        jogadorService.adicionarJogador(jogador);

        Jogador jogadorVitorioso = jogadorService.adicionarVitoria(1);

        Assertions.assertNotNull(jogadorVitorioso);
    }

    @Test
    public void naoDeveAdicionarUmaVitoriaQuandoOIdNaoForInformado() {
        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () -> jogadorService.adicionarVitoria(null));

        Assertions.assertEquals("Jogador deve ser informado.", ex.getMessage());
    }

    @Test
    public void naoDeveAdicionarUmaVitoriaQuandoOJogadorNaoExistir() {
        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () -> jogadorService.adicionarVitoria(1));

        Assertions.assertEquals("Jogador 1 não encontrado.", ex.getMessage());
    }

    @Test
    public void deveAdicionarUmaPartidaAoJogador() {
        Jogador jogador = Jogador.builder().idJogador(1).nomeJogador("Jogador").build();
        Mockito.when(jogadorRepository.findById(1)).thenReturn(Optional.of(jogador));
        Mockito.when(jogadorRepository.save(Mockito.any(Jogador.class))).thenReturn(jogador);

        jogadorService.adicionarJogador(jogador);

        Jogador jogadorAtualizado = jogadorService.adicionarPartidaParaJogador(1);

        Assertions.assertNotNull(jogadorAtualizado);
    }

    @Test
    public void naoDeveAdicionarUmaPartidaQuandoOIdNaoForInformado() {
        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () -> jogadorService.adicionarPartidaParaJogador(null));

        Assertions.assertEquals("Jogador deve ser informado.", ex.getMessage());
    }

    @Test
    public void naoDeveAdicionarUmaPartidaQuandoOJogadorNaoExistir() {
        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () ->  jogadorService.adicionarPartidaParaJogador(1));

        Assertions.assertEquals("Jogador 1 não encontrado.", ex.getMessage());
    }

    @Test
    public void deveApresentarORankingGeral() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(Jogador.builder().idJogador(1).nomeJogador("Jogador 1").build());
        jogadores.add(Jogador.builder().idJogador(2).nomeJogador("Jogador 2").build());
        Mockito.when(jogadorRepository.findAll()).thenReturn(jogadores);

        List<Jogador> ranking = jogadorService.visualizarRanking(null);

        Assertions.assertNotNull(ranking);
        Assertions.assertTrue(ranking.size() > 0);
    }

    @Test
    public void deveApresentarORankingDoJogador() {
        List<Jogador> jogadores = new ArrayList<>();
        Jogador jogador = Jogador.builder().idJogador(1).nomeJogador("Jogador 1").build();

        jogadores.add(jogador);
        Mockito.when(jogadorRepository.save(jogador)).thenReturn(jogador);
        Mockito.when(jogadorRepository.findAllByIdJogador(jogador.getIdJogador())).thenReturn(jogadores);

        jogadorService.adicionarJogador(jogador);
        List<Jogador> ranking = jogadorService.visualizarRanking(jogador.getIdJogador());

        Assertions.assertNotNull(ranking);
        Assertions.assertTrue(ranking.size() > 0);
    }
}
