package com.biblioteca.prestamos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long libroId;
    private Long usuarioId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo() {}
    public Prestamo(Long id, Long libroId, Long usuarioId, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id; this.libroId = libroId; this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo; this.fechaDevolucion = fechaDevolucion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLibroId() { return libroId; }
    public void setLibroId(Long libroId) { this.libroId = libroId; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }
}
