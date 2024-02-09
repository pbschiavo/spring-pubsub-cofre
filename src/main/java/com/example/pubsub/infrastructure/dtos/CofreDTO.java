package com.example.pubsub.infrastructure.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CofreDTO(
        @JsonProperty("id") String id,
        @JsonProperty("tag") String tag,
        @JsonProperty("tipo") String tipo,
        @JsonProperty("conteudo") String conteudo) {}