import {Injectable} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient} from "@angular/common/http";
import {catchError, EMPTY, Observable} from "rxjs";
import {ResponseBody} from "../api/response-body.model";
import {map} from "rxjs/operators";
import {Partida} from "./partida.model";

@Injectable({
    providedIn: 'root'
})
export class PartidaService {

    baseUrl = "http://localhost:8080/api/jogadores"
    baseUrlPartidas = "http://localhost:8080/api/partidas"

    constructor(private snackBar: MatSnackBar,
                private http: HttpClient) {
    }

    showMessage(msg: string, isError: boolean = false): void {
        this.snackBar.open(msg, 'X', {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: isError ? ["msg-error"] : ["msg-success"]
        })
    }

    addPartida(id?: number): Observable<ResponseBody> {
        const url = `${this.baseUrl}/partidas/${id}`
        return this.http.put<ResponseBody>(url, null).pipe(
            map(obj => obj),
            catchError(e => this.errorHandler(e))
        )
    }

    addVitoria(id?: number): Observable<ResponseBody> {
        const url = `${this.baseUrl}/vitorias/${id}`
        return this.http.put<ResponseBody>(url, null).pipe(
            map(obj => obj),
            catchError(e => this.errorHandler(e))
        )
    }

    novaPartida(partida: Partida): Observable<ResponseBody> {
        return this.http.post<ResponseBody>(this.baseUrlPartidas, partida).pipe(
            map(obj => obj),
            catchError(e => this.errorHandler(e))
        )
    }

    errorHandler(e: any): Observable<any> {
        this.showMessage(e.error.errorMessage, true)
        return EMPTY
    }
}
