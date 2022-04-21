package com.gameranking.api.controllers;

import com.gameranking.api.utils.APIUtils;
import com.gameranking.model.entities.Jogador;
import com.gameranking.services.JogadorService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JsonObject> adicionarJogador(@RequestBody Jogador jogador) {
        try {
            return new ResponseEntity<>(APIUtils.buildResponseBody(jogadorService.adicionarJogador(jogador)), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/vitorias/{idJogador}")
    public ResponseEntity<JsonObject> adicionarVitoria(@PathVariable("idJogador") Integer idJogador) {
       try {
           return new ResponseEntity<>(APIUtils.buildResponseBody(jogadorService.adicionarVitoria(idJogador)), HttpStatus.OK);
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
       }
    }

    @PutMapping("/partidas/{idJogador}")
    public ResponseEntity<JsonObject> adicionarPartidaParaJogador(@PathVariable("idJogador") Integer idJogador) {
        try {
            return new ResponseEntity<>(APIUtils.buildResponseBody(jogadorService.adicionarPartidaParaJogador(idJogador)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<JsonObject> visualizarRanking(@RequestParam(value = "idJogador", required = false) Integer idJogador) {
        try {
            return new ResponseEntity<>(APIUtils.buildResponseBody(jogadorService.visualizarRanking(idJogador)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(APIUtils.buildErrorResponse(e.getMessage()));
        }
    }
}
