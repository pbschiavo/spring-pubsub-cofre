package com.example.pubsub.application.adaptadores.controllers;

import static org.mockito.Mockito.doNothing;

import com.example.pubsub.domain.adaptadores.dtos.SolicitacaoDTO;
import com.example.pubsub.domain.adaptadores.portas.interfaces.SolicitacaoServicePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SolicitacaoController.class})
@ExtendWith(SpringExtension.class)
class SolicitacaoControllerTest {
    @Autowired
    private SolicitacaoController solicitacaoController;

    @MockBean
    private SolicitacaoServicePort solicitacaoServicePort;

    @Test
    void testSolicitacao_sucesso() throws Exception {
        doNothing().when(solicitacaoServicePort).solicitacao(Mockito.<String>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/v1/minhaapi/solicitacao")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new SolicitacaoDTO("Nome", "Sexo", "Idade")));
        MockMvcBuilders.standaloneSetup(solicitacaoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSolicitacao_exception() throws Exception {
        doNothing().when(solicitacaoServicePort).solicitacao(Mockito.<String>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/v1/minhaapi/solicitacao")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new SolicitacaoDTO("", "Sexo", "Idade")));
        MockMvcBuilders.standaloneSetup(solicitacaoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
