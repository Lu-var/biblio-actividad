package com.biblioteca.prestamos.client;

import com.biblioteca.prestamos.dto.UsuarioClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class UsuarioRestClient {

    private final RestClient restClient;

    public UsuarioRestClient(RestClient.Builder builder,
                             @Value("${usuarios-service.url}") String usuariosServiceUrl) {
        this.restClient = builder
                .baseUrl(usuariosServiceUrl)
                .build();
    }

    public UsuarioClientResponse getById(Long id) {
        log.info("Calling usuarios-service: GET /api/usuarios/{}", id);
        return restClient.get()
                .uri("/api/usuarios/{id}", id)
                .retrieve()
                .body(UsuarioClientResponse.class);
    }
}
