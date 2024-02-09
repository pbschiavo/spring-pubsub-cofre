package com.example.pubsub.infrastructure.adaptadores.gcp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.core.SettableApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.pubsub.v1.PubsubMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PublisherPubSubImp.class})
@ExtendWith(SpringExtension.class)
class PublisherPubSubImpTest {
    @MockBean
    private Publisher publisher;

    @Autowired
    private PublisherPubSubImp publisherPubSubImp;

    @Test
    void testPublishMessage_sucesso() {
        SettableApiFuture<Object> apiFuture = SettableApiFuture.create();
        when(publisher.publish(Mockito.<PubsubMessage>any())).thenReturn(any());
        publisherPubSubImp.publishMessage("Json");
        verify(publisher).publish(Mockito.<PubsubMessage>any());
    }

    @Test
    void testClose() {
        doNothing().when(publisher).shutdown();
        publisherPubSubImp.close();
        verify(publisher).shutdown();
    }
}
