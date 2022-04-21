package com.gameranking.api.controllers;

import com.gameranking.api.utils.APIUtils;
import com.gameranking.model.entities.Partida;
import com.gameranking.services.PartidaService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;

    @PostMapping
    public ResponseEntity<JsonObject> adicionarPartida(@RequestBody Partida partida){
        try {
            return new ResponseEntity<>(APIUtils.buildResponseBody(partidaService.adicionarPartida(partida)), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<JsonObject> finalizarPartida(
            @RequestParam(value = "idPartida") Integer idPartida,
            @RequestParam(value = "idJogadorVencedor") Integer idJogadorVencedor){
        try {
            return new ResponseEntity<>(APIUtils.buildResponseBody(partidaService.finalizarPartida(idPartida, idJogadorVencedor)), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
        }
    }
}
