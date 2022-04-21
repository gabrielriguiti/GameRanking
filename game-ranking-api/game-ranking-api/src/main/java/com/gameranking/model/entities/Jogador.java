package com.gameranking.model.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "JOGADORES")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_JOGADORES")
    @SequenceGenerator(name = "SQ_JOGADORES", sequenceName = "SQ_JOGADORES", allocationSize = 1)
    @Column(name = "IDJOGADOR")
    private Integer idJogador;

    @Column(name = "NOMEJOGADOR", length = 50)
    private String nomeJogador;

    @Column(name = "QTDPARTIDAS", columnDefinition = "integer default 0")
    private Integer qtdPartidas;

    @Column(name = "QTDVITORIAS", columnDefinition = "integer default 0")
    private Integer qtdJogadores;
}
