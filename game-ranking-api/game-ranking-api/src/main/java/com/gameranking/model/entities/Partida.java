package com.gameranking.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PARTIDAS")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PARTIDAS")
    @SequenceGenerator(name = "SQ_PARTIDAS", sequenceName = "SQ_PARTIDAS", allocationSize = 1)
    @Column(name = "IDPARTIDA")
    private Integer idPartida;

    @Column(name = "DTINIC", columnDefinition = "timestamp default sysdate")
    private Timestamp dtInicio;

    @Column(name = "DTFIM")
    private Timestamp dtFim;

    @OneToOne
    @JoinColumn(name = "IDJOGADORVENC")
    private Jogador vencedor;

    @OneToMany
    @JoinColumn(name = "IDPARTIDA")
    private Collection<JogadorPartida> jogadores;
}
