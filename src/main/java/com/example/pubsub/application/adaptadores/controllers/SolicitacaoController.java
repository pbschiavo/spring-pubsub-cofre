package com.example.pubsub.application.adaptadores.controllers;

import com.example.pubsub.domain.adaptadores.dtos.ResponseDTO;
import com.example.pubsub.domain.adaptadores.dtos.SolicitacaoDTO;
import com.example.pubsub.domain.adaptadores.portas.interfaces.SolicitacaoServicePort;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/minhaapi")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoServicePort exemploServicePort;

    @Operation(
            summary = "Solicitação",
            description = "Solicitação de xpto"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Sucesso",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping("/solicitacao")
    ResponseEntity<ResponseDTO> solicitacao(@RequestBody SolicitacaoDTO solicitacaoDTO) {
        exemploServicePort.solicitacao(new Gson().toJson(solicitacaoDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

