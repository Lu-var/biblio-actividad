# Sistema de Biblioteca — Gateway + Eureka

Sistema de gestión bibliotecaria basado en microservicios con Spring Cloud Gateway y Netflix Eureka como service registry.

## Microservicios

| Módulo | Puerto | Responsabilidad |
|---|---|---|
| `eureka-server` | 8761 | Registro y descubrimiento de servicios |
| `api-gateway` | 8080 | Punto de entrada único, enrutamiento por nombre lógico |
| `libros` | 8081 | Catálogo de libros (CRUD + disponibilidad) |
| `usuarios` | 8082 | Gestión de usuarios (CRUD) |
| `prestamos` | 8083 | Gestión de préstamos con comunicación entre servicios |

## Instrucciones para levantar el proyecto

1. Iniciar Eureka Server:
   ```bash
   mvn -pl eureka-server spring-boot:run
   ```
2. Iniciar los microservicios (pueden iniciarse en cualquier orden):
   ```bash
   mvn -pl services/libros spring-boot:run
   mvn -pl services/usuarios spring-boot:run
   mvn -pl services/prestamos spring-boot:run
   ```
3. Iniciar el API Gateway:
   ```bash
   mvn -pl api-gateway spring-boot:run
   ```

Todo el tráfico entra por `http://localhost:8080/api/{servicio}/...`

## Documentación

Ver `docs/` para detalles de:
- [Requerimientos](docs/requerimientos.md)
- [Arquitectura](docs/arquitectura.md)
- [API](docs/api.md)
