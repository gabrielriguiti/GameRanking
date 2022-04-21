package com.gameranking.model.entities.pk;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class JogadorPartidaPK implements Serializable {

    private Integer idPartida;

    private Integer idJogador;
}
