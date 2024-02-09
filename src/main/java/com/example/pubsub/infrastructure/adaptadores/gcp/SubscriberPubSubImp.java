package com.example.pubsub.infrastructure.adaptadores.gcp;

import com.example.pubsub.infrastructure.configuracao.CredencialGCP;
import com.example.pubsub.infrastructure.configuracao.PropertiesGCP;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriberPubSubImp {

    private final CredencialGCP credencialGCP;

    private final PropertiesGCP propertiesGCP;

    @Bean
    public Subscriber createSubscriber() throws IOException {

        String credentialsJson = credencialGCP.getCredencial();

        GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(credentialsJson.getBytes()))
                .createScoped("https://www.googleapis.com/auth/cloud-platform");

        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(propertiesGCP.getProjectId(), propertiesGCP.getSubscriptionId());
        Subscriber subscriber = Subscriber.newBuilder(subscriptionName, new MessageReceiverExample())
                .setCredentialsProvider(() -> credentials)
                .build();

        subscriber.startAsync().awaitRunning();

        return subscriber;
    }

    class MessageReceiverExample implements MessageReceiver {
        @Override
        public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {

            try {
                ByteString data = message.getData();
                log.info(data.toStringUtf8());
                log.info("Mensagem confirmada no PUB/SUB.");
                consumer.ack();
            }catch (Exception e){
                log.error("Circuit Breaker está aberto. Ignorando o processamento da mensagem e aguardando 1 min.");
                sleepForRetry();
                consumer.nack();
            }
        }
    }

    private void sleepForRetry() {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
