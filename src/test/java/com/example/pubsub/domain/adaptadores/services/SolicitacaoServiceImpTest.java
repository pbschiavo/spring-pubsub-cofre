package com.example.pubsub.domain.adaptadores.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.example.pubsub.infrastructure.exception.BadRequestException;
import com.example.pubsub.infrastructure.portas.PublisherPubSub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SolicitacaoServiceImp.class})
@ExtendWith(SpringExtension.class)
class SolicitacaoServiceImpTest {
    @MockBean
    private PublisherPubSub publisherPubSub;

    @Autowired
    private SolicitacaoServiceImp solicitacaoServiceImp;

    @Test
    void testSolicitacao_sucesso() {
        doNothing().when(publisherPubSub).publishMessage(Mockito.<String>any());
        solicitacaoServiceImp.solicitacao("Json");
        verify(publisherPubSub).publishMessage(Mockito.<String>any());
    }

    @Test
    void testSolicitacao_exception() {
        doThrow(new BadRequestException("An error occurred")).when(publisherPubSub).publishMessage(Mockito.<String>any());
        assertThrows(BadRequestException.class, () -> solicitacaoServiceImp.solicitacao("Json"));
        verify(publisherPubSub).publishMessage(Mockito.<String>any());
    }
}
