import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
import {HeaderService} from "../../components/template/header/header.service";

@Component({
    selector: 'app-ranking',
    templateUrl: './ranking-crud.component.html',
    styleUrls: ['./ranking-crud.component.css']
})
export class RankingCrudComponent implements OnInit {

    constructor(private router: Router, private headerService: HeaderService) {
        headerService.headerData = {
            title: "Ranking",
            icon: "bar_chart",
            routeUrl: ""
        }
    }

    ngOnInit(): void {
    }

    navigateToProductCreate(): void {
        this.router.navigate(["/jogador"])
    }
}
