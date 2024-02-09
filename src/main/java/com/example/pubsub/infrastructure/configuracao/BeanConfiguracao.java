package com.example.pubsub.infrastructure.configuracao;

import com.example.pubsub.domain.adaptadores.portas.interfaces.SolicitacaoServicePort;
import com.example.pubsub.domain.adaptadores.services.SolicitacaoServiceImp;
import com.example.pubsub.infrastructure.portas.PublisherPubSub;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.ProjectTopicName;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguracao {

    private final CredencialGCP credencialGCP;

    @Bean
    SolicitacaoServicePort service (PublisherPubSub publisherPubSub){
        return new SolicitacaoServiceImp(publisherPubSub);
    }

    @Bean
    public Publisher criarPublisher(PropertiesGCP propertiesGCP) throws IOException {
        ProjectTopicName topicName = ProjectTopicName.of(propertiesGCP.getProjectId(), propertiesGCP.getTopicId());
        GoogleCredentials credentials = retrieveCredentialsFromSecureSource();
        FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
        return Publisher.newBuilder(topicName).setCredentialsProvider(credentialsProvider).build();
    }

    private GoogleCredentials retrieveCredentialsFromSecureSource() throws IOException {
        String jsonCredentials = credencialGCP.getCredencial();
        return GoogleCredentials.fromStream(new ByteArrayInputStream(jsonCredentials.getBytes()));
    }
}
