package com.gameranking.model.entities;

import com.gameranking.model.entities.pk.JogadorPartidaPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOGADORESPARTIDAS")
@IdClass(JogadorPartidaPK.class)
public class JogadorPartida {

    @Id
    @Column(name = "IDPARTIDA")
    private Integer idPartida;

    @Id
    @Column(name = "IDJOGADOR")
    private Integer idJogador;
}
