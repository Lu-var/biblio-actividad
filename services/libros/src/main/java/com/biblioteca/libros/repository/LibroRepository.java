package com.biblioteca.libros.repository;

import com.biblioteca.libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findByDisponible(boolean disponible);
}
