package com.example.pubsub.infrastructure.adaptadores.gcp;

import com.example.pubsub.infrastructure.portas.PublisherPubSub;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PublisherPubSubImp implements PublisherPubSub {

    private final Publisher publisher;

    @Override
    public void publishMessage(String json) {
        try {
            ByteString byteString = ByteString.copyFromUtf8(json);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(byteString).build();
            publisher.publish(pubsubMessage);
            log.info("Mensagem publicada com sucesso no tópico ");
        } catch (Exception e) {
            log.error("Erro ao publicar mensagem: " + e.getMessage());
        }
    }

    @PreDestroy
    public void close() {
        if (publisher != null) {
            try {
                publisher.shutdown();
                log.info("Publisher fechado com sucesso");
            } catch (Exception e) {
                log.error("Erro ao fechar o Publisher: " + e.getMessage());
            }
        }
    }
}
