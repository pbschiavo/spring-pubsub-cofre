package com.example.pubsub.infrastructure.configuracao;

import com.example.pubsub.infrastructure.dtos.CofreDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CredencialGCPImp implements CredencialGCP {

    public String getCredencial() throws IOException {
        return getCredencialMockHml();
    }

    public String getCredencialGCP() throws IOException {
        String retornoCofre = System.getenv("CREDENTIAL_CONTA_SERVICO_RAW");
        CofreDTO cofreContaServico = new Gson().fromJson(retornoCofre, CofreDTO.class);
        return cofreContaServico.conteudo();
    }

    public String getCredencialMockHml() {
        String jsonCredentials = System.getenv("credencialGCPhml");
        return jsonCredentials;

    }

    public String getCredencialMockProd() {
        String jsonCredentials = System.getenv("credencialGCPPrd");
        return jsonCredentials;
    }
}
