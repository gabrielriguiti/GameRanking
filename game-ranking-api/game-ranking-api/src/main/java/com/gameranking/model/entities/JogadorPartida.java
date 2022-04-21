package com.gameranking.model.entities;

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
public class JogadorPartida {

    @Id
    @Column(name = "IDPARTIDA")
    private Integer idPartida;

    @Column(name = "IDJOGADOR")
    private Integer idJogador;
}
