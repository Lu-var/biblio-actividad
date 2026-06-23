package com.biblioteca.prestamos.client;

import com.biblioteca.prestamos.dto.LibroClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class LibroRestClient {

    private final RestClient restClient;

    public LibroRestClient(RestClient.Builder builder,
                           @Value("${libros-service.url}") String librosServiceUrl) {
        this.restClient = builder
                .baseUrl(librosServiceUrl)
                .build();
    }

    public LibroClientResponse getById(Long id) {
        log.info("Calling libros-service: GET /api/libros/{}", id);
        return restClient.get()
                .uri("/api/libros/{id}", id)
                .retrieve()
                .body(LibroClientResponse.class);
    }

    public void toggleDisponibilidad(Long id) {
        log.info("Calling libros-service: PATCH /api/libros/{}/disponibilidad", id);
        restClient.patch()
                .uri("/api/libros/{id}/disponibilidad", id)
                .retrieve()
                .toBodilessEntity();
    }
}
