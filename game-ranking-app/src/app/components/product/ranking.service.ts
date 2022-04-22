import {Injectable} from '@angular/core';

import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient} from "@angular/common/http";
import {catchError, EMPTY, Observable} from "rxjs";
import {map} from "rxjs/operators";
import {ResponseBody} from "../api/response-body.model";
import {Jogador} from "./jogador.model";

@Injectable({
    providedIn: 'root'
})
export class RankingService {

    baseUrl = "http://localhost:8080/api/jogadores"

    constructor(private snackBar: MatSnackBar, private http: HttpClient) {
    }

    showMessage(msg: string, isError: boolean = false): void {
        this.snackBar.open(msg, 'X', {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: isError ? ["msg-error"] : ["msg-success"]
        })
    }

    create(jogador: Jogador): Observable<ResponseBody> {
        return this.http.post<ResponseBody>(this.baseUrl, jogador).pipe(
            map(obj => obj),
            catchError(e => this.errorHandler(e))
        )
    }

    read(): Observable<ResponseBody> {
        const url = `${this.baseUrl}/ranking`
        return this.http.get<ResponseBody>(url).pipe(
            map(obj => obj),
            catchError(e => this.errorHandler(e))
        )
    }

    errorHandler(e: any): Observable<any> {
        this.showMessage(e.error.errorMessag, true)
        return EMPTY
    }
}
