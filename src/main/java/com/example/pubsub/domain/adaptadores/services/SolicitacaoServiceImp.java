package com.example.pubsub.domain.adaptadores.services;

import com.example.pubsub.domain.adaptadores.portas.interfaces.SolicitacaoServicePort;
import com.example.pubsub.infrastructure.exception.BadRequestException;
import com.example.pubsub.infrastructure.portas.PublisherPubSub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SolicitacaoServiceImp implements SolicitacaoServicePort {

    private final PublisherPubSub publisherPubSub;

    @Override
    public void solicitacao(String json) {
        try {
            publisherPubSub.publishMessage(json);
        } catch (Exception e) {
            log.error("Erro ao publicar mensagem no tópico -> {}", e.getMessage());
            throw new BadRequestException("Erro ao publicar mensagem no tópico");
        }
    }
}
