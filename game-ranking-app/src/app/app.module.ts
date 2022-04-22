import {LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HeaderComponent} from './components/template/header/header.component';
import {FooterComponent} from './components/template/footer/footer.component';
import {NavComponent} from './components/template/nav/nav.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {RankingCrudComponent} from './views/ranking/ranking-crud.component';
import {MatButtonModule} from "@angular/material/button";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {HttpClientModule} from "@angular/common/http";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {RankingReadComponent} from './components/product/ranking-read/ranking-read.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatToolbarModule} from "@angular/material/toolbar";

import localePt from '@angular/common/locales/pt'
import {registerLocaleData} from "@angular/common";
import {JogadorCreateComponent} from "./components/product/jogador-create/jogador-create.component";
import { PartidaComponent } from './views/partida/partida/partida.component';
import { PartidaCreateComponent } from './components/partida/partida-create/partida-create.component';
import {MatCheckboxModule} from "@angular/material/checkbox";

registerLocaleData(localePt)

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        NavComponent,
        RankingCrudComponent,
        RankingReadComponent,
        JogadorCreateComponent,
        PartidaComponent,
        PartidaCreateComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatSidenavModule,
        MatListModule,
        MatCardModule,
        MatButtonModule,
        MatSnackBarModule,
        HttpClientModule,
        MatFormFieldModule,
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
        MatTableModule,
        MatPaginatorModule,
        MatSortModule,
        MatCheckboxModule
    ],
    providers: [{
        provide: LOCALE_ID,
        useValue: "pt-BR"
    }],
    bootstrap: [AppComponent]
})

export class AppModule {
}
