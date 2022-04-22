import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {RankingService} from "../../product/ranking.service";
import {Jogador} from "../../product/jogador.model";
import {PartidaService} from "../partida.service";
import {Partida} from "../partida.model";

@Component({
    selector: 'app-partida-create',
    templateUrl: './partida-create.component.html',
    styleUrls: ['./partida-create.component.css']
})
export class PartidaCreateComponent implements OnInit {

    jogadoresPartida: Jogador[] = []
    jogadores: Jogador[] = []
    displayedColumns = ['selected', 'idJogador', 'nomeJogador', 'qtdPartidas', 'qtdVitorias']

    constructor(private router: Router,
                private rankingService: RankingService,
                private partidaService: PartidaService) {
    }

    ngOnInit(): void {
        this.rankingService.read().subscribe(response => {
            const {responseBody} = response;
            // @ts-ignore
            this.jogadores = responseBody
        })
    }

    selectJogador(checked: boolean, row: Jogador) {
        if (checked) {
            this.jogadoresPartida.push(row)
        }
    }

    addPartida() {
        let partida: Partida = {
            jogadores: this.jogadoresPartida
        }

        this.partidaService.novaPartida(partida).subscribe(() => {
            this.partidaService.showMessage("Partida adicionada com sucesso!")
            this.router.navigate([""])
        })
    }
}
