package com.biblioteca.prestamos.config;

import com.biblioteca.prestamos.model.Prestamo;
import com.biblioteca.prestamos.repository.PrestamoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PrestamoRepository repository;

    public DataInitializer(PrestamoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        var p1 = new Prestamo(); p1.setLibroId(1L); p1.setUsuarioId(1L); p1.setFechaPrestamo(LocalDate.of(2026, 6, 15));
        var p2 = new Prestamo(); p2.setLibroId(3L); p2.setUsuarioId(2L); p2.setFechaPrestamo(LocalDate.of(2026, 6, 18));

        repository.save(p1);
        repository.save(p2);
    }
}
