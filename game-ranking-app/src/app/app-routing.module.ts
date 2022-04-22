import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {RankingCrudComponent} from "./views/ranking/ranking-crud.component";
import {JogadorCreateComponent} from "./components/product/jogador-create/jogador-create.component";
import {PartidaComponent} from "./views/partida/partida/partida.component";

const routes: Routes = [
    {
        path: "",
        component: RankingCrudComponent
    },
    {
        path: "partida",
        component: PartidaComponent
    },
    {
        path: "jogador",
        component: JogadorCreateComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
