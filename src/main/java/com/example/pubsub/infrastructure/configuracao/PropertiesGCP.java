package com.example.pubsub.infrastructure.configuracao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.pubsub")
public class PropertiesGCP {
    private String projectId;
    private String topicId;
    private String subscriptionId;
    private String subscription;
}
