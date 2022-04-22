import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Jogador} from "../jogador.model";
import {RankingService} from "../ranking.service";

@Component({
    selector: 'app-product-create',
    templateUrl: './jogador-create.component.html',
    styleUrls: ['./jogador-create.component.css']
})
export class JogadorCreateComponent implements OnInit {

    jogador: Jogador = {
        nomeJogador: "",
    }

    constructor(private rankingService: RankingService,
                private router: Router) {
    }

    ngOnInit(): void {
    }

    createProduct(): void {
        this.rankingService.create(this.jogador).subscribe(() => {
            this.rankingService.showMessage("Jogador adicionado com sucesso!")
            this.router.navigate([""])
        })
    }

    cancel(): void {
        this.router.navigate([""])
    }
}
