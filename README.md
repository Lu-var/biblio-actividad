# Actividad Gateway + Eureka

## Estructura

```
api-gateway/       → Spring Cloud Gateway (puerto 8080)
services/
  libros/          → Catálogo de libros (puerto 8081)
  usuarios/        → Gestión de usuarios (puerto 8082)
  prestamos/       → Gestión de préstamos (puerto 8083)
```