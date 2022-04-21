package com.gameranking.services;

import com.gameranking.exception.RegraNegocioException;
import com.gameranking.model.entities.Jogador;
import com.gameranking.model.entities.JogadorPartida;
import com.gameranking.model.entities.Partida;
import com.gameranking.model.repository.JogadorPartidaRepository;
import com.gameranking.model.repository.JogadorRepository;
import com.gameranking.model.repository.PartidaRepository;
import com.gameranking.services.impl.JogadorServiceImpl;
import com.gameranking.services.impl.PartidaServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PartidaServiceTest {

    @SpyBean
    PartidaServiceImpl partidaService;
    @SpyBean
    JogadorServiceImpl jogadorService;

    @MockBean
    PartidaRepository partidaRepository;
    @MockBean
    JogadorRepository jogadorRepository;

    @MockBean
    JogadorPartidaRepository jogadorPartidaRepository;

    @Test
    public void deveAdicionarUmaPartida() {
        JogadorPartida jogador1 = JogadorPartida.builder().idJogador(1).build();
        JogadorPartida jogador2 = JogadorPartida.builder().idJogador(2).build();
        List<JogadorPartida> jogadores = new ArrayList<>();
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        Partida partida = Partida.builder().idPartida(1).jogadores(jogadores).build();

        Mockito.when(partidaRepository.save(Mockito.any(Partida.class))).thenReturn(partida);

        Partida partidaSalva = partidaService.adicionarPartida(partida);

        Assertions.assertNotNull(partidaSalva);
        Assertions.assertNotNull(partidaSalva.getDtInicio());
    }

    @Test
    public void naoDeveAdicionarUmaPartidaSemJogadores() {
        Partida partida = Partida.builder().idPartida(1).build();

        Mockito.when(partidaRepository.save(Mockito.any(Partida.class))).thenReturn(partida);

        RegraNegocioException ex = Assertions.assertThrows(RegraNegocioException.class, () -> partidaService.adicionarPartida(partida));

        Assertions.assertEquals("Os jogadores devem ser informados.", ex.getMessage());
    }

    @Test
    public void deveFinalizarUmaPartida(){
        Jogador jogador = Jogador.builder().idJogador(1).build();
        JogadorPartida jogador1 = JogadorPartida.builder().idJogador(1).build();
        JogadorPartida jogador2 = JogadorPartida.builder().idJogador(2).build();
        List<JogadorPartida> jogadores = new ArrayList<>();
        jogadores.add(jogador1);
        jogadores.add(jogador2);
        Partida partida = Partida.builder().idPartida(1).jogadores(jogadores).build();

        Mockito.when(partidaRepository.save(Mockito.any(Partida.class))).thenReturn(partida);
        Mockito.when(partidaRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(partida));
        Mockito.when(jogadorRepository.findById(1)).thenReturn(Optional.of(jogador));
        Mockito.when(jogadorRepository.save(Mockito.any(Jogador.class))).thenReturn(jogador);

        partidaService.adicionarPartida(partida);

        Partida partidaFinalizada = partidaService.finalizarPartida(1,1);

        System.out.println(partidaFinalizada);
        Assertions.assertNotNull(partidaFinalizada);
        Assertions.assertNotNull(partidaFinalizada.getDtFim());
        Assertions.assertNotNull(partidaFinalizada.getVencedor());
    }
}
