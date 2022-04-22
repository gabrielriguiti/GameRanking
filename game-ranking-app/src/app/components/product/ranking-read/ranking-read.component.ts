import {Component, OnInit} from '@angular/core';
import {RankingService} from "../ranking.service";
import {Jogador} from "../jogador.model";
import {PartidaService} from "../../partida/partida.service";

@Component({
    selector: 'app-ranking-read',
    templateUrl: './ranking-read.component.html',
    styleUrls: ['./ranking-read.component.css']
})
export class RankingReadComponent implements OnInit {

    jogadores: Jogador[] = [];
    displayedColumns = ['idJogador', 'nomeJogador', 'qtdPartidas', 'qtdVitorias', 'action']

    constructor(private rankingService: RankingService,
                private partidaService:  PartidaService) {

    }

    ngOnInit(): void {
        this.rankingService.read().subscribe(response => {
            const {responseBody} = response;
            // @ts-ignore
            this.jogadores = responseBody
        })
    }

    addPartida(row: Jogador){
        this.partidaService.addPartida(row.idJogador).subscribe(() =>{
            this.partidaService.showMessage("Partida adicionada com sucesso!")
            location.reload()
        })
    }

    addVitoria(row: Jogador){
        this.partidaService.addVitoria(row.idJogador).subscribe(() =>{
            this.partidaService.showMessage("Vitória adicionada com sucesso!")
            location.reload()
        })
    }
}
