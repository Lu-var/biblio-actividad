package com.biblioteca.usuarios.config;

import com.biblioteca.usuarios.model.Usuario;
import com.biblioteca.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository repository;

    public DataInitializer(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) return;

        var u1 = new Usuario(); u1.setNombre("María González"); u1.setEmail("maria.gonzalez@email.com"); u1.setTelefono("912345678");
        var u2 = new Usuario(); u2.setNombre("Pedro López"); u2.setEmail("pedro.lopez@email.com"); u2.setTelefono("923456789");
        var u3 = new Usuario(); u3.setNombre("Ana Martínez"); u3.setEmail("ana.martinez@email.com"); u3.setTelefono("934567890");

        repository.save(u1);
        repository.save(u2);
        repository.save(u3);
    }
}
