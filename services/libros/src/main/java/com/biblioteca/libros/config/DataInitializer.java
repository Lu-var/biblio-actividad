package com.biblioteca.libros.config;

import com.biblioteca.libros.model.Libro;
import com.biblioteca.libros.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LibroRepository repository;

    public DataInitializer(LibroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        var l1 = new Libro(); l1.setTitulo("Cien años de soledad"); l1.setAutor("Gabriel García Márquez"); l1.setIsbn("978-84-376-0494-7"); l1.setDisponible(true);
        var l2 = new Libro(); l2.setTitulo("El principito"); l2.setAutor("Antoine de Saint-Exupéry"); l2.setIsbn("978-84-663-2876-4"); l2.setDisponible(true);
        var l3 = new Libro(); l3.setTitulo("1984"); l3.setAutor("George Orwell"); l3.setIsbn("978-84-9759-229-1"); l3.setDisponible(true);
        var l4 = new Libro(); l4.setTitulo("Don Quijote de la Mancha"); l4.setAutor("Miguel de Cervantes"); l4.setIsbn("978-84-670-3366-3"); l4.setDisponible(true);
        var l5 = new Libro(); l5.setTitulo("Orgullo y prejuicio"); l5.setAutor("Jane Austen"); l5.setIsbn("978-84-206-8461-9"); l5.setDisponible(true);

        repository.save(l1);
        repository.save(l2);
        repository.save(l3);
        repository.save(l4);
        repository.save(l5);
    }
}
